package org.jellyfin.sample.console.cli

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.api.operations.UserApi

class Users(
	private val jellyfin: Jellyfin
) : Subcommand("users", "List all public users") {
	private val server by option(ArgType.String, description = "Url of the server", shortName = "s").required()

	override fun execute() = runBlocking {
		val api = jellyfin.createApi(baseUrl = server)
		val userApi = UserApi(api)

		val users by userApi.getPublicUsers()

		users.forEach {
			println(it.name)
		}
	}
}
