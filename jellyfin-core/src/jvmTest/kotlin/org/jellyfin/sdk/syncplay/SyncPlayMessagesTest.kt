package org.jellyfin.sdk.syncplay

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SyncPlayMessagesTest {
    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun testGroupUpdateSerialization() {
        val msg = SyncPlayGroupUpdate(
            GroupId = "group1",
            Users = listOf(SyncPlayUser("user1", true)),
            NowPlayingItem = SyncPlayNowPlayingItem("item1", 123L, false)
        )
        val str = json.encodeToString(msg)
        val parsed = json.decodeFromString<SyncPlayGroupUpdate>(str)
        assertEquals(msg, parsed)
    }

    @Test
    fun testPauseSerialization() {
        val msg = SyncPlayPause(GroupId = "group1", PositionTicks = 456L)
        val str = json.encodeToString(msg)
        val parsed = json.decodeFromString<SyncPlayPause>(str)
        assertEquals(msg, parsed)
    }

    @Test
    fun testErrorSerialization() {
        val msg = SyncPlayError(ErrorMessage = "error!")
        val str = json.encodeToString(msg)
        val parsed = json.decodeFromString<SyncPlayError>(str)
        assertEquals(msg, parsed)
    }
}
