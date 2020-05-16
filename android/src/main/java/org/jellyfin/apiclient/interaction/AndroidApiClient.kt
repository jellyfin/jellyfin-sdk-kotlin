package org.jellyfin.apiclient.interaction

import android.content.Context
import org.jellyfin.apiclient.interaction.device.IDevice
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient
import org.jellyfin.apiclient.logging.AndroidLogger
import org.jellyfin.apiclient.logging.ILogger
import org.jellyfin.apiclient.serialization.GsonJsonSerializer

class AndroidApiClient(
	httpClient: IAsyncHttpClient,
	logger: ILogger = AndroidLogger(),
	serverAddress: String,
	clientName: String,
	device: IDevice,
	applicationVersion: String,
	apiEventListener: ApiEventListener = ApiEventListener()
) : ApiClient(
	httpClient,
	GsonJsonSerializer(),
	logger,
	serverAddress,
	clientName,
	applicationVersion,
	device,
	apiEventListener
) {
	override fun getResponseStream(address: String, response: Response<ResponseStreamInfo>) {
		val thread = Thread(Runnable { getResponseStreamInternal(address, response) })
		thread.start()
	}

	override fun detectBitrate(downloadBytes: Long, response: Response<Long>) {
		val thread = Thread(Runnable { detectBitrateInternal(downloadBytes, response) })
		thread.start()
	}

	companion object {
		fun fromContext(
			context: Context,
			serverAddress: String,
			clientName: String,
			applicationVersion: String,
			logger: ILogger = AndroidLogger(),
			apiEventListener: ApiEventListener = ApiEventListener()
		) {
			AndroidApiClient(
				httpClient = VolleyHttpClient(logger, context),
				logger = logger,
				serverAddress = serverAddress,
				clientName = clientName,
				device = AndroidDevice.fromContext(context),
				applicationVersion = applicationVersion,
				apiEventListener = apiEventListener
			)
		}
	}
}
