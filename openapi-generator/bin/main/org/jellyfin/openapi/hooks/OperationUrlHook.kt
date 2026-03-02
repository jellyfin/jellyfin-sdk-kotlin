package org.jellyfin.openapi.hooks

import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation

interface OperationUrlHook {
	/**
	 * Determine if a function should be added to get the URL of a given operation
	 */
	fun shouldOperationBuildUrlFun(api: ApiService, operation: ApiServiceOperation): Boolean
}
