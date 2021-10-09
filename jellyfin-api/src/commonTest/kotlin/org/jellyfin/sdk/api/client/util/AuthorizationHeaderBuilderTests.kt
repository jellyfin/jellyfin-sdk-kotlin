package org.jellyfin.sdk.api.client.util

import kotlin.test.Test
import kotlin.test.assertEquals

class AuthorizationHeaderBuilderTests {
	@Test
	fun `encodeParameter removes special characters`() {
		assertEquals("test", AuthorizationHeaderBuilder.encodeParameterValue("""test"""))
		assertEquals("test+", AuthorizationHeaderBuilder.encodeParameterValue("""test+"""))
		assertEquals("'test'", AuthorizationHeaderBuilder.encodeParameterValue("""'test'"""))
		assertEquals("???", AuthorizationHeaderBuilder.encodeParameterValue("""今日は"""))
		assertEquals("??", AuthorizationHeaderBuilder.encodeParameterValue("""水母"""))
		assertEquals("??????", AuthorizationHeaderBuilder.encodeParameterValue("""ἈᾼᾺΆᾍᾋ"""))
	}

	@Test
	fun `buildParameter creates a valid header with access token`() {
		assertEquals("""key="val"""", AuthorizationHeaderBuilder.buildParameter("key", "val"))
		assertEquals("""one="two"""", AuthorizationHeaderBuilder.buildParameter("one", "two"))
		assertEquals("""1="2"""", AuthorizationHeaderBuilder.buildParameter("1", "2"))
	}

	@Test
	fun `buildAuthorizationHeader creates a valid header with access token`() {
		val value = AuthorizationHeaderBuilder.buildHeader("a", "b", "c", "d", "e")
		assertEquals("""MediaBrowser Client="a", Version="b", DeviceId="c", Device="d", Token="e"""",
			value)
	}

	@Test
	fun `buildAuthorizationHeader creates a valid header without access token`() {
		val value = AuthorizationHeaderBuilder.buildHeader("a", "b", "c", "d")
		assertEquals("""MediaBrowser Client="a", Version="b", DeviceId="c", Device="d"""", value)
	}
}
