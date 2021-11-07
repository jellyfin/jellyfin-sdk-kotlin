package org.jellyfin.sdk.api.sockets.data

import org.jellyfin.sdk.model.socket.ActivityLogEntryMessage
import org.jellyfin.sdk.model.socket.ActivityLogEntryStartMessage
import org.jellyfin.sdk.model.socket.ActivityLogEntryStopMessage
import org.jellyfin.sdk.model.socket.IncomingSocketMessage
import org.jellyfin.sdk.model.socket.ScheduledTasksInfoMessage
import org.jellyfin.sdk.model.socket.ScheduledTasksInfoStartMessage
import org.jellyfin.sdk.model.socket.ScheduledTasksInfoStopMessage
import org.jellyfin.sdk.model.socket.SessionsMessage
import org.jellyfin.sdk.model.socket.SessionsStartMessage
import org.jellyfin.sdk.model.socket.SessionsStopMessage

/**
 * All socket message types that require a subscription. Each type contains the message type and start/stop message
 * constructors. If a message type does not exist in this set it does not need a start/stop message.
 *
 * This is an internal type. Do not use this in your application.
 */
public val SUBSCRIPTION_TYPES: Set<SubscriptionType<out IncomingSocketMessage>> = setOf(
	subscriptionType<SessionsMessage>(::SessionsStartMessage, ::SessionsStopMessage),
	subscriptionType<ActivityLogEntryMessage>(::ActivityLogEntryStartMessage, ::ActivityLogEntryStopMessage),
	subscriptionType<ScheduledTasksInfoMessage>(::ScheduledTasksInfoStartMessage, ::ScheduledTasksInfoStopMessage),
)
