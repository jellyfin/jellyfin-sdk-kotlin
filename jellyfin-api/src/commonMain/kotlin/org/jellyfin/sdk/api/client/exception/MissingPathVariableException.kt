package org.jellyfin.sdk.api.client.exception

/**
 * A variable in the path template is missing.
 */
public class MissingPathVariableException(
	public val name: String,
	public val path: String
) : ApiClientException("Missing path variable $name for path $path")
