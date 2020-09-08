package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.TypeSpec
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.EmptyApiModel
import org.jellyfin.openapi.util.asPascalCase

class EmptyModelBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder
) : Builder<EmptyApiModel, TypeSpec> {
	override fun build(data: EmptyApiModel): TypeSpec {
		return TypeSpec.classBuilder(data.name.asPascalCase().toPascalCase())
			.apply {
				data.description?.let { addKdoc(it) }
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
			}
			.build()
	}
}
