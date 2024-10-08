package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.apiInstanceHolder
import org.jellyfin.sample.cli.logger
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.userViewsApi

class Libraries(
	jellyfin: Jellyfin
) : CliktCommand(name = "libraries") {
	private val logger by logger()
	private val api by apiInstanceHolder(jellyfin)

	override fun help(context: Context): String = "List all libraries"

	override fun run(): Unit = runBlocking {
		val libraries by api.userViewsApi.getUserViews(includeHidden = false)

		if (libraries.items.isNullOrEmpty()) logger.info("No libraries found")

		libraries.items?.forEach {
			logger.info(it.name)
		}
	}
}
