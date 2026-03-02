package org.jellyfin.openapi.hooks.api

import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.hooks.OperationUrlHook
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.HttpMethod

class BinaryOperationUrlHook : OperationUrlHook {
	override fun shouldOperationBuildUrlFun(api: ApiService, operation: ApiServiceOperation) =
		operation.method == HttpMethod.GET && operation.returnType == Types.BYTE_ARRAY
}
