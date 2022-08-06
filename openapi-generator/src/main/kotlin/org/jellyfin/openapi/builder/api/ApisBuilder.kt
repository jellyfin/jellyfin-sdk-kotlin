package org.jellyfin.openapi.builder.api

import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.GeneratorContext

class ApisBuilder(
	private val apiBuilder: ApiBuilder,
) : ContextBuilder<Collection<ApiService>, Unit> {
	override fun build(context: GeneratorContext, data: Collection<ApiService>) {
		for (apiService in data) apiBuilder.build(context, apiService)
	}
}
