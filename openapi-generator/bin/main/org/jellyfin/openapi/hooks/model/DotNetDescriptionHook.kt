package org.jellyfin.openapi.hooks.model

import org.jellyfin.openapi.hooks.DescriptionHook
import org.jellyfin.openapi.model.DescriptionType

class DotNetDescriptionHook : DescriptionHook {
	private companion object {
		private val getsOrSetsRegex = Regex("""^(Gets or sets|Gets|Sets)\s+(.*)$""")
	}

	override fun modify(type: DescriptionType, description: String): String {
		// Ignore API operations
		if (type == DescriptionType.OPERATION) return description

		return description.replace(getsOrSetsRegex) { result ->
			result.groupValues[2].replaceFirstChar(Char::uppercase)
		}
	}
}
