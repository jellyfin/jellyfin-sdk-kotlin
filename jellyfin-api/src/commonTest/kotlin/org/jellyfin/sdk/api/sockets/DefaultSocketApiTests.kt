package org.jellyfin.sdk.api.sockets

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.client.HttpMethod
import org.jellyfin.sdk.api.client.RawResponse
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

class DefaultSocketApiTests : FunSpec({
	test("WebSocket stays connected when subscriptions resume within grace period") {
		val connection = TestSocketConnection()
		val socketApi = DefaultSocketApi(
			api = TestApiClient(),
			socketReconnectPolicy = SocketReconnectPolicy.ExponentialDelayReconnect(),
			socketConnectionFactory = SocketConnectionFactory { _, _ -> connection },
		)

		coroutineScope {
			val firstSubscription = launch(start = CoroutineStart.UNDISPATCHED) {
				socketApi.subscribeAll().collect {}
			}
			connection.connected.await()

			firstSubscription.cancelAndJoin()

			val secondSubscription = launch(start = CoroutineStart.UNDISPATCHED) {
				socketApi.subscribeAll().collect {}
			}

			withTimeoutOrNull(1_000) {
				connection.disconnected.await()
			} shouldBe null
			connection.connectCount.value shouldBe 1

			secondSubscription.cancelAndJoin()
		}
	}
})

private class TestSocketConnection : SocketConnection {
	private val _state = MutableStateFlow<SocketConnectionState>(SocketConnectionState.Disconnected())

	override val state: StateFlow<SocketConnectionState> = _state

	val connected = CompletableDeferred<Unit>()
	val disconnected = CompletableDeferred<Unit>()
	val connectCount = MutableStateFlow(0)

	override suspend fun connect(
		url: String,
		clientName: String,
		clientVersion: String,
		deviceId: String,
		deviceName: String,
		accessToken: String,
		languages: List<String>,
	): Boolean {
		connectCount.value++
		_state.value = SocketConnectionState.Connecting
		connected.complete(Unit)
		return true
	}

	override suspend fun send(message: String): Boolean = true

	override suspend fun disconnect() {
		_state.value = SocketConnectionState.Disconnected()
		disconnected.complete(Unit)
	}
}

private class TestApiClient : ApiClient() {
	override val baseUrl: String = "http://localhost"
	override val accessToken: String = "test-token"
	override val clientInfo: ClientInfo = ClientInfo(
		name = "Test Client",
		version = "1.0",
	)
	override val deviceInfo: DeviceInfo = DeviceInfo(
		id = "test-device",
		name = "Test Device",
	)
	override val httpClientOptions: HttpClientOptions = HttpClientOptions()

	override val webSocket: SocketApi
		get() = error("Not used in this test")

	override fun update(
		baseUrl: String?,
		accessToken: String?,
		clientInfo: ClientInfo,
		deviceInfo: DeviceInfo,
	) = Unit

	override suspend fun request(
		method: HttpMethod,
		pathTemplate: String,
		pathParameters: Map<String, Any?>,
		queryParameters: Map<String, Any?>,
		requestBody: Any?,
	): RawResponse = error("Not used in this test")
}
