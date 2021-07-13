package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.jellyfin.sdk.Jellyfin

class Discover(
	private val jellyfin: Jellyfin
) : CliktCommand("Discover servers on the local network") {
	private val address by argument(
		name = "address",
		help = "Address to discover servers for. \"local\" to discovery servers in the local network."
	).default("local")

	override fun run() = runBlocking {
		if (address == "local") runLocal()
		else runAddress(address)
	}

	private suspend fun runLocal() {
		println("Starting local network discovery")

		jellyfin.discovery.discoverLocalServers().onEach {
			println("Server ${it.name} was found at address ${it.address}:")
			println("  $it")
		}.collect()
	}

	private suspend fun runAddress(address: String) {
		println("Starting discovery for $address")

		val candidates = jellyfin.discovery.getAddressCandidates(address)
		println("Found ${candidates.size} candidates")

		jellyfin.discovery.getRecommendedServers(candidates).collect {
			buildString {
				append(it.address)
				append(": ")
				append(it.score.toString())
				append(" (")
				append("replied in ${it.responseTime}ms")
				append(", ")
				if (it.systemInfo.isFailure) append("system information not found")
				else append("system information found")
				append(")")
				it.issues.forEach {
					appendLine()
					append("\t")
					append(it)
				}

			}.let(::println)
		}
	}
}
