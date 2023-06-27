package org.jellyfin.sdk.api.client.util

import io.ktor.utils.io.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.jellyfin.sdk.model.api.InboundWebSocketMessage
import org.jellyfin.sdk.model.api.OutboundWebSocketMessage

public object ApiSerializer {
	public val json: Json = Json {
		isLenient = false
		ignoreUnknownKeys = true
		allowSpecialFloatingPointValues = true
		useArrayPolymorphism = false
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

	public fun encodeSocketMessage(message: InboundWebSocketMessage): String =
		json.encodeToString(InboundWebSocketMessage.serializer(), message)

	public fun decodeSocketMessage(message: String): OutboundWebSocketMessage =
		json.decodeFromString<OutboundWebSocketMessage>(message)
}
