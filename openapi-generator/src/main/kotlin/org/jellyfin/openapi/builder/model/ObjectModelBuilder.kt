package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.*
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.ObjectApiModel
import org.jellyfin.openapi.util.asPascalCase

class ObjectModelBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder
) : Builder<ObjectApiModel, TypeSpec> {
	override fun build(data: ObjectApiModel): TypeSpec {
		val properties = mutableListOf<PropertySpec>()
		val constructor = FunSpec.constructorBuilder().apply {
			data.properties.forEach { property ->
				// Create constructor parameter
				addParameter(ParameterSpec.builder(property.name, property.type).apply {
					// Set value to null by default for nullable values
					if (property.type.isNullable) defaultValue("%L", "null")
				}.build())

				// Create class property
				properties.add(PropertySpec
					.builder(property.name, property.type)
					.initializer(property.name)
					.apply {
						property.description?.let { addKdoc(it) }

						if (property.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))
					}
					.build()
				)
			}
		}.build()

		// Create class
		return TypeSpec.classBuilder(data.name.asPascalCase().toPascalCase())
			.apply {
				modifiers += KModifier.DATA
				data.description?.let { addKdoc(it) }
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
			}
			.primaryConstructor(constructor)
			.addProperties(properties)
			.build()
	}
}
