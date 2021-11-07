package org.jellyfin.sdk.api.client

import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.features.HttpRequestTimeoutException
import io.ktor.client.features.HttpTimeout
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.network.sockets.ConnectTimeoutException
import io.ktor.network.sockets.SocketTimeoutException
import io.ktor.util.toMap
import kotlinx.serialization.SerializationException
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.exception.ApiClientException
import org.jellyfin.sdk.api.client.exception.InvalidContentException
import org.jellyfin.sdk.api.client.exception.InvalidStatusException
import org.jellyfin.sdk.api.client.exception.TimeoutException
import org.jellyfin.sdk.api.client.util.ApiSerializer
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.api.sockets.SocketInstance
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.UUID
import java.net.UnknownHostException
import io.ktor.http.HttpMethod as KtorHttpMethod

@Suppress("LongParameterList")
public actual open class KtorClient actual constructor(
	override var baseUrl: String?,
	override var accessToken: String?,
	override var userId: UUID?,
	override var clientInfo: ClientInfo,
	override var deviceInfo: DeviceInfo,
	override val httpClientOptions: HttpClientOptions,
	private val socketConnectionFactory: SocketConnectionFactory,
) : ApiClient() {
	private val client: HttpClient = HttpClient {
		followRedirects = httpClientOptions.followRedirects
		expectSuccess = false

		install(HttpTimeout) {
			connectTimeoutMillis = httpClientOptions.connectTimeout
			requestTimeoutMillis = httpClientOptions.requestTimeout
			socketTimeoutMillis = httpClientOptions.socketTimeout
		}
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

		@Suppress("TooGenericExceptionCaught")
		try {
			val response = client.request<HttpResponse>(url) {
				this.method = method.asKtorHttpMethod()

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

				ApiSerializer.encodeRequestBody(requestBody)?.let { encodedRequestBody ->
					body = TextContent(encodedRequestBody, ContentType.Application.Json)
				}
			}

			// Check HTTP status
			if (!response.status.isSuccess()) throw InvalidStatusException(response.status.value)
			// Return custom response instance
			return RawResponse(response.content, response.status.value, response.headers.toMap())
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
		} catch (err: NoTransformationFoundException) {
			logger.error(err) { "Requested model does not exist" }
			throw InvalidContentException("Requested model does not exist", err)
		} catch (err: SerializationException) {
			logger.error(err) { "Serialization failed" }
			throw InvalidContentException("Serialization failed", err)
		} catch (err: Throwable) {
			logger.error(err) { "Unknown error occurred!" }
			throw ApiClientException("Unknown error occurred!", err)
		}
	}

	public actual override fun ws(): SocketInstance = SocketInstance(this, socketConnectionFactory)

	private fun HttpMethod.asKtorHttpMethod(): KtorHttpMethod = when (this) {
		HttpMethod.GET -> KtorHttpMethod.Get
		HttpMethod.POST -> KtorHttpMethod.Post
		HttpMethod.DELETE -> KtorHttpMethod.Delete
	}
}
