package org.jellyfin.apiclient.discovery

import org.jellyfin.apiclient.Jellyfin
import kotlin.test.Test

public class DiscoveryServiceTests {
	private fun getInstance() = DiscoveryService(Jellyfin {}, MockDiscoveryBroadcastAddressesProvider())

	@Test
	public fun `getAddressCandidates prefers https`() {
		val instance = getInstance()

		assert(instance.getAddressCandidates("demo.jellyfin.org:433/stable/").first().startsWith("https://"))
		assert(instance.getAddressCandidates("http://demo.jellyfin.org:433/stable/").first().startsWith("https://"))
	}

	@Test
	public fun `getAddressCandidates adds Jellyfin ports`() {
		val instance = getInstance()

		assert(instance.getAddressCandidates("localhost").contains("http://localhost:8096"))
		assert(instance.getAddressCandidates("localhost").contains("https://localhost:8920"))
	}

	@Test
	public fun `getAddressCandidates accepts hostnames`() {
		val instance = getInstance()

		assert(instance.getAddressCandidates("localhost").contains("http://localhost"))
		assert(instance.getAddressCandidates("jellyfin.local").contains("http://jellyfin.local"))
		assert(instance.getAddressCandidates("demo.jellyfin.org").contains("http://demo.jellyfin.org"))
		assert(instance.getAddressCandidates("jellyfin.local:8096").contains("http://jellyfin.local:8096"))
	}

	@Test
	public fun `getAddressCandidates accepts ipv4 addresses`() {
		val instance = getInstance()

		assert(instance.getAddressCandidates("127.0.0.1").contains("http://127.0.0.1"))
		assert(instance.getAddressCandidates("192.168.0.1").contains("http://192.168.0.1"))
		assert(instance.getAddressCandidates("127.0.0.1:8096").contains("http://127.0.0.1:8096"))
	}

	@Test
	public fun `getAddressCandidates accepts ipv6 addresses`() {
		val instance = getInstance()

		assert(instance.getAddressCandidates("[::1]").contains("http://[::1]"))
		assert(instance.getAddressCandidates("[0:0:0:0:0:0:0:1]").contains("http://[0:0:0:0:0:0:0:1]"))
		assert(instance.getAddressCandidates("[::1]:8096").contains("http://[::1]:8096"))
		assert(instance.getAddressCandidates("[0:0:0:0:0:0:0:1]:8096").contains("http://[0:0:0:0:0:0:0:1]:8096"))
	}

	@Test
	public fun `getAddressCandidates fails on bad input`() {
		val instance = getInstance()

		assert(instance.getAddressCandidates("::").isEmpty())
	}
}
