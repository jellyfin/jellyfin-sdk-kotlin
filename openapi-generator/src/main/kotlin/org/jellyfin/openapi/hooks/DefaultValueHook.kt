package org.jellyfin.openapi.hooks

import com.squareup.kotlinpoet.TypeName
import io.swagger.v3.oas.models.parameters.Parameter
import org.jellyfin.openapi.model.CustomDefaultValue

interface DefaultValueHook {
	/**
	 * Create a [CustomDefaultValue] to use when generating the code for this parameter.
	 * Return `null` if the hook does not want to modify the default value.
	 */
	fun onBuildDefaultValue(path: ApiTypePath, type: TypeName, parameterSpec: Parameter): CustomDefaultValue?
}
