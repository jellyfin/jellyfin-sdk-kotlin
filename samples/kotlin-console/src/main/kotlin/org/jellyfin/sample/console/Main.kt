package org.jellyfin.sample.console

import kotlinx.cli.ArgParser
import org.jellyfin.apiclient.AppInfo
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.interaction.device.IDevice
import org.jellyfin.sample.console.cli.Discover
import org.jellyfin.sample.console.cli.Libraries
import org.jellyfin.sample.console.cli.Login
import org.jellyfin.sample.console.utils.GarbageHttpClient

fun main(args: Array<String>) {
	val jellyfin = Jellyfin {
		appInfo = AppInfo("Jellyfin Sample: Kotlin Console", "DEV")
		httpClient = GarbageHttpClient()
	}

	val device = object : IDevice {
		override val deviceName: String = "cli"
		override val deviceId: String = "cli"
	}

	ArgParser("jellyfin").apply {
		subcommands(Discover(jellyfin))
		subcommands(Login(jellyfin, device))
		subcommands(Libraries(jellyfin, device))

		parse(args)
	}
}
