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
	 * Timeout before a request failed. In milliseconds.
	 * Defaults to 6 seconds.
	 */
	val timeout: Long = 6_000
)
