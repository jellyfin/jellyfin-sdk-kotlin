package org.jellyfin.sdk.api.client.exception

/**
 * The body of the response was invalid or the serializer for the requested model was not found.
 */
public class InvalidContentException(
	message: String?,
) : ApiClientException(message)
