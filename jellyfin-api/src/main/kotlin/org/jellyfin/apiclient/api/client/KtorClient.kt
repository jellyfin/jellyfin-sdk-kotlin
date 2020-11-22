package org.jellyfin.apiclient.api.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import org.jellyfin.apiclient.model.ClientInfo
import org.jellyfin.apiclient.model.DeviceInfo
import kotlin.collections.set

public open class KtorClient(
	override var baseUrl: String? = null,
	public var accessToken: String? = null,
	public var clientInfo: ClientInfo,
	public var deviceInfo: DeviceInfo
) : ApiClient {
	internal val json = Json {
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
			connectTimeoutMillis = 10000
		}

		install(WebSockets)
	}

	override fun createPath(path: String, pathParameters: Map<String, Any?>): String = path
		.split('/')
		.filterNot { it.isEmpty() }
		.mapNotNull { rawName ->
			val name = rawName.removeSurrounding(prefix = "{", suffix = "}")

			// Only act if the name actually is a variable
			if (name != rawName) {
				// Check if key is set
				if (!pathParameters.containsKey(name))
					throw MissingPathVariableError(name, path)

				// Check value
				return@mapNotNull when (val value = pathParameters[name]) {
					// Don't encode null values
					null -> null
					else -> value.toString().encodeURLParameter(true)
				}
			}

			return@mapNotNull rawName
		}
		.joinToString("/")

	override fun createUrl(
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
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
				.forEach {
					parameters.append(it.key, it.value.toString())
				}
		}.buildString()
	}

	override fun createAuthorizationHeader(): String? {
		val params = mutableMapOf(
			"client" to clientInfo.name,
			"version" to clientInfo.version,
			"deviceId" to deviceInfo.id,
			"device" to deviceInfo.name
		)

		// Only set access token when it's not null
		accessToken?.let { params["token"] = it }

		// Format: `MediaBrowser key1="value1", key2="value2"`
		return params.entries.joinToString(
			separator = ", ",
			prefix = "MediaBrowser ",
			transform = {
				// Check for bad strings to prevent endless hours debugging why the server throws http 500 errors
				require(!it.key.contains('=')) { "Key ${it.key} can not contain the = character in the authorization header" }
				require(!it.key.contains(',')) { "Key ${it.key} can not contain the , character in the authorization header" }
				require(!it.key.startsWith('"') && !it.key.endsWith('"')) { "Key ${it.key} can not start or end with the \" character in the authorization header" }
				require(!it.value.contains('=')) { "Value ${it.value} (for key ${it.key}) can not contain the = character in the authorization header" }
				require(!it.value.contains(',')) { "Value ${it.value} (for key ${it.key}) can not contain the , character in the authorization header" }
				require(!it.value.startsWith('"') && !it.value.endsWith('"')) { "Value ${it.value} (for key ${it.key}) can not start or end with the \" character in the authorization header" }

				// key="value"
				"""${it.key.capitalize()}="${it.value}""""
			})
	}

	public suspend inline fun <reified T> request(
		method: HttpMethod = HttpMethod.Get,
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		requestBody: Any? = null
	): Response<T> {
		val response = client.request<HttpResponse> {
			this.method = method
			url(createUrl(pathTemplate, pathParameters, queryParameters))
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
		requestBody: Any? = null
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
		requestBody: Any? = null
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
		requestBody: Any? = null
	): Response<T> = request(
		method = HttpMethod.Delete,
		pathTemplate = pathTemplate,
		pathParameters = pathParameters,
		queryParameters = queryParameters,
		requestBody = requestBody
	)
}
