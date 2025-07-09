package org.jellyfin.sdk.api.okhttp

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.SerializationException
import mu.KotlinLogging
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttp
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.client.HttpMethod
import org.jellyfin.sdk.api.client.RawResponse
import org.jellyfin.sdk.api.client.exception.ApiClientException
import org.jellyfin.sdk.api.client.exception.InvalidContentException
import org.jellyfin.sdk.api.client.exception.InvalidStatusException
import org.jellyfin.sdk.api.client.exception.SecureConnectionException
import org.jellyfin.sdk.api.client.exception.TimeoutException
import org.jellyfin.sdk.api.client.util.ApiSerializer
import org.jellyfin.sdk.api.client.util.AuthorizationHeaderBuilder
import org.jellyfin.sdk.api.sockets.DefaultSocketApi
import org.jellyfin.sdk.api.sockets.SocketApi
import org.jellyfin.sdk.api.sockets.SocketConnectionFactory
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.FileInfo
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlin.coroutines.resumeWithException

@Suppress("LongParameterList")
public class OkHttpClient(
	private val client: okhttp3.OkHttpClient,
	initialBaseUrl: String?,
	initialAccessToken: String?,
	initialClientInfo: ClientInfo,
	initialDeviceInfo: DeviceInfo,
	override val httpClientOptions: HttpClientOptions,
	private val socketConnectionFactory: SocketConnectionFactory,
) : ApiClient() {
	public override var baseUrl: String? = initialBaseUrl
		private set
	public override var accessToken: String? = initialAccessToken
		private set
	public override var clientInfo: ClientInfo = initialClientInfo
		private set
	public override var deviceInfo: DeviceInfo = initialDeviceInfo
		private set

	private val _webSocket = lazy {
		DefaultSocketApi(this, httpClientOptions.socketReconnectPolicy, socketConnectionFactory)
	}

	override val webSocket: SocketApi by _webSocket

	override fun update(baseUrl: String?, accessToken: String?, clientInfo: ClientInfo, deviceInfo: DeviceInfo) {
		this.baseUrl = baseUrl
		this.accessToken = accessToken
		this.clientInfo = clientInfo
		this.deviceInfo = deviceInfo

		// Notify websocket only if initialized
		if (_webSocket.isInitialized()) {
			_webSocket.value.notifyApiClientUpdate()
		}
	}

	@Suppress("ThrowsCount")
	override suspend fun request(
		method: HttpMethod,
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
		requestBody: Any?,
	): RawResponse {
		val url = createUrl(pathTemplate, pathParameters, queryParameters)

		// Log HTTP call with access token removed
		val logger = KotlinLogging.logger {}
		logger.info {
			val safeUrl = accessToken?.let { url.replace(it, "******") } ?: url
			"$method $safeUrl"
		}

		val request = Request.Builder().apply {
			val body = when (method) {
				HttpMethod.POST -> when (requestBody) {
					// String content
					is String -> requestBody.toRequestBody("text/plain".toMediaType())
					// File content
					is FileInfo -> requestBody.content.toRequestBody(requestBody.mediaType.toMediaType())
					// Binary content
					is ByteArray -> requestBody.toRequestBody("application/octet-stream".toMediaType())
					// Json content
					else -> ApiSerializer.encodeRequestBody(requestBody)
						?.toRequestBody("application/json".toMediaType())
				} ?: ByteArray(0).toRequestBody(null, 0, 0)

				else -> null
			}
			method(method.name, body)
			url(url)

			header("Accept", HEADER_ACCEPT)

			val authorization = AuthorizationHeaderBuilder.buildHeader(
				clientName = clientInfo.name,
				clientVersion = clientInfo.version,
				deviceId = deviceInfo.id,
				deviceName = deviceInfo.name,
				accessToken = accessToken
			)
			header("Authorization", authorization)
			header("User-Agent", "${clientInfo.name}/${clientInfo.version} via jellyfin-sdk-kotlin (OkHttp/${OkHttp.VERSION})")
		}.build()

		try {
			val response = client.newCall(request).await()
			if (!response.isSuccessful) throw InvalidStatusException(response.code)
			return RawResponse(response.body!!.bytes(), response.code, response.headers.toMultimap())
		} catch (err: UnknownHostException) {
			logger.debug(err) { "HTTP host unreachable" }
			throw TimeoutException("HTTP host unreachable", err)
		} catch (err: InterruptedIOException) {
			logger.debug(err) { "Connection timed out or was interrupted" }
			throw TimeoutException("Connection timed out or was interrupted", err)
		} catch (err: ConnectException) {
			logger.debug(err) { "Connection failed" }
			throw TimeoutException("Connection failed", err)
		} catch (err: SerializationException) {
			logger.error(err) { "Serialization failed" }
			throw InvalidContentException("Serialization failed", err)
		} catch (err: SSLException) {
			logger.error(err) { "Unknown SSL error occurred" }
			throw SecureConnectionException("Unknown SSL error occurred", err)
		} catch (err: IOException) {
			logger.error(err) { "Unknown IO error occurred!" }
			throw ApiClientException("Unknown IO error occurred!", err)
		}
	}

	private suspend fun Call.await(): Response = suspendCancellableCoroutine { continuation ->
		enqueue(object : Callback {
			override fun onResponse(call: Call, response: Response) {
				continuation.resume(response) { cause, response, _ ->
					response.close()
				}
			}

			override fun onFailure(call: Call, e: IOException) {
				continuation.resumeWithException(e)
			}
		})

		continuation.invokeOnCancellation {
			cancel()
		}
	}
}
