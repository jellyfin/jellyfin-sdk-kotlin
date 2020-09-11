package org.jellyfin.sample.console.cli

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.IDevice
import org.jellyfin.apiclient.model.users.AuthenticationResult
import org.jellyfin.sample.console.utils.callApi

class Login(
	private val jellyfin: Jellyfin,
	private val device: IDevice
) : Subcommand("login", "Login to a given server and retrieve an access token") {
	private val server by option(ArgType.String, description = "Url of the server", shortName = "s").required()
	private val username by option(ArgType.String, description = "Username", shortName = "u").required()
	private val password by option(ArgType.String, description = "Password", shortName = "p")

	override fun execute() = runBlocking {
		val api = jellyfin.createApi(serverAddress = server, device = device)

		val result = callApi<AuthenticationResult> { callback ->
			api.AuthenticateUserAsync(username, password ?: "", callback)
		}

		if (result.accessToken != null) println(result.accessToken)
	}
}
