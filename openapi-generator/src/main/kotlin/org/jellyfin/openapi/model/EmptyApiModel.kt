package org.jellyfin.openapi.model

data class EmptyApiModel(
	override val name: String,
	override val description: String?,
	override val deprecated: Boolean
) : ApiModel
