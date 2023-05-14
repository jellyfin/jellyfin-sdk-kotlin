package org.jellyfin.openapi.model

data class ObjectApiModel(
	override val name: String,
	override val description: String?,
	override val deprecated: Boolean,
	override val interfaces: Set<String>,
	val properties: Set<ObjectApiModelProperty>,
) : ApiModel
