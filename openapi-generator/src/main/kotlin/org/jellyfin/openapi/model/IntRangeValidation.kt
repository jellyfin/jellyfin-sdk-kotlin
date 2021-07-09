package org.jellyfin.openapi.model

data class IntRangeValidation(
	val min: Int,
	val max: Int,
) : ParameterValidation
