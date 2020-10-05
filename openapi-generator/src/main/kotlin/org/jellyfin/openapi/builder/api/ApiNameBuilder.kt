package org.jellyfin.openapi.builder.api

import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.Builder

/**
 * Converts strings to API service names.
 *
 * Examples:
 *
 * hello -> HelloApi
 * helloWorld -> HelloWorldApi
 */
class ApiNameBuilder : Builder<String, String> {
	override fun build(data: String) = "${data.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL)}Api"
}
