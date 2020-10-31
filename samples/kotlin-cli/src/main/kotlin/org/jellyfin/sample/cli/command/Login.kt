package org.jellyfin.sample.cli.command

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.api.client.extensions.authenticateUserByName
import org.jellyfin.apiclient.api.operations.UserApi

class Login(
	private val jellyfin: Jellyfin
) : Subcommand("login", "Login to a given server and retrieve an access token") {
	private val server by option(ArgType.String, description = "Url of the server", shortName = "s").required()
	private val username by option(ArgType.String, description = "Username", shortName = "u").required()
	private val password by option(ArgType.String, description = "Password", shortName = "p")

	override fun execute() = runBlocking {
		val api = jellyfin.createApi(baseUrl = server)
		val userApi = UserApi(api)

		val result by userApi.authenticateUserByName(username, password.orEmpty())

		if (result.accessToken != null) println(result.accessToken)
	}
}
