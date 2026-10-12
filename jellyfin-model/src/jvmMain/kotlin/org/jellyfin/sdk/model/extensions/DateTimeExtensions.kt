package org.jellyfin.sdk.model.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.UUID
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlin.uuid.toJavaUuid
import kotlin.uuid.toKotlinUuid as javaToKotlinUuid

public actual fun DateTime.toKotlinxLocalDateTime(): LocalDateTime = toKotlinLocalDateTime()

public actual fun LocalDateTime.toJellyfinDateTime(): DateTime = toJavaLocalDateTime()

@ExperimentalUuidApi
public actual fun UUID.toStdlibUuid(): Uuid = javaToKotlinUuid()

@ExperimentalUuidApi
public actual fun Uuid.toJellyfinUuid(): UUID = toJavaUuid()

public actual fun DateTime.toIsoOffsetString(): String = atZone(ZoneId.systemDefault())
	.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
