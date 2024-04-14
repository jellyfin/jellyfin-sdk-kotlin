package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.apiInstanceHolder
import org.jellyfin.sample.cli.logger
import org.jellyfin.sdk.Jellyfin

class Login(
	jellyfin: Jellyfin
) : CliktCommand("Login to a given server and retrieve an access token") {
	private val logger by logger()
	private val api by apiInstanceHolder(jellyfin)

	override fun run() = runBlocking {
		if (api.accessToken != null) logger.info(api.accessToken)
	}
}
