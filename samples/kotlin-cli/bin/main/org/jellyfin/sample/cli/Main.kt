package org.jellyfin.sample.cli

import com.github.ajalt.clikt.core.NoOpCliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import org.jellyfin.sample.cli.command.Discover
import org.jellyfin.sample.cli.command.Libraries
import org.jellyfin.sample.cli.command.Login
import org.jellyfin.sample.cli.command.Observe
import org.jellyfin.sample.cli.command.Ping
import org.jellyfin.sample.cli.command.Users
import org.jellyfin.sdk.createJellyfin
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

fun main(args: Array<String>) {
	val jellyfin = createJellyfin {
		clientInfo = ClientInfo("Jellyfin Sample: Kotlin CLI", "DEV")
		deviceInfo = DeviceInfo("cli", "cli")
	}

	val instance = NoOpCliktCommand(name = "jellyfin").apply {
		subcommands(Discover(jellyfin))
		subcommands(Libraries(jellyfin))
		subcommands(Login(jellyfin))
		subcommands(Observe(jellyfin))
		subcommands(Ping(jellyfin))
		subcommands(Users(jellyfin))
	}

	instance.main(args)
}
