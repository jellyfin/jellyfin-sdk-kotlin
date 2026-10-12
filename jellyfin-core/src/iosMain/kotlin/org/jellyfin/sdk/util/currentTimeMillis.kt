package org.jellyfin.sdk.util

import kotlin.time.Clock

internal actual fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()
