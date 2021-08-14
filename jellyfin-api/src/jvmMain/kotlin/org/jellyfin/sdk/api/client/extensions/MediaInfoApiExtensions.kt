package org.jellyfin.sdk.api.client.extensions

import org.jellyfin.sdk.api.operations.MediaInfoApi
import org.jellyfin.sdk.model.BitrateMeasurement

/**
 * Create a [BitrateMeasurement] for a response of [bytes]. The returned value contains the input, duration and the
 * bitrate.
 */
public suspend fun MediaInfoApi.measureBitrate(bytes: Int): BitrateMeasurement {
	val now = System.currentTimeMillis()

	// Request the bits
	getBitrateTestBytes(size = bytes).apply {
		// Download full content
		content.awaitContent()
	}

	// Calculate duration
	val duration = System.currentTimeMillis() - now

	// Return results
	return BitrateMeasurement(
		bytes = bytes,
		duration = duration,
	)
}

/**
 * Detect the bitrate of the current connection by calling [measureBitrate] until a measurement of at least 1 second or
 * with the maximum byte size is created.
 */
@Suppress("MagicNumber")
public suspend fun MediaInfoApi.detectBitrate(): BitrateMeasurement {
	val maxBytes = 10_000_000 // Max is 10 MB
	var testBytes = 4_000_000 // Start with 4 MB

	var measurement: BitrateMeasurement
	do {
		measurement = measureBitrate(testBytes)
		testBytes += 2_000_000 // Increase by 2 MB
	} while (measurement.duration <= 1_000 && measurement.bytes < maxBytes)

	return measurement
}
