package org.jellyfin.openapi.hooks.model

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.openapi.OpenApiTypeBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath

/**
 * A hook that modifies the "ImageTags" and "ImageBlurHashes" properties to use a map instead of objects
 * The map uses the type Map<ImageType, String> for images and Map<ImageType, Map<String, String>> for blurhashes
 */
class ImageMapsHook : TypeBuilderHook {
	override fun onBuildType(path: TypePath, schema: Schema<*>, typeBuilder: OpenApiTypeBuilder) = when (path) {
		ModelTypePath("BaseItemDto", "imageTags") -> buildImageTags()
		ModelTypePath("BaseItemDto", "imageBlurHashes") -> buildImageBlurHashes()
		ModelTypePath("BaseItemPerson", "imageBlurHashes") -> buildImageBlurHashes()
		ModelTypePath("SeriesTimerInfoDto", "imageTags") -> buildImageTags()
		else -> null
	}?.copy(
		// Add nullability
		nullable = schema.nullable ?: false
	)

	private fun buildImageBlurHashes() = Types.MAP
		.plusParameter(ClassName(Packages.MODEL, "ImageType"))
		.plusParameter(Types.MAP
			.plusParameter(Types.STRING)
			.plusParameter(Types.STRING)
		)

	private fun buildImageTags() = Types.MAP
		.plusParameter(ClassName(Packages.MODEL, "ImageType"))
		.plusParameter(Types.STRING)
}
