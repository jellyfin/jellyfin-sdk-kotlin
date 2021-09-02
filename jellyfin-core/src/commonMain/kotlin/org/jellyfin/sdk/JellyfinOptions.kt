package org.jellyfin.sdk

import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.util.ApiClientFactory

public expect class JellyfinOptions {
	public val clientInfo: ClientInfo?
	public val deviceInfo: DeviceInfo?
	public val apiClientFactory: ApiClientFactory

	@Suppress("EmptyDefaultConstructor")
	public class Builder() {
		public fun build(): JellyfinOptions
	}

	public companion object
}

public inline fun createJellyfinOptions(
	init: JellyfinOptions.Builder.() -> Unit,
): JellyfinOptions = JellyfinOptions.Builder().apply(init).build()
