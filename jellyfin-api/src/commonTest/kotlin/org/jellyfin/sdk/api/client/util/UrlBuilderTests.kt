package org.jellyfin.sdk.api.client.util

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.jellyfin.sdk.api.client.exception.MissingPathVariableException
import org.jellyfin.sdk.model.api.ItemFields

class UrlBuilderTests : FunSpec({
	test("buildPath replaces values") {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"two" to "2",
			"three" to "3"
		)

		UrlBuilder.buildPath(path, parameters) shouldBe "test/1/2/three"
	}

	test("buildPath ignores path parameters") {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"two" to "2",
			"three" to "3"
		)

		UrlBuilder.buildPath(path, parameters, true) shouldBe "test/{one}/{two}/three"
	}

	test("buildPath replaces values not separated by a slash") {
		val path = "/test/{twe}{lve}/three"
		val parameters = mapOf(
			"twe" to "1",
			"lve" to "2",
			"three" to "3"
		)

		UrlBuilder.buildPath(path, parameters) shouldBe "test/12/three"
	}

	test("buildPath removes repeated slashes") {
		val path = "test/1//2////three"

		UrlBuilder.buildPath(path, emptyMap()) shouldBe "test/1/2/three"
	}

	test("buildPath fails when parameters are missing") {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1",
			"three" to "3"
		)

		shouldThrow<MissingPathVariableException> { UrlBuilder.buildPath(path, parameters) }
	}

	test("buildPath replaces integers") {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to 1,
			"two" to 2,
			"three" to 3
		)

		UrlBuilder.buildPath(path, parameters) shouldBe "test/1/2/three"
	}

	test("buildPath escapes values") {
		val path = "/test/{one}/{two}/three"
		val parameters = mapOf(
			"one" to "1/0",
			"two" to "value with whitespace",
			"three" to 3
		)

		UrlBuilder.buildPath(path, parameters) shouldBe "test/1%2F0/value+with+whitespace/three"
	}

	test("buildPath removes null values") {
		val path = "/test/{foo}/{bar}/{baz}"
		val parameters = mapOf(
			"foo" to "foo",
			"bar" to null,
			"baz" to "baz"
		)

		UrlBuilder.buildPath(path, parameters) shouldBe "test/foo/baz"
	}

	test("buildPath extension function works") {
		val path = "/test/{foo}/{bar}/{baz}"
		val parameters = mapOf(
			"foo" to "foo",
			"bar" to null,
			"baz" to "baz"
		)

		path.buildPath(parameters) shouldBe "test/foo/baz"
	}

	test("buildUrl with empty path template uses baseUrl") {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "") shouldBe baseUrl
	}

	test("buildUrl with slash only path template uses baseUrl") {
		val baseUrl = "https://demo.jellyfin.org/stable"
		UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "/") shouldBe "$baseUrl/"
	}

	test("buildUrl appends query parameters") {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"a" to "b",
			"c" to "2"
		)

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "test",
			queryParameters = parameters
		) shouldBe "${baseUrl}test?a=b&c=2"
	}

	test("buildUrl removes null values") {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"a" to "b",
			"c" to null,
			"d" to "2"
		)

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "test",
			queryParameters = parameters
		) shouldBe "${baseUrl}test?a=b&d=2"
	}

	test("buildUrl stringifies values") {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"one" to 1,
			"bool" to true,
			"str" to "str"
		)

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "test",
			queryParameters = parameters
		) shouldBe "${baseUrl}test?one=1&bool=true&str=str"
	}

	test("buildUrl stringifies enum values") {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"field1" to ItemFields.CHAPTERS,
			"field2" to ItemFields.DATE_CREATED,
		)

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "test",
			queryParameters = parameters
		) shouldBe "${baseUrl}test?field1=Chapters&field2=DateCreated"
	}

	test("buildUrl adds missing slashes") {
		val baseUrl = "https://demo.jellyfin.org/stable"

		UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "test") shouldBe "$baseUrl/test"
	}

	test("buildUrl removes unneeded slashes") {
		val baseUrl = "https://demo.jellyfin.org/stable///"

		UrlBuilder.buildUrl(baseUrl = baseUrl, pathTemplate = "test") shouldBe "https://demo.jellyfin.org/stable/test"
	}

	test("buildUrl uses path templates") {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"one" to 1,
			"two" to "2",
			"three" to '3'
		)

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "/test/{one}/{two}/{three}",
			pathParameters = parameters
		) shouldBe "${baseUrl}test/1/2/3"
	}

	test("buildUrl adds collections as separate query parameters") {
		val baseUrl = "https://demo.jellyfin.org/stable/"
		val parameters = mapOf(
			"example" to listOf("value1", "value2"),
			"example2" to "value3",
		)

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "/test",
			queryParameters = parameters,
		) shouldBe "${baseUrl}test?example=value1&example=value2&example2=value3"
	}

	test("buildUrl keeps query parameters in pathTemplate") {
		val baseUrl = "https://demo.jellyfin.org/stable/"

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "/example?foo=bar"
		) shouldBe "${baseUrl}example?foo=bar"
	}

	test("buildUrl prevents host from changing") {
		val baseUrl = "https://demo.jellyfin.org/stable/"

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "https://second.example"
		) shouldBe "${baseUrl}https:/second.example"

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "https://second.example",
			ignorePathParameters = true,
		) shouldBe "${baseUrl}https:/second.example"
	}

	test("buildUrl ignores path parameters when ignorePathParameters is set") {
		val baseUrl = "https://demo.jellyfin.org/stable/"

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "{foo}/{foo/{bar",
			ignorePathParameters = true,
		) shouldBe "${baseUrl}%7Bfoo%7D/%7Bfoo/%7Bbar"
	}

	test("buildUrl ignores path parameters when ignorePathParameters is set but has parameters") {
		val baseUrl = "https://demo.jellyfin.org/stable/"

		UrlBuilder.buildUrl(
			baseUrl = baseUrl,
			pathTemplate = "/{foo}/{foo/{bar",
			pathParameters = mapOf("foo" to "test", "bar" to "test2"),
			ignorePathParameters = true,
		) shouldBe "${baseUrl}%7Bfoo%7D/%7Bfoo/%7Bbar"
	}
})
