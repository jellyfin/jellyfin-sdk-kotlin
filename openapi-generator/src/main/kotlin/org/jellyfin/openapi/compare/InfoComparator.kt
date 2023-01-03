package org.jellyfin.openapi.compare

import org.jellyfin.openapi.compare.model.CompareValueDiff
import org.jellyfin.openapi.compare.model.buildCompareValueDiffCollection
import org.jellyfin.openapi.model.GeneratorContext

class InfoComparator {
	fun compare(
		oldSchema: GeneratorContext,
		newSchema: GeneratorContext,
	): Collection<CompareValueDiff> = buildCompareValueDiffCollection(oldSchema.info, newSchema.info) {
		detect({ title }, "Title")
		detect({ description }, "Description")
		detect({ version }, "API version")
		detect({ extensions["x-jellyfin-version"] }, "Jellyfin version")
	}
}
