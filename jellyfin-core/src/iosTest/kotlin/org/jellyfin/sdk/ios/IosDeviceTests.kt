package org.jellyfin.sdk.ios

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank
import io.kotest.matchers.types.shouldBeInstanceOf
import org.jellyfin.sdk.api.ktor.KtorFactory
import org.jellyfin.sdk.createJellyfinOptions

class IosDeviceTests : FunSpec({
	test("iosDevice returns a stable id and a name") {
		val device = iosDevice()

		device.id.shouldNotBeBlank()
		device.name.shouldNotBeBlank()
		iosDevice().id shouldBe device.id
	}

	test("default options use Ktor") {
		val options = createJellyfinOptions {}

		options.apiClientFactory.shouldBeInstanceOf<KtorFactory>()
		options.socketConnectionFactory.shouldBeInstanceOf<KtorFactory>()
	}
})
