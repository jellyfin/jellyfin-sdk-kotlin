package org.jellyfin.openapi.model

data class ApiService(
	val name: String,
	val operations: Set<ApiServiceOperation> = emptySet()
)
