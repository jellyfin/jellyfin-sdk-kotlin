package org.jellyfin.sample.console.cli

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.interaction.device.IDevice
import org.jellyfin.apiclient.model.querying.ItemsResult
import org.jellyfin.apiclient.model.session.SessionInfoDto
import org.jellyfin.sample.console.utils.callApi

class Libraries(
	private val jellyfin: Jellyfin,
	private val device: IDevice
) : Subcommand("libraries", "List all libraries") {
	val server by option(ArgType.String, description = "Url of the server", shortName = "s").required()
	val token by option(ArgType.String, description = "Access token", shortName = "t").required()

	override fun execute() = runBlocking {
		val api = jellyfin.createApi(serverAddress = server, accessToken = token, device = device)

		val sessionInfo = callApi<Array<SessionInfoDto>> { callback ->
			api.GetCurrentSessionAsync(callback)
		}.firstOrNull()

		if (sessionInfo == null) println("Unknown session")

		val libraries = callApi<ItemsResult> { callback ->
			api.GetUserViews(sessionInfo!!.userId, callback)
		}

		libraries.items.forEach {
			println(it.name)
		}
	}
}
