package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.api.operations.SystemApi
import org.jellyfin.sample.cli.serverOption

class Ping(
	private val jellyfin: Jellyfin
) : CliktCommand("Pings a given server and retrieve basic system information") {
	private val server by serverOption()
	private val extended by option("-e", "--extended", help = "Find servers based on input using recommended server algorithm").flag(default = false)

	override fun run() = runBlocking {
		if (extended) runExtended()
		else runSimple()
	}

	suspend fun runSimple() {
		val api = jellyfin.createApi(baseUrl = server)
		val systemApi = SystemApi(api)

		val result by systemApi.getPublicSystemInfo()

		println("id: ${result.id}")
		println("name: ${result.serverName}")
		println("version: ${result.version}")
	}

	suspend fun runExtended() {
		val servers = jellyfin.discovery.getRecommendedServers(server)
		servers.onEach {
			println("${it.address}: score=${it.score} duration=${it.responseTime}ms parent(${it.isAppended})=${it.parent}")
			println("info=${it.systemInfo}")
			println()
		}.collect()
	}
}
