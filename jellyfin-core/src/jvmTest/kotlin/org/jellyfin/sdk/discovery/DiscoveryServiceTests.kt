package org.jellyfin.sdk.discovery

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.jellyfin.sdk.createJellyfin

class DiscoveryServiceTests : FunSpec({
	fun getInstance() = DiscoveryService(createJellyfin {})

	test("getAddressCandidates prefers https") {
		val instance = getInstance()

		instance.getAddressCandidates("demo.jellyfin.org:433/stable/").first().startsWith("https://") shouldBe true
		instance.getAddressCandidates("http://demo.jellyfin.org:433/stable/").first()
			.startsWith("https://") shouldBe true
	}

	test("getAddressCandidates adds Jellyfin ports") {
		val instance = getInstance()

		instance.getAddressCandidates("localhost") shouldContain "http://localhost:8096"
		instance.getAddressCandidates("localhost") shouldContain "https://localhost:8920"
	}

	test("getAddressCandidates accepts hostnames") {
		val instance = getInstance()

		instance.getAddressCandidates("localhost") shouldContain "http://localhost"
		instance.getAddressCandidates("jellyfin.local") shouldContain "http://jellyfin.local"
		instance.getAddressCandidates("demo.jellyfin.org") shouldContain "http://demo.jellyfin.org"
		instance.getAddressCandidates("jellyfin.local:8096") shouldContain "http://jellyfin.local:8096"
	}

	test("getAddressCandidates accepts ipv4 addresses") {
		val instance = getInstance()

		instance.getAddressCandidates("127.0.0.1") shouldContain "http://127.0.0.1"
		instance.getAddressCandidates("192.168.0.1") shouldContain "http://192.168.0.1"
		instance.getAddressCandidates("127.0.0.1:8096") shouldContain "http://127.0.0.1:8096"
	}

	test("getAddressCandidates accepts ipv6 addresses") {
		val instance = getInstance()

		instance.getAddressCandidates("[::1]") shouldContain "http://[::1]"
		instance.getAddressCandidates("[0:0:0:0:0:0:0:1]") shouldContain "http://[0:0:0:0:0:0:0:1]"
		instance.getAddressCandidates("[::1]:8096") shouldContain "http://[::1]:8096"
		instance.getAddressCandidates("[0:0:0:0:0:0:0:1]:8096") shouldContain "http://[0:0:0:0:0:0:0:1]:8096"
	}

	test("getAddressCandidates fails on bad input") {
		val instance = getInstance()

		instance.getAddressCandidates("::").shouldBeEmpty()
	}
})
