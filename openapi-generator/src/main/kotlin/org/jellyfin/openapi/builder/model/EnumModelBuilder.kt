package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.TypeSpec
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.util.asPascalCase

class EnumModelBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder
) : Builder<EnumApiModel, TypeSpec> {
	override fun build(data: EnumApiModel): TypeSpec {
		return TypeSpec.enumBuilder(data.name.asPascalCase().toPascalCase())
			.apply {
				data.constants.forEach {
					addEnumConstant(it.asPascalCase().toScreamingSnakeCase())
				}
				data.description?.let { addKdoc(it) }
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
			}
			.build()
	}
}
