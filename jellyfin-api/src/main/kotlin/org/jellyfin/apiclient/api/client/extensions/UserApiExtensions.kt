package org.jellyfin.apiclient.api.client.extensions

import org.jellyfin.apiclient.api.operations.UserApi
import org.jellyfin.apiclient.model.api.AuthenticateUserByName

/**
 * Extension function for the authenticateUserByName operation that accepts the username and password directly
 */
suspend inline fun UserApi.authenticateUserByName(username: String, password: String) = authenticateUserByName(
	data = AuthenticateUserByName(
		username = username,
		pw = password
	)
)
