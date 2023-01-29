package org.jellyfin.openapi.hooks

import org.jellyfin.openapi.model.DescriptionType

interface DescriptionHook {
	/**
	 * Modify or drop a given description for API models and operations.
	 *
	 * @return The new description, null means removing the description.
	 */
	fun modify(type: DescriptionType, description: String): String?
}
