package org.jellyfin.apiclient.api.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

open class KtorClient(
	var baseUrl: String? = null,
	var accessToken: String? = null,
	var clientInfo: ClientInfo,
	var deviceInfo: DeviceInfo
) {
	val client = HttpClient {
		install(JsonFeature) {
			serializer = KotlinxSerializer()
		}

		install(HttpTimeout) {
			requestTimeoutMillis = 5000
		}
	}

	/**
	 * Replaces the variables in a path with the values in the map.
	 * Will also remove trailing and repeated slashes
	 */
	fun createPath(path: String, pathParameters: Map<String, Any?>) = path
		.split('/')
		.filterNot { it.isEmpty() }
		.map { rawName ->
			val name = rawName.removeSurrounding(prefix = "{", suffix = "}")

			// only act if the name actually is a variable
			if (name != rawName) {
				val value = pathParameters[name] ?: throw MissingPathVariableError(name, path)
				return@map value.toString().encodeURLParameter(true)
			}

			return@map rawName
		}
		.joinToString("/")

	fun createAuthorizationHeader(): String? {
		val params = mutableMapOf(
			"client" to clientInfo.name,
			"version" to clientInfo.version,
			"deviceId" to deviceInfo.id,
			"device" to deviceInfo.name
		)

		// Only set access token when it's not null
		accessToken?.let { params["token"] = it }

		// Format: `MediaBrowser "key1"="value1", "key"="value"`
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

				// "key"="value"
				""""${it.key.capitalize()}"="${it.value}""""
			})
	}

	suspend inline fun <reified T> request(
		method: HttpMethod = HttpMethod.Get,
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		body: Any? = null
	): Response<T> {
		// Check if the base url is not null
		val baseUrl = checkNotNull(baseUrl)

		val response = client.request<HttpResponse> {
			this.method = method

			url {
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
			}

			header(HttpHeaders.Authorization, createAuthorizationHeader())

			if (body != null) {
				contentType(ContentType.Application.Json)
				this.body = defaultSerializer().write(body)
			}
		}

		return Response(response.receive())
	}

	suspend inline fun <reified T> get(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		body: Any? = null
	) = request<T>(
		method = HttpMethod.Get,
		pathTemplate = pathTemplate,
		pathParameters = pathParameters,
		queryParameters = queryParameters,
		body = body
	)

	suspend inline fun <reified T> post(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		body: Any? = null
	) = request<T>(
		method = HttpMethod.Post,
		pathTemplate = pathTemplate,
		pathParameters = pathParameters,
		queryParameters = queryParameters,
		body = body
	)

	suspend inline fun <reified T> delete(
		pathTemplate: String,
		pathParameters: Map<String, Any?> = emptyMap(),
		queryParameters: Map<String, Any?> = emptyMap(),
		body: Any? = null
	) = request<T>(
		method = HttpMethod.Delete,
		pathTemplate = pathTemplate,
		pathParameters = pathParameters,
		queryParameters = queryParameters,
		body = body
	)
}
