package org.jellyfin.sdk.api.sockets.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.jellyfin.sdk.api.sockets.SocketInstance
import org.jellyfin.sdk.model.api.InboundKeepAliveMessage
import kotlin.time.Duration

private val logger = KotlinLogging.logger {}

internal class KeepAliveHelper(
	private val coroutineScope: CoroutineScope,
) {
	private var keepAliveTicker: Job? = null

	fun reset(instance: SocketInstance, lostTimeout: Duration) {
		// The server considers a socket lost after [lostTimeout] seconds
		// to make sure the socket doesn't get lost we divide the value by
		// 2 to get the delay between sending KeepAlive messages
		val delay = lostTimeout / 2
		logger.info { "Using a KeepAlive message delay of ${delay.inWholeSeconds} seconds" }
		keepAliveTicker?.cancel()
		keepAliveTicker = coroutineScope.launch(Dispatchers.Unconfined) {
			while (true) {
				instance.publish(InboundKeepAliveMessage())
				delay(delay)
			}
		}
	}
}
