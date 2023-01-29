package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.TypeName

data class ApiServiceOperation(
	val name: String,
	val description: String?,
	val deprecated: Boolean,
	val pathTemplate: String,
	val method: HttpMethod,
	val requireAuthentication: Boolean,
	val returnType: TypeName,
	val pathParameters: Collection<ApiServiceOperationParameter> = emptyList(),
	val queryParameters: Collection<ApiServiceOperationParameter> = emptyList(),
	val bodyType: TypeName?
)
