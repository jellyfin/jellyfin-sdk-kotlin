package org.jellyfin.apiclient.api.client.adapter

import com.google.gson.stream.JsonReader
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class UUIDTypeAdapterTests {
	private fun String.asJsonReader() = JsonReader("\"$this\"".reader())

	@Test
	fun `Parses correctly formatted UUIDs`() {
		val instance = UUIDTypeAdapter()

		assertEquals(UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"), instance.read("713dc3fe-952b-438f-a70e-d35e4ef0525a".asJsonReader()))
		assertEquals(UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"), instance.read("713dc3fe-952b-438f-a70e-d35e4ef0525a".asJsonReader()))
	}

	@Test
	fun `Parses UUIDs formatted without dashes`() {
		val instance = UUIDTypeAdapter()

		assertEquals(UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"), instance.read("713dc3fe952b438fa70ed35e4ef0525a".asJsonReader()))
		assertEquals(UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"), instance.read("713dc3fe952b438fa70ed35e4ef0525a".asJsonReader()))
	}
}
