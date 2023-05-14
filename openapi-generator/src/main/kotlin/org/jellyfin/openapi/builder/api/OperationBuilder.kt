package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.buildCodeBlock
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.builder.extra.toCodeBlock
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.ApiServiceOperationParameter
import org.jellyfin.openapi.model.DescriptionType
import org.jellyfin.openapi.model.IntRangeValidation
import org.jellyfin.openapi.model.ParameterValidation

open class OperationBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
) : Builder<ApiServiceOperation, FunSpec> {
	protected open fun buildFunctionShell(data: ApiServiceOperation) = FunSpec.builder(data.name).apply {
		// Make function suspended
		addModifiers(KModifier.SUSPEND)

		// Add description
		descriptionBuilder.build(DescriptionType.OPERATION, data.description)?.let {
			addKdoc("%L", it)
		}

		// Add deprecated annotation
		if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))

		// Set return type
		returns(ClassName(Packages.API_CLIENT, Classes.API_RESPONSE).plusParameter(data.returnType))
	}

	protected fun buildParameter(
		data: ApiServiceOperationParameter,
	) = ParameterSpec.builder(data.name, data.type).apply {
		defaultValue(data.defaultValue.toCodeBlock(data.type, true))

		// Add description
		descriptionBuilder.build(DescriptionType.OPERATION_PARAMETER, data.description)?.let {
			addKdoc("%L", it)
		}
	}.build()

	private fun FunSpec.Builder.createParameterValidation(
		name: String,
		validation: ParameterValidation,
	) = when (validation) {
		is IntRangeValidation -> addStatement(
			"%M(%N·in·%L..%L)·{·%S·}",
			MemberName("kotlin", "require"),
			name,
			validation.min,
			validation.max,
			"Parameter \"$name\" must be in range ${validation.min}..${validation.max} (inclusive)."
		)
	}

	protected fun FunSpec.Builder.addParameterMapStatements(
		name: String,
		parameters: Collection<ApiServiceOperationParameter>,
	) {
		if (parameters.isEmpty()) {
			val emptyMapType = MemberName("kotlin.collections", "emptyMap")
			addStatement("val·%N·=·%M<%T,·%T?>()", name, emptyMapType, Types.STRING, Types.ANY)
			return
		}

		buildCodeBlock {
			// Parameter validation
			parameters.forEach { parameter ->
				if (parameter.validation != null) createParameterValidation(parameter.name, parameter.validation)
			}
			// Map building
			val buildMapType = MemberName("kotlin.collections", "buildMap")
			beginControlFlow("val·%N·= %M<%T, %T?>(%L)", name, buildMapType, Types.STRING, Types.ANY, parameters.size)
			parameters.forEach { parameter ->
				addStatement("put(%S, %N)", parameter.originalName, parameter.name)
			}
			endControlFlow()
		}.let(::addCode)
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
			"val response = %L.%M<%T>(%S, pathParameters, queryParameters, data)",
			Strings.API_CLIENT_PARAMETER_NAME,
			MemberName(Packages.API_METHODS, data.method.toString().lowercase(), true),
			data.returnType,
			data.pathTemplate
		)

		// Return response
		addStatement("return response")
	}.build()
}
