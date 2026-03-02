package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.logger
import org.jellyfin.sample.cli.serverOption
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.userApi

class Users(
	private val jellyfin: Jellyfin
) : CliktCommand(name = "users") {
	private val logger by logger()
	private val server by serverOption()

	override fun help(context: Context): String = "List all public users"

	override fun run() = runBlocking {
		val api = jellyfin.createApi(baseUrl = server)

		val users by api.userApi.getPublicUsers()

		users.forEach {
			logger.info(it.name)
		}
	}
}
