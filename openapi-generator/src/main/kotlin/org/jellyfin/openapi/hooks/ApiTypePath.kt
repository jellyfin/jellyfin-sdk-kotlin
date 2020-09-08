package org.jellyfin.openapi.hooks

data class ApiTypePath(
	val service: String,
	val operation: String,
	val parameter: String
) : TypePath {
	fun isBodyType() = parameter == PARAMETER_BODY
	fun isReturnType() = parameter == PARAMETER_RETURN

	override fun toString(): String = "api.${service}.${operation}.${parameter}"

	companion object {
		const val PARAMETER_BODY = ":body"
		const val PARAMETER_RETURN = ":return"
	}
}
