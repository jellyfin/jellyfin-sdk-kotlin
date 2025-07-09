package org.jellyfin.sdk.api.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsBytes
import io.ktor.content.ByteArrayContent
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.util.toMap
import kotlinx.serialization.SerializationException
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.client.HttpMethod
import org.jellyfin.sdk.api.client.RawResponse
import org.jellyfin.sdk.api.client.exception.ApiClientException
import org.jellyfin.sdk.api.client.exception.InvalidContentException
import org.jellyfin.sdk.api.client.exception.InvalidStatusException
import org.jellyfin.sdk.api.client.exception.SecureConnectionException
import org.jellyfin.sdk.api.client.exception.TimeoutException
import org.jellyfin.sdk.api.client.util.ApiSerializer
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder
import org.jellyfin.sdk.api.sockets.DefaultSocketApi
import org.jellyfin.sdk.api.sockets.SocketApi
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.FileInfo
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import io.ktor.http.HttpMethod as KtorHttpMethod

@Suppress("LongParameterList")
public class KtorClient(
	initialBaseUrl: String?,
	initialAccessToken: String?,
	initialClientInfo: ClientInfo,
	initialDeviceInfo: DeviceInfo,
	override val httpClientOptions: HttpClientOptions,
	private val socketConnectionFactory: SocketConnectionFactory,
) : ApiClient() {
	public override var baseUrl: String? = initialBaseUrl
		private set
	public override var accessToken: String? = initialAccessToken
		private set
	public override var clientInfo: ClientInfo = initialClientInfo
		private set
	public override var deviceInfo: DeviceInfo = initialDeviceInfo
		private set

	private val client: HttpClient = HttpClient {
		followRedirects = httpClientOptions.followRedirects
		expectSuccess = false

		install(HttpTimeout) {
			connectTimeoutMillis = httpClientOptions.connectTimeout.inWholeMilliseconds
			requestTimeoutMillis = httpClientOptions.requestTimeout.inWholeMilliseconds
			socketTimeoutMillis = httpClientOptions.socketTimeout.inWholeMilliseconds
		}
	}

	private val _webSocket = lazy {
		DefaultSocketApi(this, httpClientOptions.socketReconnectPolicy, socketConnectionFactory)
	}

	override fun update(baseUrl: String?, accessToken: String?, clientInfo: ClientInfo, deviceInfo: DeviceInfo) {
		this.baseUrl = baseUrl
		this.accessToken = accessToken
		this.clientInfo = clientInfo
		this.deviceInfo = deviceInfo

		// Notify websocket only if initialized
		if (_webSocket.isInitialized()) {
			_webSocket.value.notifyApiClientUpdate()
		}
	}

	@Suppress("ThrowsCount")
	public override suspend fun request(
		method: HttpMethod,
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
		requestBody: Any?,
	): RawResponse {
		val url = createUrl(pathTemplate, pathParameters, queryParameters)

		// Log HTTP call with access token removed
		val logger = KotlinLogging.logger {}
		logger.info {
			val safeUrl = accessToken?.let { url.replace(it, "******") } ?: url
			"$method $safeUrl"
		}

		try {
			val response = client.request(url) {
				this.method = method.asKtorHttpMethod()

				header(
					key = HttpHeaders.Accept,
					value = HEADER_ACCEPT,
				)

				header(
					key = HttpHeaders.Authorization,
					value = AuthorizationHeaderBuilder.buildHeader(
						clientName = clientInfo.name,
						clientVersion = clientInfo.version,
						deviceId = deviceInfo.id,
						deviceName = deviceInfo.name,
						accessToken = accessToken
					)
				)

				header(
					key = HttpHeaders.UserAgent,
					value = "${clientInfo.name}/${clientInfo.version} via jellyfin-sdk-kotlin (Ktor)"
				)

				when (requestBody) {
					// String content
					is String -> setBody(TextContent(requestBody, ContentType.Text.Plain))
					// File content
					is FileInfo -> setBody(
						ByteArrayContent(
							requestBody.content,
							ContentType.parse(requestBody.mediaType)
						)
					)
					// Binary content
					is ByteArray -> setBody(ByteArrayContent(requestBody, ContentType.Application.OctetStream))
					// Json content
					else -> ApiSerializer.encodeRequestBody(requestBody)?.let { encodedRequestBody ->
						setBody(TextContent(encodedRequestBody, ContentType.Application.Json))
					}
				}
			}

			// Check HTTP status
			if (!response.status.isSuccess()) throw InvalidStatusException(response.status.value)
			// Return custom response instance
			return RawResponse(response.bodyAsBytes(), response.status.value, response.headers.toMap())
		} catch (err: UnknownHostException) {
			logger.debug(err) { "HTTP host unreachable" }
			throw TimeoutException("HTTP host unreachable", err)
		} catch (err: HttpRequestTimeoutException) {
			logger.debug(err) { "HTTP request timed out" }
			throw TimeoutException("HTTP request timed out", err)
		} catch (err: ConnectTimeoutException) {
			logger.debug(err) { "Connection timed out" }
			throw TimeoutException("Connection timed out", err)
		} catch (err: SocketTimeoutException) {
			logger.debug(err) { "Socket timed out" }
			throw TimeoutException("Socket timed out", err)
		} catch (err: ConnectException) {
			logger.debug(err) { "Connection failed" }
			throw TimeoutException("Connection failed", err)
		} catch (err: NoTransformationFoundException) {
			logger.error(err) { "Requested model does not exist" }
			throw InvalidContentException("Requested model does not exist", err)
		} catch (err: SerializationException) {
			logger.error(err) { "Serialization failed" }
			throw InvalidContentException("Serialization failed", err)
		} catch (err: SSLException) {
			logger.error(err) { "Unknown SSL error occurred" }
			throw SecureConnectionException("Unknown SSL error occurred", err)
		} catch (err: IOException) {
			logger.error(err) { "Unknown IO error occurred!" }
			throw ApiClientException("Unknown IO error occurred!", err)
		}
	}

	override val webSocket: SocketApi by _webSocket

	private fun HttpMethod.asKtorHttpMethod(): KtorHttpMethod = when (this) {
		HttpMethod.GET -> KtorHttpMethod.Get
		HttpMethod.POST -> KtorHttpMethod.Post
		HttpMethod.DELETE -> KtorHttpMethod.Delete
	}
}
