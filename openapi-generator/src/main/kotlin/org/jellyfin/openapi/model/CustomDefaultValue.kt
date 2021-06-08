package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.CodeBlock

/**
 * Custom value builder used in hooks.
 * The implementation should use a [CodeBlock.Builder] to create a value.
 *
 * Creating a default value with the integer 1 can be done with:
 *
 * ```kotlin
 * CodeBlock.Builder()
 * 	.add("1")
 * 	.build()
 * ```
 */
interface CustomDefaultValue {
	fun build(): CodeBlock
}
