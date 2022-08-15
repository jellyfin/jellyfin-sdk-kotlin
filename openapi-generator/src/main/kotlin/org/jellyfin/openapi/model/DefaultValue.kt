package org.jellyfin.openapi.model

sealed interface DefaultValue {
	@JvmInline
	value class String(val value: kotlin.String) : DefaultValue

	@JvmInline
	value class Int(val value: kotlin.Int) : DefaultValue

	@JvmInline
	value class Boolean(val value: kotlin.Boolean) : DefaultValue

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
	interface CodeBlock : DefaultValue {
		fun build(): com.squareup.kotlinpoet.CodeBlock
	}
}
