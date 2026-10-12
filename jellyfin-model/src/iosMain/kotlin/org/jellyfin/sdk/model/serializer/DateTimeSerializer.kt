package org.jellyfin.sdk.model.serializer

import kotlinx.datetime.TimeZone
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.internal.DateTimeCodec

public actual class DateTimeSerializer : KSerializer<DateTime> {
	actual override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("org.jellyfin.LocalDateTime", PrimitiveKind.STRING)

	actual override fun deserialize(decoder: Decoder): DateTime =
		DateTimeCodec.decode(decoder.decodeString(), TimeZone.currentSystemDefault())

	actual override fun serialize(encoder: Encoder, value: DateTime): Unit =
		encoder.encodeString(DateTimeCodec.encode(value, TimeZone.currentSystemDefault()))
}
