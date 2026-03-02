package org.jellyfin.openapi.compare.model

import kotlinx.serialization.Serializable

@Serializable
data class CompareOperation(
	val name: String,
	val pathParameters: CompareCollectionDiff<CompareOperationParameter>,
	val queryParameters: CompareCollectionDiff<CompareOperationParameter>,

	val changes: Collection<CompareValueDiff>,
)
