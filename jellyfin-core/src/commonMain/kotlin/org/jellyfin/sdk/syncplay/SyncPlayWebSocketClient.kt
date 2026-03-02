package org.jellyfin.sdk.syncplay

/**
 * WebSocket client for SyncPlay communication.
 *
 * Security best practices:
 * - Always use wss:// URLs for secure connections.
 * - Validate serverUrl and authToken before connecting.
 * - Never log sensitive tokens.
 */
public expect class SyncPlayWebSocketClient() {
    /**
     * Connect to the SyncPlay WebSocket server.
     * @param serverUrl The wss:// URL of the server.
     * @param authToken The authentication token.
     * @throws IllegalArgumentException if serverUrl is not secure or invalid.
     */
    public fun connect(serverUrl: String, authToken: String): Unit

    /**
     * Disconnect from the server.
     */
    public fun disconnect(): Unit

    /**
     * Send a SyncPlay message.
     */
    public fun sendMessage(message: SyncPlayMessage): Unit

    /**
     * Set the listener for WebSocket events.
     */
    public fun setListener(listener: SyncPlayListener): Unit
}

/**
 * Listener for SyncPlay WebSocket events.
 * All callbacks are posted to the main thread.
 */
public interface SyncPlayListener {
    /** Called when the connection is established. */
    public fun onOpen(): Unit
    /** Called when a SyncPlay message is received. */
    public fun onMessage(message: SyncPlayMessage): Unit
    /** Called on error. */
    public fun onError(error: Throwable): Unit
    /** Called when the connection is closed. */
    public fun onClosed(code: Int, reason: String): Unit
}
