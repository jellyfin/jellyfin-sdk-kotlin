package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ActivityLogEntryStart")
public data class ActivityLogEntryStartMessage(
	@SerialName("Data")
	val period: PeriodicListenerPeriod = PeriodicListenerPeriod(),
) : OutgoingSocketMessage
