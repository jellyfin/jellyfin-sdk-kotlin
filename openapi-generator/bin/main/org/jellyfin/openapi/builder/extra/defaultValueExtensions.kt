package org.jellyfin.openapi.builder.extra

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.DefaultValue

@Suppress("ComplexMethod")
fun DefaultValue?.toCodeBlock(
	type: TypeName,
	allowEmptyCollection: Boolean,
): CodeBlock? {
	// Determine class name without parameters
	val typeClassName = when (type) {
		is ClassName -> type
		is ParameterizedTypeName -> type.rawType
		else -> null
	}

	// Set default value
	return when (this) {
		is DefaultValue.String -> CodeBlock.of("%S", value)
		is DefaultValue.Int -> CodeBlock.of("%L", value)
		is DefaultValue.Boolean -> CodeBlock.of("%L", value)
		is DefaultValue.EnumMember -> CodeBlock.of("%T.%L", enumType, memberName)
		is DefaultValue.CodeBlock -> build()
		is DefaultValue.Conditional -> parameterValue.toCodeBlock(type, allowEmptyCollection)
		// Set value to null by default for nullable values
		null -> when {
			typeClassName == Types.COLLECTION && allowEmptyCollection ->
				CodeBlock.of("%M()", MemberName("kotlin.collections", "emptyList"))

			typeClassName == Types.LIST && allowEmptyCollection ->
				CodeBlock.of("%M()", MemberName("kotlin.collections", "emptyList"))

			typeClassName == Types.MAP && allowEmptyCollection ->
				CodeBlock.of("%M()", MemberName("kotlin.collections", "emptyMap"))

			type.isNullable -> CodeBlock.of("%L", "null")
			else -> null
		}
	}
}
