package org.jellyfin.sdk.model


public object RegexValidator {
	private val patterns = mutableMapOf<String, Regex>()

	public fun matches(value: String, pattern: String): Boolean {
		return patterns.getOrPut(pattern) { Regex(pattern) }.matches(value)
	}
}
