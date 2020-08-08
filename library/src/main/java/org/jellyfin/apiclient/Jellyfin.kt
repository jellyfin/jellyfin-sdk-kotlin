package org.jellyfin.apiclient

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.jellyfin.apiclient.interaction.ApiClient
import org.jellyfin.apiclient.interaction.ApiEventListener
import org.jellyfin.apiclient.interaction.device.IDevice

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

	fun createApi(
		serverAddress: String? = null,
		accessToken: String? = null,
		device: IDevice,
		eventListener: ApiEventListener = ApiEventListener()
	) = ApiClient(
		options.httpClient,
		options.logger,
		serverAddress,
		accessToken,
		options.appInfo.name,
		options.appInfo.version,
		device,
		eventListener
	)
}
