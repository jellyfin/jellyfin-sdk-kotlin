package org.jellyfin.sdk.model.extensions

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.jellyfin.sdk.model.api.GeneralCommand
import org.jellyfin.sdk.model.api.GeneralCommandMessage
import org.jellyfin.sdk.model.api.GeneralCommandType
import java.util.UUID

class GeneralCommandMessageExtensionTests : FunSpec({
	val dataArguments = mapOf(
		"Header" to "This is the header",
		"Text" to "This is the text",
		"TimeoutMs" to "4000"
	)
	val message = GeneralCommandMessage(
		messageId = UUID.randomUUID(),
		data = GeneralCommand(
			name = GeneralCommandType.DISPLAY_MESSAGE,
			controllingUserId = UUID.randomUUID(),
			arguments = dataArguments,
		),
	)

	test("GeneralCommandMessage allows array access for arguments") {
		val header = message["Header"]
		val text = message["Text"]
		val timeoutMs = message["TimeoutMs"]

		header shouldBe "This is the header"
		text shouldBe "This is the text"
		timeoutMs shouldBe "4000"
	}

	test("GeneralCommandMessage allows multi-array access for arguments") {
		val (header, text, timeoutMs) = message["Header", "Text", "TimeoutMs"]

		header shouldBe "This is the header"
		text shouldBe "This is the text"
		timeoutMs shouldBe "4000"
	}

	test("GeneralCommandMessage allows property delegation read access for arguments") {
		val header by message
		val text by message
		val timeoutMs by message

		header shouldBe "This is the header"
		text shouldBe "This is the text"
		timeoutMs shouldBe "4000"
	}

	test("GeneralCommandMessage allows checking for values") {
		("Header" in message) shouldBe true
		("Text" in message) shouldBe true
		("TimeoutMs" in message) shouldBe true
		("Unknown" in message) shouldBe false
	}

	test("GeneralCommandMessage extensions are case insensitive") {
		("header" in message) shouldBe true
		("HEADER" in message) shouldBe true
		("Header" in message) shouldBe true

		message["header"] shouldBe "This is the header"
		message["HEADER"] shouldBe "This is the header"
		message["Header"] shouldBe "This is the header"
	}
})
