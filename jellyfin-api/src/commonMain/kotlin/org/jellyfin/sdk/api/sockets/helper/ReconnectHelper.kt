package org.jellyfin.sdk.api.sockets.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mu.KotlinLogging
import kotlin.time.Duration.Companion.milliseconds
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

		if (attempts >= RETRY_TIMINGS.size) {
			logger.debug { "Reconnect schedule failed: exceeded maximum retry attempts of ${RETRY_TIMINGS.size}" }
			return false
		}

		reconnectJob = coroutineScope.launch {
			var retryAfter = RETRY_TIMINGS[attempts]
			if (error) retryAfter *= ERROR_MULTIPLIER

			logger.info { "Reconnect scheduled in $retryAfter (error=$error)" }

			delay(retryAfter)
			reconnect()
		}

		return true
	}

	private companion object {
		// Timing is about 5 minutes until giving up
		private val RETRY_TIMINGS = listOf(
			200.milliseconds,
			2.seconds,
			5.seconds,
			10.seconds,
			15.seconds,
			20.seconds,
			21.seconds,
			22.seconds,
			23.seconds,
			24.seconds,
			25.seconds,
			26.seconds,
			27.seconds,
			28.seconds,
			29.seconds,
			30.seconds,
		)

		private const val ERROR_MULTIPLIER = 3
	}
}
