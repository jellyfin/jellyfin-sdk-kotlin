package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.FileSpec
import io.swagger.v3.oas.models.Paths
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.parser.core.models.SwaggerParseResult
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.openapi.OpenApiModelBuilder

class GeneratorContext(
	openApiSpecification: SwaggerParseResult,
	private val openApiModelBuilder: OpenApiModelBuilder,
) {
	// Phase 1: Parse
	val schemas: Map<String, Schema<Any>> = openApiSpecification.openAPI.components.schemas
	val paths: Paths = openApiSpecification.openAPI.paths
	val info: Info = openApiSpecification.openAPI.info

	// Phase 2: OpenAPI -> Intermediate model
	private val _models = mutableMapOf<String, ApiModel>()
	val models: Collection<ApiModel> get() = _models.values

	private val _apiServices = mutableListOf<ApiService>()
	val apiServices: Collection<ApiService> get() = _apiServices

	fun getOrGenerateModel(reference: String): ApiModel {
		val name = reference.removePrefix("#/components/schemas/")

		return _models.getOrPut(name) {
			val schema = requireNotNull(schemas[name]) { "Invalid schema $reference" }

			if (schema.name == null) schema.name = name.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL)
			openApiModelBuilder.build(this, schema)
		}
	}

	operator fun plusAssign(service: ApiService) {
		_apiServices.add(service)
	}

	// Phase 3: Intermediate model -> Code
	private val _files = mutableListOf<FileSpec>()
	private val files: Collection<FileSpec> get() = _files

	operator fun plusAssign(file: FileSpec) {
		_files.add(file)
	}

	fun toGeneratorResult() = GeneratorResult(files.toList())
}
