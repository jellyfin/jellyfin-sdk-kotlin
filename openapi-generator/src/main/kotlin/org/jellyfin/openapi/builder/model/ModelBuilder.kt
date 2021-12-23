package org.jellyfin.openapi.builder.model

import org.jellyfin.openapi.OpenApiGeneratorError
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.model.ApiModel
import org.jellyfin.openapi.model.EmptyApiModel
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.JellyFile
import org.jellyfin.openapi.model.ObjectApiModel

class ModelBuilder(
	private val emptyModelBuilder: EmptyModelBuilder,
	private val enumModelBuilder: EnumModelBuilder,
	private val objectModelBuilder: ObjectModelBuilder
) : Builder<ApiModel, JellyFile> {
	override fun build(data: ApiModel) = when (data) {
		is EmptyApiModel -> emptyModelBuilder.build(data)
		is EnumApiModel -> enumModelBuilder.build(data)
		is ObjectApiModel -> objectModelBuilder.build(data)
		else -> throw OpenApiGeneratorError("Unknown model class ${data::class.qualifiedName}")
	}
}
