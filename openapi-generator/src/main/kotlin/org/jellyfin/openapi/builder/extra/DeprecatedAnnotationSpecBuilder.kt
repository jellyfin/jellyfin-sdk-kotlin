package org.jellyfin.openapi.builder.extra

import com.squareup.kotlinpoet.AnnotationSpec
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.constants.Types

class DeprecatedAnnotationSpecBuilder : Builder<String, AnnotationSpec> {
	override fun build(data: String): AnnotationSpec {
		return AnnotationSpec.builder(Types.DEPRECATED)
			.addMember("%S", data)
			.build()
	}
}
