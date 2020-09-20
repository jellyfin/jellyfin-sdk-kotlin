package org.jellyfin.openapi.hooks.model

import com.squareup.kotlinpoet.TypeName
import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.openapi.OpenApiTypeBuilder
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath

/**
 * A hook that makes references to objects in models nullable as workaround for the missing
 * nullable properties in the OpenAPI document.
 */
class NullableReferencesHook : TypeBuilderHook {
	override fun onBuildType(path: TypePath, schema: Schema<*>, typeBuilder: OpenApiTypeBuilder): TypeName? {
		if (path !is ModelTypePath) return null
		if (schema.`$ref` == null) return null

		val type = typeBuilder.buildSchema(schema)
		return type.copy(nullable = true)
	}
}
