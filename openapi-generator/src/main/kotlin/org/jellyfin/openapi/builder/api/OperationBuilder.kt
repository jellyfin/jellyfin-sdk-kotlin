package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.ApiServiceOperationParameter

class OperationBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder
) : Builder<ApiServiceOperation, FunSpec> {
	private fun buildFunctionShell(data: ApiServiceOperation) = FunSpec.builder(data.name).apply {
		// Make function suspended
		addModifiers(KModifier.SUSPEND)

		// Add description
		data.description?.let { addKdoc("%L", it) }

		// Add deprecated annotation
		if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))

		// Set return type
		returns(ClassName(Packages.API_CLIENT, Classes.API_RESPONSE).plusParameter(data.returnType))
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

		// Add request body
		if (data.bodyType != null) addParameter(ParameterSpec("data", data.bodyType))
		else addStatement("val data = null")

		// Call API
		addStatement(
			"val response = api.%N<%T>(%S, pathParameters, queryParameters, data)",
			data.method.toString().toLowerCase(),
			data.returnType,
			data.pathTemplate
		)

		// Return response
		addStatement("return·response")
	}.build()
}
