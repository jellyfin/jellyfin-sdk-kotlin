package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.serverOption
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.user

class Users(
	private val jellyfin: Jellyfin
) : CliktCommand("List all public users") {
	private val server by serverOption()

	override fun run() = runBlocking {
		val api = jellyfin.createApi(baseUrl = server)
		val userApi = api.user

		val users by userApi.getPublicUsers()

		users.forEach {
			println(it.name)
		}
	}
}
