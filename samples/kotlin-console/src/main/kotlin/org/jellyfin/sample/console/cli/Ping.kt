package org.jellyfin.sample.console.cli

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.api.operations.SystemApi

class Ping(
	private val jellyfin: Jellyfin
) : Subcommand("ping", "Pings a given server and retrieve basic system information") {
	private val server by option(ArgType.String, description = "Url of the server", shortName = "s").required()

	override fun execute() = runBlocking {
		val api = jellyfin.createApi(baseUrl = server)
		val systemApi = SystemApi(api)

		val result by systemApi.getPublicSystemInfo()

		println("id: ${result.id}")
		println("name: ${result.serverName}")
		println("version: ${result.version}")
	}
}
