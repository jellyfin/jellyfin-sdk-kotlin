package org.jellyfin.openapi.compare.reporter

import org.jellyfin.openapi.compare.model.CompareResult

interface CompareReporter {
	val name: String

	fun format(result: CompareResult): String
}
