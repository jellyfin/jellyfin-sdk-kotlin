package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.apiInstanceHolder
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.sockets.addGlobalListener

class Observe(
	jellyfin: Jellyfin,
) : CliktCommand("Create a WebSocket connection and listen to all events") {
	private val api by apiInstanceHolder(jellyfin)

	override fun run() = runBlocking {
		println("Starting subscription")

		val connection = api.ws()

		launch {
			connection.state.collect { state ->
				println("State $state")
			}
		}

		// Listen for messages
		// this automatically subscribes to activity log entries etc.
		connection.addGlobalListener { message ->
			println("Received $message")
		}

		awaitCancellation()
	}
}
