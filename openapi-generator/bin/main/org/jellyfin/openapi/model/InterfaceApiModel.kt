package org.jellyfin.openapi.model

data class InterfaceApiModel(
	override val name: String,
	override val description: String?,
	override val deprecated: Boolean,
	override val interfaces: Set<String>,
	val polymorphicDiscriminator: String?,
	val properties: Set<InterfaceApiModelProperty>,
) : ApiModel
