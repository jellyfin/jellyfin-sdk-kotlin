package org.jellyfin.sdk.tests

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.jellyfin.sdk.createJellyfin
import org.jellyfin.sdk.api.client.exception.ApiClientException
import org.jellyfin.sdk.api.client.extensions.authenticateUserByName
import org.jellyfin.sdk.api.client.extensions.itemsApi
import org.jellyfin.sdk.api.client.extensions.syncPlayApi
import org.jellyfin.sdk.api.client.extensions.systemApi
import org.jellyfin.sdk.api.client.extensions.userApi
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.api.GroupQueueMode
import org.jellyfin.sdk.model.api.GroupRepeatMode
import org.jellyfin.sdk.model.api.GroupShuffleMode
import org.jellyfin.sdk.model.api.JoinGroupRequestDto
import org.jellyfin.sdk.model.api.MovePlaylistItemRequestDto
import org.jellyfin.sdk.model.api.NewGroupRequestDto
import org.jellyfin.sdk.model.api.PingRequestDto
import org.jellyfin.sdk.model.api.PlayRequestDto
import org.jellyfin.sdk.model.api.QueueRequestDto
import org.jellyfin.sdk.model.api.ReadyRequestDto
import org.jellyfin.sdk.model.api.RemoveFromPlaylistRequestDto
import org.jellyfin.sdk.model.api.SeekRequestDto
import org.jellyfin.sdk.model.api.SetPlaylistItemRequestDto
import org.jellyfin.sdk.model.api.SetRepeatModeRequestDto
import org.jellyfin.sdk.model.api.SetShuffleModeRequestDto
import org.jellyfin.sdk.model.api.SyncPlayGroupUpdateMessage
import org.jellyfin.sdk.model.api.SyncPlayPlayQueueUpdate
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.request.GetItemsRequest
import java.time.LocalDateTime
import java.io.File
import java.util.UUID

private const val DEFAULT_WS_DURATION_MS = 10_000L

