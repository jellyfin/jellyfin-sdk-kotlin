package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import kotlinx.serialization.Serializable
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.JellyFile
import org.jellyfin.openapi.util.asPascalCase

class EnumModelBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder
) : Builder<EnumApiModel, JellyFile> {
	override fun build(data: EnumApiModel): JellyFile {
		return TypeSpec.enumBuilder(data.name.asPascalCase().toPascalCase())
			.apply {
				data.constants.forEach {
					addEnumConstant(it.asPascalCase().toScreamingSnakeCase())
				}
				data.description?.let { addKdoc(it) }
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
				addAnnotation(Serializable::class.asTypeName())
			}
			.build()
			.let { JellyFile(Packages.MODEL, emptySet(), it) }
	}
}
