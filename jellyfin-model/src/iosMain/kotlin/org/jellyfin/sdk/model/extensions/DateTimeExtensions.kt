package org.jellyfin.sdk.model.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.internal.DateTimeCodec
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

public actual fun DateTime.toKotlinxLocalDateTime(): LocalDateTime = this

public actual fun LocalDateTime.toJellyfinDateTime(): DateTime = this

@ExperimentalUuidApi
public actual fun UUID.toStdlibUuid(): Uuid = this

@ExperimentalUuidApi
public actual fun Uuid.toJellyfinUuid(): UUID = this

public actual fun DateTime.toIsoOffsetString(): String =
	DateTimeCodec.encode(this, TimeZone.currentSystemDefault())
