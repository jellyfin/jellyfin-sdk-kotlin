package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.ApiServiceOperationParameter
import org.jellyfin.openapi.model.CustomDefaultValue

open class OperationBuilder(
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
) : Builder<ApiServiceOperation, FunSpec> {
	protected open fun buildFunctionShell(data: ApiServiceOperation) = FunSpec.builder(data.name).apply {
		// Make function suspended
		addModifiers(KModifier.SUSPEND)

		// Add description
		data.description?.let { addKdoc("%L", it) }

		// Add deprecated annotation
		if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))

		// Set return type
		returns(ClassName(Packages.API_CLIENT, Classes.API_RESPONSE).plusParameter(data.returnType))
	}

	protected fun buildParameter(data: ApiServiceOperationParameter) = ParameterSpec.builder(data.name, data.type).apply {
		// Determine class name without parameters
		val typeClassName = when (data.type) {
			is ClassName -> data.type
			is ParameterizedTypeName -> data.type.rawType
			else -> null
		}

		// Set default value
		when (data.defaultValue) {
			is String -> defaultValue("%S", data.defaultValue)
			is Int -> defaultValue("%L", data.defaultValue)
			is Boolean -> defaultValue("%L", data.defaultValue)
			is CustomDefaultValue -> defaultValue(data.defaultValue.build())
			// Set value to null by default for nullable values
			null -> when {
				typeClassName == List::class.asClassName() -> defaultValue("%N()", "emptyList")
				typeClassName == Map::class.asClassName() -> defaultValue("%N()", "emptyMap")
				data.type.isNullable -> defaultValue("%L", "null")
			}
		}

		// Add description
		data.description?.let { addKdoc("%L", it) }
	}.build()


	protected fun FunSpec.Builder.addParameterMapStatements(
		name: String,
		parameters: Collection<ApiServiceOperationParameter>,
	) {
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
		if (data.bodyType != null) {
			addParameter(ParameterSpec.builder("data", data.bodyType).apply {
				// Set default value to null if parameter is nullable
				if (data.bodyType.isNullable) defaultValue("%L", "null")
			}.build())
		} else {
			// No data parameter needed, use a null value
			addStatement("val data = null")
		}

		// Call API
		addStatement(
			"val response = api.%N<%T>(%S, pathParameters, queryParameters, data)",
			data.method.toString().toLowerCase(),
			data.returnType,
			data.pathTemplate
		)

		// Return response
		addStatement("return response")
	}.build()
}
