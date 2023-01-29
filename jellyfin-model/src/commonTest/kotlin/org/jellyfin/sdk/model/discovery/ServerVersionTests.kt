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
})
