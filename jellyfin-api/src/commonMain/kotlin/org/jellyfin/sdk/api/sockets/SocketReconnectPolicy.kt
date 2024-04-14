package org.jellyfin.sdk.api.sockets

import kotlin.math.pow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * The SocketReconnectPolicy describes the behavior for reconnecting WebSockets when there are active subscriptions. It
 * will be notified about specific events and asked for a delay when the socket is disconnected but still has active
 * subscriptions.
 */
public sealed class SocketReconnectPolicy {
	/**
	 * Notify about changed credentials. This means a new connection attempt will be made automatically.
	 */
	public open fun notifyUpdated(): Unit = Unit

	/**
	 * Notify about a successful connection attempt.
	 */
	public open fun notifyConnected(): Unit = Unit

	/**
	 * Notify about a disconnect event while there are active subscriptions. This is always called before
	 * [getReconnectDelay]. This function is not called when the WebSocket is disconnected without active subscriptions.
	 */
	public open fun notifyDisconnected(): Unit = Unit

	/**
	 * The delay to use before attempting to reconnect or null if no new attempt should be made.
	 */
	public abstract fun getReconnectDelay(): Duration?

	/**
	 * Implementation of [SocketReconnectPolicy] that always uses the same [delay].
	 * @see SocketReconnectPolicy
	 */
	public class DelayReconnect(
		public val delay: Duration = 5.seconds
	) : SocketReconnectPolicy() {
		override fun getReconnectDelay(): Duration = delay
	}

	/**
	 * Implementation of [SocketReconnectPolicy] that grows the delay exponentially, starting with [minDelay] until the
	 * maximum of [maxDelay].
	 * @see SocketReconnectPolicy
	 */
	public class ExponentialDelayReconnect(
		public val minDelay: Duration = 1.seconds,
		public val maxDelay: Duration = 5.minutes,
		public val factor: Double = 1.5
	) : SocketReconnectPolicy() {
		private var _attempts = 0

		override fun notifyUpdated() {
			_attempts = 0
		}

		override fun notifyConnected() {
			_attempts = 0
		}

		override fun notifyDisconnected() {
			_attempts++
		}

		override fun getReconnectDelay(): Duration = minDelay
			.times(factor.pow(_attempts - 1))
			.coerceIn(minDelay, maxDelay)
	}
}
