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

		/**
		 * Create an instance of [ServerVersion] from a string. The string must be in the format
		 * "\d+\.\d+\.\d+\". Example: 1.0.0 or 10.6.4. Characters are not allowed.
		 */
		fun fromString(str: String): ServerVersion? {
			// Split into major, minor and patch
			val stringParts = str.split('.')
			// Check if we found 3 parts
			if (stringParts.size != 3) return null

			// Convert to integers and drop bad values
			val intParts = stringParts.mapNotNull(String::toIntOrNull)
			// Check if we have 3 parts left to make a valid version
			if (intParts.size != 3) return null

			// Return server version
			return ServerVersion(
				major = intParts[0],
				minor = intParts[1],
				patch = intParts[2]
			)
		}
	}
}
