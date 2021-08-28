package org.jellyfin.openapi.constants

object Classes {
	const val API_CLIENT = "ApiClient"
	const val API_RESPONSE = "Response"

	object Serializers {
		const val UUID = "UUIDSerializer"
		const val DATETIME = "DateTimeSerializer"
	}

	object Types {
		const val UUID = "UUID"
		const val DATETIME = "DateTime"
	}

	const val CONSTANTS_OBJECT = "ApiConstants"

	object Exceptions {
		const val USERID_IS_NULL = "MissingUserIdException"
	}
}
