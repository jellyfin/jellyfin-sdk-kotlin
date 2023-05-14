package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.builder.extra.TypeSerializerBuilder
import org.jellyfin.openapi.builder.extra.defaultValue
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.DefaultValue
import org.jellyfin.openapi.model.DescriptionType
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.InterfaceApiModel
import org.jellyfin.openapi.model.JellyFile
import org.jellyfin.openapi.model.ObjectApiModel

class ObjectModelBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
	private val typeSerializerBuilder: TypeSerializerBuilder
) : ContextBuilder<ObjectApiModel, JellyFile> {
	@Suppress("ComplexMethod", "LoopWithTooManyJumpStatements", "LongMethod", "NestedBlockDepth")
	override fun build(context: GeneratorContext, data: ObjectApiModel): JellyFile {
		val properties = mutableListOf<PropertySpec>()
		val serializers = mutableSetOf<TypeName>()

		var polymorphicProperty: String? = null
		var polymorphicPropertyValue: String? = null
		val superPropertyNames = mutableSetOf<String>()

		for (interfaceName in data.interfaces) {
			// Add shared properties
			val interfaceModel = context.getOrGenerateModel(interfaceName) as? InterfaceApiModel ?: continue
			interfaceModel.properties.forEach { superPropertyNames.add(it.name) }

			if (interfaceModel.polymorphicDiscriminator == null) continue

			// Determine discriminator property name
			val discriminator = interfaceModel.polymorphicDiscriminator
			require(polymorphicProperty == null || polymorphicProperty == discriminator) {
				"Multiple polymorphic interfaces with different discriminator are not supported." +
						" (a=$polymorphicProperty, b=$discriminator)."
			}
			polymorphicProperty = discriminator
		}

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

						// Add override modifier if in interface
						if (property.name in superPropertyNames) modifiers += KModifier.OVERRIDE

						if (property.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))
						addAnnotation(
							AnnotationSpec.builder(Types.SERIAL_NAME).addMember("%S", property.originalName).build()
						)
					}
					.build()
				)

				// Check if serializer is required
				val serializer = typeSerializerBuilder.build(property.type)
				if (serializer != null && serializer !in serializers) serializers += serializer

				// Save polymorphic value
				if (property.originalName == polymorphicProperty) {
					require(polymorphicPropertyValue == null) { "Polymorphic property value already set" }
					requireNotNull(property.defaultValue) { "Default value must be set for polymorphic property" }

					polymorphicPropertyValue = when (property.defaultValue) {
						is DefaultValue.String -> property.defaultValue.value
						is DefaultValue.EnumMember -> property.defaultValue.serialName

						else -> error("Polymorphic property value is of invalid type ${property.defaultValue}")
					}
				}
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
				data.interfaces.forEach { interfaceName ->
					addSuperinterface(
						ClassName(
							Packages.MODEL,
							interfaceName.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL)
						)
					)
				}

				modifiers += KModifier.DATA
				descriptionBuilder.build(DescriptionType.MODEL, data.description)?.let {
					addKdoc("%L", it)
				}
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
				addAnnotation(AnnotationSpec.builder(Types.SERIALIZABLE).build())

				if (polymorphicProperty != null) {
					addAnnotation(
						AnnotationSpec.builder(Types.SERIAL_NAME).addMember(
							"%S",
							requireNotNull(polymorphicPropertyValue) { "Polymorphic property value is missing" }
						).build()
					)
				}
			}
			.primaryConstructor(constructor)
			.addProperties(properties)
			.build()
			.let { JellyFile(Packages.MODEL, fileAnnotations, it) }
	}
}
