package org.jellyfin.openapi

import org.jellyfin.openapi.hooks.hooksModule
import org.koin.dsl.koinApplication
import java.io.File

fun main(vararg args: String) {
	// Read arguments
	val arguments = args.asArguments()
	val openApiFile by arguments
	val apiOutputDir by arguments
	val modelsOutputDir by arguments

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
