package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.TypeName

data class ApiServiceOperationParameter(
	val name: String,
	val originalName: String,
	val type: TypeName,
	val defaultValue: DefaultValue?,
	val description: String?,
	val deprecated: Boolean,
	val validation: ParameterValidation?,
)
