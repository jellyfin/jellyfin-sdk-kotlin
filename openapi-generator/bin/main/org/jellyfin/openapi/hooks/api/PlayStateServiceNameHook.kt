package org.jellyfin.openapi.hooks.api

import io.swagger.v3.oas.models.Operation
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.hooks.ServiceNameHook

class PlayStateServiceNameHook : ServiceNameHook {
	companion object {
		const val PLAYSTATE_BAD = "Playstate${Strings.API_SERVICE_SUFFIX}"
		const val PLAYSTATE_GOOD = "PlayState${Strings.API_SERVICE_SUFFIX}"
	}

	override fun mapServiceNames(operation: Operation, serviceNames: Set<String>) =
		serviceNames.map { serviceName ->
			when (serviceName) {
				PLAYSTATE_BAD -> PLAYSTATE_GOOD
				else -> serviceName
			}
		}.toSet()
}
