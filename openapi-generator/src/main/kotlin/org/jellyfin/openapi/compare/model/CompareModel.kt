package org.jellyfin.openapi.compare.model

import kotlinx.serialization.Serializable

@Serializable
data class CompareModel(
	val name: String,
	val description: String?,
	val properties: CompareCollectionDiff<CompareModelProperty>,
	val constants: CompareCollectionDiff<CompareModelConstant>,

	val changes: Collection<CompareValueDiff>,
)
