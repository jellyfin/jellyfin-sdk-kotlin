package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.ClassName
import io.swagger.v3.oas.models.media.ComposedSchema
import io.swagger.v3.oas.models.media.Schema
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import net.pearx.kasechange.toScreamingSnakeCase
import org.jellyfin.openapi.OpenApiGeneratorError
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.model.DefaultValue
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.GeneratorContext

@Suppress("TooManyFunctions")
class OpenApiDefaultValueBuilder : ContextBuilder<Schema<Any>, DefaultValue?> {
	override fun build(context: GeneratorContext, data: Schema<Any>): DefaultValue? =
		when (val schemaDefault = data.default) {
			is String -> getDefaultEnumMember(context, data) ?: DefaultValue.String(schemaDefault)
			is Int -> DefaultValue.Int(schemaDefault)
			is Boolean -> DefaultValue.Boolean(schemaDefault)
			null -> null
			else -> throw OpenApiGeneratorError("""Unsupported default value "$schemaDefault".""")
		}

	private fun getDefaultEnumMember(context: GeneratorContext, schema: Schema<Any>): DefaultValue.EnumMember? {
		val schemaDefault = schema.default
		if (schemaDefault !is String) return null

		val reference = getSchemaReference(schema) ?: return null
		val modelName = reference
			.removePrefix("#/components/schemas/")
			.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL)

		val model = context.models.firstOrNull { it.name == modelName }
		if (model !is EnumApiModel) return null
		if (model.constants.none { it.equals(schemaDefault, ignoreCase = true) }) return null

		return DefaultValue.EnumMember(
			enumType = ClassName(Packages.MODEL, model.name),
			memberName = schemaDefault.toScreamingSnakeCase(from = CaseFormat.CAPITALIZED_CAMEL)
		)
	}

	private fun getSchemaReference(schema: Schema<Any>): String? = when {
		schema is ComposedSchema -> when {
			// Limited support for anyOf / allOf containing a single item
			schema.anyOf?.size == 1 -> getSchemaReference(schema.anyOf.first())
			schema.allOf?.size == 1 -> getSchemaReference(schema.allOf.first())
			else -> throw OpenApiTypeBuilder.UnknownTypeError(schema.type, schema.format)
		}

		schema.`$ref` != null -> schema.`$ref`

		else -> null
	}
}
