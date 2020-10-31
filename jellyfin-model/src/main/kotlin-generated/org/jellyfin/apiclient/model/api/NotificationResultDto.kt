// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlin.Int
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A list of notifications with the total record count for pagination.
 */
@Serializable
public data class NotificationResultDto(
	/**
	 * Gets or sets the current page of notifications.
	 */
	@SerialName("Notifications")
	public val notifications: List<NotificationDto>? = null,
	/**
	 * Gets or sets the total number of notifications.
	 */
	@SerialName("TotalRecordCount")
	public val totalRecordCount: Int
)
