package org.jellyfin.openapi.hooks.api

import org.jellyfin.openapi.builder.openapi.OpenApiReturnTypeBuilder.Companion.TYPE_BINARY
import org.jellyfin.openapi.hooks.OperationUrlHook
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.HttpMethod

class BinaryOperationUrlHook : OperationUrlHook {
	override fun shouldOperationBuildUrlFun(api: ApiService, operation: ApiServiceOperation) =
		operation.method == HttpMethod.GET && operation.returnType == TYPE_BINARY
}
