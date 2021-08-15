package org.jellyfin.sdk.discovery

import org.jellyfin.sdk.Jellyfin
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertTrue

public class DiscoveryServiceTests {
	private fun getInstance() = DiscoveryService(Jellyfin {})

	@Test
	public fun `getAddressCandidates prefers https`() {
		val instance = getInstance()

		assertTrue(instance.getAddressCandidates("demo.jellyfin.org:433/stable/").first().startsWith("https://"))
		assertTrue(instance.getAddressCandidates("http://demo.jellyfin.org:433/stable/").first().startsWith("https://"))
	}

	@Test
	public fun `getAddressCandidates adds Jellyfin ports`() {
		val instance = getInstance()

		assertContains(instance.getAddressCandidates("localhost"), "http://localhost:8096")
		assertContains(instance.getAddressCandidates("localhost"), "https://localhost:8920")
	}

	@Test
	public fun `getAddressCandidates accepts hostnames`() {
		val instance = getInstance()

		assertContains(instance.getAddressCandidates("localhost"), "http://localhost")
		assertContains(instance.getAddressCandidates("jellyfin.local"), "http://jellyfin.local")
		assertContains(instance.getAddressCandidates("demo.jellyfin.org"), "http://demo.jellyfin.org")
		assertContains(instance.getAddressCandidates("jellyfin.local:8096"), "http://jellyfin.local:8096")
	}

	@Test
	public fun `getAddressCandidates accepts ipv4 addresses`() {
		val instance = getInstance()

		assertContains(instance.getAddressCandidates("127.0.0.1"), "http://127.0.0.1")
		assertContains(instance.getAddressCandidates("192.168.0.1"), "http://192.168.0.1")
		assertContains(instance.getAddressCandidates("127.0.0.1:8096"), "http://127.0.0.1:8096")
	}

	@Test
	public fun `getAddressCandidates accepts ipv6 addresses`() {
		val instance = getInstance()

		assertContains(instance.getAddressCandidates("[::1]"), "http://[::1]")
		assertContains(instance.getAddressCandidates("[0:0:0:0:0:0:0:1]"), "http://[0:0:0:0:0:0:0:1]")
		assertContains(instance.getAddressCandidates("[::1]:8096"), "http://[::1]:8096")
		assertContains(instance.getAddressCandidates("[0:0:0:0:0:0:0:1]:8096"), "http://[0:0:0:0:0:0:0:1]:8096")
	}

	@Test
	public fun `getAddressCandidates fails on bad input`() {
		val instance = getInstance()

		assertTrue(instance.getAddressCandidates("::").isEmpty())
	}
}
