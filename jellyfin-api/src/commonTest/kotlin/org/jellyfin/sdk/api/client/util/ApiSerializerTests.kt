package org.jellyfin.sdk.api.client.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.charsets.Charsets
import kotlinx.coroutines.test.runTest

class ApiSerializerTests : FunSpec({
	test("ApiSerializer can decode Unit") {
		val result = ApiSerializer.decodeResponseBody<Unit>(ByteReadChannel("", Charsets.UTF_8))

		result.shouldBeTypeOf<Unit>()
	}

	test("ApiSerializer can decode ByteReadChannel") {
		val channel = ByteReadChannel(byteArrayOf(0x1, 0x0, 0x1))
		val result = ApiSerializer.decodeResponseBody<ByteReadChannel>(channel)

		result shouldBe channel
	}

	test("ApiSerializer can decode JSON") {
		val channel = ByteReadChannel("""{"test": true}""")
		val result = ApiSerializer.decodeResponseBody<Map<String, Boolean>>(channel)

		result["test"] shouldBe true
	}

	test("ApiSerializer can decode primitive") {
		val channel = ByteReadChannel("""42""")
		val result = ApiSerializer.decodeResponseBody<Int>(channel)

		result shouldBe 42
	}
})
