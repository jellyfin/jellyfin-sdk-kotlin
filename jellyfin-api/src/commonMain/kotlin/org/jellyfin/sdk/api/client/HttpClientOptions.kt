package org.jellyfin.sdk.api.client

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Options to use when making HTTP requests.
 */
public data class HttpClientOptions(
	/**
	 * Follows HTTP redirect responses if set to true.
	 * Defaults to true.
	 */
	val followRedirects: Boolean = true,

	/**
	 * Timeout for a complete HTTP request.
	 * Defaults to 30 seconds.
	 */
	val requestTimeout: Duration = 30.seconds,

	/**
	 * Timeout for connecting to the server.
	 * Defaults to 6 seconds.
	 */
	val connectTimeout: Duration = 6.seconds,

	/**
	 * Timeout between receiving or writing messages.
	 * Defaults to 30 seconds.
	 */
	val socketTimeout: Duration = 30.seconds,
)
