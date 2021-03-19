package org.jellyfin.sdk.model.discovery

import kotlinx.serialization.Serializable
import org.jellyfin.sdk.model.discovery.ServerVersion.Companion.fromString

/**
 * Model to help with Jellyfin server versions.
 * Use [fromString] to parse strings. The format is similar to SemVer.
 */
@Serializable
public data class ServerVersion(
	val major: Int,
	val minor: Int,
	val patch: Int,
	val build: Int? = null
) {
	public operator fun compareTo(other: ServerVersion): Int = comparator.compare(this, other)

	public companion object {
		private val comparator = compareBy<ServerVersion>(
			{ it.major },
			{ it.minor },
			{ it.patch },
			{ it.build ?: -1 }
		)

		/**
		 * Create an instance of [ServerVersion] from a string. The string must be in the format
		 * "^\d+\.\d+\.\d+(?:\.\d+)?$". Example: 1.0.0, 10.6.4 or 10.7.0.0. Characters are not allowed.
		 */
		public fun fromString(str: String): ServerVersion? {
			// Split into major, minor and patch
			val parts = str.split('.').map(String::toIntOrNull)

			// Check if we found enough parts
			if (parts.size != 3 && parts.size != 4) return null

			// Bad value found
			if (parts.any { it == null }) return null

			// Return server version
			return ServerVersion(
				major = parts[0]!!,
				minor = parts[1]!!,
				patch = parts[2]!!,
				build = parts.getOrNull(3)
			)
		}
	}
}
