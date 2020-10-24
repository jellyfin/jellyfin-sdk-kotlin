package org.jellyfin.apiclient.interaction

import org.jellyfin.apiclient.model.DeviceInfo
import kotlin.test.Test
import kotlin.test.assertEquals

class AndroidDeviceTests {
	@Test
	fun `Removes special characters`() {
		assertEquals("s S9256GB_m", DeviceInfo("","Ã‘Ã¡Å¾Ã Å•'s S9+256GB..._\\m/\"").normalize().name)
	}

	@Test
	fun `Keeps normal characters`() {
		assertEquals("My Awesome Device 123", DeviceInfo("","My Awesome Device 123").normalize().name)
	}
}
