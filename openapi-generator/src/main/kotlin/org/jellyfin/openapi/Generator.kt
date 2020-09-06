package org.jellyfin.openapi

import com.squareup.kotlinpoet.FileSpec
import io.swagger.v3.oas.models.Paths
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.parser.OpenAPIV3Parser
import io.swagger.v3.parser.core.models.SwaggerParseResult
import org.jellyfin.openapi.builder.api.ApiBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiApiServicesBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiModelBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.model.JellyFile
import java.io.File

class Generator(
	private val fileSpecBuilder: FileSpecBuilder,
	private val openApiModelBuilder: OpenApiModelBuilder,
	private val openApiApiServicesBuilder: OpenApiApiServicesBuilder,
	private val apiBuilder: ApiBuilder
) {
	private fun parse(openApiJson: String): SwaggerParseResult {
		val parseResult = OpenAPIV3Parser().readContents(openApiJson)
		parseResult.messages.forEach { println(it) }
		return parseResult
	}

	private fun createModels(schemas: Map<String, Schema<Any>>) = schemas.map { (name, schema) ->
		if (schema.name == null) schema.name = name

		openApiModelBuilder.build(schema)
			.let { JellyFile(Packages.MODEL, it) }
			.let(fileSpecBuilder::build)
	}

	private fun createApis(paths: Paths): List<FileSpec> = openApiApiServicesBuilder.build(paths)
		.map(apiBuilder::build)
		.map { JellyFile(Packages.API, it) }
		.map(fileSpecBuilder::build)

	fun generate(
		openApiJson: String,
		apiOutputDir: File,
		modelsOutputDir: File
	) {
		val parseResult = parse(openApiJson)

		// Get relevant OpenAPI parts
		val schemas = parseResult.openAPI.components.schemas
		val paths = parseResult.openAPI.paths

		// Clear output directories
		modelsOutputDir.deleteRecursively()
		apiOutputDir.deleteRecursively()

		// Create models
		createModels(schemas).forEach { file -> file.writeTo(modelsOutputDir) }
		// Create API operations
		createApis(paths).forEach { file -> file.writeTo(apiOutputDir) }
	}
}
