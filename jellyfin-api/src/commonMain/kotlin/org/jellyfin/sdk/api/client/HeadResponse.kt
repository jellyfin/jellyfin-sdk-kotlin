package org.jellyfin.sdk.api.client

import io.ktor.http.Headers

public class HeadResponse(
	public val status: Int,
	public val headers: Headers
)
