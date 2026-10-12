package org.jellyfin.sdk.model.internal

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toKotlinLocalDateTime
import kotlinx.serialization.json.Json
import org.jellyfin.sdk.model.serializer.DateTimeSerializer
import org.jellyfin.sdk.model.serializer.toUUID
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.toJavaUuid
import java.util.TimeZone as JavaTimeZone

// Compares the iOS implementations against the JVM ones so behaviour stays the same on all platforms
@OptIn(ExperimentalUuidApi::class)
class NativeParityTests : FunSpec({
	val zones = listOf("UTC", "Europe/Paris", "America/New_York", "Asia/Kolkata", "Pacific/Chatham", "UTC-03:30")

	val dateTimes = listOf(
		LocalDateTime.of(2021, 6, 30, 1, 33, 7),
		LocalDateTime.of(2021, 6, 30, 1, 33, 0),
		LocalDateTime.of(2021, 1, 1, 0, 0, 0),
		LocalDateTime.of(2024, 7, 23, 20, 25, 24, 420_000_000),
		LocalDateTime.of(2024, 7, 23, 20, 25, 24, 1),
		LocalDateTime.of(2024, 7, 23, 20, 25, 24, 123_456_789),
		LocalDateTime.of(2024, 12, 31, 23, 59, 59, 999_000_000),
		LocalDateTime.of(1999, 3, 1, 12, 0, 0),
		LocalDateTime.of(9999, 12, 31, 23, 59, 59),
	)

	fun <T> withZone(id: String, block: () -> T): T {
		val previous = JavaTimeZone.getDefault()
		JavaTimeZone.setDefault(JavaTimeZone.getTimeZone(ZoneId.of(id)))
		try {
			return block()
		} finally {
			JavaTimeZone.setDefault(previous)
		}
	}

	test("DateTime encoding matches java.time") {
		for (zone in zones) for (value in dateTimes) {
			val expected = withZone(zone) { Json.encodeToString(DateTimeSerializer(), value) }
			val actual = "\"${DateTimeCodec.encode(value.toKotlinLocalDateTime(), TimeZone.of(zone))}\""

			actual shouldBe expected
		}
	}

	test("DateTime decoding matches java.time") {
		val inputs = listOf(
			"2021-06-30T01:33:07Z",
			"2021-06-30T01:33:07.420Z",
			"2021-06-30T01:33:07+01:00",
			"2021-06-30T01:33:07.420+01:00",
			"2024-07-23T20:25:24.420Z",
			"2024-07-23T20:25:24.123456789-05:00",
			"2021-03-14T02:30:00-08:00",
			"2021-12-31T23:59:59+14:00",
		)

		for (zone in zones) for (input in inputs) {
			val expected = withZone(zone) { Json.decodeFromString(DateTimeSerializer(), "\"$input\"") }
			val actual = DateTimeCodec.decode(input, TimeZone.of(zone))

			actual shouldBe expected.toKotlinLocalDateTime()
		}
	}

	test("DateTime decoding falls back for unparsable values like the JVM implementation") {
		for (input in listOf("0001-01-01T00:00:00", "", "garbage", "2021-06-30")) {
			val jvm = withZone("UTC") { Json.decodeFromString(DateTimeSerializer(), "\"$input\"") }

			jvm shouldBe LocalDateTime.MIN
			DateTimeCodec.decode(input, TimeZone.UTC) shouldBe DateTimeCodec.MIN
		}
	}

	test("UUID parsing matches java.util.UUID") {
		val inputs = listOf(
			"713dc3fe-952b-438f-a70e-d35e4ef0525a",
			"713dc3fe952b438fa70ed35e4ef0525a",
			"be275f0bdb1471d86d2dbe5b8bb6918e",
			"70a37b76f99624a57725cbf8345e2b62",
			"00000000-0000-0000-0000-000000000000",
			"00000000000000000000000000000000",
		)

		for (input in inputs) {
			val expected: UUID = input.toUUID()
			parseUuid(input).toJavaUuid() shouldBe expected
			parseUuid(input).toString() shouldBe expected.toString()
		}
	}

	test("UUID parsing rejects what java.util.UUID rejects") {
		for (input in listOf("", "not-a-uuid", "713dc3fe952b438fa70ed35e4ef0525", "713dc3fe952b438fa70ed35e4ef0525aa")) {
			val jvmFails = runCatching { input.toUUID() }.isFailure
			val nativeFails = runCatching { parseUuid(input) }.isFailure

			nativeFails shouldBe jvmFails
		}
	}
})
