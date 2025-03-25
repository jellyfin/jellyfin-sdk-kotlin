package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.withIndent
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.ApiServiceOperationParameter
import org.jellyfin.openapi.model.ApiServiceOperationRequestBody
import org.jellyfin.openapi.model.DefaultValue
import org.jellyfin.openapi.model.DescriptionType

class OperationParameterModelBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
) : OperationBuilder(descriptionBuilder, deprecatedAnnotationSpecBuilder) {
	private fun FunSpec.Builder.addOperationCall(
		operationName: String,
		requestParameterName: String,
		parameters: Collection<String>,
		includeData: Boolean,
	) = buildCodeBlock {
		require(parameters.isNotEmpty()) { "At least 1 parameter expected to generate a valid function call" }

		add("return %N(\n", operationName)
		withIndent {
			parameters.forEach { parameter ->
				addStatement("%1N = %2N.%1N,", parameter, requestParameterName)
			}
			if (includeData) addStatement("%1N = %1N,", "data")
		}
		add(")\n")
	}.let(::addCode)

	override fun build(data: ApiServiceOperation): FunSpec = buildFunctionShell(data).apply {
		val requestParameterType = ClassName(
			Packages.MODEL_REQUEST,
			data.name.toPascalCase() + Strings.MODEL_REQUEST_SUFFIX
		)
		val parameters = data.pathParameters + data.queryParameters

		ParameterSpec.builder(Strings.MODEL_REQUEST_PARAMETER_NAME, requestParameterType).apply {
			descriptionBuilder.build(DescriptionType.OPERATION_PARAMETER, Strings.MODEL_REQUEST_PARAMETER_DESCRIPTION)?.let {
				addKdoc("%L", it)
			}

			// Add default value is all parameters have a default
			if (parameters.all { it.containsDefault() }) defaultValue(
				"%T()",
				requestParameterType
			)
		}.build().let(::addParameter)

		val includeData = data.body != ApiServiceOperationRequestBody.None
		if (includeData) {
			addParameter(ParameterSpec.builder("data", data.body.type).apply {
				// Set default value to null if parameter is nullable
				if (data.body.type.isNullable) defaultValue("%L", "null")
			}.build())
		}

		addOperationCall(data.name, Strings.MODEL_REQUEST_PARAMETER_NAME, parameters.map { it.name }, includeData)
	}.build()

	private fun ApiServiceOperationParameter.containsDefault(): Boolean {
		if (type.isNullable) return true
		if (defaultValue is DefaultValue.Conditional) return defaultValue.modelValue != null
		if (defaultValue != null) return true

		return false
	}
}
