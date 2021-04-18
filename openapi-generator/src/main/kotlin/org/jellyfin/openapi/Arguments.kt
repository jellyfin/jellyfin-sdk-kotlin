package org.jellyfin.openapi

import kotlin.reflect.KProperty

typealias Arguments = Map<String, String>

operator fun Arguments.getValue(thisRef: Any?, property: KProperty<*>): String =
	this[property.name] ?: throw OpenApiGeneratorError("Missing argument --${property.name}")

fun Array<out String>.asArguments(): Arguments {
	val iterator = iterator()
	var key: String? = null
	val arguments = mutableMapOf<String, String>()

	while (iterator.hasNext()) {
		val value = iterator.next()
		if (value.startsWith("--")) key = value.removePrefix("--")
		else if (key != null) arguments[key] = value
	}

	return arguments
}
