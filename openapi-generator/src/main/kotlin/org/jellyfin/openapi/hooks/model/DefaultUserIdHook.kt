package org.jellyfin.openapi.hooks.model

import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import io.swagger.v3.oas.models.parameters.Parameter
import org.jellyfin.openapi.hooks.ApiTypePath
import org.jellyfin.openapi.hooks.DefaultValueHook
import org.jellyfin.openapi.model.CurrentUserIdDefaultValue
import java.util.*

class DefaultUserIdHook : DefaultValueHook {
	companion object {
		val VALID_NAMES = arrayOf("userId")
		val TYPE_UUID = UUID::class.asTypeName()
		val TYPE_STRING = String::class.asTypeName()
	}

	override fun onBuildDefaultValue(
		path: ApiTypePath,
		type: TypeName,
		parameterSpec: Parameter,
	) = when {
		// Check for name and nullability
		path.parameter in VALID_NAMES && !type.isNullable -> when (type) {
			// Check for supported types
			TYPE_UUID -> CurrentUserIdDefaultValue(false)
			TYPE_STRING -> CurrentUserIdDefaultValue(true)
			else -> null
		}
		else -> null
	}
}
