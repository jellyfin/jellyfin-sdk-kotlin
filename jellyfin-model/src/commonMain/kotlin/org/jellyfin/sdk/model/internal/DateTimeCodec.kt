package org.jellyfin.sdk.model.internal

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.offsetAt
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

internal object DateTimeCodec {
	val MIN: LocalDateTime = LocalDateTime(1, 1, 1, 0, 0)

	@Suppress("SwallowedException", "TooGenericExceptionCaught")
	fun decode(value: String, zone: TimeZone): LocalDateTime = try {
		Instant.parse(value).toLocalDateTime(zone)
	} catch (err: IllegalArgumentException) {
		MIN
	}

	// Matches java.time's ISO_OFFSET_DATE_TIME
	fun encode(value: LocalDateTime, zone: TimeZone): String = buildString {
		append(value.year.toString().padStart(4, '0'))
		append('-')
		append((value.month.ordinal + 1).toString().padStart(2, '0'))
		append('-')
		append(value.day.toString().padStart(2, '0'))
		append('T')
		append(value.hour.toString().padStart(2, '0'))
		append(':')
		append(value.minute.toString().padStart(2, '0'))
		append(':')
		append(value.second.toString().padStart(2, '0'))

		if (value.nanosecond != 0) {
			append('.')
			append(value.nanosecond.toString().padStart(9, '0').trimEnd('0'))
		}

		append(formatOffset(zone.offsetAt(value.toInstant(zone))))
	}

	private fun formatOffset(offset: UtcOffset): String {
		val total = offset.totalSeconds
		if (total == 0) return "Z"

		val abs = if (total < 0) -total else total
		val hours = abs / SECONDS_PER_HOUR
		val minutes = abs % SECONDS_PER_HOUR / SECONDS_PER_MINUTE
		val seconds = abs % SECONDS_PER_MINUTE

		return buildString {
			append(if (total < 0) '-' else '+')
			append(hours.toString().padStart(2, '0'))
			append(':')
			append(minutes.toString().padStart(2, '0'))
			if (seconds != 0) {
				append(':')
				append(seconds.toString().padStart(2, '0'))
			}
		}
	}

	private const val SECONDS_PER_MINUTE = 60
	private const val SECONDS_PER_HOUR = 3600
}
