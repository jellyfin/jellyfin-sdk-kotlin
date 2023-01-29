@file:Suppress("MagicNumber")

package org.jellyfin.sdk.model.extensions

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * Returns a [Duration] equal to this [Long] number of ticks.
 */
public inline val Long.ticks: Duration get() = times(100L).toDuration(DurationUnit.NANOSECONDS)

/**
 * Returns a [Duration] equal to this [Int] number of ticks.
 */
public inline val Int.ticks: Duration get() = times(100).toDuration(DurationUnit.NANOSECONDS)

/**
 * Returns a [Duration] equal to this [Double] number of ticks.
 */
public inline val Double.ticks: Duration get() = times(100.0).toDuration(DurationUnit.NANOSECONDS)

/**
 * The value of this duration expressed as a [Long] number of ticks.
 */
public val Duration.inWholeTicks: Long get() = toLong(DurationUnit.NANOSECONDS).div(100L)
