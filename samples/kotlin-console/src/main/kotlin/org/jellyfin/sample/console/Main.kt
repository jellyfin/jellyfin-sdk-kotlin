package org.jellyfin.sample.console

import kotlinx.cli.ArgParser
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.model.ClientInfo
import org.jellyfin.apiclient.model.DeviceInfo
import org.jellyfin.sample.console.cli.Discover
import org.jellyfin.sample.console.cli.Libraries
import org.jellyfin.sample.console.cli.Login
import org.jellyfin.sample.console.cli.Users

fun main(args: Array<String>) {
	val jellyfin = Jellyfin {
		clientInfo = ClientInfo("Jellyfin Sample: Kotlin Console", "DEV")
		deviceInfo = DeviceInfo("cli", "cli")
	}

	ArgParser("jellyfin").apply {
		subcommands(Discover(jellyfin))
		subcommands(Login(jellyfin))
		subcommands(Libraries(jellyfin))
		subcommands(Users(jellyfin))

		parse(args)
	}
}
