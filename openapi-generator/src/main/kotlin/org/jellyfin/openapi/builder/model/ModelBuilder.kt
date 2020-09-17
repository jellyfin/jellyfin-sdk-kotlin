package org.jellyfin.openapi.builder.model

import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.model.*

class ModelBuilder(
	private val emptyModelBuilder: EmptyModelBuilder,
	private val enumModelBuilder: EnumModelBuilder,
	private val objectModelBuilder: ObjectModelBuilder
) : Builder<ApiModel, JellyFile> {
	override fun build(data: ApiModel) = when (data) {
		is EmptyApiModel -> emptyModelBuilder.build(data)
		is EnumApiModel -> enumModelBuilder.build(data)
		is ObjectApiModel -> objectModelBuilder.build(data)
		else -> throw Error("Unknown model class ${data::class.qualifiedName}")
	}
}
