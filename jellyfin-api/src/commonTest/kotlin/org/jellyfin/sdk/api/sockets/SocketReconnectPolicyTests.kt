package org.jellyfin.sdk.api.sockets

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.time.Duration.Companion.seconds

class SocketReconnectPolicyTests : FunSpec({
	test("DelayReconnect always returns the specified delay") {
		SocketReconnectPolicy.DelayReconnect(1.seconds)
			.getReconnectDelay() shouldBe 1.seconds

		SocketReconnectPolicy.DelayReconnect(1.seconds).apply {
			notifyDisconnected()
			notifyDisconnected()
		}.getReconnectDelay() shouldBe 1.seconds

		SocketReconnectPolicy.DelayReconnect(1.seconds).apply {
			notifyDisconnected()
		}.getReconnectDelay() shouldBe 1.seconds
	}

	test("ExponentialDelayReconnect grows exponentially") {
		val policy = SocketReconnectPolicy.ExponentialDelayReconnect(
			minDelay = 1.seconds,
			maxDelay = 16.seconds,
			factor = 2.0,
		)

		policy.notifyDisconnected()
		policy.getReconnectDelay() shouldBe 1.seconds

		policy.notifyDisconnected()
		policy.getReconnectDelay() shouldBe 2.seconds

		policy.notifyDisconnected()
		policy.getReconnectDelay() shouldBe 4.seconds

		policy.notifyDisconnected()
		policy.getReconnectDelay() shouldBe 8.seconds

		policy.notifyDisconnected()
		policy.getReconnectDelay() shouldBe 16.seconds
	}

	test("ExponentialDelayReconnect should not go over maxDelay") {
		val policy = SocketReconnectPolicy.ExponentialDelayReconnect(
			minDelay = 1.seconds,
			maxDelay = 4.seconds,
			factor = 2.0,
		)

		policy.notifyDisconnected()
		policy.notifyDisconnected()
		policy.notifyDisconnected()
		policy.getReconnectDelay() shouldBe 4.seconds

		policy.notifyDisconnected()
		policy.getReconnectDelay() shouldBe 4.seconds
	}
})
