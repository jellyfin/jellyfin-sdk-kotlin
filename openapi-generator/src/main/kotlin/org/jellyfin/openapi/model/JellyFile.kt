package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.TypeSpec

data class JellyFile(
	val namespace: String,
	val annotations: Set<AnnotationSpec>,
	val typeSpec: TypeSpec
)
