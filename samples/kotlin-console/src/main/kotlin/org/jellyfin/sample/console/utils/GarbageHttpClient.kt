package org.jellyfin.sample.console.utils

import org.jellyfin.apiclient.interaction.Response
import org.jellyfin.apiclient.interaction.http.HttpRequest
import org.jellyfin.apiclient.interaction.http.IAsyncHttpClient
import java.net.HttpURLConnection
import java.net.URL

class GarbageHttpClient : IAsyncHttpClient {
	override fun Send(request: HttpRequest?, response: Response<String>?) {
		requireNotNull(request)
		requireNotNull(response)

		val headers = request.requestHeaders.toMap().toMutableMap()

		// Magic
		if (request.requestContentType != null && !headers.containsKey("Content-Type"))
			headers["Content-Type"] = request.requestContentType
		else if (!request.postData.isNullOrEmpty() && !headers.containsKey("Content-Type"))
			headers["Content-Type"] = "application/x-www-form-urlencoded"

		if (request.requestHeaders.authorizationParameter != null)
			headers["X-Emby-Authorization"] = "${request.requestHeaders.authorizationScheme} ${request.requestHeaders.authorizationParameter}"

		try {
			val connection = URL(request.url).openConnection() as HttpURLConnection
			connection.apply {
				useCaches = request.enableCaching

				requestMethod = request.method

				headers.forEach { header ->
					setRequestProperty(header.key, header.value)
				}

				if (!request.postData.isNullOrEmpty()) {
					doOutput = true
					outputStream.write(request.postData.GetQueryString().toByteArray())
				} else if (request.requestContent != null) {
					doOutput = true
					outputStream.write(request.requestContent.toByteArray())
				}
			}

			val res = connection.inputStream.readBytes().toString(Charsets.UTF_8)
			response.onResponse(res)
		} catch (err: Exception) {
			response.onError(err)
		}
	}
}
