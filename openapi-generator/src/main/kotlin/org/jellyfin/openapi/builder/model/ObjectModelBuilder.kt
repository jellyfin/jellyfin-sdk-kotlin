package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.builder.extra.TypeSerializerBuilder
import org.jellyfin.openapi.builder.extra.defaultValue
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.DescriptionType
import org.jellyfin.openapi.model.JellyFile
import org.jellyfin.openapi.model.ObjectApiModel

class ObjectModelBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
	private val typeSerializerBuilder: TypeSerializerBuilder
) : Builder<ObjectApiModel, JellyFile> {
	@Suppress("ComplexMethod")
	override fun build(data: ObjectApiModel): JellyFile {
		val properties = mutableListOf<PropertySpec>()
		val serializers = mutableSetOf<TypeName>()
		val constructor = FunSpec.constructorBuilder().apply {
			data.properties.forEach { property ->
				// Create constructor parameter
				addParameter(ParameterSpec.builder(property.name, property.type).apply {
					defaultValue(property)
				}.build())

				// Create class property
				properties.add(PropertySpec
					.builder(property.name, property.type)
					.initializer(property.name)
					.apply {
						descriptionBuilder.build(DescriptionType.MODEL_PROPERTY, property.description)?.let {
							addKdoc("%L", it)
						}

						if (property.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))
						addAnnotation(AnnotationSpec.builder(Types.SERIAL_NAME).addMember("%S", property.originalName).build())
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
				AnnotationSpec.builder(Types.USE_SERIALIZERs).apply {
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
				descriptionBuilder.build(DescriptionType.MODEL, data.description)?.let {
					addKdoc("%L", it)
				}
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
				addAnnotation(AnnotationSpec.builder(Types.SERIALIZABLE).build())
			}
			.primaryConstructor(constructor)
			.addProperties(properties)
			.build()
			.let { JellyFile(Packages.MODEL, fileAnnotations, it) }
	}
}
