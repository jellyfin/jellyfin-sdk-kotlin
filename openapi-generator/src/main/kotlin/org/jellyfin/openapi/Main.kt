package org.jellyfin.openapi

import org.jellyfin.openapi.hooks.hooksModule
import org.koin.dsl.koinApplication
import java.io.File
import kotlin.system.exitProcess

fun main(vararg args: String) {
	// Read arguments
	val arguments = args.asArguments()
	val openApiFile by arguments
	val apiOutputDir by arguments
	val modelsOutputDir by arguments
	val verify = arguments.containsKey("verify")

	// Read OpenAPI json
	val openApiJson = File(openApiFile).readText()

	// Start Koin
	val koin = koinApplication { modules(mainModule, hooksModule) }.koin
	val generator = koin.get<Generator>()

	if (verify) {
		// Verify OpenAPI output
		val valid = generator.verify(
			openApiJson = openApiJson,
			apiOutputDir = File(apiOutputDir),
			modelsOutputDir = File(modelsOutputDir)
		)

		if (!valid) exitProcess(1)
	} else {
		// Generate OpenAPI output
		generator.generate(
			openApiJson = openApiJson,
			apiOutputDir = File(apiOutputDir),
			modelsOutputDir = File(modelsOutputDir)
		)
	}
}
