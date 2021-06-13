package org.jellyfin.sdk.api.client.exception

public class MissingUserIdException : ApiClientException(
	message = "Required value userId is null. Provide it by setting ApiClient.userId or passing it in the function call."
)
