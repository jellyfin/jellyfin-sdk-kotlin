package org.jellyfin.openapi

import org.jellyfin.openapi.hooks.hooksModule
import org.koin.dsl.koinApplication
import java.io.File

fun Array<out String>.asArguments(): Map<String, String> {
	val iterator = iterator()
	var key: String? = null
	val arguments = mutableMapOf<String, String>()

	while (iterator.hasNext()) {
		val value = iterator.next()
		if (value.startsWith("--")) key = value.removePrefix("--")
		else if (key != null) arguments[key] = value
	}

	return arguments
}

fun main(vararg args: String) {
	// Read arguments
	val arguments = args.asArguments()
	val openApiFile = arguments["openApiFile"] ?: throw Error("Missing argument --openApiFile")
	val apiOutputDir = arguments["apiOutputDir"] ?: throw Error("Missing argument --apiOutputDir")
	val modelsOutputDir = arguments["modelsOutputDir"] ?: throw Error("Missing argument --modelsOutputDir")

	// Read OpenAPI json
	val openApiJson = File(openApiFile).readText()

	// Start Koin
	val koin = koinApplication { modules(mainModule, hooksModule) }.koin

	// Generate OpenAPI output
	koin.get<Generator>().generate(
		openApiJson = openApiJson,
		apiOutputDir = File(apiOutputDir),
		modelsOutputDir = File(modelsOutputDir)
	)
}
