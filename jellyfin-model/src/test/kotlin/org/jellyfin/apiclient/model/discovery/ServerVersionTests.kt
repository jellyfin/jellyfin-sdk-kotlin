package org.jellyfin.apiclient.model.discovery

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

public class ServerVersionTests {
	@Test
	public fun `Parses correct version strings`() {
		assertEquals(ServerVersion.fromString("10.6.4"), ServerVersion(10, 6, 4))
		assertEquals(ServerVersion.fromString("10.7.0"), ServerVersion(10, 7, 0))
		assertEquals(ServerVersion.fromString("1.2.3"), ServerVersion(1, 2, 3))
		assertEquals(ServerVersion.fromString("111.222.333"), ServerVersion(111, 222, 333))
	}

	@Test
	public fun `Returns null for incorrect version strings`() {
		assertNull(ServerVersion.fromString("10.6.4-2"))
		assertNull(ServerVersion.fromString("10.6.4.2"))
		assertNull(ServerVersion.fromString("10.7"))
		assertNull(ServerVersion.fromString("10"))
		assertNull(ServerVersion.fromString("test"))
		assertNull(ServerVersion.fromString("11.0.0-rc.1"))
	}

	@Test
	public fun `Compares to other versions`() {
		assertTrue { ServerVersion(10, 6, 0) == ServerVersion(10, 6, 0) }

		assertTrue { ServerVersion(10, 6, 0) < ServerVersion(10, 6, 1) }
		assertTrue { ServerVersion(10, 6, 0) < ServerVersion(10, 7, 0) }
		assertTrue { ServerVersion(10, 6, 0) < ServerVersion(11, 6, 0) }

		assertTrue { ServerVersion(1, 2, 3) > ServerVersion(0, 0, 0) }
	}
}
