package org.jellyfin.openapi.cli

import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import org.jellyfin.openapi.Generator
import org.koin.core.component.inject

class GenerateCommand : BaseCommand() {
	private val generator by inject<Generator>()

	private val openApiFile by option(
		"--openApiFile",
		"-i",
		help = "The OpenAPI JSON file"
	).file(
		mustExist = true,
		canBeFile = true,
		canBeDir = false,
		mustBeReadable = true,
	).required()

	private val apiOutputDir by option(
		"--apiOutputDir",
		"-oa",
		help = "The directory to place generated API sources"
	).file(
		mustExist = false,
		canBeFile = false,
		canBeDir = true,
		mustBeWritable = true,
	).required()

	private val modelsOutputDir by option(
		"--modelsOutputDir",
		"-om",
		help = "The directory to place generated model sources"
	).file(
		mustExist = false,
		canBeFile = false,
		canBeDir = true,
		mustBeWritable = true,
	).required()

	override fun run() {
		// Read OpenAPI json
		val openApiJson = openApiFile.readText()

		// Generate OpenAPI output
		generator.generate(
			openApiJson = openApiJson,
			apiOutputDir = apiOutputDir,
			modelsOutputDir = modelsOutputDir,
		)
	}
}
