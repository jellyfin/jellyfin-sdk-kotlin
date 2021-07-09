package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.Paths
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.parameters.Parameter
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toCamelCase
import org.jellyfin.openapi.OpenApiGeneratorError
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.api.ApiNameBuilder
import org.jellyfin.openapi.constants.MimeType
import org.jellyfin.openapi.constants.Security
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.hooks.ApiTypePath
import org.jellyfin.openapi.hooks.DefaultValueHook
import org.jellyfin.openapi.hooks.ServiceNameHook
import org.jellyfin.openapi.model.*

class OpenApiApiServicesBuilder(
	private val apiNameBuilder: ApiNameBuilder,
	private val openApiTypeBuilder: OpenApiTypeBuilder,
	private val openApiReturnTypeBuilder: OpenApiReturnTypeBuilder,
	private val serviceNameHooks: Collection<ServiceNameHook>,
	private val defaultValueHooks: Collection<DefaultValueHook>,
) : Builder<Paths, Collection<ApiService>> {
	private fun getMethod(method: PathItem.HttpMethod) = when (method) {
		PathItem.HttpMethod.POST -> HttpMethod.POST
		PathItem.HttpMethod.GET -> HttpMethod.GET
		PathItem.HttpMethod.DELETE -> HttpMethod.DELETE
		else -> null
	}

	private fun buildServiceNames(operation: Operation): Collection<String> = serviceNameHooks
		.fold(
			operation.tags
				.map(apiNameBuilder::build)
				.toSet()
				.ifEmpty { setOf(Strings.DEFAULT_API_SERVICE) }
		) { serviceNames, hook ->
			hook.mapServiceNames(operation, serviceNames)
		}

	/**
	 * Returns the default value of the first hook that does not return null or use the default from the schema
	 */
	private fun buildDefaultValue(path: ApiTypePath, type: TypeName, parameterSpec: Parameter) = defaultValueHooks
		.firstNotNullOfOrNull { hook -> hook.onBuildDefaultValue(path, type, parameterSpec) }
		?: parameterSpec.schema?.default

	private fun buildValidation(schema: Schema<*>): ParameterValidation? = when {
		schema is IntegerSchema && schema.minimum != null && schema.maximum != null -> IntRangeValidation(
			schema.minimum.intValueExact(),
			schema.maximum.intValueExact()
		)
		else -> null
	}

	private fun buildOperation(
		operation: Operation,
		path: String,
		serviceName: String,
		method: HttpMethod,
	): ApiServiceOperation {
		val operationName = operation.operationId.toCamelCase(from = CaseFormat.CAPITALIZED_CAMEL)

		val pathParameters = mutableListOf<ApiServiceOperationParameter>()
		val queryParameters = mutableListOf<ApiServiceOperationParameter>()

		operation.parameters?.forEach { parameterSpec ->
			val parameterName = parameterSpec.name.toCamelCase(from = CaseFormat.CAPITALIZED_CAMEL)
			val type = openApiTypeBuilder
				.build(ApiTypePath(serviceName, operationName, parameterName), parameterSpec.schema)
				.copy(nullable = parameterSpec.required != true)

			val parameterTypePath = ApiTypePath(serviceName, operationName, parameterName)

			when (parameterSpec.`in`) {
				"path" -> pathParameters
				"query" -> queryParameters
				else -> throw OpenApiGeneratorError("""Unknown "in": ${parameterSpec.`in`}""")
			} += ApiServiceOperationParameter(
				name = parameterName,
				originalName = parameterSpec.name,
				type = type,
				defaultValue = buildDefaultValue(parameterTypePath, type, parameterSpec),
				description = parameterSpec.description,
				deprecated = parameterSpec.deprecated == true,
				validation = buildValidation(parameterSpec.schema),
			)

			if (parameterSpec.`in` == "path") {
				if (type.isNullable)
					println("Path parameter $parameterName in $operationName is marked as nullable")

				if (!path.contains("{${parameterName}}", ignoreCase = true))
					println("Path parameter $parameterName in $operationName is missing in path $path")
			}
		}

		val returnType = openApiReturnTypeBuilder.build(
			ApiTypePath(serviceName, operationName, ApiTypePath.PARAMETER_RETURN),
			operation.responses["200"]
		)
		if (returnType == Unit::class.asTypeName() && "200" in operation.responses)
			println("Missing return-type for operation $operationName (status-codes: ${operation.responses.keys})")

		val requireAuthentication = operation.security
			?.firstOrNull { requirement -> requirement.containsKey(Security.SECURITY_SCHEME) }
			?.get(Security.SECURITY_SCHEME)
			?.any(Security.AUTHENTICATION_POLICIES::contains)
			?: false

		return ApiServiceOperation(
			name = operationName,
			description = operation.description ?: operation.summary,
			deprecated = operation.deprecated == true,
			pathTemplate = path,
			method = method,
			requireAuthentication = requireAuthentication,
			returnType = returnType,
			pathParameters = pathParameters,
			queryParameters = queryParameters,
			bodyType = operation.requestBody?.content?.get(MimeType.APPLICATION_JSON)?.schema?.let { schema ->
				openApiTypeBuilder.build(ApiTypePath(serviceName, operationName, ApiTypePath.PARAMETER_BODY), schema)
			}
		)
	}

	override fun build(data: Paths): Collection<ApiService> {
		val operations = mutableMapOf<String, MutableSet<ApiServiceOperation>>()

		data.forEach { path, item ->
			item.readOperationsMap().forEach operation@{ (operationMethod, operation) ->
				val method = getMethod(operationMethod) ?: return@operation

				buildServiceNames(operation).forEach { serviceName ->
					// Create service name is missing
					if (serviceName !in operations) operations[serviceName] = mutableSetOf()

					// Add operation
					operations[serviceName]!!.add(buildOperation(operation, path, serviceName, method))
				}
			}
		}

		return operations.map { (serviceName, operations) -> ApiService(serviceName, operations) }
	}
}
