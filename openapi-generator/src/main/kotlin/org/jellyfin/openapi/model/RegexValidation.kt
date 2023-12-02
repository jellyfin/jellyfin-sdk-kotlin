package org.jellyfin.openapi.model

data class RegexValidation(
	val pattern: String,
) : ParameterValidation
