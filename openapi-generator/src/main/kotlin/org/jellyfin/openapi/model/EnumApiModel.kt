package org.jellyfin.openapi.model

data class EnumApiModel(
	override val name: String,
	override val description: String?,
	override val deprecated: Boolean,
	val constants: Set<String>
) : ApiModel
