package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.serverOption
import org.jellyfin.sample.cli.tokenOption
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.detectBitrate
import org.jellyfin.sdk.api.client.extensions.measureBitrate
import org.jellyfin.sdk.api.operations.MediaInfoApi

class Bitrate(
	private val jellyfin: Jellyfin,
) : CliktCommand("Detect or measure bitrate") {
	private val server by serverOption()
	private val token by tokenOption()
	private val bytes by option(
		"--bytes", "-b",
		help = "Amount of bytes to request from the server. Leave empty to detect automatically."
	).int()

	override fun run(): Unit = runBlocking {
		val api = jellyfin.createApi(baseUrl = server, accessToken = token)
		val mediaInfoApi = MediaInfoApi(api)

		val measurement = when {
			bytes != null -> mediaInfoApi.measureBitrate(bytes!!)
			else -> mediaInfoApi.detectBitrate()
		}

		echo(buildString {
			appendLine("Requested ${measurement.bytes} bytes from the server.")
			appendLine("Reply took ${measurement.duration}ms.")
			appendLine("Measured bitrate is ${measurement.bitrate} (${measurement.bitrate / 1000000} megabit).")
		})
	}
}
