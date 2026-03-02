package org.jellyfin.sdk.syncplay

/**
 * Manages SyncPlay group and playback events.
 * @property wsClient The WebSocket client for SyncPlay communication.
 */
public class SyncPlayManager(private val wsClient: SyncPlayWebSocketClient) {

    /**
     * Create a SyncPlay group. Implementation may require a REST call.
     */
    public fun createGroup(groupId: String): Unit {
        // Implementation depends on server API, may require REST call
    }

    /**
     * Join a SyncPlay group.
     * @param groupId The group to join.
     */
    public fun joinGroup(groupId: String): Unit {
        try {
            wsClient.sendMessage(SyncPlayJoinGroup(GroupId = groupId))
        } catch (e: Exception) {
            // Log or handle error
            throw SyncPlayException("Failed to join group", e)
        }
    }

    /**
     * Leave a SyncPlay group.
     * @param groupId The group to leave.
     */
    public fun leaveGroup(groupId: String): Unit {
        try {
            wsClient.sendMessage(SyncPlayLeaveGroup(GroupId = groupId))
        } catch (e: Exception) {
            throw SyncPlayException("Failed to leave group", e)
        }
    }

    /**
     * Pause playback for the group.
     */
    public fun pause(groupId: String, positionTicks: Long): Unit {
        try {
            wsClient.sendMessage(SyncPlayPause(GroupId = groupId, PositionTicks = positionTicks))
        } catch (e: Exception) {
            throw SyncPlayException("Failed to send pause", e)
        }
    }

    /**
     * Unpause playback for the group.
     */
    public fun unpause(groupId: String, positionTicks: Long): Unit {
        try {
            wsClient.sendMessage(SyncPlayUnpause(GroupId = groupId, PositionTicks = positionTicks))
        } catch (e: Exception) {
            throw SyncPlayException("Failed to send unpause", e)
        }
    }

    /**
     * Seek playback for the group.
     */
    public fun seek(groupId: String, positionTicks: Long): Unit {
        try {
            wsClient.sendMessage(SyncPlaySeek(GroupId = groupId, PositionTicks = positionTicks))
        } catch (e: Exception) {
            throw SyncPlayException("Failed to send seek", e)
        }
    }
}

/**
 * Exception for SyncPlay errors.
 */
public class SyncPlayException(message: String, cause: Throwable? = null) : Exception(message, cause)
