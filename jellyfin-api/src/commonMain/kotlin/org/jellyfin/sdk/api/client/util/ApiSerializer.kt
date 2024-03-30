package org.jellyfin.sdk.api.client.util

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readRemaining
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import kotlinx.serialization.serializer
import org.jellyfin.sdk.api.sockets.data.serializer
import org.jellyfin.sdk.model.api.SessionMessageType
import org.jellyfin.sdk.model.socket.IncomingSocketMessage
import org.jellyfin.sdk.model.socket.OutgoingSocketMessage
import org.jellyfin.sdk.model.socket.RawIncomingSocketMessage

public object ApiSerializer {
	private const val SOCKET_MESSAGE_DATA = "Data"
	private const val SOCKET_MESSAGE_MESSAGE_ID = "MessageId"
	private const val SOCKET_MESSAGE_MESSAGE_TYPE = "MessageType"

	public val json: Json = Json {
		// Require strict JSON syntax
		isLenient = false
		// Properties from newer API versions should be ignored
		ignoreUnknownKeys = true
		// Allow NaN and Infinity values for numbers
		allowSpecialFloatingPointValues = true
		// Deprecated fields may be removed in a newer Jellyfin version, fall back to null if possible
		explicitNulls = false
		// Unknown enum members should fall back to a default value (null/other member)
		coerceInputValues = true
	}

	private val jsonSocketMessage: Json = Json(json) {
		encodeDefaults = true
	}

	@OptIn(InternalSerializationApi::class)
	public fun encodeRequestBody(requestBody: Any? = null): String? {
		if (requestBody == null) return null

		return json.encodeToString(requestBody::class.serializer() as KSerializer<Any>, requestBody)
	}

	public suspend inline fun <reified T : Any> decodeResponseBody(responseBody: ByteReadChannel): T = when {
		T::class == Unit::class -> Unit as T
		T::class == ByteReadChannel::class -> responseBody as T
		else -> json.decodeFromString(responseBody.readRemaining().readText())
	}

	@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
	public fun encodeSocketMessage(message: OutgoingSocketMessage): String {
		// Serialize with default serializer
		val serializer = message::class.serializer() as KSerializer<Any>
		val jsonObject = jsonSocketMessage.encodeToJsonElement(serializer, message).jsonObject

		// Extract type name
		val messageType = serializer.descriptor.serialName

		// Create actual message
		return jsonSocketMessage.encodeToString(buildJsonObject {
			// Set type property
			put(SOCKET_MESSAGE_MESSAGE_TYPE, messageType)

			// Set data property
			val data = jsonObject[SOCKET_MESSAGE_DATA]
			if (data != null) put(SOCKET_MESSAGE_DATA, data)
			else putJsonObject(SOCKET_MESSAGE_DATA) {
				jsonObject.entries
					.filterNot { (key, _) -> key == SOCKET_MESSAGE_MESSAGE_TYPE }
					.forEach { (key, value) -> put(key, value) }
			}
		})
	}

	public fun decodeSocketMessage(message: String): IncomingSocketMessage? {
		val rawMessage = jsonSocketMessage.decodeFromString<RawIncomingSocketMessage>(message)

		// The KeepAliveMessage type is used for both sending and receiving
		// the SDK doesn't support this behavior, so we need to ignore
		// it for now. It's not that useful for a client anyway.
		if (rawMessage.type == SessionMessageType.KEEP_ALIVE) return null

		// Modify JSON to flatten the Data object
		val modifiedJson = buildJsonObject {
			put(SOCKET_MESSAGE_MESSAGE_ID, rawMessage.id.toString())

			val data = rawMessage.data
			if (data is JsonObject) data.entries.forEach { (key, value) -> put(key, value) }
			if (data != null) put(SOCKET_MESSAGE_DATA, data)
		}

		return jsonSocketMessage.decodeFromJsonElement(rawMessage.type.serializer, modifiedJson) as? IncomingSocketMessage
	}
}
