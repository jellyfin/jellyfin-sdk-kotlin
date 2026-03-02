package org.jellyfin.openapi.model

data class EnumApiModel(
	override val name: String,
	override val description: String?,
	override val deprecated: Boolean,
	override val interfaces: Set<String>,
	val constants: Set<String>,
) : ApiModel
