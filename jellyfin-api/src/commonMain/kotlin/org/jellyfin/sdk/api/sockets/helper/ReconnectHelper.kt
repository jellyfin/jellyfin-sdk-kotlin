package org.jellyfin.sdk.api.sockets.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KotlinLogging
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger {}

internal class ReconnectHelper(
	private val coroutineScope: CoroutineScope,
	private val reconnect: suspend () -> Unit,
) {
	private var reconnectJob: Job? = null
	private var attempts = 0

	fun reset() {
		logger.debug { "Resetting" }
		reconnectJob?.cancel()
		attempts = 0
	}

	fun notifyReconnect() {
		reconnectJob?.cancel()
		attempts++
		logger.debug { "Notified about reconnect, attempts=${attempts}" }
	}

	fun notifyConnected() {
		attempts = 0
		logger.debug { "Notified about connect, attempts reset" }
	}

	fun scheduleReconnect(error: Boolean = false): Boolean {
		reconnectJob?.cancel()

		if (attempts > RETRY_ATTEMPTS) {
			logger.debug { "Reconnect schedule failed: exceeded maximum retry attempts" }
			return false
		}

		reconnectJob = coroutineScope.launch {
			val retryAfter = if (error) RETRY_ERROR else RETRY_NORMAL

			logger.info { "Reconnect scheduled in $retryAfter (error=$error)" }

			delay(retryAfter)
			reconnect()
		}

		return true
	}

	companion object {
		val RETRY_NORMAL = 5.seconds
		val RETRY_ERROR = 30.seconds
		const val RETRY_ATTEMPTS = 5
	}
}
