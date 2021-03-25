package org.jellyfin.sdk

import org.jellyfin.sdk.api.client.KtorClient
import org.jellyfin.sdk.api.info.ApiConstants
import org.jellyfin.sdk.discovery.DiscoveryService
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.discovery.ServerVersion

public class Jellyfin(
	private val options: JellyfinOptions
) {
	public constructor(initOptions: JellyfinOptions.Builder.() -> Unit) : this(JellyfinOptions.build(initOptions))

	public val discovery: DiscoveryService by lazy {
		DiscoveryService(this, options.discoverBroadcastAddressesProvider)
	}

	/**
	 * Create a new API instance to use in API services.
	 * The [clientInfo] and [deviceInfo] parameters are required when not passed as option in [JellyfinOptions].
	 * The [baseUrl] is only required when HTTP calls are made.
	 */
	public fun createApi(
		baseUrl: String? = null,
		accessToken: String? = null,
		clientInfo: ClientInfo? = options.clientInfo,
		deviceInfo: DeviceInfo? = options.deviceInfo
	): KtorClient {
		checkNotNull(clientInfo) {
			"ClientInfo needs to be set when calling createApi() or by providing it when constructing the Jellyfin instance"
		}
		checkNotNull(deviceInfo) {
			"DeviceInfo needs to be set when calling createApi() or by providing it when constructing the Jellyfin instance"
		}

		return KtorClient(
			baseUrl,
			accessToken,
			clientInfo,
			deviceInfo
		)
	}

	public companion object {
		public val recommendedVersion: ServerVersion = ServerVersion(10, 7, 0, 0)
		public val apiVersion: ServerVersion = ServerVersion.fromString(ApiConstants.apiVersion)!!
	}
}
