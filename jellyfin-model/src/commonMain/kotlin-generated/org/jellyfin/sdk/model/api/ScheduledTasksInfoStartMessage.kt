// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Scheduled tasks info start message.
 * Data is the timing data encoded as "$initialDelay,$interval" in ms.
 */
@Serializable
@SerialName("ScheduledTasksInfoStart")
public data class ScheduledTasksInfoStartMessage(
	/**
	 * The data.
	 */
	@SerialName("Data")
	public val `data`: String? = null,
) : InboundWebSocketMessage {
	/**
	 * The different kinds of messages that are used in the WebSocket api.
	 */
	override val messageType: SessionMessageType = SessionMessageType.SCHEDULED_TASKS_INFO_START
}