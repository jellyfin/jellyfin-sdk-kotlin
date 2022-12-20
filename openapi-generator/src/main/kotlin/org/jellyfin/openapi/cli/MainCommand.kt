package org.jellyfin.openapi.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class MainCommand(
	commands: List<CliktCommand>,
) : CliktCommand() {
	init {
		subcommands(commands)
	}

	override fun run() = Unit
}
