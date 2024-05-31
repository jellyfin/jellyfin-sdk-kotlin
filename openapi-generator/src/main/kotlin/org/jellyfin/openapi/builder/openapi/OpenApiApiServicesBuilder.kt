package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.TypeName
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.Paths
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.parameters.RequestBody
import mu.KotlinLogging
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toCamelCase
import org.jellyfin.openapi.OpenApiGeneratorError
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.builder.api.ApiNameBuilder
import org.jellyfin.openapi.constants.MimeType
import org.jellyfin.openapi.constants.Security
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.hooks.ApiTypePath
import org.jellyfin.openapi.hooks.DefaultValueHook
import org.jellyfin.openapi.hooks.ServiceNameHook
import org.jellyfin.openapi.model.ApiService
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.ApiServiceOperationParameter
import org.jellyfin.openapi.model.ApiServiceOperationRequestBody
import org.jellyfin.openapi.model.DefaultValue
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.HttpMethod
import org.jellyfin.openapi.model.IntRangeValidation
import org.jellyfin.openapi.model.ParameterValidation
import org.jellyfin.openapi.model.RegexValidation

private val logger = KotlinLogging.logger { }

class OpenApiApiServicesBuilder(
	private val apiNameBuilder: ApiNameBuilder,
	private val openApiTypeBuilder: OpenApiTypeBuilder,
	private val openApiReturnTypeBuilder: OpenApiReturnTypeBuilder,
	private val openApiDefaultValueBuilder: OpenApiDefaultValueBuilder,
	private val serviceNameHooks: Collection<ServiceNameHook>,
	private val defaultValueHooks: Collection<DefaultValueHook>,
) : ContextBuilder<Paths, Unit> {
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
	private fun buildDefaultValue(
		context: GeneratorContext,
		path: ApiTypePath,
		type: TypeName,
		parameterSpec: Parameter,
	): DefaultValue? {
		val hookDefault = defaultValueHooks.firstNotNullOfOrNull { hook ->
			hook.onBuildDefaultValue(path, type, parameterSpec)
		}
		val generatorDefault = openApiDefaultValueBuilder.build(context, parameterSpec.schema)

		if (hookDefault is DefaultValue.Conditional) {
			return hookDefault.copy(
				modelValue = hookDefault.modelValue ?: generatorDefault,
				parameterValue = hookDefault.parameterValue ?: generatorDefault,
			)
		}

		return generatorDefault
	}

	private fun buildValidation(schema: Schema<*>): ParameterValidation? = when {
		schema is IntegerSchema && schema.minimum != null && schema.maximum != null -> IntRangeValidation(
			schema.minimum.intValueExact(),
			schema.maximum.intValueExact()
		)

		schema is StringSchema && schema.pattern != null -> RegexValidation(schema.pattern)

		else -> null
	}

	private fun buildRequestBody(
		requestBody: RequestBody,
		serviceName: String,
		operationName: String
	): ApiServiceOperationRequestBody {
		// Filter out duplicate JSON values
		val requestBodyTypes = requestBody.content.keys.filterNot {
			MimeType.IGNORED_JSON_TYPES.contains(it)
		}

		// Check amount of types and get the first
		val requestBodyType = when (requestBodyTypes.size) {
			0 -> return ApiServiceOperationRequestBody.None
			1 -> requestBodyTypes.first()
			else -> throw OpenApiGeneratorError("Multiple request body types are not supported. Found types: $requestBodyTypes")
		}

		// Retrieve info
		val content = requestBody.content[requestBodyType]
		val required = requestBody.required ?: false

		val model by lazy {
			openApiTypeBuilder.build(
				ApiTypePath(serviceName, operationName, ApiTypePath.PARAMETER_BODY),
				requireNotNull(content?.schema)
			).copy(nullable = !required)
		}

		// Determine the correct type
		return when (requestBodyType) {
			// JSON model body
			MimeType.APPLICATION_JSON -> ApiServiceOperationRequestBody.Json(model)
			// Image body
			MimeType.IMAGE_ALL -> ApiServiceOperationRequestBody.Binary(requestBodyType)
			// String body
			MimeType.TEXT_PLAIN -> ApiServiceOperationRequestBody.Text
			// Unknown type
			else -> throw OpenApiGeneratorError("""Unsupported request body type "$requestBodyType".""")
		}
	}

	private fun buildOperation(
		context: GeneratorContext,
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
				defaultValue = buildDefaultValue(context, parameterTypePath, type, parameterSpec),
				description = parameterSpec.description,
				deprecated = parameterSpec.deprecated == true,
				validation = buildValidation(parameterSpec.schema),
			)

			if (parameterSpec.`in` == "path") {
				if (type.isNullable)
					logger.warn { "Path parameter $parameterName in $operationName is marked as nullable" }

				if (!path.contains("{${parameterName}}", ignoreCase = true))
					logger.warn { "Path parameter $parameterName in $operationName is missing in path $path" }
			}
		}

		val returnType = openApiReturnTypeBuilder.build(
			ApiTypePath(serviceName, operationName, ApiTypePath.PARAMETER_RETURN),
			operation.responses["200"]
		)
		if (returnType == Types.NONE && "200" in operation.responses)
			logger.warn { "Missing return-type for operation $operationName (status-codes: ${operation.responses.keys})" }

		val requireAuthentication = operation.security
			?.firstOrNull { requirement -> requirement.containsKey(Security.SECURITY_SCHEME) }
			?.get(Security.SECURITY_SCHEME)
			?.any(Security.AUTHENTICATION_POLICIES::contains)
			?: false

		val bodyType = operation.requestBody?.let { requestBody ->
			buildRequestBody(requestBody, serviceName, operationName)
		} ?: ApiServiceOperationRequestBody.None

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
			body = bodyType,
		)
	}

	override fun build(context: GeneratorContext, data: Paths) {
		val operationsSets = mutableMapOf<String, MutableSet<ApiServiceOperation>>()

		data.forEach { path, item ->
			item.readOperationsMap().forEach operation@{ (operationMethod, operation) ->
				val method = getMethod(operationMethod) ?: return@operation

				buildServiceNames(operation).forEach { serviceName ->
					// Create service name is missing
					if (serviceName !in operationsSets) operationsSets[serviceName] = mutableSetOf()

					// Add operation
					operationsSets[serviceName]!!.add(buildOperation(context, operation, path, serviceName, method))
				}
			}
		}

		for ((serviceName, operations) in operationsSets) {
			val service = ApiService(serviceName, operations)
			context += service
		}
	}
}
