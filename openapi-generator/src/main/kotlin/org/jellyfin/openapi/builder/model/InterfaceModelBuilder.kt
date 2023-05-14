package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.DescriptionType
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.InterfaceApiModel
import org.jellyfin.openapi.model.JellyFile

class InterfaceModelBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
) : ContextBuilder<InterfaceApiModel, JellyFile> {
	@Suppress("ComplexMethod")
	override fun build(context: GeneratorContext, data: InterfaceApiModel): JellyFile {
		val interfacePropertyNames = data.interfaces
			.flatMap { (context.getOrGenerateModel(it) as? InterfaceApiModel)?.properties.orEmpty() }
			.map { it.name }

		// Create class properties
		val properties = data.properties.map { property ->
			PropertySpec
				.builder(property.name, property.type)
				.apply {
					descriptionBuilder.build(DescriptionType.MODEL_PROPERTY, property.description)?.let {
						addKdoc("%L", it)
					}

					// Add override modifier if in interface
					if (property.name in interfacePropertyNames) modifiers += KModifier.OVERRIDE

					if (property.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))
				}
				.build()
		}

		// Create class
		return TypeSpec.interfaceBuilder(data.name.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL))
			.apply {
				data.interfaces.forEach { interfaceName ->
					addSuperinterface(
						ClassName(
							Packages.MODEL,
							interfaceName.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL)
						)
					)
				}

				modifiers += KModifier.SEALED
				descriptionBuilder.build(DescriptionType.MODEL, data.description)?.let {
					addKdoc("%L", it)
				}
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))

				// Only allow serialization when a discriminator is defined
				data.polymorphicDiscriminator?.let {
					addAnnotation(AnnotationSpec.builder(Types.SERIALIZABLE).build())
					addAnnotation(AnnotationSpec.builder(Types.JSON_DISCRIMINATOR).addMember("%S", it).build())
				}
			}
			.addProperties(properties)
			.build()
			.let { JellyFile(Packages.MODEL, emptySet(), it) }
	}
}
