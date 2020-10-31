package org.jellyfin.sample.cli

import kotlinx.cli.ArgParser
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.model.ClientInfo
import org.jellyfin.apiclient.model.DeviceInfo
import org.jellyfin.sample.cli.command.*

fun main(args: Array<String>) {
	val jellyfin = Jellyfin {
		clientInfo = ClientInfo("Jellyfin Sample: Kotlin CLI", "DEV")
		deviceInfo = DeviceInfo("cli", "cli")
	}

	ArgParser("jellyfin").apply {
		subcommands(Discover(jellyfin))
		subcommands(Login(jellyfin))
		subcommands(Libraries(jellyfin))
		subcommands(Users(jellyfin))
		subcommands(Ping(jellyfin))

		parse(args)
	}
}
