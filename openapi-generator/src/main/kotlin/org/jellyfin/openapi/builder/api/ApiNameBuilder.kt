package org.jellyfin.openapi.builder.api

import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.util.asPascalCase

/**
 * Converts strings to API service names.
 *
 * Examples:
 *
 * hello -> HelloApi
 * helloWorld -> HelloWorldApi
 */
class ApiNameBuilder : Builder<String, String> {
	override fun build(data: String) = "${data.asPascalCase().toPascalCase()}Api"
}
