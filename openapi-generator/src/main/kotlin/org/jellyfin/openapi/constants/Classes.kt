package org.jellyfin.openapi.constants

object Classes {
	const val API_CLIENT = "ApiClient"
	const val API_CLIENT_EXTENSIONS = "ApiClientExtensions"
	const val API_RESPONSE = "Response"
	const val API_INTERFACE = "Api"

	object Serializers {
		const val UUID = "UUIDSerializer"
		const val DATETIME = "DateTimeSerializer"
	}

	object Types {
		const val UUID = "UUID"
		const val DATETIME = "DateTime"
		const val FILE_INFO = "FileInfo"
	}

	const val CONSTANTS_OBJECT = "ApiConstants"
}
