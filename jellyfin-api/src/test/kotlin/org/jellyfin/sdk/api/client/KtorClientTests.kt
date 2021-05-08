package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

public class KtorClientTests {
	private fun createClient() = KtorClient(
		baseUrl = "https://demo.jellyfin.org/stable/",
		accessToken = null,
		clientInfo = ClientInfo("Test", "0"),
		deviceInfo = DeviceInfo("test", "test"),
		httpClientOptions = HttpClientOptions()
	)

	@Test
	public fun `createPath replaces values`() {
		val instance = createClient()
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"two" to "2",
			"three" to "3"
		)

		assertEquals("test/1/2/three", instance.createPath(path, parameters))
	}

	@Test
	public fun `createPath replaces values not separated by a slash`() {
		val instance = createClient()
		val path = "/test/{twe}{lve}/three"
		val parameters = mapOf(
			"twe" to "1",
			"lve" to "2",
			"three" to "3"
		)

		assertEquals("test/12/three", instance.createPath(path, parameters))
	}

	@Test
	public fun `createPath removes repeated slashes`() {
		val instance = createClient()
		val path = "test/1//2////three"

		assertEquals("test/1/2/three", instance.createPath(path, emptyMap()))
	}

	@Test
	public fun `createPath fails when parameters are missing`() {
		val instance = createClient()
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"three" to "3"
		)

		assertFails { instance.createPath(path, parameters) }
	}

	@Test
	public fun `createPath replaces integers`() {
		val instance = createClient()
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to 1,
			"two" to 2,
			"three" to 3
		)

		assertEquals("test/1/2/three", instance.createPath(path, parameters))
	}

	@Test
	public fun `createPath escapes values`() {
		val instance = createClient()
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1/0",
			"two" to "value with whitespace",
			"three" to 3
		)

		assertEquals("test/1%2F0/value+with+whitespace/three", instance.createPath(path, parameters))
	}

	@Test
	public fun `createPath removes null values`() {
		val instance = createClient()
		val path = "/test/{foo}/{bar}/{baz}"
		val parameters = mapOf(
			"foo" to "foo",
			"bar" to null,
			"baz" to "baz"
		)

		assertEquals("test/foo/baz", instance.createPath(path, parameters))
	}
}
