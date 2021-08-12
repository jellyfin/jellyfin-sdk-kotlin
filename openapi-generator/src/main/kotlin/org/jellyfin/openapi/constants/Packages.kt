package org.jellyfin.openapi.constants

object Packages {
	/**
	 * Package for the generated API operations
	 */
	const val API = "org.jellyfin.sdk.api.operations"

	/**
	 * Package for the generated API constants
	 */
	const val API_CONSTANTS = "org.jellyfin.sdk.api.info"

	/**
	 * Package for the client implementation
	 */
	const val API_CLIENT = "org.jellyfin.sdk.api.client"

	/**
	 * Package for the API exceptions
	 */
	const val API_EXCEPTION = "org.jellyfin.sdk.api.client.exception"

	/**
	 * Package for the generated models
	 */
	const val MODEL = "org.jellyfin.sdk.model.api"

	/**
	 * Package containing all kotlinx.serialization serializers
	 */
	const val MODEL_SERIALIZERS = "org.jellyfin.sdk.model.serializer"

	/**
	 * Package containing all custom types like UUID
	 */
	const val MODEL_TYPES = "org.jellyfin.sdk.model"
}
