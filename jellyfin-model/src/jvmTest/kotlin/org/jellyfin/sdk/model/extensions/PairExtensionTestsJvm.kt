package org.jellyfin.sdk.model.extensions

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.jellyfin.sdk.model.api.NameGuidPair
import java.util.UUID

class PairExtensionTestsJvm : FunSpec({
	test("NameGuidPair to Pair") {
		val uuid = UUID.randomUUID()

		NameGuidPair(id = uuid, name = "name").toPair() shouldBe Pair(uuid, "name")
		NameGuidPair(id = uuid, name = null).toPair() shouldBe Pair(uuid, null)
		NameGuidPair("name", uuid).toPair() shouldBe Pair(uuid, "name")
	}

	test("Pair to NameGuidPair") {
		val uuid = UUID.randomUUID()

		Pair(uuid, "name").toNameGuidPair() shouldBe NameGuidPair(id = uuid, name = "name")
		Pair(uuid, null).toNameGuidPair() shouldBe NameGuidPair(id = uuid, name = null)
		Pair(uuid, "name").toNameGuidPair() shouldBe NameGuidPair("name", uuid)
	}
})
