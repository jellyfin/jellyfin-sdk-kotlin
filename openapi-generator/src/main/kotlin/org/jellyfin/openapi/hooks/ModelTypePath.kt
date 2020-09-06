package org.jellyfin.openapi.hooks

data class ModelTypePath(
	val model: String,
	val property: String
) : TypePath {
	override fun toString(): String = "model.${model}.${property}"
}
