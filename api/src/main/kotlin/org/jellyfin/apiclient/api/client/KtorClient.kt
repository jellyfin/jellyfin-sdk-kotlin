package org.jellyfin.apiclient.api.client

import com.google.gson.FieldNamingPolicy
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.jellyfin.apiclient.api.client.adapter.LocalDateTimeTypeAdapter
import org.jellyfin.apiclient.api.client.adapter.UUIDTypeAdapter
import java.time.LocalDateTime
import java.util.*

open class KtorClient(
	var baseUrl: String
) {
	val client = HttpClient {
		install(JsonFeature) {
			serializer = GsonSerializer {
				setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				serializeNulls()
				registerTypeAdapter(UUID::class.java, UUIDTypeAdapter())
				registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
			}
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
		// TODO retrieve this via the library module (future PR)
		val params = mutableMapOf(
			"Client" to "CLIENTNAME",
			"Device" to "DEVICENAME",
			"DeviceId" to "DEVICEID",
			"Version" to "VERSION"
		)

//		params["Token"] = ""

		// Format: `MediaBrowser key="value", key="value"`
		return params.entries.joinToString(
			separator = ", ",
			prefix = "MediaBrowser ",
			transform = {
				// key="value"
				"${it.key}=\"${it.value}\""
			})
	}

	suspend inline fun <reified T> request(
		method: HttpMethod = HttpMethod.Get,
		path: String,
		queryParameters: Map<String, Any?> = emptyMap(),
		body: Any? = null
	): Response<T> {
		val response = client.request<HttpResponse> {
			this.method = method

			url {
				// Create from base URL
				takeFrom(baseUrl)

				// Assign path
				encodedPath = "${encodedPath.trimEnd('/')}/${path.trimStart('/')}"

				// Append query parameters
				queryParameters
					.filterNot { it.value == null }
					.forEach {
						parameters.append(it.key, it.value.toString())
					}
			}

			header("X-Emby-Authorization", createAuthorizationHeader())

			if (body != null) {
				contentType(ContentType.Application.Json)
				this.body = body
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
		path = createPath(pathTemplate, pathParameters),
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
		path = createPath(pathTemplate, pathParameters),
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
		path = createPath(pathTemplate, pathParameters),
		queryParameters = queryParameters,
		body = body
	)
}
