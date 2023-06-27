package org.jellyfin.sdk.api.sockets.data

import org.jellyfin.sdk.model.api.ActivityLogEntryMessage
import org.jellyfin.sdk.model.api.ActivityLogEntryStartMessage
import org.jellyfin.sdk.model.api.ActivityLogEntryStopMessage
import org.jellyfin.sdk.model.api.OutboundWebSocketMessage
import org.jellyfin.sdk.model.api.ScheduledTasksInfoMessage
import org.jellyfin.sdk.model.api.ScheduledTasksInfoStartMessage
import org.jellyfin.sdk.model.api.ScheduledTasksInfoStopMessage
import org.jellyfin.sdk.model.api.SessionsMessage
import org.jellyfin.sdk.model.api.SessionsStartMessage
import org.jellyfin.sdk.model.api.SessionsStopMessage

/**
 * All socket message types that require a subscription. Each type contains the message type and start/stop message
 * constructors. If a message type does not exist in this set it does not need a start/stop message.
 *
 * This is an internal type. Do not use this in your application.
 */
@Suppress("MoveLambdaOutsideParentheses")
public val SUBSCRIPTION_TYPES: Set<SubscriptionType<out OutboundWebSocketMessage>> = setOf(
	subscriptionType<SessionsMessage>(
		{ period -> SessionsStartMessage(period.toString()) },
		{ SessionsStopMessage() }
	),

	subscriptionType<ActivityLogEntryMessage>(
		{ period -> ActivityLogEntryStartMessage(period.toString()) },
		{ ActivityLogEntryStopMessage() }
	),

	subscriptionType<ScheduledTasksInfoMessage>(
		{ period -> ScheduledTasksInfoStartMessage(period.toString()) },
		{ ScheduledTasksInfoStopMessage() }
	),
)
