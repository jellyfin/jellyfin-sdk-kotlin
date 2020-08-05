package org.jellyfin.sample.console.cli

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.interaction.device.IDevice
import org.jellyfin.apiclient.model.dto.UserDto
import org.jellyfin.sample.console.utils.callApi

class Users(
		private val jellyfin: Jellyfin,
		private val device: IDevice
) : Subcommand("users", "List all public users") {
	private val server by option(ArgType.String, description = "Url of the server", shortName = "s").required()

	override fun execute() = runBlocking {
		val api = jellyfin.createApi(serverAddress = server, device = device)

		val users = callApi<Array<UserDto>> { callback ->
			api.GetPublicUsersAsync(callback)
		}

		users.forEach {
			println(it.name)
		}
	}
}
