package org.jellyfin.openapi.builder.openapi

import io.swagger.v3.oas.models.media.Schema
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toCamelCase
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.model.ApiModel
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.InterfaceApiModel
import org.jellyfin.openapi.model.InterfaceApiModelProperty
import org.jellyfin.openapi.model.ObjectApiModel
import org.jellyfin.openapi.model.ObjectApiModelProperty

class OpenApiModelBuilder(
	private val openApiTypeBuilder: OpenApiTypeBuilder,
	private val openApiDefaultValueBuilder: OpenApiDefaultValueBuilder,
) : ContextBuilder<Schema<Any>, ApiModel> {
	override fun build(context: GeneratorContext, data: Schema<Any>): ApiModel {
		return when {
			// Object
			data.type == "object" -> when {
				// When properties set, use the object model
				!data.properties.isNullOrEmpty() -> buildObjectModel(context, data)
				// When oneOf set, use the interface model
				!data.oneOf.isNullOrEmpty() -> buildInterfaceModel(context, data)
				// Otherwise use an empty object model
				else -> buildObjectModel(context, data)
			}
			// Enum
			data.enum.isNotEmpty() -> buildEnumModel(data)

			// Unknown type
			else -> throw NotImplementedError("Unknown top-level type: ${data.type} for ${data.name}")
		}
	}

	private fun buildInterfaceModel(context: GeneratorContext, data: Schema<Any>): InterfaceApiModel {
		val referencedModels = data.oneOf.mapNotNull { it.`$ref`?.let(context::getOrGenerateModel) }
		var sharedProperties: Set<InterfaceApiModelProperty>? = null
		val polymorphicDiscriminator = data.discriminator?.propertyName

		for ((index, model) in referencedModels.withIndex()) {
			// Add interface to referenced model
			context.addModelInterface(model, data.name, polymorphicDiscriminator)

			// Only search for shared properties if the lookup didn't fail before this iteration
			if (sharedProperties == null && index != 0) continue

			// Find properties of current model
			val modelProperties = when (model) {
				is ObjectApiModel -> model.properties.map {
					InterfaceApiModelProperty(
						name = it.name,
						originalName = it.originalName,
						type = it.type,
						description = it.description,
						deprecated = it.deprecated,
					)
				}.toSet()
				is InterfaceApiModel -> model.properties
				else -> null
			}

			// Search for shared properties between previous and current iteration
			sharedProperties = when {
				// Unsupported model type
				modelProperties == null -> null
				// First iteration
				sharedProperties == null -> modelProperties
				// Compare with existing properties
				else -> sharedProperties.filter { a -> modelProperties.any { b -> a.typeMatches(b) } }.toSet()
			}
		}

		return InterfaceApiModel(
			name = data.name,
			description = data.description,
			deprecated = data.deprecated == true,
			interfaces = emptySet(),
			polymorphicDiscriminator = polymorphicDiscriminator,
			properties = sharedProperties.orEmpty(),
		)
	}

	private fun buildObjectModel(context: GeneratorContext, data: Schema<Any>) = ObjectApiModel(
		name = data.name,
		description = data.description,
		deprecated = data.deprecated == true,
		interfaces = emptySet(),
		properties = data.properties.orEmpty().map { (originalName, property) ->
			val name = originalName.toCamelCase(from = CaseFormat.CAPITALIZED_CAMEL)
			val defaultValue = openApiDefaultValueBuilder.build(context, property)
			ObjectApiModelProperty(
				name = name,
				originalName = originalName,
				defaultValue = defaultValue,
				type = openApiTypeBuilder.build(ModelTypePath(data.name, name), property),
				description = property.description,
				deprecated = property.deprecated == true,
				static = false,
			)
		}.toSet()
	)

	private fun buildEnumModel(data: Schema<Any>) = EnumApiModel(
		name = data.name,
		description = data.description,
		deprecated = data.deprecated == true,
		interfaces = emptySet(),
		constants = data.enum.orEmpty().map { it.toString() }.toSet(),
	)
}
