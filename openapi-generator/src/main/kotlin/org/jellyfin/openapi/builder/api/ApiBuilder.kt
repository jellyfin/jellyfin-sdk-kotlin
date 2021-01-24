package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.*
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
	private val operationUrlHooks: Collection<OperationUrlHook>
) : Builder<ApiService, JellyFile> {
	override fun build(data: ApiService): JellyFile = TypeSpec.classBuilder(data.name).apply {
		// Add "api" value to constructor
		val apiClientType = ClassName(Packages.API_CLIENT,  Classes.API_CLIENT)
		addProperty(PropertySpec.builder("api", apiClientType, KModifier.PRIVATE).initializer("api").build())
		primaryConstructor(FunSpec.constructorBuilder().addParameter("api", apiClientType).build())

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
		operations.forEach { namedOperation ->
			addFunction(operationBuilder.build(namedOperation))

			if (operationUrlHooks.any { it.shouldOperationBuildUrlFun(data, namedOperation) })
				addFunction(operationUrlBuilder.build(namedOperation))
		}
	}.build().let { JellyFile(Packages.API, emptySet(), it) }
}
