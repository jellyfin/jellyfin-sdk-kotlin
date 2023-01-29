package org.jellyfin.sdk.model.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jellyfin.sdk.model.UUID

/**
 * Convert string to UUID. Accepts simple and hyphenated notations.
 * @throws IllegalArgumentException if string is not a valid UUID.
 */
public expect fun String.toUUID(): UUID

/**
 * Convert string to UUID or null if the string is not an UUID.
 * Accepts simple and hyphenated notations.
 */
public expect fun String.toUUIDOrNull(): UUID?

/**
 * A UUID serializer that supports the GUIDs without dashes from the Jellyfin API.
 */
public expect class UUIDSerializer : KSerializer<UUID> {
	override val descriptor: SerialDescriptor

	override fun deserialize(decoder: Decoder): UUID

	override fun serialize(encoder: Encoder, value: UUID)
}
