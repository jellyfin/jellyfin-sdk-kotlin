package org.jellyfin.sdk.api.client.exception

/**
 * Exception for when we are looking for redirects and don't find one
 */
public class NoRedirectException(
	public val status: Int,
	cause: Throwable? = null,
) : ApiClientException("HTTP status is not a redirect. status: $status", cause)
