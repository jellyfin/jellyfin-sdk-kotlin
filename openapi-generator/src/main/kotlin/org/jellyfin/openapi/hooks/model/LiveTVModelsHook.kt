package org.jellyfin.openapi.hooks.model

import com.squareup.kotlinpoet.TypeName
import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.openapi.OpenApiTypeBuilder
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath
import org.jellyfin.openapi.hooks.model.LiveTVModelsHook.Companion.liveTvNullableModels

/**
 * A hook that modifies all properties of the models stored in [liveTvNullableModels] to be nullable.
 */
class LiveTVModelsHook : TypeBuilderHook {
	companion object {
		private val liveTvNullableModels = setOf(
			"SeriesTimerInfoDto",
			"TimerInfoDto",
		)

		private val ignoreProperties = setOf(
			"imageTags",
		)
	}

	override fun onBuildType(path: TypePath, schema: Schema<*>, typeBuilder: OpenApiTypeBuilder): TypeName? {
		return when {
			path is ModelTypePath && liveTvNullableModels.contains(path.model) && !ignoreProperties.contains(path.property) ->
				typeBuilder
					.buildSchema(path, schema)
					.copy(nullable = true)

			else -> null
		}
	}
}
