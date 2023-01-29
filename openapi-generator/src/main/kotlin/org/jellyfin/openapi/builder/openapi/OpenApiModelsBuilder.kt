package org.jellyfin.openapi.builder.openapi

import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.model.GeneratorContext

class OpenApiModelsBuilder(
	private val openApiModelBuilder: OpenApiModelBuilder,
) : ContextBuilder<Map<String, Schema<Any>>, Unit> {
	override fun build(context: GeneratorContext, data: Map<String, Schema<Any>>) {
		for ((name, schema) in data) {
			if (schema.name == null) schema.name = name
			context += openApiModelBuilder.build(context, schema)
		}
	}
}
