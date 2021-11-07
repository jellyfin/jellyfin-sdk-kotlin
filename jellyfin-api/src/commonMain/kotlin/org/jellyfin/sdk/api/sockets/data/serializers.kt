package org.jellyfin.sdk.api.sockets.data

import kotlinx.serialization.serializer
import org.jellyfin.sdk.model.api.SessionMessageType
import org.jellyfin.sdk.model.socket.ActivityLogEntryMessage
import org.jellyfin.sdk.model.socket.ActivityLogEntryStartMessage
import org.jellyfin.sdk.model.socket.ActivityLogEntryStopMessage
import org.jellyfin.sdk.model.socket.ForceKeepAliveMessage
import org.jellyfin.sdk.model.socket.GeneralCommandMessage
import org.jellyfin.sdk.model.socket.KeepAliveMessage
import org.jellyfin.sdk.model.socket.LibraryChangedMessage
import org.jellyfin.sdk.model.socket.PackageInstallationCancelledMessage
import org.jellyfin.sdk.model.socket.PackageInstallationCompletedMessage
import org.jellyfin.sdk.model.socket.PackageInstallationFailedMessage
import org.jellyfin.sdk.model.socket.PackageInstallingMessage
import org.jellyfin.sdk.model.socket.PackageUninstalledMessage
import org.jellyfin.sdk.model.socket.PlayMessage
import org.jellyfin.sdk.model.socket.PlayStateMessage
import org.jellyfin.sdk.model.socket.RefreshProgressMessage
import org.jellyfin.sdk.model.socket.RestartRequiredMessage
import org.jellyfin.sdk.model.socket.ScheduledTaskEndedMessage
import org.jellyfin.sdk.model.socket.ScheduledTasksInfoMessage
import org.jellyfin.sdk.model.socket.ScheduledTasksInfoStartMessage
import org.jellyfin.sdk.model.socket.ScheduledTasksInfoStopMessage
import org.jellyfin.sdk.model.socket.SeriesTimerCancelledMessage
import org.jellyfin.sdk.model.socket.SeriesTimerCreatedMessage
import org.jellyfin.sdk.model.socket.ServerRestartingMessage
import org.jellyfin.sdk.model.socket.ServerShuttingDownMessage
import org.jellyfin.sdk.model.socket.SessionsMessage
import org.jellyfin.sdk.model.socket.SessionsStartMessage
import org.jellyfin.sdk.model.socket.SessionsStopMessage
import org.jellyfin.sdk.model.socket.SyncPlayCommandMessage
import org.jellyfin.sdk.model.socket.SyncPlayGroupUpdateMessage
import org.jellyfin.sdk.model.socket.TimerCancelledMessage
import org.jellyfin.sdk.model.socket.TimerCreatedMessage
import org.jellyfin.sdk.model.socket.UserDataChangedMessage
import org.jellyfin.sdk.model.socket.UserDeletedMessage
import org.jellyfin.sdk.model.socket.UserUpdatedMessage

/**
 * Mapping between [SessionMessageType] enum and their respective serializers.
 */
internal val SessionMessageType.serializer
	get() = when (this) {
		SessionMessageType.FORCE_KEEP_ALIVE -> serializer<ForceKeepAliveMessage>()
		SessionMessageType.GENERAL_COMMAND -> serializer<GeneralCommandMessage>()
		SessionMessageType.USER_DATA_CHANGED -> serializer<UserDataChangedMessage>()
		SessionMessageType.SESSIONS -> serializer<SessionsMessage>()
		SessionMessageType.PLAY -> serializer<PlayMessage>()
		SessionMessageType.SYNC_PLAY_COMMAND -> serializer<SyncPlayCommandMessage>()
		SessionMessageType.SYNC_PLAY_GROUP_UPDATE -> serializer<SyncPlayGroupUpdateMessage>()
		SessionMessageType.PLAYSTATE -> serializer<PlayStateMessage>()
		SessionMessageType.RESTART_REQUIRED -> serializer<RestartRequiredMessage>()
		SessionMessageType.SERVER_SHUTTING_DOWN -> serializer<ServerShuttingDownMessage>()
		SessionMessageType.SERVER_RESTARTING -> serializer<ServerRestartingMessage>()
		SessionMessageType.LIBRARY_CHANGED -> serializer<LibraryChangedMessage>()
		SessionMessageType.USER_DELETED -> serializer<UserDeletedMessage>()
		SessionMessageType.USER_UPDATED -> serializer<UserUpdatedMessage>()
		SessionMessageType.SERIES_TIMER_CREATED -> serializer<SeriesTimerCreatedMessage>()
		SessionMessageType.TIMER_CREATED -> serializer<TimerCreatedMessage>()
		SessionMessageType.SERIES_TIMER_CANCELLED -> serializer<SeriesTimerCancelledMessage>()
		SessionMessageType.TIMER_CANCELLED -> serializer<TimerCancelledMessage>()
		SessionMessageType.REFRESH_PROGRESS -> serializer<RefreshProgressMessage>()
		SessionMessageType.SCHEDULED_TASK_ENDED -> serializer<ScheduledTaskEndedMessage>()
		SessionMessageType.PACKAGE_INSTALLATION_CANCELLED -> serializer<PackageInstallationCancelledMessage>()
		SessionMessageType.PACKAGE_INSTALLATION_FAILED -> serializer<PackageInstallationFailedMessage>()
		SessionMessageType.PACKAGE_INSTALLATION_COMPLETED -> serializer<PackageInstallationCompletedMessage>()
		SessionMessageType.PACKAGE_INSTALLING -> serializer<PackageInstallingMessage>()
		SessionMessageType.PACKAGE_UNINSTALLED -> serializer<PackageUninstalledMessage>()
		SessionMessageType.ACTIVITY_LOG_ENTRY -> serializer<ActivityLogEntryMessage>()
		SessionMessageType.SCHEDULED_TASKS_INFO -> serializer<ScheduledTasksInfoMessage>()

		// Shared type, only implemented as outgoing message
		SessionMessageType.KEEP_ALIVE -> serializer<KeepAliveMessage>()

		// Send only - should not be possible to receive
		SessionMessageType.ACTIVITY_LOG_ENTRY_START -> serializer<ActivityLogEntryStartMessage>()
		SessionMessageType.ACTIVITY_LOG_ENTRY_STOP -> serializer<ActivityLogEntryStopMessage>()
		SessionMessageType.SESSIONS_START -> serializer<SessionsStartMessage>()
		SessionMessageType.SESSIONS_STOP -> serializer<SessionsStopMessage>()
		SessionMessageType.SCHEDULED_TASKS_INFO_START -> serializer<ScheduledTasksInfoStartMessage>()
		SessionMessageType.SCHEDULED_TASKS_INFO_STOP -> serializer<ScheduledTasksInfoStopMessage>()
	}
