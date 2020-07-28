package org.jellyfin.sample.console.cli

import kotlinx.cli.Subcommand
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin

class Discover(
	private val jellyfin: Jellyfin
) : Subcommand("discover", "Discover servers on the local network") {
	override fun execute() = runBlocking {
		println("Starting discovery")

		jellyfin.discovery.discover().onEach {
			println("Server ${it.name} was found at address ${it.address}:")
			println("  $it")
		}.collect()
	}
}
