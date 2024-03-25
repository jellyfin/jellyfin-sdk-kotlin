package org.jellyfin.sdk.model.extensions

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.jellyfin.sdk.model.api.NameIdPair
import org.jellyfin.sdk.model.api.NameValuePair

class PairExtensionTests : FunSpec({
	test("NameIdPair to Pair") {
		NameIdPair(id = "id", name = "name").toPair() shouldBe Pair("id", "name")
		NameIdPair(id = "id", name = null).toPair() shouldBe Pair("id", null)
		NameIdPair(id = null, name = "name").toPair() shouldBe Pair(null, "name")
		NameIdPair(id = null, name = null).toPair() shouldBe Pair(null, null)
		NameIdPair("name", "id").toPair() shouldBe Pair("id", "name")
	}

	test("Pair to NameIdPair") {
		Pair("id", null).toNameIdPair() shouldBe NameIdPair(id = "id", name = null)
		Pair("id", "name").toNameIdPair() shouldBe NameIdPair(id = "id", name = "name")
		Pair(null, "name").toNameIdPair() shouldBe NameIdPair(id = null, name = "name")
		Pair(null, null).toNameIdPair() shouldBe NameIdPair(id = null, name = null)
		Pair("id", "name").toNameIdPair() shouldBe NameIdPair("name", "id")
	}

	test("NameValuePair to Pair") {
		NameValuePair(name = "name", value = "value").toPair() shouldBe Pair("name", "value")
		NameValuePair(name = "name", value = null).toPair() shouldBe Pair("name", null)
		NameValuePair(name = null, value = "value").toPair() shouldBe Pair(null, "value")
		NameValuePair(name = null, value = null).toPair() shouldBe Pair(null, null)
		NameValuePair("name", "value").toPair() shouldBe Pair("name", "value")
	}

	test("Pair to NameValuePair") {
		Pair("name", "value").toNameValuePair() shouldBe NameValuePair(name = "name", value = "value")
		Pair("name", null).toNameValuePair() shouldBe NameValuePair(name = "name", value = null)
		Pair(null, "value").toNameValuePair() shouldBe NameValuePair(name = null, value = "value")
		Pair(null, null).toNameValuePair() shouldBe NameValuePair(name = null, value = null)
		Pair("name", "value").toNameValuePair() shouldBe NameValuePair("name", "value")
	}
})
