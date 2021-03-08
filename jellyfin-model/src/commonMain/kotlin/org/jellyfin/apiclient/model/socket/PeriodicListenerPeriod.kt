package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = PeriodicListenerPeriod.Serializer::class)
public data class PeriodicListenerPeriod(
	val initialDelay: Long = 0,
	val interval: Long = 1000,
) {
	override fun toString(): String {
		return "$initialDelay,$interval"
	}

	public companion object {
		public 	fun fromString(str: String): PeriodicListenerPeriod? {
			val values = str.split(',')
			val dueTimeMs = values.getOrNull(0)?.toLongOrNull() ?: return null
			val periodMs = values.getOrNull(1)?.toLongOrNull() ?: return null

			return PeriodicListenerPeriod(
				initialDelay = dueTimeMs,
				interval = periodMs,
			)
		}
	}

	public class Serializer : KSerializer<PeriodicListenerPeriod> {
		override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("PeriodicListenerPeriod", PrimitiveKind.STRING)

		override fun serialize(encoder: Encoder, value: PeriodicListenerPeriod): Unit =
			encoder.encodeString(value.toString())

		override fun deserialize(decoder: Decoder): PeriodicListenerPeriod =
			fromString(decoder.decodeString())!!
	}
}
