package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.TypeSerializerBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.JellyFile
import org.jellyfin.openapi.model.ObjectApiModel

class ObjectModelBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
	private val typeSerializerBuilder: TypeSerializerBuilder
) : Builder<ObjectApiModel, JellyFile> {
	override fun build(data: ObjectApiModel): JellyFile {
		val properties = mutableListOf<PropertySpec>()
		val serializers = mutableSetOf<TypeName>()
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
						addAnnotation(AnnotationSpec.builder(SerialName::class).addMember("%S", property.originalName).build())
					}
					.build()
				)

				// Check if serializer is required
				val serializer = typeSerializerBuilder.build(property.type)
				if (serializer != null && serializer !in serializers) serializers += serializer
			}
		}.build()

		// Create UseSerializers annotation
		val useSerializersAnnotation = serializers
			.ifEmpty { null }
			?.let {
				AnnotationSpec.builder(UseSerializers::class).apply {
					useSiteTarget(AnnotationSpec.UseSiteTarget.FILE)
					// Add all serializers
					serializers.forEach { serializer -> addMember("%T::class", serializer) }
				}.build()
			}

		val fileAnnotations = useSerializersAnnotation
			?.let { setOf(useSerializersAnnotation) }
			?: emptySet()

		// Create class
		return TypeSpec.classBuilder(data.name.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL))
			.apply {
				modifiers += KModifier.DATA
				data.description?.let { addKdoc(it) }
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
				addAnnotation(AnnotationSpec.builder(Serializable::class).build())
			}
			.primaryConstructor(constructor)
			.addProperties(properties)
			.build()
			.let { JellyFile(Packages.MODEL, fileAnnotations, it) }
	}
}
