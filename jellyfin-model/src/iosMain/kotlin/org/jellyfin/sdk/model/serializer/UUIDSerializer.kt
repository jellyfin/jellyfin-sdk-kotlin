package org.jellyfin.sdk.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.internal.parseUuid

public actual fun String.toUUID(): UUID = parseUuid(this)

@Suppress("SwallowedException")
public actual fun String.toUUIDOrNull(): UUID? = try {
	toUUID()
} catch (err: IllegalArgumentException) {
	null
}

public actual class UUIDSerializer : KSerializer<UUID> {
	actual override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("org.jellyfin.UUID", PrimitiveKind.STRING)

	actual override fun deserialize(decoder: Decoder): UUID = decoder.decodeString().toUUID()

	actual override fun serialize(encoder: Encoder, value: UUID) {
		encoder.encodeString(value.toString())
	}
}
