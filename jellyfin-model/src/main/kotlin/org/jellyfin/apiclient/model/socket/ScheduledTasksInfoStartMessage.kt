package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ScheduledTasksInfoStart")
public data class ScheduledTasksInfoStartMessage(
	@SerialName("Data")
	val period: PeriodicListenerPeriod = PeriodicListenerPeriod(),
) : OutgoingSocketMessage
