package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.apiInstanceHolder
import org.jellyfin.sample.cli.logger
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.scheduledTasksApi

class Tasks(
	jellyfin: Jellyfin,
) : CliktCommand(name = "tasks") {
	private val logger by logger()
	private val api by apiInstanceHolder(jellyfin)

	override fun help(context: Context): String = "List scheduled tasks"

	override fun run(): Unit = runBlocking {
		val tasks by api.scheduledTasksApi.getTasks()

		for (task in tasks) {
			logger.info("${task.name}: ${task.description}")
		}
	}
}
