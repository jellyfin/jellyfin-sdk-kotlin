package org.jellyfin.apiclient.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ActivityLogEntryStop")
public class ActivityLogEntryStopMessage : OutgoingSocketMessage
