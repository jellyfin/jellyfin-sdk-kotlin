package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterSpec
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.ApiServiceOperation

class OperationUrlBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
) : OperationBuilder(descriptionBuilder, deprecatedAnnotationSpecBuilder) {
	override fun buildFunctionShell(data: ApiServiceOperation) = FunSpec.builder(
		data.name + Strings.URL_OPERATION_SUFFIX
	).apply {
		// Add description
		descriptionBuilder.build(data.description)?.let {
			addKdoc("%L", it)
		}

		// Add deprecated annotation
		if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_MEMBER))

		// Set return type
		returns(Types.STRING)
	}

	override fun build(data: ApiServiceOperation): FunSpec = buildFunctionShell(data).apply {
		// Add parameters receivers
		data.pathParameters
			.union(data.queryParameters)
			.forEach { parameter -> addParameter(buildParameter(parameter)) }

		ParameterSpec.builder("includeCredentials", Types.BOOLEAN).apply {
			defaultValue("%L", data.requireAuthentication)
			addKdoc("%L", Strings.INCLUDE_CREDENTIALS_DESCRIPTION)
		}.build().let(::addParameter)

		// Create parameter maps
		addParameterMapStatements("pathParameters", data.pathParameters)
		addParameterMapStatements("queryParameters", data.queryParameters)

		// Call API
		addStatement(
			"return·api.createUrl(%S, pathParameters, queryParameters, includeCredentials)",
			data.pathTemplate
		)
	}.build()
}
