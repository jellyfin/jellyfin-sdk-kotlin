package org.jellyfin.sdk.discovery

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
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
		instance.getAddressCandidates("[0:0:0:0:0:0:0:1]") shouldNotContain "http://[0:0:0:0:0:0:0:1]"
		instance.getAddressCandidates("[0:0:0:0:0:0:0:1]") shouldContain "http://[::1]"
		instance.getAddressCandidates("[::1]:8096") shouldContain "http://[::1]:8096"
		instance.getAddressCandidates("[0:0:0:0:0:0:0:1]:8096") shouldContain "http://[::1]:8096"
	}

	test("getAddressCandidates returns empty on bad input") {
		val instance = getInstance()

		// Invalid host
		instance.getAddressCandidates("::").shouldBeEmpty()

		// Empty input
		instance.getAddressCandidates("").shouldBeEmpty()

		// Port out of range
		instance.getAddressCandidates("localhost:65536").shouldBeEmpty()
		instance.getAddressCandidates("localhost:999999").shouldBeEmpty()
	}

	test("getAddressCandidates is case insensitive for protocol") {
		val instance = getInstance()

		// Lowercase
		instance.getAddressCandidates("https://localhost") shouldContain "https://localhost"

		// Uppercase
		instance.getAddressCandidates("HTTPS://localhost") shouldContain "https://localhost"

		// Mixed
		instance.getAddressCandidates("Https://localhost") shouldContain "https://localhost"
	}

	test("getAddressCandidates keeps both trailing slash variants for subpaths") {
		val instance = getInstance()

		val withSlash = instance.getAddressCandidates("https://nas.example.com/jellyfin/")
		withSlash shouldContain "https://nas.example.com/jellyfin/"
		withSlash shouldContain "https://nas.example.com/jellyfin"
		withSlash.first() shouldBe "https://nas.example.com/jellyfin/"

		val withoutSlash = instance.getAddressCandidates("https://nas.example.com/jellyfin")
		withoutSlash shouldContain "https://nas.example.com/jellyfin"
		withoutSlash shouldContain "https://nas.example.com/jellyfin/"
		withoutSlash.first() shouldBe "https://nas.example.com/jellyfin"
	}

	test("getAddressCandidates does not keep trailing slash on root urls") {
		val instance = getInstance()

		instance.getAddressCandidates("https://localhost/") shouldContain "https://localhost"
		instance.getAddressCandidates("https://localhost/") shouldNotContain "https://localhost/"
	}
})
