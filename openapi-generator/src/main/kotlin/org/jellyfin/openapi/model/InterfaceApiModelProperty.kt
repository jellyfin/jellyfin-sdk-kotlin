package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.TypeName

data class InterfaceApiModelProperty(
	val name: String,
	val originalName: String,
	val type: TypeName,
	val description: String?,
	val deprecated: Boolean,
) {
	fun typeMatches(other: InterfaceApiModelProperty): Boolean =
		other === this || (name == other.name && originalName == other.originalName && type == other.type)
}
