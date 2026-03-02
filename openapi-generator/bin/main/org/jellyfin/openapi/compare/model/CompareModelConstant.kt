package org.jellyfin.openapi.compare.model

import kotlinx.serialization.Serializable

@Serializable
data class CompareModelConstant(
	val name: String,

	val changes: Collection<CompareValueDiff> = emptyList(),
)
