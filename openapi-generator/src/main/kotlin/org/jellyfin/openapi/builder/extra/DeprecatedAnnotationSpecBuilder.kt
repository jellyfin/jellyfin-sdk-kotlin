package org.jellyfin.openapi.builder.extra

import com.squareup.kotlinpoet.AnnotationSpec
import org.jellyfin.openapi.builder.Builder

class DeprecatedAnnotationSpecBuilder : Builder<String, AnnotationSpec> {
	override fun build(data: String): AnnotationSpec {
		return AnnotationSpec.builder(Deprecated::class)
			.addMember("%S", data)
			.build()
	}
}
