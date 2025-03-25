package org.jellyfin.sdk.api.client.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.jellyfin.sdk.api.client.RawResponse

private fun createResponse(content: ByteArray) = RawResponse(content, 200, emptyMap())

class RawResponseTests : FunSpec({
	test("ApiSerializer can decode Unit") {
		val result = createResponse(ByteArray(0)).createContent<Unit>()

		result.shouldBeTypeOf<Unit>()
	}

	test("ApiSerializer can decode ByteReadChannel") {
		val content = byteArrayOf(0x1, 0x0, 0x1)
		val result = createResponse(content).createContent<ByteArray>()

		result shouldBe content
	}

	test("ApiSerializer can decode JSON") {
		val content = """{"test": true}""".toByteArray()
		val result = createResponse(content).createContent<Map<String, Boolean>>()

		result["test"] shouldBe true
	}

	test("ApiSerializer can decode primitive") {
		val content = """42""".toByteArray()
		val result = createResponse(content).createContent<Int>()

		result shouldBe 42
	}
})
