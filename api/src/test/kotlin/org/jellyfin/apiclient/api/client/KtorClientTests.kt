package org.jellyfin.apiclient.api.client

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class KtorClientTests {
	private fun createClient() = KtorClient("https://demo.jellyfin.org/stable/")

	@Test
	fun `createPath replaces values`() {
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
	fun `createPath removes repeated slashes`() {
		val instance = createClient()
		val path = "test/1//2////three"

		assertEquals("test/1/2/three", instance.createPath(path, emptyMap()))
	}

	@Test
	fun `createPath fails when parameters are missing`() {
		val instance = createClient()
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"three" to "3"
		)

		assertFails { instance.createPath(path, parameters) }
	}

	@Test
	fun `createPath replaces integers`() {
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
	fun `createPath escapes values`() {
		val instance = createClient()
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1/0",
			"two" to "value with whitespace",
			"three" to 3
		)

		assertEquals("test/1%2F0/value+with+whitespace/three", instance.createPath(path, parameters))
	}
}
