package org.jellyfin.openapi.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import org.jellyfin.openapi.hooks.hooksModule
import org.koin.core.Koin

class MainCommand(
	commands: List<CliktCommand>,
	private val koin: Koin,
) : CliktCommand() {
	private val noHooks by option(
		"--no-hooks",
		help = "Disable all hooks"
	).flag()

	init {
		subcommands(commands)
	}

	override fun run() {
		if (noHooks) koin.unloadModules(listOf(hooksModule))
	}
}
