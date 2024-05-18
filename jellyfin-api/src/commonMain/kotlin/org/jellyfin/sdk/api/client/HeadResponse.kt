package org.jellyfin.sdk.api.client

public class HeadResponse(
	public val status: Int,
	public val headers: Map<String, List<String>>
)
