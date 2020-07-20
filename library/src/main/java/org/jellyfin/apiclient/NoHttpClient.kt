package org.jellyfin.apiclient

import org.jellyfin.apiclient.interaction.Response
import org.jellyfin.apiclient.interaction.http.HttpRequest
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient

class NoHttpClient : IAsyncHttpClient {
	override fun Send(request: HttpRequest?, response: Response<String>?) {
		throw NotImplementedError("Making HTTP requests is not possible because no http client was passed to the Jellyfin options constructor.")
	}
}
