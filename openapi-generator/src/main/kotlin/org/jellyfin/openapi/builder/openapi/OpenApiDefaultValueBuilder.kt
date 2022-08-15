package org.jellyfin.openapi.builder.openapi

import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.OpenApiGeneratorError
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.model.DefaultValue

@Suppress("TooManyFunctions")
class OpenApiDefaultValueBuilder : Builder<Schema<Any>, DefaultValue?> {
	override fun build(data: Schema<Any>): DefaultValue? = when (val schemaDefault = data.default) {
		is String -> DefaultValue.String(schemaDefault)
		is Int -> DefaultValue.Int(schemaDefault)
		is Boolean -> DefaultValue.Boolean(schemaDefault)
		null -> null
		else -> throw OpenApiGeneratorError("""Unsupported default value "$schemaDefault".""")
	}
}
