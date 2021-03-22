package org.jellyfin.sdk.api.client

import io.ktor.http.*
import kotlin.reflect.KProperty

/**
 * Response from a HTTP class in the [ApiClient].
 *
 * @param status - See [HttpStatusCode]
 */
public class Response<T>(
	public val content: T,
	public val status: Int,
	public val headers: Map<String, List<String>>
) {
	/**
	 * Get the response content using property delegation.
	 *
	 * ```kt
	 * val content by response
	 * ```
	 */
	@JvmSynthetic
	public operator fun getValue(thisRef: Any?, property: KProperty<*>): T = content

	/**
	 * Get a header by name. If multiple headers with the name exist the first is returned.
	 * Use [getHeaders] to get all headers with [name].
	 */
	public fun getHeader(name: String): String? = getHeaders(name).firstOrNull()

	/**
	 * Get multiple headers sharing the same name. Use [getHeader] to retrieve the first occurrence.
	 */
	public fun getHeaders(name: String): List<String> = headers[name].orEmpty()
}
