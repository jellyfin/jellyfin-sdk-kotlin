package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import io.swagger.v3.oas.models.media.*
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.OpenApiGeneratorError
import org.jellyfin.openapi.builder.openapi.OpenApiReturnTypeBuilder.Companion.TYPE_BINARY
import org.jellyfin.openapi.builder.openapi.OpenApiReturnTypeBuilder.Companion.TYPE_STRING
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath
import java.time.LocalDateTime
import java.util.*

class OpenApiTypeBuilder(
	private val hooks: Collection<TypeBuilderHook>,
) {
	fun build(path: TypePath, schema: Schema<*>): TypeName =
		buildWithHooks(path, schema) ?: buildSchema(schema)

	private fun buildWithHooks(path: TypePath, data: Schema<*>): TypeName? {
		for (hook in hooks) {
			val result = hook.onBuildType(path, data, this)
			if (result != null) return result
		}

		return null
	}

	@Suppress("ComplexMethod")
	fun buildSchema(schema: Schema<*>): TypeName = when {
		// Use referenced type
		schema.`$ref` != null -> buildReference(schema.`$ref`)
		// Use type based on schema class
		else -> when (schema) {
			// Primitives
			is StringSchema -> buildString()
			is BooleanSchema -> buildBoolean()
			// Numbers (will base the type on "format")
			is NumberSchema -> buildNumber(schema)
			is IntegerSchema -> buildNumber(schema)
			// Time
			is DateTimeSchema -> buildDateTime()
			// UUID
			is UUIDSchema -> buildUUIDType()
			// Binary
			is BinarySchema -> buildBinary()
			// Collections
			is ArraySchema -> buildArrayType(schema)
			is MapSchema -> buildMapType(schema)
			// Composed
			is ComposedSchema -> when {
				// Limited support for anyOf / allOf containing a single item
				schema.anyOf?.size == 1 -> buildSchema(schema.anyOf.first())
				schema.allOf?.size == 1 -> buildSchema(schema.allOf.first())
				else -> throw UnknownTypeError(schema.type, schema.format)
			}
			else -> throw UnknownTypeError(schema.type, schema.format)
		}.copy(
			// Add nullability
			nullable = schema.nullable ?: false,
			// Remove tags when set
			tags = emptyMap()
		)
	}

	fun buildNumber(schema: Schema<*>) = when (schema.format) {
		"int32" -> Int::class
		"int64" -> Long::class
		"double" -> Double::class
		"float" -> Float::class
		else -> throw UnknownTypeError(schema.type, schema.format)
	}.asTypeName()

	fun buildString() = TYPE_STRING
	fun buildBoolean() = Boolean::class.asTypeName()
	fun buildDateTime() = LocalDateTime::class.asTypeName()
	fun buildUUIDType() = UUID::class.asTypeName()

	fun buildArrayType(schema: ArraySchema) = List::class.asTypeName()
		.plusParameter(buildSchema(schema.items as Schema<*>))

	fun buildMapType(schema: MapSchema) = Map::class.asTypeName()
		.plusParameter(String::class.asTypeName())
		.plusParameter(buildSchema(schema.additionalProperties as Schema<*>))

	fun buildReference(reference: String) = ClassName(
		Packages.MODEL,
		reference
			.removePrefix("#/components/schemas/")
			.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL)
	)

	fun buildBinary() = TYPE_BINARY

	class UnknownTypeError(
		type: String?,
		format: String?,
	) : OpenApiGeneratorError("Unknown type $type with format $format")
}
