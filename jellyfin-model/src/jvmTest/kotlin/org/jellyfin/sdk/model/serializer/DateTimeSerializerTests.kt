package org.jellyfin.sdk.model.serializer

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.ZoneId

class DateTimeSerializerTests : FunSpec({
	test("Encodes dates and times (UTC)") {
		val instance = DateTimeSerializer(ZoneId.of("UTC"))

		Json.encodeToString(
			instance,
			LocalDateTime.of(2021, 6, 30, 1, 33, 7)
		) shouldBe """"2021-06-30T01:33:07Z""""
	}

	test("Encodes dates and times (Offset)") {
		val instance = DateTimeSerializer(ZoneId.of("UTC+01:00"))

		Json.encodeToString(
			instance,
			LocalDateTime.of(2021, 6, 30, 1, 33, 7)
		) shouldBe """"2021-06-30T01:33:07+01:00""""
	}

	test("Parses minimum value") {
		val instance = DateTimeSerializer(ZoneId.of("UTC"))

		Json.decodeFromString(instance, """"0001-01-01T00:00:00"""") shouldBe LocalDateTime.MIN
	}

	test("Parses dates and times (UTC)") {
		val instance = DateTimeSerializer(ZoneId.of("UTC"))

		Json.decodeFromString(
			instance,
			""""2021-06-30T01:33:07Z""""
		) shouldBe LocalDateTime.of(2021, 6, 30, 1, 33, 7)

		Json.decodeFromString(
			instance,
			""""2021-06-30T01:33:07.420Z""""
		) shouldBe LocalDateTime.of(2021, 6, 30, 1, 33, 7, 420000000)
	}

	test("Parses dates and times (Offset)") {
		val instance = DateTimeSerializer(ZoneId.of("UTC+01:00"))

		Json.decodeFromString(
			instance,
			""""2021-06-30T01:33:07+01:00""""
		) shouldBe LocalDateTime.of(2021, 6, 30, 1, 33, 7)

		Json.decodeFromString(
			instance,
			""""2021-06-30T01:33:07.420+01:00""""
		) shouldBe LocalDateTime.of(2021, 6, 30, 1, 33, 7, 420000000)
	}
})
