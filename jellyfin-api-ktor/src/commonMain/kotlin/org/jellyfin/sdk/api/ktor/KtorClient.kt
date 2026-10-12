package org.jellyfin.sdk.api.ktor

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.HttpClient
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.readRawBytes
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.content.ByteArrayContent
import io.ktor.http.isSuccess
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
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
import io.ktor.http.HttpMethod as KtorHttpMethod

private const val USER_AGENT_SUFFIX = "via jellyfin-sdk-kotlin (Ktor)"

/**
 * [ApiClient] implementation based on Ktor.
 */
@Suppress("LongParameterList")
public class KtorClient(
	private val client: HttpClient,
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

	private val _webSocket = lazy {
		DefaultSocketApi(this, httpClientOptions.socketReconnectPolicy, socketConnectionFactory)
	}

	override val webSocket: SocketApi by _webSocket

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

	@Suppress("ThrowsCount", "CyclomaticComplexMethod")
	override suspend fun request(
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
			val response = client.request {
				this.method = KtorHttpMethod.parse(method.name)
				url(url)

				header(HttpHeaders.Accept, HEADER_ACCEPT)
				header(
					HttpHeaders.Authorization,
					AuthorizationHeaderBuilder.buildHeader(
						clientName = clientInfo.name,
						clientVersion = clientInfo.version,
						deviceId = deviceInfo.id,
						deviceName = deviceInfo.name,
						accessToken = accessToken,
					)
				)
				header(HttpHeaders.UserAgent, "${clientInfo.name}/${clientInfo.version} $USER_AGENT_SUFFIX")
				if (deviceInfo.languages.isNotEmpty()) {
					header(HttpHeaders.AcceptLanguage, deviceInfo.languages.joinToString(","))
				}

				if (method == HttpMethod.POST) {
					val (body, contentType) = createBody(requestBody)
					// Ktor ignores the content type when it is set as header
					setBody(ByteArrayContent(body, contentType?.let(ContentType::parse)))
				}
			}

			if (!response.status.isSuccess()) throw InvalidStatusException(response.status.value)

			return RawResponse(
				body = response.readRawBytes(),
				status = response.status.value,
				headers = response.headers.entries().associate { it.key to it.value },
			)
		} catch (err: CancellationException) {
			throw err
		} catch (err: ApiClientException) {
			throw err
		} catch (err: ConnectTimeoutException) {
			logger.debug(err) { "Connection failed" }
			throw TimeoutException("Connection failed", err)
		} catch (err: SocketTimeoutException) {
			logger.debug(err) { "Connection timed out or was interrupted" }
			throw TimeoutException("Connection timed out or was interrupted", err)
		} catch (err: HttpRequestTimeoutException) {
			logger.debug(err) { "Connection timed out or was interrupted" }
			throw TimeoutException("Connection timed out or was interrupted", err)
		} catch (err: SerializationException) {
			logger.error(err) { "Serialization failed" }
			throw InvalidContentException("Serialization failed", err)
		} catch (err: Throwable) {
			when {
				err.isUnreachableHostError() -> {
					logger.debug(err) { "HTTP host unreachable" }
					throw TimeoutException("HTTP host unreachable", err)
				}

				err.isSecureConnectionError() -> {
					logger.error(err) { "Unknown SSL error occurred" }
					throw SecureConnectionException("Unknown SSL error occurred", err)
				}

				else -> {
					logger.error(err) { "Unknown IO error occurred!" }
					throw ApiClientException("Unknown IO error occurred!", err)
				}
			}
		}
	}

	private fun createBody(requestBody: Any?): Pair<ByteArray, String?> = when (requestBody) {
		// String content
		is String -> requestBody.encodeToByteArray() to "text/plain; charset=utf-8"
		// File content
		is FileInfo -> requestBody.content to requestBody.mediaType
		// Binary content
		is ByteArray -> requestBody to "application/octet-stream"
		// Json content
		else -> ApiSerializer.encodeRequestBody(requestBody)
			?.let { it.encodeToByteArray() to "application/json; charset=utf-8" }
			?: (ByteArray(0) to null)
	}
}
