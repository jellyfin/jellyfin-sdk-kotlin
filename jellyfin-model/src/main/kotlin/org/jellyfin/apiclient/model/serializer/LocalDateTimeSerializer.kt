package org.jellyfin.apiclient.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeParseException

/**
 * Serializer to read zoned date times as local date time and writing it back
 */
class LocalDateTimeSerializer : KSerializer<LocalDateTime> {
	override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

	override fun deserialize(decoder: Decoder): LocalDateTime = try {
		ZonedDateTime.parse(decoder.decodeString()).toLocalDateTime()
	} catch (err: DateTimeParseException) {
		// Server will sometimes return 0001-01-01T00:00:00
		// but java.time can't parse that
		LocalDateTime.MIN
	}

	override fun serialize(encoder: Encoder, value: LocalDateTime) =
		encoder.encodeString(value.atZone(ZoneId.systemDefault()).toString())
}
