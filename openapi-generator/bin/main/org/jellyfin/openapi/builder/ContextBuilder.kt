package org.jellyfin.openapi.builder

import org.jellyfin.openapi.model.GeneratorContext

interface ContextBuilder<T, R> {
	fun build(context: GeneratorContext, data: T): R
}
