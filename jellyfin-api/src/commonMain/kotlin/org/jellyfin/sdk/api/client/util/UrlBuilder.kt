package org.jellyfin.sdk.api.client.util

import io.ktor.http.URLBuilder
import io.ktor.http.encodeURLParameter
import io.ktor.http.takeFrom
import org.jellyfin.sdk.api.client.exception.MissingPathVariableException

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
	): String {
		return URLBuilder(baseUrl).apply {
			// Create from base URL
			takeFrom(baseUrl)

			// Replace path variables
			val path = buildPath(pathTemplate, pathParameters)
			// Assign path making sure to remove duplicated slashes between the base and appended path
			encodedPath = "${encodedPath.trimEnd('/')}/${path.trimStart('/')}"

			// Append query parameters
			queryParameters
				.filterNot { it.value == null }
				.map { (key, rawValue) ->
					// Determine value
					val value = when (rawValue) {
						// Lists should be comma-separated
						is Collection<*> -> if (rawValue.isNotEmpty()) rawValue.joinToString(",") else null
						else -> rawValue.toString()
					}

					// Add not-null values
					if (value != null) parameters.append(key, value)
				}
		}.buildString()
	}

	public fun buildPath(template: String, parameters: Map<String, Any?>): String = buildString {
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
					check(lastStart < 0) {
						"Nested path variable at $i in path $template"
					}

					// Append content from last path variable end up to here
					append(template.substring(lastEnd + 1, i))

					// Set path variable start index (exclude opening brace)
					lastStart = i + 1
				}
				TOKEN_BRACKET_CLOSE -> {
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
						append(value.toString().encodeURLParameter(true))
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

