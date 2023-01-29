package org.jellyfin.openapi.builder.extra

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.ApiServiceOperationParameter
import org.jellyfin.openapi.model.DefaultValue
import org.jellyfin.openapi.model.ObjectApiModelProperty

@Suppress("ComplexMethod")
fun ParameterSpec.Builder.defaultValue(
	type: TypeName,
	defaultValue: DefaultValue?,
	allowEmptyCollection: Boolean,
) {
	// Determine class name without parameters
	val typeClassName = when (type) {
		is ClassName -> type
		is ParameterizedTypeName -> type.rawType
		else -> null
	}

	// Set default value
	when (defaultValue) {
		is DefaultValue.String -> defaultValue("%S", defaultValue.value)
		is DefaultValue.Int -> defaultValue("%L", defaultValue.value)
		is DefaultValue.Boolean -> defaultValue("%L", defaultValue.value)
		is DefaultValue.EnumMember -> defaultValue("%T.%L", defaultValue.enumType, defaultValue.memberName)
		is DefaultValue.CodeBlock -> defaultValue(defaultValue.build())
		is DefaultValue.Conditional -> defaultValue(type, defaultValue.parameterValue, allowEmptyCollection)
		// Set value to null by default for nullable values
		null -> when {
			typeClassName == Types.COLLECTION && allowEmptyCollection ->
				defaultValue("%M()", MemberName("kotlin.collections", "emptyList"))

			typeClassName == Types.LIST && allowEmptyCollection ->
				defaultValue("%M()", MemberName("kotlin.collections", "emptyList"))

			typeClassName == Types.MAP && allowEmptyCollection ->
				defaultValue("%M()", MemberName("kotlin.collections", "emptyMap"))

			type.isNullable -> defaultValue("%L", "null")
		}
	}
}

fun ParameterSpec.Builder.defaultValue(parameter: ApiServiceOperationParameter) = defaultValue(
	type = parameter.type,
	defaultValue = parameter.defaultValue,
	allowEmptyCollection = true,
)

fun ParameterSpec.Builder.defaultValue(property: ObjectApiModelProperty) = defaultValue(
	type = property.type,
	defaultValue = property.defaultValue,
	allowEmptyCollection = false,
)
