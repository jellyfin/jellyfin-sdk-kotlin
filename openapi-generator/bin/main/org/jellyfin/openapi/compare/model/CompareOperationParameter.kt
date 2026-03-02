package org.jellyfin.openapi.compare.model

import kotlinx.serialization.Serializable

@Serializable
data class CompareOperationParameter(
	val name: String,

	val changes: Collection<CompareValueDiff> = emptyList(),
)
