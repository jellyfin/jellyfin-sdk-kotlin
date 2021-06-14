package org.jellyfin.openapi.builder.extra

import org.jellyfin.openapi.builder.Builder

class DescriptionBuilder : Builder<String?, String?> {
	private val replacements = mapOf(
		// Replace CRLF with LF
		Regex("""\r\n?""") to "\n",
		// Replace <br /> elements with new line
		Regex("""<br\s?/?>""") to "\n",
		// Replace <seealso> elements with their value
		Regex("""<seealso\s+cref="(.*?)"\s?/?>""") to "`$1`",
		// Replace <see> elements with their value
		Regex("""<see\s+cref="(.*?)"\s?/?>""") to "`$1`",
	)

	override fun build(data: String?): String? {
		if (data == null) return null

		return replacements.entries.fold(data) { acc, (search, replace) ->
			acc.replace(search, replace)
		}
	}
}
