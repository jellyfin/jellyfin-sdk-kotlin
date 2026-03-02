package org.jellyfin.openapi.hooks.api

import org.jellyfin.openapi.hooks.OperationRequestModelHook
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation

class LargeOperationRequestModelHook : OperationRequestModelHook {
	companion object {
		const val PARAMETER_TRESHOLD = 5
	}

	override fun shouldOperationBuildRequestModelFun(api: ApiService, operation: ApiServiceOperation): Boolean =
		(operation.queryParameters.size + operation.pathParameters.size) >= PARAMETER_TRESHOLD
}
