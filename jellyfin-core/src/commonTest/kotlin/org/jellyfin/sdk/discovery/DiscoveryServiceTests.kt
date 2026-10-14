package org.jellyfin.sdk.discovery

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class DiscoveryServiceTests : FunSpec({
	// DiscoveryService.getAddressCandidates without a Jellyfin instance, which requires a context on Android
	val instance = object {
		fun getAddressCandidates(input: String) = AddressCandidateHelper(input).run {
			addCommonCandidates()
			getCandidates()
		}
	}

	test("getAddressCandidates prefers https") {
		instance.getAddressCandidates("demo.jellyfin.org:433/stable/").first().startsWith("https://") shouldBe true
		instance.getAddressCandidates("http://demo.jellyfin.org:433/stable/").first()
			.startsWith("https://") shouldBe true
	}

	test("getAddressCandidates adds Jellyfin ports") {
		instance.getAddressCandidates("localhost") shouldContain "http://localhost:8096"
		instance.getAddressCandidates("localhost") shouldContain "https://localhost:8920"
	}

	test("getAddressCandidates accepts hostnames") {
		instance.getAddressCandidates("localhost") shouldContain "http://localhost"
		instance.getAddressCandidates("jellyfin.local") shouldContain "http://jellyfin.local"
		instance.getAddressCandidates("demo.jellyfin.org") shouldContain "http://demo.jellyfin.org"
		instance.getAddressCandidates("jellyfin.local:8096") shouldContain "http://jellyfin.local:8096"
	}

	test("getAddressCandidates accepts ipv4 addresses") {
		instance.getAddressCandidates("127.0.0.1") shouldContain "http://127.0.0.1"
		instance.getAddressCandidates("192.168.0.1") shouldContain "http://192.168.0.1"
		instance.getAddressCandidates("127.0.0.1:8096") shouldContain "http://127.0.0.1:8096"
	}

	test("getAddressCandidates accepts ipv6 addresses") {
		instance.getAddressCandidates("[::1]") shouldContain "http://[::1]"
		instance.getAddressCandidates("[0:0:0:0:0:0:0:1]") shouldNotContain "http://[0:0:0:0:0:0:0:1]"
		instance.getAddressCandidates("[0:0:0:0:0:0:0:1]") shouldContain "http://[::1]"
		instance.getAddressCandidates("[::1]:8096") shouldContain "http://[::1]:8096"
		instance.getAddressCandidates("[0:0:0:0:0:0:0:1]:8096") shouldContain "http://[::1]:8096"
	}

	test("getAddressCandidates returns empty on bad input") {
		// Invalid host
		instance.getAddressCandidates("::").shouldBeEmpty()

		// Empty input
		instance.getAddressCandidates("").shouldBeEmpty()

		// Port out of range
		instance.getAddressCandidates("localhost:65536").shouldBeEmpty()
		instance.getAddressCandidates("localhost:999999").shouldBeEmpty()
	}

	test("getAddressCandidates is case insensitive for protocol") {
		// Lowercase
		instance.getAddressCandidates("https://localhost") shouldContain "https://localhost"

		// Uppercase
		instance.getAddressCandidates("HTTPS://localhost") shouldContain "https://localhost"

		// Mixed
		instance.getAddressCandidates("Https://localhost") shouldContain "https://localhost"
	}

	test("getAddressCandidates keeps and normalizes paths") {
		// Input with a directory style path ending
		instance.getAddressCandidates("https://demo.jellyfin.org/stable/") shouldContain "https://demo.jellyfin.org/stable/"
		instance.getAddressCandidates("https://demo.jellyfin.org/stable/") shouldNotContain "https://demo.jellyfin.org/stable"

		// Input with a non-directory style path ending
		instance.getAddressCandidates("https://demo.jellyfin.org/stable") shouldContain "https://demo.jellyfin.org/stable/"
		instance.getAddressCandidates("https://demo.jellyfin.org/stable") shouldNotContain "https://demo.jellyfin.org/stable"
		// Nested
		instance.getAddressCandidates("https://demo.jellyfin.org/example/stable") shouldContain "https://demo.jellyfin.org/example/stable/"
		instance.getAddressCandidates("https://demo.jellyfin.org/example/stable") shouldNotContain "https://demo.jellyfin.org/example/stable"

		// Input with extraneous trailing slashes
		instance.getAddressCandidates("https://demo.jellyfin.org/unstable//") shouldContain "https://demo.jellyfin.org/unstable/"
		instance.getAddressCandidates("https://demo.jellyfin.org/unstable//") shouldNotContain "https://demo.jellyfin.orgun/stable"
		instance.getAddressCandidates("https://demo.jellyfin.org/unstable//") shouldNotContain "https://demo.jellyfin.org/unstable//"
		instance.getAddressCandidates("https://demo.jellyfin.org//") shouldContain "https://demo.jellyfin.org"
		instance.getAddressCandidates("https://demo.jellyfin.org//") shouldNotContain "https://demo.jellyfin.org/"
	}
})
