package org.jellyfin.sdk.api.client.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class AuthorizationHeaderBuilderTests : FunSpec({
	test("encodeParameter removes special characters") {
		forAll(
			row("""test""", "test"),
			row("""test+""", "test%2B"),
			row("""'test'""", "%27test%27"),
			row("""今日は""", "%E4%BB%8A%E6%97%A5%E3%81%AF"),
			row("""水母""", "%E6%B0%B4%E6%AF%8D"),
			row("""ἈᾼᾺΆᾍᾋ""", "%E1%BC%88%E1%BE%BC%E1%BE%BA%E1%BE%BB%E1%BE%8D%E1%BE%8B"),
		) { a, b -> AuthorizationHeaderBuilder.encodeParameterValue(a) shouldBe b }
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
