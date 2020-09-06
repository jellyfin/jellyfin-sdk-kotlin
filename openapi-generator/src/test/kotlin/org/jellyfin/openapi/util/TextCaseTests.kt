package org.jellyfin.openapi.util

import kotlin.test.Test
import kotlin.test.assertEquals

class TextCaseTests {
	@Test
	fun `Companion fromPascalCase`() {
		assertEquals(listOf("Sample"), TextCase.fromPascalCase("Sample").parts)
		assertEquals(listOf("Sample", "String"), TextCase.fromPascalCase("SampleString").parts)
		assertEquals(listOf("UI", "Culture"), TextCase.fromPascalCase("UICulture").parts)
		assertEquals(listOf("Is", "Remote", "IP", "Filter", "Blacklist"), TextCase.fromPascalCase("IsRemoteIPFilterBlacklist").parts)
	}

	@Test
	fun toCamelCase() {
		assertEquals("word", TextCase(listOf("word")).toCamelCase())
		assertEquals("word", TextCase(listOf("Word")).toCamelCase())
		assertEquals("uiCulture", TextCase(listOf("UI", "Culture")).toCamelCase())
		assertEquals("isRemoteIpFilterBlacklist", TextCase(listOf("Is", "Remote", "IP", "Filter", "Blacklist")).toCamelCase())
	}

	@Test
	fun toPascalCase() {
		assertEquals("Word", TextCase(listOf("word")).toPascalCase())
		assertEquals("Word", TextCase(listOf("Word")).toPascalCase())
		assertEquals("UiCulture", TextCase(listOf("UI", "Culture")).toPascalCase())
		assertEquals("IsRemoteIpFilterBlacklist", TextCase(listOf("Is", "Remote", "IP", "Filter", "Blacklist")).toPascalCase())
	}

	@Test
	fun toScreamingSnakeCase() {
		assertEquals("WORD", TextCase(listOf("word")).toScreamingSnakeCase())
		assertEquals("WORD", TextCase(listOf("Word")).toScreamingSnakeCase())
		assertEquals("UI_CULTURE", TextCase(listOf("UI", "Culture")).toScreamingSnakeCase())
		assertEquals("IS_REMOTE_IP_FILTER_BLACKLIST", TextCase(listOf("Is", "Remote", "IP", "Filter", "Blacklist")).toScreamingSnakeCase())
	}
}
