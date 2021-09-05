package org.jellyfin.sdk.api.client

import io.ktor.utils.io.*
import org.jellyfin.sdk.api.client.util.ApiSerializer

public class RawResponse(
	public val body: ByteReadChannel,
	public val status: Int,
	public val headers: Map<String, List<String>>,
) {
	public suspend inline fun <reified T : Any> createContent(): T = ApiSerializer.decodeResponseBody(body)

	public suspend inline fun <reified T : Any> createResponse(): Response<T> =
		Response(createContent(), status, headers)
}
