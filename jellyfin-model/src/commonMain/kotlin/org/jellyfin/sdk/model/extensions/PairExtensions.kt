package org.jellyfin.sdk.model.extensions

import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.NameGuidPair
import org.jellyfin.sdk.model.api.NameIdPair
import org.jellyfin.sdk.model.api.NameValuePair

/**
 * Convert a [NameIdPair] to a [Pair] with [NameIdPair.id] as [Pair.first] and [NameIdPair.name] as [Pair.second].
 */
public fun NameIdPair.toPair(): Pair<String?, String?> = id to name

/**
 * Convert a [Pair] to a [NameIdPair] with [Pair.first] as [NameIdPair.id] and [Pair.second] as [NameIdPair.name].
 */
public fun Pair<String?, String?>.toNameIdPair(): NameIdPair = NameIdPair(id = first, name = second)


/**
 * Convert a [NameGuidPair] to a [Pair] with [NameGuidPair.id] as [Pair.first] and [NameGuidPair.name] as [Pair.second].
 */
public fun NameGuidPair.toPair(): Pair<UUID, String?> = id to name

/**
 * Convert a [Pair] to a [NameGuidPair] with [Pair.first] as [NameGuidPair.id] and [Pair.second] as [NameGuidPair.name].
 */
public fun Pair<UUID, String?>.toNameGuidPair(): NameGuidPair = NameGuidPair(id = first, name = second)


/**
 * Convert a [NameValuePair] to a [Pair] with [NameValuePair.name] as [Pair.first] and [NameValuePair.value] as
 * [Pair.second].
 */
public fun NameValuePair.toPair(): Pair<String?, String?> = name to value

/**
 * Convert a [Pair] to a [NameValuePair] with [Pair.first] as [NameValuePair.name] and [Pair.second] as
 * [NameValuePair.value].
 */
public fun Pair<String?, String?>.toNameValuePair(): NameValuePair = NameValuePair(name = first, value = second)
