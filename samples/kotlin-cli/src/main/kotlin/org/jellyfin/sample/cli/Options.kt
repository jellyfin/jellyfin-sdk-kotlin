package org.jellyfin.sample.cli

import com.github.ajalt.clikt.core.ParameterHolder
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.extensions.authenticateUserByName
import org.jellyfin.sdk.api.client.extensions.userApi

fun ParameterHolder.serverOption() = option(
	"-s", "--server",
	help = "Url of the server",
	envvar = "JELLYFIN_SERVER"
).required()

fun ParameterHolder.apiInstanceHolder(jellyfin: Jellyfin): Lazy<ApiClient> {
	val server = serverOption().also(::registerOption)

	val token = option(
		"-t", "--token",
		help = "Access token",
		envvar = "JELLYFIN_TOKEN"
	).also(::registerOption)

	val username = option(
		"-u", "--username",
		help = "Username"
	).also(::registerOption)

	val password = option(
		"-p", "--password",
		help = "Password"
	).also(::registerOption)

	return lazy {
		runBlocking {
			// Create instance
			val api = jellyfin.createApi(baseUrl = server.value, accessToken = token.value)

			// Authenticate manually if access token is not provided
			if (api.accessToken == null && username.value != null) {
				val response by api.userApi.authenticateUserByName(username.value.orEmpty(), password.value.orEmpty())
				api.update(accessToken = response.accessToken)
			}

			// Return
			api
		}
	}
}
