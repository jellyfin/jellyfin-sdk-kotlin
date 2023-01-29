package org.jellyfin.openapi.hooks.model

import com.squareup.kotlinpoet.TypeName
import io.swagger.v3.oas.models.parameters.Parameter
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.hooks.ApiTypePath
import org.jellyfin.openapi.hooks.DefaultValueHook
import org.jellyfin.openapi.model.CurrentUserIdDefaultValue
import org.jellyfin.openapi.model.DefaultValue

class DefaultUserIdHook : DefaultValueHook {
	companion object {
		val VALID_NAMES = arrayOf("userId")
	}

	override fun onBuildDefaultValue(
		path: ApiTypePath,
		type: TypeName,
		parameterSpec: Parameter,
	) = when {
		// Check for name and nullability
		path.parameter in VALID_NAMES && !type.isNullable -> when (type) {
			// Check for supported types
			Types.UUID -> DefaultValue.Conditional(parameterValue = CurrentUserIdDefaultValue(false))
			Types.STRING -> DefaultValue.Conditional(parameterValue = CurrentUserIdDefaultValue(true))
			else -> null
		}
		else -> null
	}
}
