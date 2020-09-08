package org.jellyfin.openapi.builder.extra

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.asTypeName
import org.jellyfin.openapi.builder.Builder

class DeprecatedAnnotationSpecBuilder : Builder<String, AnnotationSpec> {
	override fun build(data: String): AnnotationSpec {
		val cls = Deprecated::class.asTypeName()
		return AnnotationSpec.Companion.builder(cls)
			.addMember("%S", data)
			.build()
	}
}
