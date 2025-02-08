package org.jellyfin.openapi.hooks.api

import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.hooks.OperationUrlHook
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation

class SubtitleOperationUrlHook : OperationUrlHook {
	override fun shouldOperationBuildUrlFun(api: ApiService, operation: ApiServiceOperation) =
		api.name == "Subtitle${Strings.API_SERVICE_SUFFIX}" && operation.name.startsWith("getSubtitle")
}
