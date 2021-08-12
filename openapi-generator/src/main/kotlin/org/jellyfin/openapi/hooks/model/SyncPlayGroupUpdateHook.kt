package org.jellyfin.openapi.hooks.model

import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.openapi.OpenApiTypeBuilder
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath

/**
 * A hook that modifies the type of the "data" property in "ObjectGroupUpdate". It uses the
 * "JsonElement" type as it is now known it advance what actual type is used.
 */
class SyncPlayGroupUpdateHook : TypeBuilderHook {
	override fun onBuildType(path: TypePath, schema: Schema<*>, typeBuilder: OpenApiTypeBuilder) = when (path) {
		ModelTypePath("ObjectGroupUpdate", "data") -> Types.JSON_ELEMENT
		else -> null
	}
}
