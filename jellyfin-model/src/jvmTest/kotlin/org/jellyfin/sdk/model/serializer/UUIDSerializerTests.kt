package org.jellyfin.sdk.model.serializer

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json
import java.util.UUID

class UUIDSerializerTests : FunSpec({
	test("Parses correctly formatted UUIDs") {
		val instance = UUIDSerializer()

		Json.decodeFromString(
			instance,
			"\"713dc3fe-952b-438f-a70e-d35e4ef0525a\""
		) shouldBe UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a")

		Json.decodeFromString(
			instance,
			"\"713dc3fe-952b-438f-a70e-d35e4ef0525a\""
		) shouldBe UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a")
	}

	test("Parses UUIDs formatted without dashes") {
		val instance = UUIDSerializer()


		Json.decodeFromString(
			instance,
			"\"713dc3fe952b438fa70ed35e4ef0525a\""
		) shouldBe UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a")

		Json.decodeFromString(
			instance,
			"\"713dc3fe952b438fa70ed35e4ef0525a\""
		) shouldBe UUID.fromString("713dc3fe-952b-438f-a70e-d35e4ef0525a")

		Json.decodeFromString(
			instance,
			"\"be275f0bdb1471d86d2dbe5b8bb6918e\""
		) shouldBe UUID.fromString("be275f0b-db14-71d8-6d2d-be5b8bb6918e")

		Json.decodeFromString(
			instance,
			"\"70a37b76f99624a57725cbf8345e2b62\""
		) shouldBe UUID.fromString("70a37b76-f996-24a5-7725-cbf8345e2b62")
	}
})
