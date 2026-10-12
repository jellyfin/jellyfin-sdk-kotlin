package org.jellyfin.sdk.model.internal

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class UuidCodecTests : FunSpec({
	test("Parses hyphenated UUIDs") {
		parseUuid("713dc3fe-952b-438f-a70e-d35e4ef0525a").toString() shouldBe "713dc3fe-952b-438f-a70e-d35e4ef0525a"
	}

	test("Parses UUIDs formatted without dashes") {
		parseUuid("713dc3fe952b438fa70ed35e4ef0525a").toString() shouldBe "713dc3fe-952b-438f-a70e-d35e4ef0525a"
		parseUuid("be275f0bdb1471d86d2dbe5b8bb6918e").toString() shouldBe "be275f0b-db14-71d8-6d2d-be5b8bb6918e"
	}

	test("Rejects invalid values") {
		runCatching { parseUuid("not-a-uuid") }.isFailure shouldBe true
		runCatching { parseUuid("") }.isFailure shouldBe true
	}
})
