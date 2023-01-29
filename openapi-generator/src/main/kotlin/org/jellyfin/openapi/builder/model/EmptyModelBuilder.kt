package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.TypeSpec
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.DescriptionType
import org.jellyfin.openapi.model.EmptyApiModel
import org.jellyfin.openapi.model.JellyFile

class EmptyModelBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder
) : Builder<EmptyApiModel, JellyFile> {
	override fun build(data: EmptyApiModel): JellyFile {
		return TypeSpec.classBuilder(data.name.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL))
			.apply {
				descriptionBuilder.build(DescriptionType.MODEL, data.description)?.let {
					addKdoc("%L", it)
				}
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
				addAnnotation(Types.SERIALIZABLE)
			}
			.build()
			.let { JellyFile(Packages.MODEL, emptySet(), it) }
	}
}
