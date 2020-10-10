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
	 * Check if the HTTP response status is equal to a given status.
	 *
	 * ```kt
	 * val isOk = response isStatus 200
	 * val isOk = response.isStatus(200)
	 * ```
	 */
	infix fun isStatus(comparable: Int) = status == comparable

	/**
	 * Check if the HTTP response status is not equal to a given status.
	 *
	 * ```kt
	 * val isNotOk = response isNotStatus 200
	 * val isNotOk = response.isNotStatus(200)
	 * ```
	 */
	infix fun isNotStatus(comparable: Int) = status != comparable

	/**
	 * Check if the HTTP response status is in a given range.
	 *
	 * ```kt
	 * val isOk = response isStatusIn (200 until 300)
	 * val isOk = response.isStatusIn(IntRange(200, 299))
	 * ```
	 */
	infix fun isStatusIn(comparable: IntRange) = status in comparable

	/**
	 * Check if the HTTP response status is in the 2xx range.
	 */
	fun isStatus200() = this isStatusIn (200 until 300)

	/**
	 * Check if the HTTP response status is in the 4xx range.
	 */
	fun isStatus400() = this isStatusIn (400 until 500)

	/**
	 * Check if the HTTP response status is in the 5xx range.
	 */
	fun isStatus500() = this isStatusIn (500 until 600)

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
