package org.jellyfin.openapi.builder.api

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ApiNameBuilderTests {
	@Test
	fun `Names end with Api`() {
		val instance = ApiNameBuilder()

		assertTrue { instance.build("test").endsWith("Api") }
		assertTrue { instance.build("api").endsWith("Api") }
		assertTrue { instance.build("testApi").endsWith("Api") }
		assertTrue { instance.build("fourSeemsLikeEnoughAsserts").endsWith("Api") }
	}

	@Test
	fun `camelCase becomes PascalCase`() {
		val instance = ApiNameBuilder()

		assertEquals("MiscApi", instance.build("misc"))
		assertEquals("LibraryApi", instance.build("library"))
		assertEquals("MediaInfoApi", instance.build("mediaInfo"))
		assertEquals("UserApi", instance.build("user"))
	}

	@Test
	fun `PascalCase stays PascalCase`() {
		val instance = ApiNameBuilder()

		assertEquals("MiscApi", instance.build("Misc"))
		assertEquals("LibraryApi", instance.build("Library"))
		assertEquals("MediaInfoApi", instance.build("MediaInfo"))
		assertEquals("UserApi", instance.build("User"))
	}
}
