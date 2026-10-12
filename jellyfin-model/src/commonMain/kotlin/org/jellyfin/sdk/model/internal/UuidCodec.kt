package org.jellyfin.sdk.model.internal

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private val UUID_REGEX = "^([a-z\\d]{8})([a-z\\d]{4})([a-z\\d]{4})([a-z\\d]{4})([a-z\\d]{12})\$".toRegex()

@OptIn(ExperimentalUuidApi::class)
internal fun parseUuid(value: String): Uuid = Uuid.parse(value.replace(UUID_REGEX, "$1-$2-$3-$4-$5"))
