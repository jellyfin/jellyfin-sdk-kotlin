// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.emptyMap
import kotlin.collections.mutableMapOf
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.model.DateTime
import org.jellyfin.sdk.model.api.ActivityLogEntryQueryResult

public class ActivityLogApi(
	private val api: ApiClient
) : Api {
	/**
	 * Gets activity log entries.
	 *
	 * @param startIndex Optional. The record index to start at. All items with a lower index will be
	 * dropped from the results.
	 * @param limit Optional. The maximum number of records to return.
	 * @param minDate Optional. The minimum date. Format = ISO.
	 * @param hasUserId Optional. Filter log entries if it has user id, or not.
	 */
	public suspend fun getLogEntries(
		startIndex: Int? = null,
		limit: Int? = null,
		minDate: DateTime? = null,
		hasUserId: Boolean? = null
	): Response<ActivityLogEntryQueryResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["startIndex"] = startIndex
		queryParameters["limit"] = limit
		queryParameters["minDate"] = minDate
		queryParameters["hasUserId"] = hasUserId
		val data = null
		val response = api.`get`<ActivityLogEntryQueryResult>("/System/ActivityLog/Entries",
				pathParameters, queryParameters, data)
		return response
	}
}
