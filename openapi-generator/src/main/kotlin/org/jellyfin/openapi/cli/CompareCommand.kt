package org.jellyfin.openapi.cli

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.file
import org.jellyfin.openapi.Generator
import org.jellyfin.openapi.compare.reporter.CompareReporter
import org.koin.core.component.inject

class CompareCommand : BaseCommand() {
	private val generator by inject<Generator>()
	private val reporters by lazy { getKoin().getAll<CompareReporter>() }

	private val oldOpenApiFile by argument(
		help = "The old OpenAPI JSON file"
	).file(
		mustExist = true,
		canBeFile = true,
		canBeDir = false,
		mustBeReadable = true,
	)

	private val newOpenApiFile by argument(
		help = "The new OpenAPI JSON file"
	).file(
		mustExist = true,
		canBeFile = true,
		canBeDir = false,
		mustBeReadable = true,
	)

	private val reporter by option(
		names = arrayOf("--format", "-f"),
		help = "The format to use"
	).choice(
		choices = reporters.associateBy { it.name }
	).default(
		value = reporters.first()
	)

	private val output by option(
		names = arrayOf("--output", "-o"),
		help = "The output"
	).file(
		canBeFile = true,
		canBeDir = false,
	)

	override fun run() {
		// Read OpenAPI json
		val oldOpenApiJson = oldOpenApiFile.readText()
		val newOpenApiJson = newOpenApiFile.readText()

		// Compare specifications
		val result = generator.compare(oldOpenApiJson, newOpenApiJson)
		val formatted = reporter.format(result)

		// Write output
		if (output != null) {
			output!!.writeText(formatted)
			println("Output written to ${output!!.absolutePath}")
		} else {
			println(formatted)
		}
	}
}
