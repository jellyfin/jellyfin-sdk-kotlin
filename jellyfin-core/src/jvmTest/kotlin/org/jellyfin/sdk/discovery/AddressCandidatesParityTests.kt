package org.jellyfin.sdk.discovery

import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AddressCandidatesParityTests : FunSpec({
	val inputs = listOf(
		"localhost",
		"localhost:8096",
		"jellyfin.local",
		"jellyfin.local:8096",
		"demo.jellyfin.org",
		"demo.jellyfin.org:433/stable/",
		"demo.jellyfin.org/stable",
		"http://demo.jellyfin.org:433/stable/",
		"https://demo.jellyfin.org",
		"https://demo.jellyfin.org:8920",
		"http://demo.jellyfin.org:80",
		"https://demo.jellyfin.org:443/",
		"127.0.0.1",
		"192.168.1.10:8096",
		"http://192.168.1.10",
		"[::1]",
		"http://[::1]:8096",
		"[0:0:0:0:0:0:0:1]",
		"[0:0:0:0:0:0:0:1]:8096",
		"[2001:DB8:0:0:1:0:0:1]",
		"[2001:db8::0:1]",
		"[1:0:0:2:0:0:0:3]",
		"[::ffff:192.168.1.1]",
		"[::ffff:c0a8:101]",
		"[::192.168.1.1]",
		"[1:2:3:4:5:6:7:8]",
		"[1::2:3:4:5:6:7:8]",
		"[::g]",
		"[1:2:3]",
		"::",
		"HTTP://UPPERCASE.example.com",
		"example.com/path/with/multiple/segments",
		"",
		"   ",
	)

	fun helper(input: String, init: AddressCandidateHelper.() -> Unit) = AddressCandidateHelper(input).apply(init)
	fun ktor(input: String, init: AddressCandidates.() -> Unit) = AddressCandidates(input).apply(init)

	test("addCommonCandidates matches the OkHttp implementation") {
		for (input in inputs) {
			withClue("input=$input") {
				ktor(input) { addCommonCandidates() }.getCandidates() shouldBe
					helper(input) { addCommonCandidates() }.getCandidates()
			}
		}
	}

	test("addProtocolCandidates matches the OkHttp implementation") {
		for (input in inputs) {
			withClue("input=$input") {
				ktor(input) { addProtocolCandidates() }.getCandidates() shouldBe
					helper(input) { addProtocolCandidates() }.getCandidates()
			}
		}
	}

	test("addPortCandidates matches the OkHttp implementation") {
		for (input in inputs) {
			withClue("input=$input") {
				ktor(input) { addPortCandidates() }.getCandidates() shouldBe
					helper(input) { addPortCandidates() }.getCandidates()
			}
		}
	}

	test("candidates without fixes match the OkHttp implementation") {
		for (input in inputs) {
			withClue("input=$input") {
				ktor(input) {}.getCandidates() shouldBe helper(input) {}.getCandidates()
			}
		}
	}
})
