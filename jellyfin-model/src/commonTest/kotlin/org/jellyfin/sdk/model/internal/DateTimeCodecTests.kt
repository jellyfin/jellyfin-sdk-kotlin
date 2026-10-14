package org.jellyfin.sdk.model.internal

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone

class DateTimeCodecTests : FunSpec({
	val utc = TimeZone.UTC
	val plusOne = TimeZone.of("UTC+01:00")

	test("Encodes dates and times (UTC)") {
		DateTimeCodec.encode(LocalDateTime(2021, 6, 30, 1, 33, 7), utc) shouldBe "2021-06-30T01:33:07Z"
	}

	test("Encodes dates and times (Offset)") {
		DateTimeCodec.encode(LocalDateTime(2021, 6, 30, 1, 33, 7), plusOne) shouldBe "2021-06-30T01:33:07+01:00"
	}

	test("Encodes seconds when zero") {
		DateTimeCodec.encode(LocalDateTime(2021, 6, 30, 1, 33, 0), utc) shouldBe "2021-06-30T01:33:00Z"
	}

	test("Encodes fractions without trailing zeros") {
		DateTimeCodec.encode(LocalDateTime(2021, 6, 30, 1, 33, 7, 420_000_000), utc) shouldBe "2021-06-30T01:33:07.42Z"
		DateTimeCodec.encode(LocalDateTime(2021, 6, 30, 1, 33, 7, 1), utc) shouldBe "2021-06-30T01:33:07.000000001Z"
	}

	test("Parses minimum value") {
		DateTimeCodec.decode("0001-01-01T00:00:00", utc) shouldBe DateTimeCodec.MIN
	}

	test("Parses dates and times (UTC)") {
		DateTimeCodec.decode("2021-06-30T01:33:07Z", utc) shouldBe LocalDateTime(2021, 6, 30, 1, 33, 7)
		DateTimeCodec.decode("2021-06-30T01:33:07.420Z", utc) shouldBe LocalDateTime(2021, 6, 30, 1, 33, 7, 420_000_000)
	}

	test("Parses dates and times (Offset)") {
		DateTimeCodec.decode("2021-06-30T01:33:07+01:00", plusOne) shouldBe LocalDateTime(2021, 6, 30, 1, 33, 7)
		DateTimeCodec.decode("2021-06-30T01:33:07.420+01:00", plusOne) shouldBe
			LocalDateTime(2021, 6, 30, 1, 33, 7, 420_000_000)
		DateTimeCodec.decode("2024-07-23T20:25:24.420Z", plusOne) shouldBe
			LocalDateTime(2024, 7, 23, 21, 25, 24, 420_000_000)
	}
})
