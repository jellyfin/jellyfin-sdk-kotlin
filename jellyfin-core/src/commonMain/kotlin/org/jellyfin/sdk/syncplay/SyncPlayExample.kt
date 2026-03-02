package org.jellyfin.sdk.syncplay

import org.jellyfin.sdk.Jellyfin

/**
 * Example usage of SyncPlay integration in an Android app.
 *
 * Shows how to connect, join a group, send playback events, and handle events.
 */
internal fun exampleSyncPlayUsage(jellyfin: Jellyfin, serverUrl: String, authToken: String): Unit {
    val wsClient = SyncPlayWebSocketClient()
    val syncPlay = jellyfin.syncPlay(wsClient)

    syncPlay.connect(serverUrl, authToken, object : SyncPlayListener {
        override fun onOpen() {
            println("WebSocket connected!")
            syncPlay.syncPlayManager.joinGroup("group-id")
        }
        override fun onMessage(message: SyncPlayMessage) {
            println("Received: $message")
        }
        override fun onError(error: Throwable) {
            println("Error: $error")
        }
        override fun onClosed(code: Int, reason: String) {
            println("Closed: $code $reason")
        }
    })

    // Example: Pause playback
    syncPlay.syncPlayManager.pause("group-id", 123456789L)

    // Example: Leave group
    syncPlay.syncPlayManager.leaveGroup("group-id")

    // When done
    syncPlay.disconnect()
}
