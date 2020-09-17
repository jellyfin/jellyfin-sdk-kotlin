package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import kotlinx.serialization.Serializable
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.EmptyApiModel
import org.jellyfin.openapi.model.JellyFile
import org.jellyfin.openapi.util.asPascalCase

class EmptyModelBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder
) : Builder<EmptyApiModel, JellyFile> {
	override fun build(data: EmptyApiModel): JellyFile {
		return TypeSpec.classBuilder(data.name.asPascalCase().toPascalCase())
			.apply {
				data.description?.let { addKdoc(it) }
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
				addAnnotation(Serializable::class.asTypeName())
			}
			.build()
			.let { JellyFile(Packages.MODEL, emptySet(), it) }
	}
}
