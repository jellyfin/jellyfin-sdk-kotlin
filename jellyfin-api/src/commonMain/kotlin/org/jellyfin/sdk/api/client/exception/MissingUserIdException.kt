package org.jellyfin.sdk.api.client.exception

public class MissingUserIdException(
	cause: Throwable? = null,
) : ApiClientException(
	"Required value userId is null. Provide it by setting ApiClient.userId or passing it in the function call.",
	cause
)
