package org.jellyfin.sample.console.cli

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import kotlinx.cli.required
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.api.operations.SystemApi

class Ping(
	private val jellyfin: Jellyfin
) : Subcommand("ping", "Pings a given server and retrieve basic system information") {
	private val server by option(ArgType.String, description = "Url of the server", shortName = "s").required()
	private val extended by option(ArgType.Boolean, description = "Normalize the URL and request", shortName = "e").default(false)

	override fun execute() = runBlocking {
		if (extended) executeExtended()
		else executeSimple()
	}

	suspend fun executeSimple() {
		val api = jellyfin.createApi(baseUrl = server)
		val systemApi = SystemApi(api)

		val result by systemApi.getPublicSystemInfo()

		println("id: ${result.id}")
		println("name: ${result.serverName}")
		println("version: ${result.version}")
	}

	suspend fun executeExtended() {
		val servers = jellyfin.discovery.getRecommendedServers(server)
		servers.onEach {
			println("${it.address}: score=${it.score} duration=${it.responseTime}ms parent(${it.isAppended})=${it.parent}")
			println("info=${it.systemInfo}")
			println()
		}.collect()
	}
}
