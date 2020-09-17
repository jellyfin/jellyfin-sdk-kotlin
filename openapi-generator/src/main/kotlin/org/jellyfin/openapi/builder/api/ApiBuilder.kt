package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.*
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.JellyFile

class ApiBuilder(
	private val operationBuilder: OperationBuilder
) : Builder<ApiService, JellyFile> {
	override fun build(data: ApiService): JellyFile = TypeSpec.classBuilder(data.name).apply {
		// Add "api" value to constructor
		val apiClientType = ClassName(Packages.API_CLIENT,  Classes.API_CLIENT)
		addProperty(PropertySpec.builder("api", apiClientType, KModifier.PRIVATE).initializer("api").build())
		primaryConstructor(FunSpec.constructorBuilder().addParameter("api", apiClientType).build())

		// Add operations
		data.operations.forEach { namedOperation -> addFunction(operationBuilder.build(namedOperation)) }
	}.build().let { JellyFile(Packages.API, emptySet(), it) }
}
