package org.jellyfin.sdk.api.client.util

import okhttp3.HttpUrl.Companion.toHttpUrl
import org.jellyfin.sdk.api.client.exception.MissingPathVariableException
import org.jellyfin.sdk.api.client.util.UrlBuilder.buildPath
import org.jellyfin.sdk.model.DateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

public object UrlBuilder {
	private const val TOKEN_SEPARATOR: Char = '/'
	private const val TOKEN_BRACKET_OPEN: Char = '{'
	private const val TOKEN_BRACKET_CLOSE: Char = '}'

	/**
	 * Create a complete url based on the [baseUrl] and given parameters.
	 * Uses [buildPath] to create the path from the [pathTemplate] and [pathParameters].
	 */
	public fun buildUrl(
		baseUrl: String,
		pathTemplate: String = "/",
		pathParameters: Map<String, Any?> = mapOf(),
		queryParameters: Map<String, Any?> = mapOf(),
		ignorePathParameters: Boolean = false,
	): String {
		val url = baseUrl.toHttpUrl()
		return url.newBuilder().apply {
			// Replace path variables
			val (path, pathQuerystring) = buildPath(
				pathTemplate,
				pathParameters,
				ignorePathParameters
			).extractQuerystring()

			// Assign path making sure to remove duplicated slashes between the base and appended path
			encodedPath("${url.encodedPath.trimEnd('/')}/${path.trimStart('/')}")
			encodedQuery(pathQuerystring)

			// Append query parameters
			queryParameters
				.filterNot { it.value == null }
				.map { (key, rawValue) ->
					// Determine values
					val values = when (rawValue) {
						is Collection<*> -> rawValue
						else -> listOfNotNull(rawValue)
					}

					// Add values
					for (value in values) {
						val stringValue = when (value) {
							is DateTime -> value
								.atZone(ZoneId.systemDefault())
								.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)

							else -> value.toString()
						}

						addQueryParameter(key, stringValue)
					}
				}
		}.build().toString()
	}

	public fun buildPath(
		template: String,
		parameters: Map<String, Any?>,
		ignorePathParameters: Boolean = false,
	): String = buildString {
		var lastStart = -1
		var lastEnd = -1

		for (i in template.indices) {
			when (template[i]) {
				TOKEN_SEPARATOR -> {
					check(lastStart < 0) {
						"Unclosed path variable ${template.substring(lastStart, i)} in path $template"
					}

					// Append content from last path variable or slash up to here, eliminating duplicate slashes
					val content = template.substring(lastEnd + 1, i + 1)
					if (content != TOKEN_SEPARATOR.toString() || (isNotEmpty() && lastOrNull() != TOKEN_SEPARATOR)) {
						append(content)
					}

					lastEnd = i
				}

				TOKEN_BRACKET_OPEN -> {
					// Don't do anything when ignorePathParameters is set
					if (ignorePathParameters) continue

					check(lastStart < 0) {
						"Nested path variable at $i in path $template"
					}

					// Append content from last path variable end up to here
					append(template.substring(lastEnd + 1, i))

					// Set path variable start index (exclude opening brace)
					lastStart = i + 1
				}

				TOKEN_BRACKET_CLOSE -> {
					// Don't do anything when ignorePathParameters is set
					if (ignorePathParameters) continue

					check(lastStart >= 0) {
						"End of path variable without start at $i in path $template"
					}
					lastEnd = i

					// Get path variable key
					val name = template.substring(lastStart, lastEnd)

					// Check if key is set
					if (!parameters.containsKey(name)) throw MissingPathVariableException(name, template)

					// Get value of path variable
					val value = parameters[name]

					// Don't encode null values
					if (value != null) {
						append(value.toString().encodeURLPart())
					}

					// Close path variable
					lastStart = -1
				}
			}
		}

		// Check for unclosed path variable
		check(lastStart < 0) {
			"Unclosed path variable ${template.substring(lastStart)} in path $template"
		}

		// Append rest of path to result (can be empty)
		append(template.substring(lastEnd + 1))
	}
}

@Suppress("NOTHING_TO_INLINE")
public inline fun String.buildPath(parameters: Map<String, Any?>): String = UrlBuilder.buildPath(this, parameters)
