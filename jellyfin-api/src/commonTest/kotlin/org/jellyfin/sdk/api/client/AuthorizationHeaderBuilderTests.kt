package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder.buildHeader
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder.buildParameter
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder.encodeParameterValue
import kotlin.test.Test
import kotlin.test.assertEquals

public class AuthorizationHeaderBuilderTests {
	@Test
	public fun `encodeParameter removes special characters`() {
		assertEquals("test", encodeParameterValue("""test"""))
		assertEquals("test+", encodeParameterValue("""test+"""))
		assertEquals("'test'", encodeParameterValue("""'test'"""))
		assertEquals("???", encodeParameterValue("""今日は"""))
		assertEquals("??", encodeParameterValue("""水母"""))
		assertEquals("??????", encodeParameterValue("""ἈᾼᾺΆᾍᾋ"""))
	}

	@Test
	public fun `buildParameter creates a valid header with access token`() {
		assertEquals("""key="val"""", buildParameter("key", "val"))
		assertEquals("""one="two"""", buildParameter("one", "two"))
		assertEquals("""1="2"""", buildParameter("1", "2"))
	}

	@Test
	public fun `buildAuthorizationHeader creates a valid header with access token`() {
		val value = buildHeader("a", "b", "c", "d", "e")
		assertEquals("""MediaBrowser Client="a", Version="b", DeviceId="c", Device="d", Token="e"""", value)
	}

	@Test
	public fun `buildAuthorizationHeader creates a valid header without access token`() {
		val value = buildHeader("a", "b", "c", "d")
		assertEquals("""MediaBrowser Client="a", Version="b", DeviceId="c", Device="d"""", value)
	}
}
