package org.jellyfin.sdk.api.client.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ApiSerializerTests : FunSpec({
	test("ApiSerializer can decode JSON") {
		val content = """{"test": true}"""
		val result = ApiSerializer.decodeResponseBody<Map<String, Boolean>>(content)

		result["test"] shouldBe true
	}

	test("ApiSerializer can decode primitive") {
		val content = """42"""
		val result = ApiSerializer.decodeResponseBody<Int>(content)

		result shouldBe 42
	}
})
