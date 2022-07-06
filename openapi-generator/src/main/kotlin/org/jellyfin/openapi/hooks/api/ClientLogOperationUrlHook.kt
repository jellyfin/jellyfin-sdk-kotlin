package org.jellyfin.openapi.hooks.api

import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.hooks.OperationUrlHook
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation

class ClientLogOperationUrlHook : OperationUrlHook {
	override fun shouldOperationBuildUrlFun(api: ApiService, operation: ApiServiceOperation) =
		api.name == "ClientLog${Strings.API_SERVICE_SUFFIX}" && operation.name == "logFile"
}
