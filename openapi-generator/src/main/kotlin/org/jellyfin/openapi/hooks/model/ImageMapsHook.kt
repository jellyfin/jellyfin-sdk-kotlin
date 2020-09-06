package org.jellyfin.openapi.hooks.model

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.asTypeName
import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath

/**
 * A hook that modifies the "ImageTags" and "ImageBlurHashes" properties to use a map instead of objects
 * The map uses the type Map<ImageType, String> for images and Map<ImageType, Map<String, String>> for blurhashes
 */
class ImageMapsHook : TypeBuilderHook {
	override fun onBuildType(path: TypePath, schema: Schema<*>) = when (path) {
		ModelTypePath("BaseItemDto", "imageTags") -> buildImageTags()
		ModelTypePath("BaseItemDto", "imageBlurHashes") -> buildImageBlurHashes()
		ModelTypePath("BaseItemPerson", "imageBlurHashes") -> buildImageBlurHashes()
		ModelTypePath("SeriesTimerInfoDto", "imageTags") -> buildImageTags()
		else -> null
	}

	private fun buildImageBlurHashes() = Map::class.asTypeName()
		.plusParameter(ClassName(Packages.MODEL, "ImageType"))
		.plusParameter(Map::class.asTypeName()
			.plusParameter(String::class.asTypeName())
			.plusParameter(String::class.asTypeName())
		)

	private fun buildImageTags() = Map::class.asTypeName()
		.plusParameter(ClassName(Packages.MODEL, "ImageType"))
		.plusParameter(String::class.asTypeName())
}
