package org.jellyfin.openapi.hooks

import com.squareup.kotlinpoet.TypeName
import io.swagger.v3.oas.models.media.Schema

interface TypeBuilderHook {
	/**
	 * Try to create an type or return null if not applicable for this hook.
	 */
	fun onBuildType(path: TypePath, schema: Schema<*>): TypeName?
}
