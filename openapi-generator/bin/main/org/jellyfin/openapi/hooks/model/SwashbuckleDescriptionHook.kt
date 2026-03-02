package org.jellyfin.openapi.hooks.model

import org.jellyfin.openapi.hooks.DescriptionHook
import org.jellyfin.openapi.model.DescriptionType

class SwashbuckleDescriptionHook : DescriptionHook {
	private companion object {
		private val replacements = mapOf(
			// Replace CRLF with LF
			Regex("""\r\n?""") to "\n",
			// Replace <br /> elements with new line
			Regex("""<br\s?/?>""") to "\n",
			// Replace <seealso> elements with their value
			Regex("""<seealso\s+cref="(.*?)"\s?/>""") to "`$1`",
			Regex("""<seealso\s+cref="(.*?)"\s?>(.*?)</see>""") to "$2 (`$1`)",
			// Replace <see> elements with their value
			Regex("""<see\s+cref="(.*?)"\s?/>""") to "`$1`",
			Regex("""<see\s+cref="(.*?)"\s?>(.*?)</see>""") to "$2 (`$1`)",
		)
	}

	override fun modify(type: DescriptionType, description: String): String = replacements.entries
		.fold(description) { acc, (search, replace) -> acc.replace(search, replace) }
}
