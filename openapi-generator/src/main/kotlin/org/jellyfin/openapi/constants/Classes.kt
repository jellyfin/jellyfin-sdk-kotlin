package org.jellyfin.openapi.constants

object Classes {
	const val API_CLIENT = "KtorClient"
	const val API_RESPONSE = "Response"

	object Serializers {
		const val UUID = "UUIDSerializer"
		const val LOCAL_DATE_TIME = "LocalDateTimeSerializer"
	}

	object Types {
		const val UUID = "UUID"
	}

	const val CONSTANTS_OBJECT = "ApiConstants"

	object Exceptions {
		const val USERID_IS_NULL = "MissingUserIdException"
	}
}
