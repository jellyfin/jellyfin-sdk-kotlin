package org.jellyfin.sdk.api.client.extensions

import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.operations.UserApi
import org.jellyfin.sdk.model.api.AuthenticateUserByName
import org.jellyfin.sdk.model.api.AuthenticationResult
import org.jellyfin.sdk.model.api.QuickConnectDto

/**
 * Extension function for the authenticateUserByName operation that accepts the username and password directly
 */
public suspend inline fun UserApi.authenticateUserByName(
	username: String,
	password: String,
): Response<AuthenticationResult> = authenticateUserByName(
	data = AuthenticateUserByName(
		username = username,
		pw = password
	)
)

/**
 * Extension function for the authenticateWithQuickConnect operation that accepts the secret directly
 */
public suspend inline fun UserApi.authenticateWithQuickConnect(
	secret: String,
): Response<AuthenticationResult> = authenticateWithQuickConnect(
	data = QuickConnectDto(
		secret = secret
	)
)
