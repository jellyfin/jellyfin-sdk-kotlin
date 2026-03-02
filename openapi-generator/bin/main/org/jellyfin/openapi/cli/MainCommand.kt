package org.jellyfin.openapi.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import org.jellyfin.openapi.hooks.hooksModule

class MainCommand : BaseCommand() {
	private val commands by lazy { getKoin().getAll<CliktCommand>() }

	private val noHooks by option(
		"--no-hooks",
		help = "Disable all hooks"
	).flag()

	init {
		subcommands(commands)
	}

	override fun run() {
		if (noHooks) getKoin().unloadModules(listOf(hooksModule))
	}
}
