package org.jellyfin.openapi.hooks

import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation

interface OperationRequestModelHook {
	/**
	 * Determine if a function should be added to request an endpoint with a model instead of parameters.
	 */
	fun shouldOperationBuildRequestModelFun(api: ApiService, operation: ApiServiceOperation): Boolean
}
