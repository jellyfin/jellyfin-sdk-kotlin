package org.jellyfin.openapi.builder.extra

import org.jellyfin.openapi.hooks.DescriptionHook
import org.jellyfin.openapi.model.DescriptionType

class DescriptionBuilder(
	private val descriptionsHooks: Collection<DescriptionHook>,
) {
	fun build(type: DescriptionType, description: String?): String? {
		if (description == null) return null

		var modifiedDescription: String? = description
		for (hook in descriptionsHooks) {
			if (modifiedDescription == null) return null
			modifiedDescription = hook.modify(type, modifiedDescription)
		}

		return modifiedDescription
	}
}
