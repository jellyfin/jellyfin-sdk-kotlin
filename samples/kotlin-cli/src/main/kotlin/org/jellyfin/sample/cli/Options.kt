package org.jellyfin.sample.cli

import com.github.ajalt.clikt.core.ParameterHolder
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

fun ParameterHolder.serverOption() = option(
	"-s", "--server",
	help = "Url of the server",
	envvar = "JELLYFIN_SERVER"
).required()

fun ParameterHolder.tokenOption() = option(
	"-t", "--token",
	help = "Access token",
	envvar = "JELLYFIN_TOKEN"
).required()
