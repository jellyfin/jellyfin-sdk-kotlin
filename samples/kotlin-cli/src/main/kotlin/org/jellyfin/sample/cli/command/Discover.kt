package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin

class Discover(
	private val jellyfin: Jellyfin
) : CliktCommand("Discover servers on the local network") {
	override fun run() = runBlocking {
		println("Starting discovery")

		jellyfin.discovery.discoverLocalServers().onEach {
			println("Server ${it.name} was found at address ${it.address}:")
			println("  $it")
		}.collect()
	}
}
