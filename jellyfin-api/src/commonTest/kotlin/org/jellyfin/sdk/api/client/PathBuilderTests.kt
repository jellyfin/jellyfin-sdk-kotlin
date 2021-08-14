package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.api.client.util.PathBuilder
import org.jellyfin.sdk.api.client.util.buildPath
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

public class PathBuilderTests {
	@Test
	public fun `buildPath replaces values`() {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"two" to "2",
			"three" to "3"
		)

		assertEquals("test/1/2/three", PathBuilder.buildPath(path, parameters))
	}

	@Test
	public fun `buildPath replaces values not separated by a slash`() {
		val path = "/test/{twe}{lve}/three"
		val parameters = mapOf(
			"twe" to "1",
			"lve" to "2",
			"three" to "3"
		)

		assertEquals("test/12/three", PathBuilder.buildPath(path, parameters))
	}

	@Test
	public fun `buildPath removes repeated slashes`() {
		val path = "test/1//2////three"

		assertEquals("test/1/2/three", PathBuilder.buildPath(path, emptyMap()))
	}

	@Test
	public fun `buildPath fails when parameters are missing`() {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"three" to "3"
		)

		assertFails { PathBuilder.buildPath(path, parameters) }
	}

	@Test
	public fun `buildPath replaces integers`() {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to 1,
			"two" to 2,
			"three" to 3
		)

		assertEquals("test/1/2/three", PathBuilder.buildPath(path, parameters))
	}

	@Test
	public fun `buildPath escapes values`() {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1/0",
			"two" to "value with whitespace",
			"three" to 3
		)

		assertEquals("test/1%2F0/value+with+whitespace/three", PathBuilder.buildPath(path, parameters))
	}

	@Test
	public fun `buildPath removes null values`() {
		val path = "/test/{foo}/{bar}/{baz}"
		val parameters = mapOf(
			"foo" to "foo",
			"bar" to null,
			"baz" to "baz"
		)

		assertEquals("test/foo/baz", PathBuilder.buildPath(path, parameters))
	}

	@Test
	public fun `buildPath extension function works`() {
		val path = "/test/{foo}/{bar}/{baz}"
		val parameters = mapOf(
			"foo" to "foo",
			"bar" to null,
			"baz" to "baz"
		)

		assertEquals("test/foo/baz",path.buildPath(parameters))
	}
}
