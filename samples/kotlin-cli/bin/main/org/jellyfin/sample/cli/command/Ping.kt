package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.logger
import org.jellyfin.sample.cli.serverOption
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.systemApi

class Ping(
	private val jellyfin: Jellyfin,
) : CliktCommand(name = "ping") {
	private val logger by logger()
	private val server by serverOption()
	private val extended by option(
		"-e", "--extended",
		help = "Find servers based on input using recommended server algorithm"
	).flag(default = false)

	override fun help(context: Context): String = "Pings a given server and retrieve basic system information"

	override fun run() = runBlocking {
		if (extended) runExtended()
		else runSimple()
	}

	private suspend fun runSimple() {
		val api = jellyfin.createApi(baseUrl = server)

		val result by api.systemApi.getPublicSystemInfo()

		logger.info("id: ${result.id}")
		logger.info("name: ${result.serverName}")
		logger.info("version: ${result.version}")
	}

	private suspend fun runExtended() {
		val servers = jellyfin.discovery.getRecommendedServers(server)
		for (server in servers) {
			logger.info("${server.address}: score=${server.score} duration=${server.responseTime}ms")
			logger.info("info=${server.systemInfo}")
		}
	}
}