fun main() = runBlocking(Dispatchers.Default) {
    val env = loadEnv()

    val serverUrl = requireSetting(env, "JELLYFIN_SERVER")
    val clientName = env["JELLYFIN_CLIENT_NAME"] ?: "Jellyfin SDK Test"
    val clientVersion = env["JELLYFIN_CLIENT_VERSION"] ?: "0.0.0"
    val deviceName = env["JELLYFIN_DEVICE_NAME"] ?: "Test Device"
    val deviceId = env["JELLYFIN_DEVICE_ID"] ?: UUID.randomUUID().toString()

    val jellyfin = createJellyfin {
        clientInfo = ClientInfo(name = clientName, version = clientVersion)
        deviceInfo = DeviceInfo(id = deviceId, name = deviceName)
    }

    val api = jellyfin.createApi(baseUrl = serverUrl)
    val primaryUserId = authenticateApi(
        api = api,
        apiKey = env["JELLYFIN_API_KEY"],
        username = env["JELLYFIN_USERNAME"],
        password = env["JELLYFIN_PASSWORD"],
        label = "primary"
    )

    val systemInfo = api.systemApi.getPublicSystemInfo().content
    println("Server: ${systemInfo.serverName} ${systemInfo.version}")

    val listItems = env["JELLYFIN_LIST_ITEMS"]?.toBooleanStrictOrNull() ?: false
    if (listItems) {
        listItemIds(
            api = api,
            userId = primaryUserId,
            env = env
        )
    }

    val syncPlayEnabled = env["JELLYFIN_SYNCPLAY"]?.toBooleanStrictOrNull() ?: true
    val wsEnabled = env["JELLYFIN_WEBSOCKET"]?.toBooleanStrictOrNull() ?: true
    val wsDurationMs = env["JELLYFIN_WS_DURATION_MS"]?.toLongOrNull() ?: DEFAULT_WS_DURATION_MS

    val playlistItemIds = mutableSetOf<UUID>()
    val wsJob = if (wsEnabled || syncPlayEnabled) {
        println("Listening for WebSocket events for ${wsDurationMs}ms...")
        launch {
            withTimeoutOrNull(if (wsEnabled) wsDurationMs else Long.MAX_VALUE) {
                api.webSocket.subscribeAll().collect { message ->
                    println("WS: ${message::class.simpleName}")
                    val update = (message as? SyncPlayGroupUpdateMessage)?.data as? SyncPlayPlayQueueUpdate
                    val queue = update?.data?.playlist
                    if (!queue.isNullOrEmpty()) {
                        queue.map { it.playlistItemId }.forEach { playlistId ->
                            if (playlistItemIds.add(playlistId)) {
                                println("Discovered playlist item id: ${playlistId}")
                            }
                        }
                    }
                }
            }
        }
    } else {
        null
    }

    if (syncPlayEnabled) {
        val secondApi = jellyfin.createApi(baseUrl = serverUrl)
        authenticateApi(
            api = secondApi,
            apiKey = env["JELLYFIN_SECOND_API_KEY"],
            username = env["JELLYFIN_SECOND_USERNAME"],
            password = env["JELLYFIN_SECOND_PASSWORD"],
            label = "secondary"
        )

        val groupName = env["JELLYFIN_SYNCPLAY_GROUP_NAME"] ?: "SDK Test Group"
        val groupInfo = api.syncPlayApi.syncPlayCreateGroup(
            data = NewGroupRequestDto(groupName = groupName)
        ).content
        val groupId = groupInfo.groupId
        println("SyncPlay group created: ${groupInfo.groupName} (${groupId})")

        api.syncPlayApi.syncPlayJoinGroup(JoinGroupRequestDto(groupId))
        secondApi.syncPlayApi.syncPlayJoinGroup(JoinGroupRequestDto(groupId))
        println("Both users joined SyncPlay group")

        api.syncPlayApi.syncPlayPing(PingRequestDto(ping = System.currentTimeMillis()))
        api.syncPlayApi.syncPlayPause()
        api.syncPlayApi.syncPlaySeek(SeekRequestDto(positionTicks = 10_000L))

        val itemIds = parseUuidList(env["JELLYFIN_SYNCPLAY_ITEM_IDS"])
        if (itemIds.isNotEmpty()) {
            api.syncPlayApi.syncPlaySetNewQueue(
                PlayRequestDto(
                    playingQueue = itemIds,
                    playingItemPosition = 0,
                    startPositionTicks = 0L
                )
            )
            api.syncPlayApi.syncPlayQueue(
                QueueRequestDto(itemIds = itemIds, mode = GroupQueueMode.QUEUE)
            )
            api.syncPlayApi.syncPlaySetShuffleMode(SetShuffleModeRequestDto(GroupShuffleMode.SHUFFLE))
            api.syncPlayApi.syncPlaySetRepeatMode(SetRepeatModeRequestDto(GroupRepeatMode.REPEAT_ALL))
        }

        val playlistItemId = parseUuidOrNull(env["JELLYFIN_SYNCPLAY_PLAYLIST_ITEM_ID"], "JELLYFIN_SYNCPLAY_PLAYLIST_ITEM_ID")
            ?: playlistItemIds.firstOrNull()
        if (playlistItemId != null) {
            api.syncPlayApi.syncPlaySetPlaylistItem(SetPlaylistItemRequestDto(playlistItemId))
            api.syncPlayApi.syncPlayReady(
                ReadyRequestDto(
                    `when` = LocalDateTime.now(),
                    positionTicks = 0L,
                    isPlaying = true,
                    playlistItemId = playlistItemId
                )
            )
            api.syncPlayApi.syncPlayNextItem(org.jellyfin.sdk.model.api.NextItemRequestDto(playlistItemId))
            api.syncPlayApi.syncPlayPreviousItem(org.jellyfin.sdk.model.api.PreviousItemRequestDto(playlistItemId))
            api.syncPlayApi.syncPlayMovePlaylistItem(MovePlaylistItemRequestDto(playlistItemId, newIndex = 0))
            api.syncPlayApi.syncPlayRemoveFromPlaylist(
                RemoveFromPlaylistRequestDto(
                    playlistItemIds = listOf(playlistItemId),
                    clearPlaylist = false,
                    clearPlayingItem = false
                )
            )
        } else {
            println("No playlist item id available. Provide JELLYFIN_SYNCPLAY_PLAYLIST_ITEM_ID or wait for queue update.")
        }

        api.syncPlayApi.syncPlayLeaveGroup()
        secondApi.syncPlayApi.syncPlayLeaveGroup()
        println("SyncPlay tests completed")
    }

    if (wsJob != null) {
        if (!wsEnabled) {
            wsJob.cancel()
        } else {
            wsJob.join()
            delay(250)
        }
    }

    println("Integration test completed.")
}

