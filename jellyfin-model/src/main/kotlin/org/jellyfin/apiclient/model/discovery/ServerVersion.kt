package org.jellyfin.apiclient.model.discovery

import kotlinx.serialization.Serializable
import org.jellyfin.apiclient.model.discovery.ServerVersion.Companion.fromString

/**
 * Model to help with Jellyfin server versions.
 * Use [fromString] to parse strings. The format is similar to SemVer.
 */
@Serializable
data class ServerVersion(
	val major: Int,
	val minor: Int,
	val patch: Int
) {
	operator fun compareTo(other: ServerVersion) = comparator.compare(this, other)

	companion object {
		private val comparator = compareBy<ServerVersion>(
			{ it.major },
			{ it.minor },
			{ it.patch }
		)

		fun fromString(str: String): ServerVersion? {
			try {
				val parts = str
					.split('.') // Split into major, minor and patch
					.also { require(it.size == 3) } // Check if we found 3 parts
					.take(3) // Take first 3 values in case the input is bad
					.mapNotNull(String::toIntOrNull) // Convert to integers and drop bad values
					.also { require(it.size == 3) } // Check if we have 3 parts left to make a valid version

				// Return server version
				return ServerVersion(
					major = parts[0],
					minor = parts[1],
					patch = parts[2]
				)
			} catch (err: IllegalArgumentException) {
				// Invalid string
				return null
			}
		}
	}
}
