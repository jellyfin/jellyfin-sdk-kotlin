package org.jellyfin.openapi.model

data class ObjectApiModel(
	override val name: String,
	override val description: String?,
	override val deprecated: Boolean,
	val properties: Set<ObjectApiModelProperty>
) : ApiModel
