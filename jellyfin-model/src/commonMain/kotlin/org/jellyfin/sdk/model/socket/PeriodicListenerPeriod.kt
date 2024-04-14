package org.jellyfin.sdk.model.socket

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Serializable(with = PeriodicListenerPeriod.Serializer::class)
public data class PeriodicListenerPeriod(
	val initialDelay: Duration = Duration.ZERO,
	val interval: Duration = 1.seconds,
) {
	override fun toString(): String = "${initialDelay.inWholeMilliseconds},${interval.inWholeMilliseconds}"

	public companion object {
		public fun fromString(str: String): PeriodicListenerPeriod? {
			val values = str.split(',')
			val initialDelay = values.getOrNull(0)?.toLongOrNull()?.milliseconds ?: return null
			val interval = values.getOrNull(1)?.toLongOrNull()?.milliseconds ?: return null

			return PeriodicListenerPeriod(
				initialDelay = initialDelay,
				interval = interval,
			)
		}
	}

	public class Serializer : KSerializer<PeriodicListenerPeriod> {
		override val descriptor: SerialDescriptor =
			PrimitiveSerialDescriptor("PeriodicListenerPeriod", PrimitiveKind.STRING)

		override fun serialize(encoder: Encoder, value: PeriodicListenerPeriod): Unit =
			encoder.encodeString(value.toString())

		override fun deserialize(decoder: Decoder): PeriodicListenerPeriod =
			fromString(decoder.decodeString())!!
	}
}
