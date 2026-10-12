package org.jellyfin.sdk.model.extensions

import kotlinx.datetime.LocalDateTime
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Convert [DateTime] to a kotlinx-datetime [LocalDateTime].
 */
public expect fun DateTime.toKotlinxLocalDateTime(): LocalDateTime

/**
 * Convert a kotlinx-datetime [LocalDateTime] to [DateTime].
 */
public expect fun LocalDateTime.toJellyfinDateTime(): DateTime

/**
 * Convert [UUID] to a Kotlin standard library [Uuid].
 */
@ExperimentalUuidApi
public expect fun UUID.toStdlibUuid(): Uuid

/**
 * Convert a Kotlin standard library [Uuid] to [UUID].
 */
@ExperimentalUuidApi
public expect fun Uuid.toJellyfinUuid(): UUID

/**
 * Format a [DateTime] as ISO offset date time in the system time zone.
 */
public expect fun DateTime.toIsoOffsetString(): String
