package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.logger
import org.jellyfin.sdk.Jellyfin

class Discover(
	private val jellyfin: Jellyfin
) : CliktCommand("Discover servers on the local network") {
	private val logger by logger()

	private val address by argument(
		name = "address",
		help = "Address to discover servers for. \"local\" to discovery servers in the local network."
	).default("local")

	override fun run() = runBlocking {
		if (address == "local") runLocal()
		else runAddress(address)
	}

	private suspend fun runLocal() {
		logger.info("Starting local network discovery")

		jellyfin.discovery.discoverLocalServers().onEach {
			logger.info("Server ${it.name} was found at address ${it.address}:")
			logger.info("  $it")
		}.collect()
	}

	private suspend fun runAddress(address: String) {
		logger.info("Starting discovery for $address")

		val candidates = jellyfin.discovery.getAddressCandidates(address)
		logger.info("Found ${candidates.size} candidates")

		val servers = jellyfin.discovery.getRecommendedServers(candidates)
		for (server in servers) {
			buildString {
				append(server.address)
				append(": ")
				append(server.score.toString())
				append(" (")
				append("replied in ${server.responseTime}ms")
				append(", ")
				if (server.systemInfo.isFailure) append("system information not found")
				else append("system information found")
				append(")")
				server.issues.forEach {
					appendLine()
					append("\t")
					append(it)
				}
			}.let(logger::info)
		}
	}
}
