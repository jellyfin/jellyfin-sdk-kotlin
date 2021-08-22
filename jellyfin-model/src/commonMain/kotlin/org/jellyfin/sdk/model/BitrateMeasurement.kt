package org.jellyfin.sdk.model

public data class BitrateMeasurement(
	/**
	 * Amount of bytes used to measure the bitrate.
	 */
	val bytes: Int,

	/**
	 * Measurement duration in milliseconds.
	 */
	val duration: Long,
) {
	/**
	 * Measured network speed in bits per second.
	 * Calculated by dividing [bytes] by the [duration] in seconds multiplied by 8 (byte -> bits).
	 */
	@Suppress("MagicNumber")
	val bitrate: Long = bytes * 8000L / duration
}
