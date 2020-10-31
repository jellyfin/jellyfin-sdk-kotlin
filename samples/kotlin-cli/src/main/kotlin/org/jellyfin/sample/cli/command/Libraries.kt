package org.jellyfin.sample.cli.command

import kotlinx.cli.ArgType
import kotlinx.cli.Subcommand
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking
import org.jellyfin.apiclient.Jellyfin
import org.jellyfin.apiclient.api.operations.SessionApi
import org.jellyfin.apiclient.api.operations.UserViewsApi

class Libraries(
	private val jellyfin: Jellyfin
) : Subcommand("libraries", "List all libraries") {
	private val server by option(ArgType.String, description = "Url of the server", shortName = "s").required()
	private val token by option(ArgType.String, description = "Access token", shortName = "t").required()

	override fun execute(): Unit = runBlocking {
		val api = jellyfin.createApi(baseUrl = server, accessToken = token)
		val sessionApi = SessionApi(api)
		val userViewsApi = UserViewsApi(api)

		val sessionInfo = sessionApi.getSessions(deviceId = api.deviceInfo.id).content.firstOrNull()
		if (sessionInfo == null) println("Unknown session")

		val libraries by userViewsApi.getUserViews(sessionInfo!!.userId, includeHidden = false)

		if (libraries.items.isNullOrEmpty()) println("No libraries found")

		libraries.items?.forEach {
			println(it.name)
		}
	}
}
