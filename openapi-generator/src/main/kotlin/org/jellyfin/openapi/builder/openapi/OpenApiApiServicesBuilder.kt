package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.asTypeName
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.Paths
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toCamelCase
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.api.ApiNameBuilder
import org.jellyfin.openapi.constants.MimeType
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.hooks.ApiTypePath
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.ApiServiceOperationParameter
import org.jellyfin.openapi.model.HttpMethod

class OpenApiApiServicesBuilder(
	private val apiNameBuilder: ApiNameBuilder,
	private val openApiTypeBuilder: OpenApiTypeBuilder,
	private val openApiReturnTypeBuilder: OpenApiReturnTypeBuilder
) : Builder<Paths, Collection<ApiService>> {
	private fun getMethod(method: PathItem.HttpMethod) = when (method) {
		PathItem.HttpMethod.POST -> HttpMethod.POST
		PathItem.HttpMethod.GET -> HttpMethod.GET
		PathItem.HttpMethod.DELETE -> HttpMethod.DELETE
		else -> null
	}

	override fun build(data: Paths): Collection<ApiService> {
		val operations = mutableMapOf<String, MutableSet<ApiServiceOperation>>()
		data.forEach { path, item ->
			item.readOperationsMap().forEach operation@{ (operationMethod, operation) ->
				val method = getMethod(operationMethod) ?: return@operation
				val serviceName = apiNameBuilder.build(operation.tags.firstOrNull()
					?: Strings.DEFAULT_API_SERVICE)
				if (serviceName !in operations) operations[serviceName] = mutableSetOf()
				val operationName = operation.operationId.toCamelCase(from = CaseFormat.CAPITALIZED_CAMEL)

				val pathParameters = mutableListOf<ApiServiceOperationParameter>()
				val queryParameters = mutableListOf<ApiServiceOperationParameter>()

				operation.parameters?.forEach { parameterSpec ->
					val parameterName = parameterSpec.name.toCamelCase(from = CaseFormat.CAPITALIZED_CAMEL)
					val type = openApiTypeBuilder.build(ApiTypePath(serviceName, operationName, parameterName), parameterSpec.schema)

					when (parameterSpec.`in`) {
						"path" -> pathParameters
						"query" -> queryParameters
						else -> throw Error("""Unknown "in": ${parameterSpec.`in`}""")
					} += ApiServiceOperationParameter(
						name = parameterName,
						originalName = parameterSpec.name,
						type = type,
						defaultValue = parameterSpec.schema?.default,
						description = parameterSpec.description,
						deprecated = parameterSpec.deprecated == true
					)

					if (parameterSpec.`in` == "path" && type.isNullable)
						println("Path parameter $parameterName in $operationName is marked as nullable")
				}

				val returnType = openApiReturnTypeBuilder.build(ApiTypePath(serviceName, operationName, ApiTypePath.PARAMETER_RETURN), operation.responses["200"])
				if (returnType == Unit::class.asTypeName() && "200" in operation.responses)
					println("Missing return-type for operation $operationName (status-codes: ${operation.responses.keys})")

				operations[serviceName]!! += ApiServiceOperation(
					name = operationName,
					description = operation.description ?: operation.summary,
					deprecated = operation.deprecated == true,
					pathTemplate = path,
					method = method,
					returnType = returnType,
					pathParameters = pathParameters,
					queryParameters = queryParameters,
					bodyType = operation.requestBody?.content?.get(MimeType.APPLICATION_JSON)?.schema?.let { schema ->
						openApiTypeBuilder.build(ApiTypePath(serviceName, operationName, ApiTypePath.PARAMETER_BODY), schema)
					}
				)
			}
		}

		return operations.map { (serviceName, operations) -> ApiService(serviceName, operations) }
	}
}
