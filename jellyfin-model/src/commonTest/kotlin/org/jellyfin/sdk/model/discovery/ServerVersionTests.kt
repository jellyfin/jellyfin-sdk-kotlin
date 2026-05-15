package org.jellyfin.sdk.model.discovery

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import org.jellyfin.sdk.model.ServerVersion

class ServerVersionTests : FunSpec({
	test("Parses correct version strings") {
		ServerVersion.fromString("10.6.4") shouldBe ServerVersion(10, 6, 4, null)
		ServerVersion.fromString("10.7.0") shouldBe ServerVersion(10, 7, 0, null)
		ServerVersion.fromString("1.2.3") shouldBe ServerVersion(1, 2, 3, null)
		ServerVersion.fromString("111.222.333") shouldBe ServerVersion(111, 222, 333, null)
		ServerVersion.fromString("10.7.0") shouldBe ServerVersion(10, 7, 0, null)
		ServerVersion.fromString("10.7.0.0") shouldBe ServerVersion(10, 7, 0, 0)
		ServerVersion.fromString("10.7.0.12345") shouldBe ServerVersion(10, 7, 0, 12345)
	}

	test("Returns null for incorrect version strings") {
		ServerVersion.fromString("10.6.4-2") shouldBe null
		ServerVersion.fromString("10.6.4.2.0") shouldBe null
		ServerVersion.fromString("10.7") shouldBe null
		ServerVersion.fromString("10") shouldBe null
		ServerVersion.fromString("test") shouldBe null
		ServerVersion.fromString("11.0.0-rc.1") shouldBe null
	}

	test("Compares to other versions") {
		ServerVersion(10, 6, 0) shouldBeEqualComparingTo ServerVersion(10, 6, 0)

		ServerVersion(10, 6, 0) shouldBeLessThan ServerVersion(10, 6, 1)
		ServerVersion(10, 6, 0) shouldBeLessThan ServerVersion(10, 7, 0)
		ServerVersion(10, 6, 0) shouldBeLessThan ServerVersion(11, 6, 0)

		ServerVersion(1, 2, 3) shouldBeGreaterThan ServerVersion(0, 0, 0)
		ServerVersion(1, 7, 0, 1) shouldBeGreaterThan ServerVersion(1, 7, 0)

		run { ServerVersion.fromString("10.8.0")!! < ServerVersion(10, 8, 0, 0) } shouldBe false
		run { ServerVersion.fromString("10.8.0")!! < ServerVersion.fromString("10.8.0")!! } shouldBe false
	}

	test("Converts to string") {
		ServerVersion(1, 0, 0).toString() shouldBe "1.0.0"
		ServerVersion(10, 6, 4).toString() shouldBe "10.6.4"
		ServerVersion(10, 11, 8, 2).toString() shouldBe "10.11.8.2"
		ServerVersion(10, 11, 8).toString() shouldBe "10.11.8"

		ServerVersion(12, 0, 0).toString() shouldBe "12.0"
		ServerVersion(12, 0, 0, null).toString() shouldBe "12.0"
		ServerVersion(12, 0, 0, 0).toString() shouldBe "12.0.0.0"
	}

	test("Converts to string with parts") {
		val version = ServerVersion(10, 6, 4, 1)
		version.toString(1) shouldBe "10"
		version.toString(2) shouldBe "10.6"
		version.toString(3) shouldBe "10.6.4"
		version.toString(4) shouldBe "10.6.4.1"

		ServerVersion(10, 6, 4, null).toString(4) shouldBe "10.6.4.0"
	}
})
