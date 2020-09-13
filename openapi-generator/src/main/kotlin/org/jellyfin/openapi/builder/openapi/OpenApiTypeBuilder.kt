package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import io.swagger.v3.oas.models.media.*
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath
import org.jellyfin.openapi.util.asPascalCase
import java.io.InputStream
import java.time.LocalDateTime
import java.util.*

class OpenApiTypeBuilder(
	private val hooks: Collection<TypeBuilderHook>
) {
	fun build(path: TypePath, schema: Schema<*>): TypeName =
		buildWithHooks(path, schema) ?: buildWithSchema(schema)

	private fun buildWithHooks(path: TypePath, data: Schema<*>): TypeName? {
		for (hook in hooks) {
			val result = hook.onBuildType(path, data)
			if (result != null) return result
		}

		return null
	}

	private fun buildWithSchema(schema: Schema<*>): TypeName = when {
		// Use referenced type
		schema.`$ref` != null -> buildReference(schema.`$ref`)
		// Use binary type for everything that identifies as file
		schema.type == "file" -> buildBinary()
		// Use type based on schema class
		else -> when (schema) {
			// Primitives
			is StringSchema -> buildString()
			is BooleanSchema -> buildBoolean()
			// Numbers (will base the type on "format")
			is NumberSchema -> buildNumber(schema)
			is IntegerSchema -> buildNumber(schema)
			// Time & Other
			is DateTimeSchema -> buildDateTime()
			is UUIDSchema -> buildUUIDType()
			// Collections
			is ArraySchema -> buildArrayType(schema)
			is MapSchema -> buildMapType(schema)

			else -> throw UnknownTypeError(schema.type, schema.format)
		}.copy(
			// Add nullability
			nullable = schema.nullable ?: false,
			// Remove tags when set
			tags = emptyMap()
		)
	}

	private fun buildNumber(schema: Schema<*>) = when (schema.format) {
		"int32" -> Int::class
		"int64" -> Long::class
		"double" -> Double::class
		"float" -> Float::class
		else -> throw UnknownTypeError(schema.type, schema.format)
	}.asTypeName()

	private fun buildString() = String::class.asTypeName()
	private fun buildBoolean() = Boolean::class.asTypeName()
	private fun buildDateTime() = LocalDateTime::class.asTypeName()
	private fun buildUUIDType() = UUID::class.asTypeName()

	private fun buildArrayType(schema: ArraySchema) = List::class.asTypeName()
		.plusParameter(buildWithSchema(schema.items as Schema<*>))

	private fun buildMapType(schema: MapSchema) = Map::class.asTypeName()
		.plusParameter(String::class.asTypeName())
		.plusParameter(buildWithSchema(schema.additionalProperties as Schema<*>))

	private fun buildReference(reference: String) = ClassName(
		Packages.MODEL,
		reference
			.removePrefix("#/components/schemas/")
			.asPascalCase()
			.toPascalCase()
	)

	private fun buildBinary() = InputStream::class.asTypeName()

	class UnknownTypeError(type: String?, format: String?) : Error("Unknown type $type with format $format")
}
