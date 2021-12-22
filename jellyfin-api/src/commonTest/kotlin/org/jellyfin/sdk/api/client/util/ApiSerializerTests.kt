package org.jellyfin.sdk.api.client.util

import io.ktor.utils.io.*
import io.ktor.utils.io.charsets.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ApiSerializerTests {
	@Test
	fun `ApiSerializer can decode Unit`() = runTest {
		val result = ApiSerializer.decodeResponseBody<Unit>(ByteReadChannel("", Charsets.UTF_8))

		assertIs<Unit>(result)
	}

	@Test
	fun `ApiSerializer can decode ByteReadChannel`() = runTest {
		val channel = ByteReadChannel(byteArrayOf(0x1, 0x0, 0x1))
		val result = ApiSerializer.decodeResponseBody<ByteReadChannel>(channel)

		assertEquals(channel, result)
	}

	@Test
	fun `ApiSerializer can decode JSON`() = runTest {
		val channel = ByteReadChannel("""{"test": true}""")
		val result = ApiSerializer.decodeResponseBody<Map<String, Boolean>>(channel)

		assertEquals(true, result["test"])
	}

	@Test
	fun `ApiSerializer can decode primitive`() = runTest {
		val channel = ByteReadChannel("""42""")
		val result = ApiSerializer.decodeResponseBody<Int>(channel)

		assertEquals(42, result)
	}
}
