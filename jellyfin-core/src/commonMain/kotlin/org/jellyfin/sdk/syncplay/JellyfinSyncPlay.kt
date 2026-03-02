package org.jellyfin.sdk.syncplay

import org.jellyfin.sdk.Jellyfin

/**
 * Integration class for SyncPlay in the Jellyfin SDK.
 *
 * @property jellyfin The Jellyfin instance.
 * @property wsClient The SyncPlay WebSocket client.
 * @property syncPlayManager The manager for SyncPlay group and playback events.
 */
public class JellyfinSyncPlay(
    private val jellyfin: Jellyfin,
    private val wsClient: SyncPlayWebSocketClient
) {
    /**
     * The SyncPlay manager for group and playback actions.
     */
    public val syncPlayManager: SyncPlayManager = SyncPlayManager(wsClient)

    /**
     * Connect to the SyncPlay WebSocket server.
     * @param serverUrl The wss:// URL of the server.
     * @param authToken The authentication token.
     * @param listener The listener for SyncPlay events.
     */
    public fun connect(serverUrl: String, authToken: String, listener: SyncPlayListener): Unit {
        wsClient.setListener(listener)
        wsClient.connect(serverUrl, authToken)
    }

    /**
     * Disconnect from the SyncPlay server.
     */
    public fun disconnect(): Unit {
        wsClient.disconnect()
    }
}
