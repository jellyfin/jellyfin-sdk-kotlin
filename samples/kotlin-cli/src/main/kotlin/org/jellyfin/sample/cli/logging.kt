package org.jellyfin.sample.cli

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.properties.ReadOnlyProperty

fun logger(): ReadOnlyProperty<Any?, Logger> = ReadOnlyProperty { thisRef, _ ->
	LoggerFactory.getLogger(thisRef?.javaClass)
}
