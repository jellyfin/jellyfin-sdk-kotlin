package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.apiInstanceHolder
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.webSocket
import org.jellyfin.sdk.model.socket.ActivityLogEntryStartMessage
import org.jellyfin.sdk.model.socket.ScheduledTasksInfoStartMessage
import org.jellyfin.sdk.model.socket.SessionsStartMessage

class Observe(
	jellyfin: Jellyfin
) : CliktCommand("Create a WebSocket connection and listen to all events") {
	private val api by apiInstanceHolder(jellyfin)

	override fun run() = runBlocking {
		val webSocketApi = api.webSocket

		println("Starting subscription")

		// Send start messages to receive all events
		webSocketApi.publish(ActivityLogEntryStartMessage())
		webSocketApi.publish(SessionsStartMessage())
		webSocketApi.publish(ScheduledTasksInfoStartMessage())

		// Listen for messages
		webSocketApi.subscribe().collect { message ->
			println(message)
		}
	}
}
