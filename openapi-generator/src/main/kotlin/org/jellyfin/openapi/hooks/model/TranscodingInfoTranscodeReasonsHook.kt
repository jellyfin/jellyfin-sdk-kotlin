package org.jellyfin.openapi.hooks.model

import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.openapi.OpenApiTypeBuilder
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath

/**
 * A hook that modifies the type of the "transcodeReasons" property in "TranscodingInfo" to fix an issue with the
 * OpenAPI specification. It replaces the "String" type with "Collection<String>". When the bug is fixed it should
 * use "Collection<TranscodeReason>".
 *
 * Reference: https://github.com/jellyfin/jellyfin-sdk-kotlin/issues/510
 */
class TranscodingInfoTranscodeReasonsHook : TypeBuilderHook {
	override fun onBuildType(path: TypePath, schema: Schema<*>, typeBuilder: OpenApiTypeBuilder) = when (path) {
		ModelTypePath("TranscodingInfo", "transcodeReasons") ->
			Types.COLLECTION.plusParameter(Types.STRING)
		else -> null
	}
}
