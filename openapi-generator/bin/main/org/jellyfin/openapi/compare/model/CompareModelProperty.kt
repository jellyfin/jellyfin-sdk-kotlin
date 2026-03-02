package org.jellyfin.openapi.compare.model

import kotlinx.serialization.Serializable

@Serializable
data class CompareModelProperty(
	val name: String,

	val changes: Collection<CompareValueDiff> = emptyList(),
)
