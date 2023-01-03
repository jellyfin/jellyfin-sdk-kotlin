package org.jellyfin.openapi.compare.model

import kotlinx.serialization.Serializable

@Serializable
data class CompareResult(
	val binaryDifference: Boolean,
	val info: Collection<CompareValueDiff>,
	val api: CompareCollectionDiff<CompareOperation>,
	val model: CompareCollectionDiff<CompareModel>,
)
