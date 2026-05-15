package org.jellyfin.sdk.model

import kotlinx.serialization.Serializable
import org.jellyfin.sdk.model.ServerVersion.Companion.fromString

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
) : Comparable<ServerVersion> {
	public override operator fun compareTo(other: ServerVersion): Int = comparator.compare(this, other)

	private val stringParts = when {
		major >= 12 && patch == 0 && build == null -> 2
		build == null -> 3
		else -> 4
	}

	/**
	 * Convert version to string. Format is "[major].[minor].[patch].[build]".
	 * [build] is omitted if null.
	 * [patch] is omitted if [major] is 12 or higher, [patch] is 0 and [build] is null.
	 * Sample output:
	 * - 1.0.0
	 * - 10.6.4
	 * - 10.7.0.0
	 * - 12.0
	 */
	override fun toString(): String = toString(stringParts)

	/**
	 * Convert version to string. Format is "[major].[minor].[patch].[build]". The amount of parts returned is
	 * determined by [parts] and should be between 1 and 4 (inclusive). Defaults to 2 parts.
	 * Sample output:
	 * - 1.0.0
	 * - 10.6.4
	 * - 10.7.0.0
	 * - 12.0
	 */
	public fun toString(parts: Int = 2): String = buildString {
		require(parts in 1..4) { "parts must be in range 1..4" }

		append(major)
		if (parts >= 2) append('.', minor)
		if (parts >= 3) append('.', patch)
		if (parts >= 4) append('.', build ?: 0)
	}

	public companion object {
		private val comparator = compareBy<ServerVersion>(
			{ it.major },
			{ it.minor },
			{ it.patch },
			{ it.build ?: 0 }
		)

		/**
		 * Create an instance of [ServerVersion] from a string. The string must be in the format
		 * "^\d+\.\d+\.\d+(?:\.\d+)?$". Example: 1.0.0, 10.6.4 or 10.7.0.0. Characters are not allowed.
		 */
		public fun fromString(str: String): ServerVersion? {
			// Split into major, minor and patch
			val parts = str.split('.').map(String::toIntOrNull)

			// Check if we found enough parts
			@Suppress("MagicNumber")
			if (parts.size != 3 && parts.size != 4) return null

			// Bad value found
			if (parts.any { it == null }) return null

			// Return server version
			@Suppress("MagicNumber")
			return ServerVersion(
				major = parts[0]!!,
				minor = parts[1]!!,
				patch = parts[2]!!,
				build = parts.getOrNull(3)
			)
		}
	}
}