private suspend fun authenticateApi(
    api: org.jellyfin.sdk.api.client.ApiClient,
    apiKey: String?,
    username: String?,
    password: String?,
    label: String,
): UUID? {
    if (!apiKey.isNullOrBlank()) {
        api.update(accessToken = apiKey)
        println("Authenticated ${label} via API key")
        return null
    }

    if (username.isNullOrBlank() || password.isNullOrBlank()) {
        error("Missing auth for ${label}. Provide API key or username/password.")
    }

    try {
        val result by api.userApi.authenticateUserByName(username, password)
        api.update(accessToken = result.accessToken)
        println("Authenticated ${label} as ${result.user?.name ?: "unknown"}")
        return result.user?.id
    } catch (err: ApiClientException) {
        error("Authentication failed for ${label}: ${err.message}")
    }
}

private suspend fun listItemIds(
    api: org.jellyfin.sdk.api.client.ApiClient,
    userId: UUID?,
    env: Map<String, String>
): Unit {
    val limit = env["JELLYFIN_ITEMS_LIMIT"]?.toIntOrNull() ?: 20
    val parentId = parseUuidOrNull(env["JELLYFIN_ITEMS_PARENT_ID"], "JELLYFIN_ITEMS_PARENT_ID")
    val types = parseItemTypes(env["JELLYFIN_ITEMS_INCLUDE_TYPES"])

    val request = GetItemsRequest(
        userId = userId,
        parentId = parentId,
        limit = limit,
        includeItemTypes = if (types.isEmpty()) null else types
    )

    val result = api.itemsApi.getItems(request).content
    val items = result.items.orEmpty()
    if (items.isEmpty()) {
        println("No items found for listing")
        return
    }

    println("Item IDs (limit=$limit):")
    items.forEach { item ->
        println("- ${item.id} | ${item.name} | ${item.type}")
    }
}

private fun parseItemTypes(raw: String?): List<BaseItemKind> {
    if (raw.isNullOrBlank()) return emptyList()
    return raw.split(',')
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .mapNotNull { BaseItemKind.fromNameOrNull(it) }
}

private fun parseUuidList(raw: String?): List<UUID> {
    if (raw.isNullOrBlank()) return emptyList()
    return raw.split(',')
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .mapNotNull { value ->
            parseUuidOrNull(value, "JELLYFIN_SYNCPLAY_ITEM_IDS")
        }
}

private fun parseUuidOrNull(value: String?, label: String): UUID? {
    if (value.isNullOrBlank()) return null
    val normalized = normalizeUuid(value)
    return try {
        UUID.fromString(normalized)
    } catch (err: IllegalArgumentException) {
        println("Invalid UUID for ${label}: ${value}")
        null
    }
}

private fun normalizeUuid(value: String): String {
    val trimmed = value.trim()
    if (trimmed.length == 32 && trimmed.all { it.isDigit() || it.lowercaseChar() in 'a'..'f' }) {
        return "${trimmed.substring(0, 8)}-${trimmed.substring(8, 12)}-${trimmed.substring(12, 16)}-" +
            "${trimmed.substring(16, 20)}-${trimmed.substring(20)}"
    }
    return trimmed
}

private fun loadEnv(): Map<String, String> {
    val envFiles = listOf(
        File("tests/.env"),
        File(".env")
    )

    val fileVars = envFiles.firstOrNull { it.exists() }?.let { parseEnvFile(it) } ?: emptyMap()
    val systemVars = System.getenv()

    return fileVars + systemVars
}

private fun parseEnvFile(file: File): Map<String, String> {
    return file.readLines()
        .map { it.trim() }
        .filter { it.isNotEmpty() && !it.startsWith("#") }
        .mapNotNull { line ->
            val idx = line.indexOf('=')
            if (idx <= 0) return@mapNotNull null
            val key = line.substring(0, idx).trim()
            val value = line.substring(idx + 1).trim()
            key to value
        }
        .toMap()
}

private fun requireSetting(env: Map<String, String>, key: String): String {
    return env[key]?.takeIf { it.isNotBlank() }
        ?: error("Missing required setting: $key")
}
