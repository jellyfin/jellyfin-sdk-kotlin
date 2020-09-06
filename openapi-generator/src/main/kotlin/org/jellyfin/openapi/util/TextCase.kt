package org.jellyfin.openapi.util

/**
 * Utility class to convert strings to various standards of text casing
 *
 * @param parts Individual words that make up a single string
 */
class TextCase(
	val parts: List<String>
) {
	/**
	 * @return camelCase
	 */
	fun toCamelCase(): String = parts.mapIndexed { i, it ->
		val lowercase = it.toLowerCase()

		// First word should be fully lowercase
		if (i > 0) lowercase.capitalize()
		else lowercase
	}.joinToString(separator = "")

	/**
	 * @return PascalCase
	 */
	fun toPascalCase(): String = parts.mapIndexed { _, it ->
		it.toLowerCase().capitalize()
	}.joinToString(separator = "")

	/**
	 * @return SCREAMING_SNAKE_CASE
	 */
	fun toScreamingSnakeCase(): String = parts.joinToString(separator = "_") {
		it.toUpperCase()
	}

	override fun toString() = parts.toString()

	companion object {
		/**
		 * Interpret this string as PascalCase
		 * @param string PascalCase
		 */
		fun fromPascalCase(string: String): TextCase {
			val regex = "(?<=[A-Z])(?=[A-Z][a-z])|(?<=[^A-Z])(?=[A-Z])|(?<=[A-Za-z])(?=[^A-Za-z])".toRegex()
			val parts = regex.split(string)

			return TextCase(parts)
		}
	}
}

/**
 * Interpret this string as PascalCase
 */
fun String.asPascalCase(): TextCase = TextCase.fromPascalCase(this)
