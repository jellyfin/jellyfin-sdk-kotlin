package org.jellyfin.sdk.api.client.exception

/**
 * The HTTP status was not in the 200 range.
 */
public class InvalidStatusException(
	public val status: Int,
) : ApiClientException("Invalid HTTP status in response: $status")
