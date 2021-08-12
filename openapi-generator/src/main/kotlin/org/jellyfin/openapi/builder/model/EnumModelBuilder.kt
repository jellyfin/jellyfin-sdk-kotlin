package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.*
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import net.pearx.kasechange.toScreamingSnakeCase
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.JellyFile

class EnumModelBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
) : Builder<EnumApiModel, JellyFile> {
	override fun build(data: EnumApiModel): JellyFile {
		return TypeSpec.enumBuilder(data.name.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL))
			.apply {
				// Constructor
				primaryConstructor(FunSpec.constructorBuilder().apply {
					addParameter("serialName", Types.STRING)
				}.build())
				addProperty(PropertySpec.builder("serialName", Types.STRING).apply {
					initializer("serialName")
				}.build())

				// toString function
				addFunction(FunSpec.builder("toString").apply {
					returns(Types.STRING)
					addStatement("return serialName")
					addModifiers(KModifier.OVERRIDE)
				}.build())

				// Members
				data.constants.forEach {
					addEnumConstant(
						it.toScreamingSnakeCase(from = CaseFormat.CAPITALIZED_CAMEL),
						TypeSpec.anonymousClassBuilder().apply {
							addAnnotation(AnnotationSpec.builder(Types.SERIAL_NAME).addMember("%S", it).build())
							addSuperclassConstructorParameter("%S", it)
						}.build()
					)
				}

				// Header
				descriptionBuilder.build(data.description)?.let {
					addKdoc("%L", it)
				}
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
				addAnnotation(Types.SERIALIZABLE)
			}
			.build()
			.let { JellyFile(Packages.MODEL, emptySet(), it) }
	}
}
