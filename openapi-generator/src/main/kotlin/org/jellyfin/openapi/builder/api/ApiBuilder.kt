package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.builder.model.RequestModelBuilder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.hooks.OperationRequestModelHook
import org.jellyfin.openapi.hooks.OperationUrlHook
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.JellyFile

@Suppress("LongParameterList")
class ApiBuilder(
	private val operationBuilder: OperationBuilder,
	private val operationUrlBuilder: OperationUrlBuilder,
	private val operationParameterModelBuilder: OperationParameterModelBuilder,
	private val requestModelBuilder: RequestModelBuilder,
	private val operationUrlHooks: Collection<OperationUrlHook>,
	private val operationRequestModelHooks: Collection<OperationRequestModelHook>,
	private val fileSpecBuilder: FileSpecBuilder,
) : ContextBuilder<ApiService, Unit> {
	private fun buildAdditionalOperations(operations: Collection<ApiServiceOperation>) =
		operations.map { namedOperation ->
			// Check if any member is deprecated
			if (namedOperation.queryParameters.any { it.deprecated }) {
				// Return 2 operations, one with and one without deprecated members
				listOf(
					// Remove deprecated parameters from normal function
					namedOperation.copy(
						queryParameters = namedOperation.queryParameters.filterNot { it.deprecated }
					),
					// Add new "deprecated" function with old parameters
					namedOperation.copy(
						name = namedOperation.name + Strings.DEPRECATED_OPERATION_SUFFIX,
						// Mark the operation as deprecated
						deprecated = true
					)
				)
			} else listOf(namedOperation)
		}.flatten()

	private fun buildTypeSpec(context: GeneratorContext, data: ApiService) = TypeSpec.classBuilder(data.name).apply {
		// Add "Api" interface as super
		addSuperinterface(ClassName(Packages.API, Classes.API_INTERFACE))

		// Add "api" value to constructor
		val apiClientType = ClassName(Packages.API_CLIENT, Classes.API_CLIENT)
		addProperty(
			PropertySpec.builder(Strings.API_CLIENT_PARAMETER_NAME, apiClientType, KModifier.PRIVATE)
				.initializer(Strings.API_CLIENT_PARAMETER_NAME).build()
		)
		primaryConstructor(
			FunSpec.constructorBuilder().addParameter(Strings.API_CLIENT_PARAMETER_NAME, apiClientType)
				.build()
		)

		// Create additional operations like the deprecated version
		val operations = buildAdditionalOperations(data.operations)

		// Add operations
		operations
			.sortedBy { it.name }
			.forEach { namedOperation ->
				val createRequestModelVariant = operationRequestModelHooks.any {
					it.shouldOperationBuildRequestModelFun(data, namedOperation)
				}
				val createUrlVariant = operationUrlHooks.any {
					it.shouldOperationBuildUrlFun(data, namedOperation)
				}

				// Default variant
				addFunction(operationBuilder.build(namedOperation))

				// Request model variant
				if (createRequestModelVariant) {
					context += fileSpecBuilder.build(requestModelBuilder.build(namedOperation))
					addFunction(operationParameterModelBuilder.build(namedOperation))
				}

				// URL variant
				if (createUrlVariant) addFunction(operationUrlBuilder.build(namedOperation))
			}
	}

	override fun build(context: GeneratorContext, data: ApiService) {
		val api = buildTypeSpec(context, data)
			.build()
			.let { JellyFile(Packages.API, emptySet(), it) }
			.let { fileSpecBuilder.build(it) }

		context += api
	}
}
