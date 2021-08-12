package org.jellyfin.sdk.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jellyfin.sdk.model.DateTime

/**
 * Serializer to read zoned date times as local date time and writing it back
 */
public expect class DateTimeSerializer : KSerializer<DateTime> {
	override val descriptor: SerialDescriptor

	override fun deserialize(decoder: Decoder): DateTime

	override fun serialize(encoder: Encoder, value: DateTime): Unit
}
