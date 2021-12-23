package org.jellyfin.sdk.api.client.util

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readRemaining
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@OptIn(InternalSerializationApi::class)
public object ApiSerializer {
	public val json: Json = Json {
		isLenient = false
		ignoreUnknownKeys = true
		allowSpecialFloatingPointValues = true
		useArrayPolymorphism = false
	}

	public fun encodeRequestBody(requestBody: Any? = null): String? {
		if (requestBody == null) return null

		return json.encodeToString(requestBody::class.serializer() as KSerializer<Any>, requestBody)
	}

	public suspend inline fun <reified T : Any> decodeResponseBody(responseBody: ByteReadChannel): T = when {
		T::class == Unit::class -> Unit as T
		T::class == ByteReadChannel::class -> responseBody as T
		else -> json.decodeFromString(responseBody.readRemaining().readText())
	}
}
