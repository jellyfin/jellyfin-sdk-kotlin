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
import io.ktor.client.statement.bodyAsChannel
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
import org.jellyfin.sdk.api.client.exception.ssl.BadPeerSSLKeyException
import org.jellyfin.sdk.api.client.exception.ssl.HandshakeCertificateException
import org.jellyfin.sdk.api.client.exception.ssl.InvalidSSLProtocolImplementationException
import org.jellyfin.sdk.api.client.exception.ssl.PeerNotAuthenticatedException
import org.jellyfin.sdk.api.client.util.ApiSerializer
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.api.sockets.SocketInstance
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLKeyException
import javax.net.ssl.SSLPeerUnverifiedException
import javax.net.ssl.SSLProtocolException
import io.ktor.http.HttpMethod as KtorHttpMethod

@Suppress("LongParameterList")
public actual open class KtorClient actual constructor(
	override var baseUrl: String?,
	override var accessToken: String?,
	override var clientInfo: ClientInfo,
	override var deviceInfo: DeviceInfo,
	override val httpClientOptions: HttpClientOptions,
	private val socketConnectionFactory: SocketConnectionFactory,
) : ApiClient() {
	private val client: HttpClient = HttpClient {
		followRedirects = httpClientOptions.followRedirects
		expectSuccess = false

		install(HttpTimeout) {
			connectTimeoutMillis = httpClientOptions.connectTimeout.inWholeMilliseconds
			requestTimeoutMillis = httpClientOptions.requestTimeout.inWholeMilliseconds
			socketTimeoutMillis = httpClientOptions.socketTimeout.inWholeMilliseconds
		}
	}

	override fun update(baseUrl: String?, accessToken: String?, clientInfo: ClientInfo, deviceInfo: DeviceInfo) {
		this.baseUrl = baseUrl
		this.accessToken = accessToken
		this.clientInfo = clientInfo
		this.deviceInfo = deviceInfo
	}

	@Suppress("ThrowsCount")
	public actual override suspend fun request(
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

				when (requestBody) {
					// String content
					is String -> setBody(TextContent(requestBody, ContentType.Text.Plain))
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
			return RawResponse(response.bodyAsChannel(), response.status.value, response.headers.toMap())
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
		} catch (err: SSLKeyException) {
			logger.error(err) { "Invalid SSL peer key format" }
			throw BadPeerSSLKeyException("Invalid SSL peer key format", err)
		} catch (err: SSLPeerUnverifiedException) {
			logger.error(err) { "Couldn't authenticate peer" }
			throw PeerNotAuthenticatedException("Couldn't authenticate peer", err)
		} catch (err: SSLHandshakeException) {
			logger.error(err) { "SSL Invalid handshake" }
			throw HandshakeCertificateException("Invalid handshake", err)
		} catch (err: SSLProtocolException) {
			logger.error(err) { "Invalid SSL protocol implementation" }
			throw InvalidSSLProtocolImplementationException("Invalid SSL protocol implementation", err)
		} catch (err: SSLException) {
			logger.error(err) { "Unknown SSL error occurred" }
			throw SecureConnectionException("Unknown SSL error occurred", err)
		} catch (err: IOException) {
			logger.error(err) { "Unknown IO error occurred!" }
			throw ApiClientException("Unknown IO error occurred!", err)
		}
	}

	public actual override fun ws(): SocketInstance = SocketInstance(this, socketConnectionFactory)

	private fun HttpMethod.asKtorHttpMethod(): KtorHttpMethod = when (this) {
		HttpMethod.GET -> KtorHttpMethod.Get
		HttpMethod.POST -> KtorHttpMethod.Post
		HttpMethod.DELETE -> KtorHttpMethod.Delete
	}
}
