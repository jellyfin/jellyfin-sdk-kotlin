package org.jellyfin.openapi.compare.reporter

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jellyfin.openapi.compare.model.CompareResult

class JsonCompareReporter : CompareReporter {
	override val name = "json"

	override fun format(result: CompareResult): String = Json.encodeToString(result)
}
