package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.TypeName

data class ObjectApiModelProperty(
	val name: String,
	val originalName: String,
	val type: TypeName,
	val description: String?,
	val deprecated: Boolean
)
