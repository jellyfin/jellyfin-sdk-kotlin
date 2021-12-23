package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.hooks.OperationUrlHook
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.JellyFile

class ApiBuilder(
	private val operationBuilder: OperationBuilder,
	private val operationUrlBuilder: OperationUrlBuilder,
	private val operationUrlHooks: Collection<OperationUrlHook>,
) : Builder<ApiService, JellyFile> {
	override fun build(data: ApiService): JellyFile = TypeSpec.classBuilder(data.name).apply {
		// Add "Api" interface as super
		addSuperinterface(ClassName(Packages.API, Classes.API_INTERFACE))

		// Add "api" value to constructor
		val apiClientType = ClassName(Packages.API_CLIENT, Classes.API_CLIENT)
		addProperty(PropertySpec.builder(Strings.API_CLIENT_PARAMETER_NAME, apiClientType, KModifier.PRIVATE)
			.initializer(Strings.API_CLIENT_PARAMETER_NAME).build())
		primaryConstructor(FunSpec.constructorBuilder().addParameter(Strings.API_CLIENT_PARAMETER_NAME, apiClientType)
			.build())

		// Handle deprecated members
		val operations = data.operations.map { namedOperation ->
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

		// Add operations
		operations
			.sortedBy { it.name }
			.forEach { namedOperation ->
				addFunction(operationBuilder.build(namedOperation))

				if (operationUrlHooks.any { it.shouldOperationBuildUrlFun(data, namedOperation) })
					addFunction(operationUrlBuilder.build(namedOperation))
			}
	}.build().let { JellyFile(Packages.API, emptySet(), it) }
}
