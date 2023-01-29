package org.jellyfin.sdk.api.client

import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.SerializationException
import mu.KotlinLogging
import org.jellyfin.sdk.api.client.exception.InvalidContentException
import org.jellyfin.sdk.api.client.util.ApiSerializer

public class RawResponse(
	public val body: ByteReadChannel,
	public val status: Int,
	public val headers: Map<String, List<String>>,
) {
	public suspend inline fun <reified T : Any> createContent(): T {
		val logger = KotlinLogging.logger {}

		return try {
			ApiSerializer.decodeResponseBody(body)
		} catch (err: SerializationException) {
			logger.error(err) { "Deserialization failed" }
			throw InvalidContentException("Deserialization failed", err)
		}
	}

	public suspend inline fun <reified T : Any> createResponse(): Response<T> =
		Response(createContent(), status, headers)
}
