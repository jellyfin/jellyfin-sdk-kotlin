package org.jellyfin.apiclient.api.client.extensions

import org.jellyfin.apiclient.api.client.Response
import org.jellyfin.apiclient.api.operations.UserApi
import org.jellyfin.apiclient.model.api.AuthenticateUserByName
import org.jellyfin.apiclient.model.api.AuthenticationResult

/**
 * Extension function for the authenticateUserByName operation that accepts the username and password directly
 */
public suspend inline fun UserApi.authenticateUserByName(
	username: String,
	password: String
): Response<AuthenticationResult> = authenticateUserByName(
	data = AuthenticateUserByName(
		username = username,
		pw = password
	)
)
