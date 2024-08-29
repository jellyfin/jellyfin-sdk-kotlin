package org.jellyfin.sdk.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jellyfin.sdk.model.UUID

private val UUID_REGEX = "^([a-z\\d]{8})([a-z\\d]{4})([a-z\\d]{4})([a-z\\d]{4})([a-z\\d]{12})\$".toRegex()

public actual fun String.toUUID(): UUID = UUID.fromString(replace(UUID_REGEX, "$1-$2-$3-$4-$5"))

@Suppress("SwallowedException")
public actual fun String.toUUIDOrNull(): UUID? = try {
	toUUID()
} catch (err: IllegalArgumentException) {
	null
}

public actual class UUIDSerializer : KSerializer<UUID> {
	actual override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("org.jellyfin.UUID", PrimitiveKind.STRING)

	actual override fun deserialize(decoder: Decoder): UUID {
		return decoder.decodeString().toUUID()
	}

	actual override fun serialize(encoder: Encoder, value: UUID) {
		encoder.encodeString(value.toString())
	}
}
