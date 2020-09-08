package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.TypeSpec

data class JellyFile(
	val namespace: String,
	val typeSpec: TypeSpec
)
