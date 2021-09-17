package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.serverOption
import org.jellyfin.sample.cli.tokenOption
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.session
import org.jellyfin.sdk.api.client.extensions.userViews

class Libraries(
	private val jellyfin: Jellyfin
) : CliktCommand("List all libraries") {
	private val server by serverOption()
	private val token by tokenOption()

	override fun run(): Unit = runBlocking {
		val api = jellyfin.createApi(baseUrl = server, accessToken = token)
		val sessionApi = api.session
		val userViewsApi = api.userViews

		val sessionInfo = sessionApi.getSessions(deviceId = api.deviceInfo.id).content.firstOrNull()
		if (sessionInfo == null) println("Unknown session")
		api.userId = sessionInfo?.userId

		val libraries by userViewsApi.getUserViews(includeHidden = false)

		if (libraries.items.isNullOrEmpty()) println("No libraries found")

		libraries.items?.forEach {
			println(it.name)
		}
	}
}
