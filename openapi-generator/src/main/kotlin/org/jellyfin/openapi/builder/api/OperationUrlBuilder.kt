package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.ApiServiceOperationParameter

class OperationUrlBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder
) : Builder<ApiServiceOperation, FunSpec> {
	private fun buildFunctionShell(data: ApiServiceOperation) = FunSpec.builder(data.name + "Url").apply {
		// Add description
		data.description?.let { addKdoc("%L", it) }

		// Add deprecated annotation
		if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))

		// Set return type
		returns(String::class)
	}

	private fun buildParameter(data: ApiServiceOperationParameter) = ParameterSpec.builder(data.name, data.type).apply {
		// Set value to null by default for nullable values
		if (data.type.isNullable) defaultValue("%L", "null")

		// Add description
		data.description?.let { addKdoc("%L", it) }

		// Add deprecated annotation
		if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))
	}.build()


	private fun FunSpec.Builder.addParameterMapStatements(name: String, parameters: Collection<ApiServiceOperationParameter>) {
		// Create map
		// val $(name) = $("emptyMap"|"mutableMapOf")<String, Any?>()
		val mapType = if (parameters.isEmpty()) "emptyMap" else "mutableMapOf"
		addStatement("val %N = %N<%T, %T?>()", name, mapType, String::class, Any::class)

		// Add parameters
		parameters.forEach { parameter ->
			// $(name)[$(parameter.originalName)] = parameter.name
			addStatement("%L[%S] = %N", name, parameter.originalName, parameter.name)
		}
	}

	override fun build(data: ApiServiceOperation): FunSpec = buildFunctionShell(data).apply {
		// Add parameters receivers
		data.pathParameters
			.union(data.queryParameters)
			.forEach { parameter -> addParameter(buildParameter(parameter)) }

		// Create parameter maps
		addParameterMapStatements("pathParameters", data.pathParameters)
		addParameterMapStatements("queryParameters", data.queryParameters)

		// Call API
		addStatement(
			"return api.createUrl(%S, pathParameters, queryParameters)",
			data.pathTemplate
		)
	}.build()
}
