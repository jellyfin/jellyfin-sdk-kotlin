package org.jellyfin.apiclient.model.serializer

import kotlinx.serialization.json.Json
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

public class UUIDSerializerTests {
	@Test
	public fun `Parses correctly formatted UUIDs`() {
		val instance = UUIDSerializer()

		assertEquals(
			UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"),
			Json.decodeFromString(instance, "\"713dc3fe-952b-438f-a70e-d35e4ef0525a\"")
		)
		assertEquals(
			UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"),
			Json.decodeFromString(instance, "\"713dc3fe-952b-438f-a70e-d35e4ef0525a\"")
		)
	}

	@Test
	public fun `Parses UUIDs formatted without dashes`() {
		val instance = UUIDSerializer()

		assertEquals(
			UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"),
			Json.decodeFromString(instance, "\"713dc3fe952b438fa70ed35e4ef0525a\"")
		)
		assertEquals(
			UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"),
			Json.decodeFromString(instance, "\"713dc3fe952b438fa70ed35e4ef0525a\"")
		)
	}
}
