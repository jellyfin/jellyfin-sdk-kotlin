package org.jellyfin.openapi

import io.swagger.v3.parser.OpenAPIV3Parser
import mu.KotlinLogging
import org.jellyfin.openapi.builder.api.ApiClientExtensionsBuilder
import org.jellyfin.openapi.builder.api.ApisBuilder
import org.jellyfin.openapi.builder.model.ModelsBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiApiServicesBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiConstantsBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiModelsBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.GeneratorResult
import java.io.File

private val logger = KotlinLogging.logger { }

class Generator(
	private val openApiApiServicesBuilder: OpenApiApiServicesBuilder,
	private val apisBuilder: ApisBuilder,
	private val openApiModelsBuilder: OpenApiModelsBuilder,
	private val modelsBuilder: ModelsBuilder,
	private val apiClientExtensionsBuilder: ApiClientExtensionsBuilder,
	private val openApiConstantsBuilder: OpenApiConstantsBuilder,
) {
	private fun generateInternal(openApiJson: String): GeneratorResult {
		val openApiSpecification = OpenAPIV3Parser().readContents(openApiJson)
		openApiSpecification.messages.forEach { message -> logger.warn { message } }

		val context = GeneratorContext(openApiSpecification)

		// Generate API
		openApiApiServicesBuilder.build(context, context.paths)
		apisBuilder.build(context, context.apiServices)

		// Generate models
		openApiModelsBuilder.build(context, context.schemas)
		modelsBuilder.build(context, context.models)

		// Add meta files
		apiClientExtensionsBuilder.build(context, context.apiServices)
		openApiConstantsBuilder.build(context, context.info)

		return context.toGeneratorResult()
	}

	fun verify(
		openApiJson: String,
		apiOutputDir: File,
		modelsOutputDir: File,
	): Boolean {
		val verification = Verification(apiOutputDir, modelsOutputDir)
		val result = generateInternal(openApiJson)

		return verification.verify(result)
	}

	fun generate(
		openApiJson: String,
		apiOutputDir: File,
		modelsOutputDir: File,
	) {
		val result = generateInternal(openApiJson)

		// Clear output directories
		modelsOutputDir.deleteRecursively()
		apiOutputDir.deleteRecursively()

		// Create models
		for (file in result.files) {
			val directory = when {
				file.packageName.startsWith(Packages.API) -> apiOutputDir
				file.packageName.startsWith(Packages.API_CLIENT_EXTENSIONS) -> apiOutputDir
				file.packageName.startsWith(Packages.API_CONSTANTS) -> apiOutputDir
				file.packageName.startsWith(Packages.MODEL) -> modelsOutputDir
				else -> throw OpenApiGeneratorError("Unable to determine directory for package ${file.packageName}")
			}
			file.writeTo(directory)
		}
	}
}
