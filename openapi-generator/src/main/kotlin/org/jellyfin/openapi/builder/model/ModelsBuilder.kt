package org.jellyfin.openapi.builder.model

import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.model.ApiModel
import org.jellyfin.openapi.model.GeneratorContext

class ModelsBuilder(
	private val modelBuilder: ModelBuilder,
	private val fileSpecBuilder: FileSpecBuilder,
) : ContextBuilder<Collection<ApiModel>, Unit> {
	override fun build(context: GeneratorContext, data: Collection<ApiModel>) {
		for (model in data) {
			val file = modelBuilder.build(model)
			context += fileSpecBuilder.build(file)
		}
	}
}
