package org.jellyfin.sdk.api.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.network.sockets.*
import io.ktor.util.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.exception.InvalidContentException
import org.jellyfin.sdk.api.client.exception.InvalidStatusException
import org.jellyfin.sdk.api.client.exception.TimeoutException
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder
import org.jellyfin.sdk.api.client.util.PathBuilder
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.UUID
import java.net.UnknownHostException

public actual open class KtorClient actual constructor(
	override var baseUrl: String?,
	override var accessToken: String?,
	override var userId: UUID?,
	override var clientInfo: ClientInfo,
	override var deviceInfo: DeviceInfo,
	override val httpClientOptions: HttpClientOptions,
) : ApiClient {
	private val json = Json {
		isLenient = false
		ignoreUnknownKeys = true
		allowSpecialFloatingPointValues = true
		useArrayPolymorphism = false
	}

	/**
	 * Internal HTTP client. Should not be used directly. Use [request] instead.
	 * Exposed publicly to allow inline functions to work.
	 */
	public actual val client: HttpClient = HttpClient {
		followRedirects = httpClientOptions.followRedirects
		expectSuccess = false

		install(JsonFeature) {
			serializer = KotlinxSerializer(json)
		}

		install(HttpTimeout) {
			connectTimeoutMillis = httpClientOptions.connectTimeout
			requestTimeoutMillis = httpClientOptions.requestTimeout
			socketTimeoutMillis = httpClientOptions.socketTimeout
		}
	}

	@OptIn(InternalAPI::class) // Required for Ktor URLBuilder
	override fun createUrl(
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
		includeCredentials: Boolean,
	): String {
		// Check if the base url is not null
		val baseUrl = checkNotNull(baseUrl)

		return URLBuilder(baseUrl).apply {
			// Create from base URL
			takeFrom(baseUrl)

			// Replace path variables
			val path = PathBuilder.buildPath(pathTemplate, pathParameters)
			// Assign path making sure to remove duplicated slashes between the base and appended path
			encodedPath = "${encodedPath.trimEnd('/')}/${path.trimStart('/')}"

			// Append query parameters
			queryParameters
				.filterNot { it.value == null }
				.forEach { (key, rawValue) ->
					// Determine value
					val value = when (rawValue) {
						// Lists should be comma-separated
						is Collection<*> -> if (rawValue.isNotEmpty()) rawValue.joinToString(",") else null
						else -> rawValue.toString()
					}

					// Add not-null values
					if (value != null)
						parameters.append(key, value)
				}

			if (includeCredentials)
				parameters.append(ApiClient.QUERY_ACCESS_TOKEN, checkNotNull(accessToken))
		}.buildString()
	}

	@Suppress("ThrowsCount")
	public actual suspend inline fun <reified T> request(
		method: HttpMethod,
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
		requestBody: Any?,
	): Response<T> {
		val url = createUrl(pathTemplate, pathParameters, queryParameters)

		// Log HTTP call with access token removed
		val logger = KotlinLogging.logger {}
		logger.info {
			val safeUrl = accessToken?.let { url.replace(it, "******") } ?: url
			"$method $safeUrl"
		}

		@Suppress("SwallowedException", "TooGenericExceptionCaught")
		try {
			val response = client.request<HttpResponse> {
				this.method = method
				url(url)
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

				if (requestBody != null) body = defaultSerializer().write(requestBody)
			}

			// Check HTTP status
			if (!response.status.isSuccess()) throw InvalidStatusException(response.status.value)
			// Read response body
			val body = response.receive<T>()
			// Return custom response instance
			return Response(body, response.status.value, response.headers.toMap())
		} catch (err: UnknownHostException) {
			logger.debug(err) { "HTTP host unreachable" }
			throw TimeoutException(err.message)
		} catch (err: HttpRequestTimeoutException) {
			logger.debug(err) { "HTTP request timed out" }
			throw TimeoutException(err.message)
		} catch (err: ConnectTimeoutException) {
			logger.debug(err) { "Connection timed out" }
			throw TimeoutException(err.message)
		} catch (err: SocketTimeoutException) {
			logger.debug(err) { "Socket timed out" }
			throw TimeoutException(err.message)
		} catch (err: NoTransformationFoundException) {
			logger.error(err) { "Requested model does not exist!?" }
			throw InvalidContentException(err.message)
		} catch (err: SerializationException) {
			logger.error(err) { "Deserialization failed" }
			throw InvalidContentException(err.message)
		} catch (err: Throwable) {
			logger.error(err) { "Unknown error occurred!" }
			throw err
		}
	}
}
