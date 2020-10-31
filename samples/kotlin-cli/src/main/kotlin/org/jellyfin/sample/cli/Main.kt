package org.jellyfin.sample.cli

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.subcommands
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.model.ClientInfo
import org.jellyfin.apiclient.model.DeviceInfo
import org.jellyfin.sample.cli.command.*

fun main(args: Array<String>) {
	val jellyfin = Jellyfin {
		clientInfo = ClientInfo("Jellyfin Sample: Kotlin CLI", "DEV")
		deviceInfo = DeviceInfo("cli", "cli")
	}

	val instance = NoOpCliktCommand(name = "jellyfin").apply {
		subcommands(Discover(jellyfin))
		subcommands(Login(jellyfin))
		subcommands(Libraries(jellyfin))
		subcommands(Users(jellyfin))
		subcommands(Ping(jellyfin))
	}

	instance.main(args)
}
