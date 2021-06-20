package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings

class CurrentUserIdDefaultValue(
	private val isString: Boolean,
) : CustomDefaultValue {
	override fun build() = CodeBlock.builder().apply {
		// api.userId
		add("%L.%L", Strings.API_CLIENT_PARAMETER_NAME, "userId")
		// ?.toString()
		if (isString) add("?.%L()", "toString")
		// ?: throw UserIdIsNullException()
		add(" ?: throw %T()", ClassName(Packages.API_EXCEPTION, Classes.Exceptions.USERID_IS_NULL))
	}.build()
}
