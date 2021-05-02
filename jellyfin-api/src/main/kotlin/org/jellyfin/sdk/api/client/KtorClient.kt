package org.jellyfin.sdk.api.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.slf4j.LoggerFactory
import kotlin.collections.set

public open class KtorClient(
	override var baseUrl: String? = null,
	override var accessToken: String? = null,
	override var clientInfo: ClientInfo,
	override var deviceInfo: DeviceInfo,
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
	public val client: HttpClient = HttpClient {
		install(JsonFeature) {
			serializer = KotlinxSerializer(json)
		}

		install(HttpTimeout) {
			@Suppress("MagicNumber")
			connectTimeoutMillis = 10_000 // 10 seconds
		}
	}

	override fun createPath(pathTemplate: String, pathParameters: Map<String, Any?>): String {
		val result = StringBuilder()

		var lastStart = -1
		var lastEnd = -1

		for (i in pathTemplate.indices) {
			when (pathTemplate[i]) {
				'/' -> {
					check(lastStart < 0) {
						"Unclosed path variable ${pathTemplate.substring(lastStart, i)} in path $pathTemplate"
					}

					// Append content from last path variable or slash up to here, eliminating duplicate slashes
					val content = pathTemplate.substring(lastEnd + 1, i + 1)
					if (content != "/" || (result.isNotEmpty() && result.lastOrNull() != '/')) {
						result.append(content)
					}

					lastEnd = i
				}
				'{' -> {
					check(lastStart < 0) {
						"Nested path variable at $i in path $pathTemplate"
					}

					// Append content from last path variable end up to here
					result.append(pathTemplate.substring(lastEnd + 1, i))

					// Set path variable start index (exclude opening brace)
					lastStart = i + 1
				}
				'}' -> {
					check(lastStart >= 0) {
						"End of path variable without start at $i in path $pathTemplate"
					}
					lastEnd = i

					// Get path variable key
					val name = pathTemplate.substring(lastStart, lastEnd)

					// Check if key is set
					if (!pathParameters.containsKey(name)) throw MissingPathVariableError(name, pathTemplate)

					// Get value of path variable
					val value = pathParameters[name]

					// Don't encode null values
					if (value != null) {
						result.append(value.toString().encodeURLParameter(true))
					}

					// Close path variable
					lastStart = -1
				}
			}
		}

		// Check for unclosed path variable
		check(lastStart < 0) {
			"Unclosed path variable ${pathTemplate.substring(lastStart)} in path $pathTemplate"
		}

		// Append rest of path to result (can be empty)
		result.append(pathTemplate.substring(lastEnd + 1))

		return result.toString()
	}

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
			val path = createPath(pathTemplate, pathParameters)
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
				parameters.append("ApiKey", checkNotNull(accessToken))
		}.buildString()
	}

	/**
	 * This is a temporary workaround until we have a proper way to send device names to the server.
	 * It will filter out all special characters.
	 */
	private fun String.encodeAuthorizationHeaderValue() = replace("[^\\w\\s]".toRegex(), "").trim()

	override fun createAuthorizationHeader(): String? {
		val params = mutableMapOf(
			"client" to clientInfo.name,
			"version" to clientInfo.version,
			"deviceId" to deviceInfo.id,
			// Only encode the device name as it is user input
			// other fields should be validated manually by the client
			"device" to deviceInfo.name.encodeAuthorizationHeaderValue()
		)

		// Only set access token when it's not null
		accessToken?.let { params["token"] = it }

		// Format: `MediaBrowser key1="value1", key2="value2"`
		return params.entries.joinToString(
			separator = ", ",
			prefix = "MediaBrowser ",
			transform = {
				// Check for bad strings to prevent endless hours debugging why the server throws http 500 errors
				require(!it.key.contains('=')) {
					"Key ${it.key} can not contain the = character in the authorization header"
				}
				require(!it.key.contains(',')) {
					"Key ${it.key} can not contain the , character in the authorization header"
				}
				require(!it.key.startsWith('"') && !it.key.endsWith('"')) {
					"Key ${it.key} can not start or end with the \" character in the authorization header"
				}
				require(!it.value.contains('=')) {
					"Value ${it.value} (for key ${it.key}) can not contain the = character in the authorization header"
				}
				require(!it.value.contains(',')) {
					"Value ${it.value} (for key ${it.key}) can not contain the , character in the authorization header"
				}
				require(!it.value.startsWith('"') && !it.value.endsWith('"')) {
					"Value ${it.value} (for key ${it.key}) can not start or end with the \" character in the authorization header"
				}

				// key="value"
				"""${it.key.capitalize()}="${it.value}""""
			})
	}

	public suspend inline fun <reified T> request(
		method: HttpMethod = HttpMethod.Get,
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T> {
		val url = createUrl(pathTemplate, pathParameters, queryParameters)

		// Log HTTP call with access token removed
		val safeUrl = accessToken?.let { url.replace(it, "******") } ?: url
		LoggerFactory.getLogger(this::class.java).info("$method $safeUrl")

		val response = client.request<HttpResponse> {
			this.method = method
			url(url)
			header(HttpHeaders.Authorization, createAuthorizationHeader())

			if (requestBody != null)
				body = defaultSerializer().write(requestBody)
		}

		return Response(response.receive(), response.status.value, response.headers.toMap())
	}

	public suspend inline fun <reified T> get(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T> = request(
		method = HttpMethod.Get,
		pathTemplate = pathTemplate,
		pathParameters = pathParameters,
		queryParameters = queryParameters,
		requestBody = requestBody
	)

	public suspend inline fun <reified T> post(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T> = request(
		method = HttpMethod.Post,
		pathTemplate = pathTemplate,
		pathParameters = pathParameters,
		queryParameters = queryParameters,
		requestBody = requestBody
	)

	public suspend inline fun <reified T> delete(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null,
	): Response<T> = request(
		method = HttpMethod.Delete,
		pathTemplate = pathTemplate,
		pathParameters = pathParameters,
		queryParameters = queryParameters,
		requestBody = requestBody
	)
}
