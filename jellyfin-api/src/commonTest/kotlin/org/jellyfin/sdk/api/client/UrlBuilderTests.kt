package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.api.client.util.UrlBuilder
import org.jellyfin.sdk.api.client.util.buildPath
import org.jellyfin.sdk.model.api.ItemFields
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class UrlBuilderTests {
	@Test
	fun `buildPath replaces values`() {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"two" to "2",
			"three" to "3"
		)

		assertEquals("test/1/2/three", UrlBuilder.buildPath(path, parameters))
	}

	@Test
	fun `buildPath replaces values not separated by a slash`() {
		val path = "/test/{twe}{lve}/three"
		val parameters = mapOf(
			"twe" to "1",
			"lve" to "2",
			"three" to "3"
		)

		assertEquals("test/12/three", UrlBuilder.buildPath(path, parameters))
	}

	@Test
	fun `buildPath removes repeated slashes`() {
		val path = "test/1//2////three"

		assertEquals("test/1/2/three", UrlBuilder.buildPath(path, emptyMap()))
	}

	@Test
	fun `buildPath fails when parameters are missing`() {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"three" to "3"
		)

		assertFails { UrlBuilder.buildPath(path, parameters) }
	}

	@Test
	fun `buildPath replaces integers`() {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to 1,
			"two" to 2,
			"three" to 3
		)

		assertEquals("test/1/2/three", UrlBuilder.buildPath(path, parameters))
	}

	@Test
	fun `buildPath escapes values`() {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1/0",
			"two" to "value with whitespace",
			"three" to 3
		)

		assertEquals("test/1%2F0/value+with+whitespace/three", UrlBuilder.buildPath(path, parameters))
	}

	@Test
	fun `buildPath removes null values`() {
		val path = "/test/{foo}/{bar}/{baz}"
		val parameters = mapOf(
			"foo" to "foo",
			"bar" to null,
			"baz" to "baz"
		)

		assertEquals("test/foo/baz", UrlBuilder.buildPath(path, parameters))
	}

	@Test
	fun `buildPath extension function works`() {
		val path = "/test/{foo}/{bar}/{baz}"
		val parameters = mapOf(
			"foo" to "foo",
			"bar" to null,
			"baz" to "baz"
		)

		assertEquals("test/foo/baz", path.buildPath(parameters))
	}

	@Test
	fun `buildUrl appends query parameters`() {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"a" to "b",
			"c" to "2"
		)

		assertEquals(
			baseUrl + "test?a=b&c=2",
			UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "test", queryParameters = parameters)
		)
	}

	@Test
	fun `buildUrl removes null values`() {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"a" to "b",
			"c" to null,
			"d" to "2"
		)

		assertEquals(
			baseUrl + "test?a=b&d=2",
			UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "test", queryParameters = parameters)
		)
	}

	@Test
	fun `buildUrl stringifies values`() {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"one" to 1,
			"bool" to true,
			"str" to "str"
		)

		assertEquals(
			baseUrl + "test?one=1&bool=true&str=str",
			UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "test", queryParameters = parameters)
		)
	}

	@Test
	fun `buildUrl stringifies enum values`() {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"field1" to ItemFields.CHAPTERS,
			"field2" to ItemFields.DATE_CREATED,
		)

		assertEquals(
			baseUrl + "test?field1=Chapters&field2=DateCreated",
			UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "test", queryParameters = parameters)
		)
	}

	@Test
	fun `buildUrl adds missing slashes`() {
		val baseUrl = "https://demo.jellyfin.org/stable"

		assertEquals(
			"$baseUrl/test",
			UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "test")
		)
	}

	@Test
	fun `buildUrl removes unneeded slashes`() {
		val baseUrl = "https://demo.jellyfin.org/stable///"

		assertEquals(
			"https://demo.jellyfin.org/stable/test",
			UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "test")
		)
	}

	@Test
	fun `buildUrl uses path templates`() {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"one" to 1,
			"two" to "2",
			"three" to '3'
		)

		assertEquals(
			baseUrl + "test/1/2/3",
			UrlBuilder.buildUrl(
				baseUrl = baseUrl,
				pathTemplate = "/test/{one}/{two}/{three}",
				pathParameters = parameters
			)
		)
	}
}
