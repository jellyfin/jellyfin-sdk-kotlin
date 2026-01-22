package org.jellyfin.sdk.model.serializer

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

class DateTimeSerializerTests : FunSpec({
	test("Encodes dates and times (UTC)") {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")))
		val instance = DateTimeSerializer()

		Json.encodeToString(
			instance,
			LocalDateTime.of(2021, 6, 30, 1, 33, 7)
		) shouldBe """"2021-06-30T01:33:07Z""""
	}

	test("Encodes dates and times (Offset)") {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC+01:00")))
		val instance = DateTimeSerializer()

		Json.encodeToString(
			instance,
			LocalDateTime.of(2021, 6, 30, 1, 33, 7)
		) shouldBe """"2021-06-30T01:33:07+01:00""""
	}

	test("Parses minimum value") {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")))
		val instance = DateTimeSerializer()

		Json.decodeFromString(instance, """"0001-01-01T00:00:00"""") shouldBe LocalDateTime.MIN
	}

	test("Parses dates and times (UTC)") {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")))
		val instance = DateTimeSerializer()

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
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC+01:00")))
		val instance = DateTimeSerializer()

		Json.decodeFromString(
			instance,
			""""2021-06-30T01:33:07+01:00""""
		) shouldBe LocalDateTime.of(2021, 6, 30, 1, 33, 7)

		Json.decodeFromString(
			instance,
			""""2021-06-30T01:33:07.420+01:00""""
		) shouldBe LocalDateTime.of(2021, 6, 30, 1, 33, 7, 420000000)

		Json.decodeFromString(
			instance,
			""""2024-07-23T20:25:24.420Z""""
		) shouldBe LocalDateTime.of(2024, 7, 23, 21, 25, 24, 420000000)
	}
})
