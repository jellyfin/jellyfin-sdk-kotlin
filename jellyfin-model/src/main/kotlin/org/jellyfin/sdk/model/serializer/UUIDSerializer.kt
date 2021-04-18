package org.jellyfin.sdk.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

private val UUID_REGEX = "^([a-z\\d]{8})([a-z\\d]{4})([a-z\\d]{4})([a-z\\d]{4})([a-z\\d]{12})\$".toRegex()

/**
 * Convert string to UUID. Accepts simple and hyphenated notations.
 * @throws IllegalArgumentException if string is not a valid UUID.
 */
public fun String.toUUID(): UUID = UUID.fromString(replace(UUID_REGEX, "$1-$2-$3-$4-$5"))

/**
 * Convert string to UUID or null if the string is not an UUID.
 * Accepts simple and hyphenated notations.
 */
@Suppress("SwallowedException")
public fun String.toUUIDOrNull(): UUID? = try {
	toUUID()
} catch (err: IllegalArgumentException) {
	null
}

/**
 * A UUID serializer that supports the GUIDs without dashes from the Jellyfin API.
 */
public class UUIDSerializer : KSerializer<UUID> {
	override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

	override fun deserialize(decoder: Decoder): UUID {
		return decoder.decodeString().toUUID()
	}

	override fun serialize(encoder: Encoder, value: UUID) {
		encoder.encodeString(value.toString())
	}
}
