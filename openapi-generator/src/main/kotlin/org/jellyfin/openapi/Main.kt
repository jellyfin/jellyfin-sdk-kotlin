package org.jellyfin.openapi

import com.github.ajalt.clikt.core.main
import org.jellyfin.openapi.cli.MainCommand
import org.jellyfin.openapi.hooks.hooksModule
import org.koin.core.context.startKoin

fun main(vararg args: String) {
	// Start Koin
	val koin = startKoin { modules(mainModule, hooksModule) }.koin

	// Invoke Clikt to run requested command
	koin.get<MainCommand>().main(args)
}
