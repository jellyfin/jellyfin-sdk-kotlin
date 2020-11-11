package org.jellyfin.apiclient.interaction

import org.jellyfin.apiclient.model.DeviceInfo
import kotlin.test.Test
import kotlin.test.assertEquals

class AndroidDeviceTests {
	@Test
	fun `Removes non-US-ASCII-printable characters`() {
		assertEquals("""'s S9+256GB..._\m/""", DeviceInfo("","""Ã‘Ã¡Å¾Ã Å•'s S9+256GB..._\m/""").normalize().name)
	}

	@Test
	fun `Keeps normal US-ASCII letters & numbers`() {
		assertEquals("My Awesome Device 123", DeviceInfo("","My Awesome Device 123").normalize().name)
	}

	@Test
	fun `Keeps normal US-ASCII punctuation & symbols`() {
		assertEquals("""\ @ ? * - + % & ~ . < = > /""", DeviceInfo("","""\ 吴 @ ඞ ? * - + % & × π ~ . < = > /""").normalize().name)
	}

	@Test
	fun `Trims & remove repetitive whitespace`() {
		assertEquals("""1 2 3 4 5""", DeviceInfo("",""" 1  2   3   4   5     """).normalize().name)
	}
}
