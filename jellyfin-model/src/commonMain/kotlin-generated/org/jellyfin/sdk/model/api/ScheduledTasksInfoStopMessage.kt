// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Scheduled tasks info stop message.
 */
@Serializable
@SerialName("ScheduledTasksInfoStop")
public class ScheduledTasksInfoStopMessage : InboundWebSocketMessage {
	/**
	 * The different kinds of messages that are used in the WebSocket api.
	 */
	override val messageType: SessionMessageType = SessionMessageType.SCHEDULED_TASKS_INFO_STOP
}
