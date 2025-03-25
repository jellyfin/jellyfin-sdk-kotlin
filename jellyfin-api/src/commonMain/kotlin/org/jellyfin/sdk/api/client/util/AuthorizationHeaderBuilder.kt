package org.jellyfin.sdk.api.client.util

public object AuthorizationHeaderBuilder {
	public const val AUTHORIZATION_SCHEME: String = "MediaBrowser"

	public fun encodeParameterValue(raw: String): String = raw
		.trim()
		.replace(Regex("\\n"), " ")
		.encodeURLPart()

	public fun buildParameter(key: String, value: String): String {
		// Check for bad strings to prevent endless hours debugging why the server throws http 500 errors
		require(!key.contains('=')) {
			"Key $key can not contain the = character in the authorization header"
		}
		require(!key.contains(',')) {
			"Key $key can not contain the , character in the authorization header"
		}
		require(!key.startsWith('"') && !key.endsWith('"')) {
			"Key $key can not start or end with the \" character in the authorization header"
		}

		// key="value"
		return """${key}="${encodeParameterValue(value)}""""
	}

	public fun buildHeader(
		clientName: String,
		clientVersion: String,
		deviceId: String,
		deviceName: String,
		accessToken: String? = null,
	): String {
		val params = arrayOf(
			"Client" to clientName,
			"Version" to clientVersion,
			"DeviceId" to deviceId,
			"Device" to deviceName,
			"Token" to accessToken
		)

		// Format: `MediaBrowser key1="value1", key2="value2"`
		return params
			// Drop null values (token)
			.filterNot { (_, value) -> value == null }
			// Join parts
			.joinToString(
				separator = ", ",
				prefix = "$AUTHORIZATION_SCHEME ",
				transform = { (key, value) -> buildParameter(key, value!!) }
			)
	}
}
