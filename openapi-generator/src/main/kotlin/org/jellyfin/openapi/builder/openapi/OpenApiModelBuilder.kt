package org.jellyfin.openapi.builder.openapi

import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.model.ModelBuilder
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.model.*
import org.jellyfin.openapi.util.asPascalCase

class OpenApiModelBuilder(
	private val openApiTypeBuilder: OpenApiTypeBuilder,
	private val modelBuilder: ModelBuilder
) : Builder<Schema<Any>, JellyFile> {
	override fun build(data: Schema<Any>): JellyFile {
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
						originalName = originalName,
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

