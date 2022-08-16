package org.jellyfin.openapi.builder.api

import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.GeneratorContext

class ApisBuilder(
	private val apiBuilder: ApiBuilder,
	private val fileSpecBuilder: FileSpecBuilder,
) : ContextBuilder<Collection<ApiService>, Unit> {
	override fun build(context: GeneratorContext, data: Collection<ApiService>) {
		for (apiService in data) {
			val file = apiBuilder.build(apiService)
			context += fileSpecBuilder.build(file)
		}
	}
}
