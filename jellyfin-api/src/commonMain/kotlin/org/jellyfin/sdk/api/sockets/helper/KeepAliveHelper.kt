package org.jellyfin.sdk.api.sockets.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.jellyfin.sdk.api.sockets.SocketInstance
import org.jellyfin.sdk.model.socket.KeepAliveMessage
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger {}

internal class KeepAliveHelper(
	private val coroutineScope: CoroutineScope,
) {
	private var keepAliveTicker: Job? = null

	fun reset(instance: SocketInstance, lostTimeout: Int) {
		// The server considers a socket lost after [lostTimeout] seconds
		// to make sure the socket doesn't get lost we divide the value by
		// 2 to get the delay between sending KeepAlive messages
		val delay = lostTimeout / 2
		logger.info { "Using a KeepAlive message delay of ${delay.seconds} seconds" }
		keepAliveTicker?.cancel()
		keepAliveTicker = coroutineScope.launch(Dispatchers.Unconfined) {
			while (true) {
				instance.publish(KeepAliveMessage())
				delay(delay.seconds)
			}
		}
	}
}
