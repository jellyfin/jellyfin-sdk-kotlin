package org.jellyfin.sdk.api.client

import kotlinx.serialization.SerializationException
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.exception.InvalidContentException
import org.jellyfin.sdk.api.client.util.ApiSerializer

public class RawResponse(
	public val body: ByteArray,
	public val status: Int,
	public val headers: Map<String, List<String>>,
) {
	public suspend inline fun <reified T : Any> createContent(): T {
		val logger = KotlinLogging.logger {}

		return try {
			when {
				T::class == Unit::class -> Unit as T
				T::class == ByteArray::class -> body as T
				else -> ApiSerializer.decodeResponseBody(body.decodeToString())
			}
		} catch (err: SerializationException) {
			logger.error(err) { "Deserialization failed" }
			throw InvalidContentException("Deserialization failed", err)
		}
	}

	public suspend inline fun <reified T : Any> createResponse(): Response<T> =
		Response(createContent(), status, headers)
}
