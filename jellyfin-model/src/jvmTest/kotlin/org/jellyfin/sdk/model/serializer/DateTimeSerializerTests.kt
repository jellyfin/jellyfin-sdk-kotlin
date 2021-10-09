package org.jellyfin.sdk.model.serializer

import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeSerializerTests {
	@Test
	fun `Encodes dates and times (UTC)`() {
		val instance = DateTimeSerializer(ZoneId.of("UTC"))

		assertEquals(
			""""2021-06-30T01:33:07Z"""",
			Json.encodeToString(instance, LocalDateTime.of(2021, 6, 30, 1, 33, 7))
		)
	}

	@Test
	fun `Encodes dates and times (Offset)`() {
		val instance = DateTimeSerializer(ZoneId.of("UTC+01:00"))

		assertEquals(
			""""2021-06-30T01:33:07+01:00"""",
			Json.encodeToString(instance, LocalDateTime.of(2021, 6, 30, 1, 33, 7))
		)
	}

	@Test
	fun `Parses minimum value`() {
		val instance = DateTimeSerializer(ZoneId.of("UTC"))

		assertEquals(LocalDateTime.MIN, Json.decodeFromString(instance, """"0001-01-01T00:00:00""""))
	}

	@Test
	fun `Parses dates and times (UTC)`() {
		val instance = DateTimeSerializer(ZoneId.of("UTC"))

		assertEquals(
			LocalDateTime.of(2021, 6, 30, 1, 33, 7),
			Json.decodeFromString(instance, """"2021-06-30T01:33:07Z"""")
		)
		assertEquals(
			LocalDateTime.of(2021, 6, 30, 1, 33, 7, 420000000),
			Json.decodeFromString(instance, """"2021-06-30T01:33:07.420Z"""")
		)
	}

	@Test
	fun `Parses dates and times (Offset)`() {
		val instance = DateTimeSerializer(ZoneId.of("UTC+01:00"))

		assertEquals(
			LocalDateTime.of(2021, 6, 30, 1, 33, 7),
			Json.decodeFromString(instance, """"2021-06-30T01:33:07+01:00"""")
		)
		assertEquals(
			LocalDateTime.of(2021, 6, 30, 1, 33, 7, 420000000),
			Json.decodeFromString(instance, """"2021-06-30T01:33:07.420+01:00"""")
		)
	}
}
