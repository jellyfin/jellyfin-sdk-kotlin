package org.jellyfin.sdk.api.client.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AuthorizationHeaderBuilderTests : FunSpec({
	test("encodeParameter removes special characters") {
		AuthorizationHeaderBuilder.encodeParameterValue("""test""") shouldBe "test"
		AuthorizationHeaderBuilder.encodeParameterValue("""test+""") shouldBe "test+"
		AuthorizationHeaderBuilder.encodeParameterValue("""'test'""") shouldBe "'test'"
		AuthorizationHeaderBuilder.encodeParameterValue("""今日は""") shouldBe "???"
		AuthorizationHeaderBuilder.encodeParameterValue("""水母""") shouldBe "??"
		AuthorizationHeaderBuilder.encodeParameterValue("""ἈᾼᾺΆᾍᾋ""") shouldBe "??????"
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
