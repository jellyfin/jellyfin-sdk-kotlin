package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.authenticateUserByName
import org.jellyfin.sdk.api.operations.UserApi
import org.jellyfin.sample.cli.serverOption

class Login(
	private val jellyfin: Jellyfin
) : CliktCommand("Login to a given server and retrieve an access token") {
	private val server by serverOption()
	private val username by option("-u", "--username", help = "Username").required()
	private val password by option("-p", "--password", help = "Password")

	override fun run() = runBlocking {
		val api = jellyfin.createApi(baseUrl = server)
		val userApi = UserApi(api)

		val result by userApi.authenticateUserByName(username, password.orEmpty())

		if (result.accessToken != null) println(result.accessToken)
	}
}
