package org.jellyfin.apiclient.api.client

import io.ktor.http.*
import kotlin.reflect.KProperty

/**
 * Response from a HTTP class in the [ApiClient].
 *
 * @param status - See [HttpStatusCode]
 */
class Response<T>(
	val content: T,
	val status: Int,
	val headers: Map<String, List<String>>
) {
	/**
	 * Get the response content using property delegation.
	 *
	 * ```kt
	 * val content by response
	 * ```
	 */
	@JvmSynthetic
	operator fun getValue(thisRef: Any?, property: KProperty<*>): T = content

	/**
	 * Get a header by name. If multiple headers with the name exist the first is returned.
	 * Use [getHeaders] to get all headers with [name].
	 */
	fun getHeader(name: String) = headers[name]?.firstOrNull()

	/**
	 * Get multiple headers sharing the same name. Use [getHeader] to retrieve the first occurrence.
	 */
	fun getHeaders(name: String) = headers[name]
}
