package org.jellyfin.apiclient

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.jellyfin.apiclient.api.client.KtorClient
import org.jellyfin.apiclient.model.ClientInfo
import org.jellyfin.apiclient.model.DeviceInfo

class Jellyfin(
	private val options: JellyfinOptions
) {
	constructor(initOptions: JellyfinOptions.Builder.() -> Unit) : this(JellyfinOptions.build(initOptions))

	private val gson = GsonBuilder()
		.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
		.create()

	val discovery by lazy {
		DiscoveryService(gson, options.logger, options.discoverBroadcastAddressesProvider)
	}

	/**
	 * Create a new API instance to use in API services.
	 * The [clientInfo] and [deviceInfo] parameters are required when not passed as option in [JellyfinOptions].
	 * The [baseUrl] is only required when HTTP calls are made.
	 */
	fun createApi(
		baseUrl: String? = null,
		accessToken: String? = null,
		clientInfo: ClientInfo? = options.clientInfo,
		deviceInfo: DeviceInfo? = options.deviceInfo
	): KtorClient {
		checkNotNull(clientInfo) { "ClientInfo needs to be set when calling createApi() or by providing it when constructing the Jellyfin instance" }
		checkNotNull(deviceInfo) { "DeviceInfo needs to be set when calling createApi() or by providing it when constructing the Jellyfin instance" }

		return KtorClient(
			baseUrl,
			accessToken,
			clientInfo,
			deviceInfo
		)
	}
}
