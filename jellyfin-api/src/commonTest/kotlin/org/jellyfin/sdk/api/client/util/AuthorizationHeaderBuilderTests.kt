package org.jellyfin.sdk.api.client.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AuthorizationHeaderBuilderTests : FunSpec({
	test("encodeParameter removes special characters") {
		listOf(
			"test" to "test",
			"test+" to "test%2B",
			"'test'" to "%27test%27",
			"今日は" to "%E4%BB%8A%E6%97%A5%E3%81%AF",
			"水母" to "%E6%B0%B4%E6%AF%8D",
			"ἈᾼᾺΆᾍᾋ" to "%E1%BC%88%E1%BE%BC%E1%BE%BA%CE%86%E1%BE%8D%E1%BE%8B",
		).forEach { (input, expected) ->
			AuthorizationHeaderBuilder.encodeParameterValue(input) shouldBe expected
		}
	}

	test("encodeParameter removes line breaks") {
		listOf(
			"with\nnewline" to "with+newline",
			"with trailing newline\n" to "with+trailing+newline",
			"\nwith prefix newline\n" to "with+prefix+newline",
			"\nwith\na\nlot\nof\nnewline\n" to "with+a+lot+of+newline",
		).forEach { (input, expected) ->
			AuthorizationHeaderBuilder.encodeParameterValue(input) shouldBe expected
		}
	}

	test("buildParameter creates a valid header with access token") {
		AuthorizationHeaderBuilder.buildParameter("key", "val") shouldBe """key="val""""
		AuthorizationHeaderBuilder.buildParameter("one", "two") shouldBe """one="two""""
		AuthorizationHeaderBuilder.buildParameter("1", "2") shouldBe """1="2""""
	}

	test("buildAuthorizationHeader creates a valid header with access token") {
		val value = AuthorizationHeaderBuilder.buildHeader("a", "b", "c", "d", "e")
		value shouldBe """MediaBrowser Client="a", Version="b", DeviceId="c", Device="d", Token="e""""
	}

	test("buildAuthorizationHeader creates a valid header without access token") {
		val value = AuthorizationHeaderBuilder.buildHeader("a", "b", "c", "d")
		value shouldBe """MediaBrowser Client="a", Version="b", DeviceId="c", Device="d""""
	}
})
