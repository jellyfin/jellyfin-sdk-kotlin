package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.TypeSpec
import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.model.ModelBuilder
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.model.EmptyApiModel
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.ObjectApiModel
import org.jellyfin.openapi.model.ObjectApiModelProperty
import org.jellyfin.openapi.util.asPascalCase

class OpenApiModelBuilder(
	private val openApiTypeBuilder: OpenApiTypeBuilder,
	private val modelBuilder: ModelBuilder
) : Builder<Schema<Any>, TypeSpec> {
	override fun build(data: Schema<Any>): TypeSpec {
		val model = when {
			// Object
			data.type == "object" -> when (data.properties.isNullOrEmpty()) {
				// No properties use the empty model
				true -> EmptyApiModel(data.name, data.description, data.deprecated == true)
				// Otherwise use the object model
				false -> ObjectApiModel(data.name, data.description, data.deprecated == true, data.properties.map { (originalName, property) ->
					val name = originalName.asPascalCase().toCamelCase()
					ObjectApiModelProperty(
						name = name,
						type = openApiTypeBuilder.build(ModelTypePath(data.name, name), property),
						description = property.description,
						deprecated = property.deprecated == true
					)
				}.toSet())
			}
			// Enum
			data.enum.isNotEmpty() -> EnumApiModel(data.name, data.description, data.deprecated == true, data.enum.orEmpty().map { it.toString() }.toSet())

			// Unknown type
			else -> throw NotImplementedError("Unknown top-level type: ${data.type} for ${data.name}")
		}

		return modelBuilder.build(model)
	}
}

