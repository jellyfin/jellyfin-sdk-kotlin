package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.apiInstanceHolder
import org.jellyfin.sample.cli.logger
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.sessionApi
import org.jellyfin.sdk.model.api.ClientCapabilitiesDto
import org.jellyfin.sdk.model.api.GeneralCommandType
import org.jellyfin.sdk.model.api.MediaType

class Observe(
	jellyfin: Jellyfin,
) : CliktCommand("Create a WebSocket connection and listen to all events") {
	private val logger by logger()
	private val api by apiInstanceHolder(jellyfin)

	override fun run(): Unit = runBlocking {
		logger.info("Starting subscription")

		// Report all capabilities
		if (api.accessToken != null) {
			api.sessionApi.postFullCapabilities(
				data = ClientCapabilitiesDto(
					playableMediaTypes = MediaType.entries,
					supportedCommands = GeneralCommandType.entries,
					supportsMediaControl = true,
					supportsPersistentIdentifier = true,
				)
			)
		}

		launch {
			// Watch connection state
			api.webSocket.state.onEach { state ->
				logger.info("State: $state")
			}.launchIn(this)

			// Listen for messages
			// this automatically subscribes to activity log entries etc.
			api.webSocket.subscribeAll().onEach { message ->
				logger.info("Received $message")
			}.launchIn(this)
		}
	}
}
