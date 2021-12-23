package org.jellyfin.sdk.model.serializer

import kotlinx.serialization.json.Json
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

class UUIDSerializerTests {
	@Test
	fun `Parses correctly formatted UUIDs`() {
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
	fun `Parses UUIDs formatted without dashes`() {
		val instance = UUIDSerializer()

		assertEquals(
			UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"),
			Json.decodeFromString(instance, "\"713dc3fe952b438fa70ed35e4ef0525a\"")
		)
		assertEquals(
			UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a"),
			Json.decodeFromString(instance, "\"713dc3fe952b438fa70ed35e4ef0525a\"")
		)
		assertEquals(
			UUID.fromString("be275f0b-db14-71d8-6d2d-be5b8bb6918e"),
			Json.decodeFromString(instance, "\"be275f0bdb1471d86d2dbe5b8bb6918e\"")
		)
		assertEquals(
			UUID.fromString("70a37b76-f996-24a5-7725-cbf8345e2b62"),
			Json.decodeFromString(instance, "\"70a37b76f99624a57725cbf8345e2b62\"")
		)
	}
}
