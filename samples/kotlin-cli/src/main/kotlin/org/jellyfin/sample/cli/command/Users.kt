package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.runBlocking
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.operations.UserApi
import org.jellyfin.sample.cli.serverOption

class Users(
	private val jellyfin: Jellyfin
) : CliktCommand("List all public users") {
	private val server by serverOption()

	override fun run() = runBlocking {
		val api = jellyfin.createApi(baseUrl = server)
		val userApi = UserApi(api)

		val users by userApi.getPublicUsers()

		users.forEach {
			println(it.name)
		}
	}
}
