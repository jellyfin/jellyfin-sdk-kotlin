package org.jellyfin.openapi.model

interface ApiModel {
	val name: String
	val description: String?
	val deprecated: Boolean
	val interfaces: Set<String>
}
