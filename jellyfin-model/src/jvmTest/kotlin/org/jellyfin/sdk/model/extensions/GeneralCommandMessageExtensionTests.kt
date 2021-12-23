package org.jellyfin.sdk.model.extensions

import org.jellyfin.sdk.model.api.GeneralCommandType
import org.jellyfin.sdk.model.socket.GeneralCommandMessage
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

class GeneralCommandMessageExtensionTests {
	private val dataArguments = mapOf(
		"Header" to "This is the header",
		"Text" to "This is the text",
		"TimeoutMs" to "4000"
	)
	private val message = GeneralCommandMessage(
		messageId = UUID.randomUUID(),
		command = GeneralCommandType.DISPLAY_MESSAGE,
		userId = UUID.randomUUID(),
		arguments = dataArguments
	)

	@Test
	fun `GeneralCommandMessage allows array access for arguments`() {
		val header = message["Header"]
		val text = message["Text"]
		val timeoutMs = message["TimeoutMs"]

		assertEquals("This is the header", header)
		assertEquals("This is the text", text)
		assertEquals("4000", timeoutMs)
	}

	@Test
	fun `GeneralCommandMessage allows multi-array access for arguments`() {
		val (header, text, timeoutMs) = message["Header", "Text", "TimeoutMs"]

		assertEquals("This is the header", header)
		assertEquals("This is the text", text)
		assertEquals("4000", timeoutMs)
	}

	@Test
	fun `GeneralCommandMessage allows property delegation read access for arguments`() {
		val header by message
		val text by message
		val timeoutMs by message

		assertEquals("This is the header", header)
		assertEquals("This is the text", text)
		assertEquals("4000", timeoutMs)
	}

	@Test
	fun `GeneralCommandMessage allows checking for values`() {
		assertEquals(true, "Header" in message)
		assertEquals(true, "Text" in message)
		assertEquals(true, "TimeoutMs" in message)
		assertEquals(false, "Unknown" in message)
	}

	@Test
	fun `GeneralCommandMessage extensions are case insensitive`() {
		assertEquals(true, "header" in message)
		assertEquals(true, "HEADER" in message)
		assertEquals(true, "Header" in message)

		assertEquals("This is the header", message["header"])
		assertEquals("This is the header", message["HEADER"])
		assertEquals("This is the header", message["Header"])
	}
}
