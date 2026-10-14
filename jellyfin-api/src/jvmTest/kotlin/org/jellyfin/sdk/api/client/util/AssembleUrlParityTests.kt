package org.jellyfin.sdk.api.client.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe

class AssembleUrlParityTests : FunSpec({
	val bases = listOf(
		"http://localhost:8096",
		"http://localhost:8096/",
		"https://demo.jellyfin.org/stable",
		"https://demo.jellyfin.org/stable/",
		"https://jellyfin.example.com:8920/jf",
		"http://192.168.1.10:8096",
		"https://example.com:443",
		"http://example.com:80/base/path",
		"http://[::1]:8096",
	)

	val paths = listOf(
		"",
		"System/Info/Public",
		"Items/713dc3fe952b438fa70ed35e4ef0525a/Images/Primary",
		"/Videos/1/stream.mkv",
		"Items/1%2F0/value+with+whitespace",
		"Users/Me",
		"{foo}/{foo/{bar",
		"path with spaces/and\"quotes\"/<tag>/pipe|caret^tick`",
		"unicode/caf\u00e9/\u30c6\u30b9\u30c8",
		"a/./b/../c",
		"a/%2e%2e/b",
		"percent/100%/%zz/%41",
		"trailing/dot/..",
	)

	val queries = listOf(
		emptyList(),
		listOf("limit" to "10"),
		listOf("fields" to "Overview", "fields" to "Genres", "recursive" to "true"),
		listOf("searchTerm" to "hello world", "q" to "a&b=c"),
		listOf("name" to "café テスト", "emoji" to "🎬"),
		listOf("date" to "2021-06-30T01:33:07+01:00"),
		listOf("plus" to "1+1", "pct" to "100%", "slash" to "a/b", "special" to "!'()*;:@,\$[]{}|^`~<>\"#?"),
		listOf("empty" to ""),
	)

	test("OkHttp and ktor implementations produce the same urls") {
		for (base in bases) for (path in paths) for (query in queries) for (pathQuery in listOf(null, "a=b&c=d")) {
			val expected = assembleUrl(base, path, pathQuery, query)
			val actual = assembleUrlKtor(base, path, pathQuery, query)

			withClue("base=$base path=$path pathQuery=$pathQuery query=$query") {
				actual shouldBe expected
			}
		}
	}
})
