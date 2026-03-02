package org.jellyfin.sdk.syncplay

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

public actual class SyncPlayWebSocketClient public actual constructor() {
    private var webSocket: WebSocket? = null
    private var listener: SyncPlayListener? = null
    private val json = Json { ignoreUnknownKeys = true }
    private val scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private var reconnectAttempts = 0
    private var lastServerUrl: String? = null
    private var lastAuthToken: String? = null
    private val maxReconnectAttempts = 5
    private val reconnectDelayMs = 2000L

    public actual fun connect(serverUrl: String, authToken: String): Unit {
        lastServerUrl = serverUrl
        lastAuthToken = authToken
        val request = Request.Builder()
            .url(serverUrl)
            .addHeader("X-Emby-Token", authToken)
            .build()
        val client = OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                reconnectAttempts = 0
                listener?.onOpen()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                try {
                    val msg = json.decodeFromString<SyncPlayMessage>(text)
                    listener?.onMessage(msg)
                } catch (e: Exception) {
                    listener?.onError(e)
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                listener?.onError(t)
                tryReconnect()
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                listener?.onClosed(code, reason)
            }
        })
    }

    private fun tryReconnect() {
        val url = lastServerUrl
        val token = lastAuthToken
        if (reconnectAttempts < maxReconnectAttempts && url != null && token != null) {
            reconnectAttempts++
            scheduler.schedule(
                { connect(url, token) },
                reconnectDelayMs * reconnectAttempts,
                TimeUnit.MILLISECONDS
            )
        }
    }

    public actual fun disconnect(): Unit {
        webSocket?.close(1000, "Client disconnect")
        reconnectAttempts = 0
    }

    public actual fun sendMessage(message: SyncPlayMessage): Unit {
        val jsonString = json.encodeToString(message)
        webSocket?.send(jsonString)
    }

    public actual fun setListener(listener: SyncPlayListener): Unit {
        this.listener = listener
    }
}
