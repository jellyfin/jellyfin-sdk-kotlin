package org.jellyfin.openapi.builder.model

import org.jellyfin.openapi.OpenApiGeneratorError
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.model.ApiModel
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.InterfaceApiModel
import org.jellyfin.openapi.model.JellyFile
import org.jellyfin.openapi.model.ObjectApiModel

class ModelBuilder(
	private val enumModelBuilder: EnumModelBuilder,
	private val objectModelBuilder: ObjectModelBuilder,
	private val interfaceModelBuilder: InterfaceModelBuilder,
) : ContextBuilder<ApiModel, JellyFile> {
	override fun build(context: GeneratorContext, data: ApiModel) = when (data) {
		is EnumApiModel -> enumModelBuilder.build(data)
		is ObjectApiModel -> objectModelBuilder.build(context, data)
		is InterfaceApiModel -> interfaceModelBuilder.build(context, data)
		else -> throw OpenApiGeneratorError("Unknown model class ${data::class.qualifiedName}")
	}
}
