package org.jellyfin.sdk.api.client

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
	 * Timeout for a complete HTTP request in milliseconds.
	 * Defaults to 30 seconds.
	 */
	val requestTimeout: Long = 30_000,

	/**
	 * Timeout for connecting to the server in milliseconds.
	 * Defaults to 6 seconds.
	 */
	val connectTimeout: Long = 6_000,

	/**
	 * Timeout between receiving or writing messages in milliseconds.
	 * Defaults to 10 seconds.
	 */
	val socketTimeout: Long = 10_000,
)
