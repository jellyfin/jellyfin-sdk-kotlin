package org.jellyfin.openapi.constants

object Security {
	const val SECURITY_SCHEME = "CustomAuthentication"
	val AUTHENTICATION_POLICIES = arrayOf("DefaultAuthorization", "RequiresElevation")
}
