package org.jellyfin.openapi.compare

import org.jellyfin.openapi.compare.model.CompareOperation
import org.jellyfin.openapi.compare.model.CompareOperationParameter
import org.jellyfin.openapi.compare.model.buildCompareCollectionDiff
import org.jellyfin.openapi.compare.model.buildCompareValueDiffCollection
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.ApiServiceOperationParameter
import org.jellyfin.openapi.model.GeneratorContext

class OperationComparator {
	private fun compareParameter(
		oldParameter: ApiServiceOperationParameter?,
		newParameter: ApiServiceOperationParameter,
	): CompareOperationParameter = CompareOperationParameter(
		name = newParameter.name,
		changes = buildCompareValueDiffCollection(oldParameter, newParameter) {
			detect({ name }, "Name")
			detect({ originalName }, "Original name")
			detect({ description }, "Description")
			detect({ deprecated }, "Deprecated")
			detect({ defaultValue }, "Default value")
			// Check type and nullability separately
			detect({ type.copy(nullable = false) }, "Type")
			detect({ type.isNullable }, "Nullable")
			detect({ validation }, "Validation")
		},
	)

	private fun compareParameters(
		oldParameters: Collection<ApiServiceOperationParameter>?,
		newParameters: Collection<ApiServiceOperationParameter>,
	) = buildCompareCollectionDiff(
		first = oldParameters ?: newParameters,
		second = newParameters,
		keySelector = { name },
		createModel = ::compareParameter,
	)

	private fun compareOperation(
		oldOperation: ApiServiceOperation?,
		newOperation: ApiServiceOperation,
	) = CompareOperation(
		name = newOperation.name,
		pathParameters = compareParameters(oldOperation?.pathParameters, newOperation.pathParameters),
		queryParameters = compareParameters(oldOperation?.queryParameters, newOperation.queryParameters),
		changes = buildCompareValueDiffCollection(oldOperation, newOperation) {
			detect({ name }, "Name")
			detect({ description }, "Description")
			detect({ deprecated }, "Deprecated")
			detect({ pathTemplate }, "URL template")
			detect({ method }, "Method")
			detect({ requireAuthentication }, "Authentication")
			detect({ returnType }, "Return type")
			detect({ body }, "Body type")
		},
	)

	fun compare(
		oldSchema: GeneratorContext,
		newSchema: GeneratorContext,
	) = buildCompareCollectionDiff(
		first = oldSchema.apiServices.flatMap { it.operations },
		second = newSchema.apiServices.flatMap { it.operations },
		keySelector = { method.toString() + pathTemplate },
		createModel = ::compareOperation,
	)
}
