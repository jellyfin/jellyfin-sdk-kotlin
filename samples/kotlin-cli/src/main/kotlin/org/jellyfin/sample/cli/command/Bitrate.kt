package org.jellyfin.sample.cli.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import kotlinx.coroutines.runBlocking
import org.jellyfin.sample.cli.serverOption
import org.jellyfin.sample.cli.tokenOption
import org.jellyfin.sdk.Jellyfin
import org.jellyfin.sdk.api.client.extensions.mediaInfoApi
import org.jellyfin.sdk.api.extensions.detectBitrate
import org.jellyfin.sdk.api.extensions.measureBitrate

class Bitrate(
	private val jellyfin: Jellyfin,
) : CliktCommand("Detect or measure bitrate") {
	private companion object {
		private const val MEGABIT = 1000000
	}

	private val server by serverOption()
	private val token by tokenOption()
	private val bytes by option(
		"--bytes", "-b",
		help = "Amount of bytes to request from the server. Leave empty to detect automatically."
	).int()

	override fun run(): Unit = runBlocking {
		val api = jellyfin.createApi(baseUrl = server, accessToken = token)

		val measurement = when {
			bytes != null -> api.mediaInfoApi.measureBitrate(bytes!!)
			else -> api.mediaInfoApi.detectBitrate()
		}

		echo(buildString {
			appendLine("Requested ${measurement.bytes} bytes from the server.")
			appendLine("Reply took ${measurement.duration}ms.")
			appendLine("Measured bitrate is ${measurement.bitrate} (${measurement.bitrate / MEGABIT} megabit).")
		})
	}
}
