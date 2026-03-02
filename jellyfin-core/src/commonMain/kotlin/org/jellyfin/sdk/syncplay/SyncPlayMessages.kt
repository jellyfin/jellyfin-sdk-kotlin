package org.jellyfin.sdk.syncplay

import kotlinx.serialization.Serializable

/**
 * Base class for all SyncPlay WebSocket messages.
 */
@Serializable
public sealed class SyncPlayMessage {
    public abstract val MessageType: String
}

/**
 * Sent by the server to update group state.
 */
@Serializable
public data class SyncPlayGroupUpdate(
    override val MessageType: String = "SyncPlayGroupUpdate",
    val GroupId: String,
    val Users: List<SyncPlayUser>,
    val NowPlayingItem: SyncPlayNowPlayingItem?
) : SyncPlayMessage()

/**
 * Represents a user in a SyncPlay group.
 */
@Serializable
public data class SyncPlayUser(
    val UserId: String,
    val IsLeader: Boolean
)

/**
 * Represents the currently playing item in a group.
 */
@Serializable
public data class SyncPlayNowPlayingItem(
    val ItemId: String,
    val PositionTicks: Long,
    val IsPaused: Boolean
)

/**
 * Sent by the client to join a group.
 */
@Serializable
public data class SyncPlayJoinGroup(
    override val MessageType: String = "SyncPlayJoinGroup",
    val GroupId: String
) : SyncPlayMessage()

/**
 * Sent by the client to leave a group.
 */
@Serializable
public data class SyncPlayLeaveGroup(
    override val MessageType: String = "SyncPlayLeaveGroup",
    val GroupId: String
) : SyncPlayMessage()

/**
 * Sent by the client to pause playback.
 */
@Serializable
public data class SyncPlayPause(
    override val MessageType: String = "SyncPlayPause",
    val GroupId: String,
    val PositionTicks: Long
) : SyncPlayMessage()

/**
 * Sent by the client to unpause playback.
 */
@Serializable
public data class SyncPlayUnpause(
    override val MessageType: String = "SyncPlayUnpause",
    val GroupId: String,
    val PositionTicks: Long
) : SyncPlayMessage()

/**
 * Sent by the client to seek playback.
 */
@Serializable
public data class SyncPlaySeek(
    override val MessageType: String = "SyncPlaySeek",
    val GroupId: String,
    val PositionTicks: Long
) : SyncPlayMessage()

/**
 * Sent by the server to indicate an error.
 */
@Serializable
public data class SyncPlayError(
    override val MessageType: String = "SyncPlayError",
    val ErrorMessage: String
) : SyncPlayMessage()
