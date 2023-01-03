package org.jellyfin.openapi

import io.swagger.v3.parser.OpenAPIV3Parser
import mu.KotlinLogging
import org.jellyfin.openapi.builder.api.ApiClientExtensionsBuilder
import org.jellyfin.openapi.builder.api.ApisBuilder
import org.jellyfin.openapi.builder.model.ModelsBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiApiServicesBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiConstantsBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiModelsBuilder
import org.jellyfin.openapi.compare.InfoComparator
import org.jellyfin.openapi.compare.ModelComparator
import org.jellyfin.openapi.compare.OperationComparator
import org.jellyfin.openapi.compare.model.CompareResult
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.model.GeneratorContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

private val logger = KotlinLogging.logger { }

class Generator : KoinComponent {
	private val openApiApiServicesBuilder by inject<OpenApiApiServicesBuilder>()
	private val apisBuilder by inject<ApisBuilder>()
	private val openApiModelsBuilder by inject<OpenApiModelsBuilder>()
	private val modelsBuilder by inject<ModelsBuilder>()
	private val apiClientExtensionsBuilder by inject<ApiClientExtensionsBuilder>()
	private val openApiConstantsBuilder by inject<OpenApiConstantsBuilder>()
	private val infoComparator by inject<InfoComparator>()
	private val operationComparator by inject<OperationComparator>()
	private val modelComparator by inject<ModelComparator>()

	private fun generateInternal(openApiJson: String): GeneratorContext {
		val openApiSpecification = OpenAPIV3Parser().readContents(openApiJson)
		openApiSpecification.messages.forEach { message -> logger.warn { message } }

		val context = GeneratorContext(openApiSpecification)

		// Generate intermediate models
		openApiModelsBuilder.build(context, context.schemas)
		openApiApiServicesBuilder.build(context, context.paths)

		// Generate code
		modelsBuilder.build(context, context.models)
		apisBuilder.build(context, context.apiServices)

		// Add meta files
		apiClientExtensionsBuilder.build(context, context.apiServices)
		openApiConstantsBuilder.build(context, context.info)

		return context
	}

	fun verify(
		openApiJson: String,
		apiOutputDir: File,
		modelsOutputDir: File,
	): Boolean {
		val verification = Verification(apiOutputDir, modelsOutputDir)
		val result = generateInternal(openApiJson)

		return verification.verify(result.toGeneratorResult())
	}

	fun generate(
		openApiJson: String,
		apiOutputDir: File,
		modelsOutputDir: File,
	) {
		val result = generateInternal(openApiJson).toGeneratorResult()

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

	fun compare(
		oldOpenApiJson: String,
		newOpenApiJson: String,
	): CompareResult {
		// Create generator contexts for both schemas
		val oldSchema = generateInternal(oldOpenApiJson)
		val newSchema = generateInternal(newOpenApiJson)

		// Construct and return compare result
		return CompareResult(
			binaryDifference = oldOpenApiJson != newOpenApiJson,
			info = infoComparator.compare(oldSchema, newSchema),
			api = operationComparator.compare(oldSchema, newSchema),
			model = modelComparator.compare(oldSchema, newSchema),
		)
	}
}
