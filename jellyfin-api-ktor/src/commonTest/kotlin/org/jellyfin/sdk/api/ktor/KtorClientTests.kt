package org.jellyfin.sdk.api.ktor

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldStartWith
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.toByteArray
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.HttpClientOptions
import org.jellyfin.sdk.api.client.HttpMethod
import org.jellyfin.sdk.api.client.exception.InvalidStatusException
import org.jellyfin.sdk.api.client.exception.TimeoutException
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

private class Fixture(handler: suspend MockRequestHandleScope.(HttpRequestData) -> HttpResponseData) {
	val requests = mutableListOf<HttpRequestData>()

	private val engine = MockEngine { request ->
		requests += request
		handler(request)
	}

	val client: ApiClient = KtorFactory(HttpClient(engine)).create(
		baseUrl = "https://jellyfin.example.com/jf",
		accessToken = "secret-token",
		clientInfo = ClientInfo("Test", "1.2.3"),
		deviceInfo = DeviceInfo("device-id", "Device", listOf("en-US", "nl")),
		httpClientOptions = HttpClientOptions(),
		socketConnectionFactory = KtorFactory(HttpClient(engine)),
	)
}

class KtorClientTests : FunSpec({
	test("Sends GET requests with authentication and returns the raw response") {
		val fixture = Fixture {
			respond("""{"hello":"world"}""", HttpStatusCode.OK, headersOf("X-Test", "value"))
		}

		val response = fixture.client.request(
			HttpMethod.GET,
			"/Items/{id}",
			pathParameters = mapOf("id" to "1/2"),
			queryParameters = mapOf("limit" to 10, "fields" to listOf("A", "B")),
		)

		response.status shouldBe 200
		response.body.decodeToString() shouldBe """{"hello":"world"}"""
		response.headers["X-Test"] shouldBe listOf("value")

		val request = fixture.requests.single()
		request.method.value shouldBe "GET"
		request.url.toString() shouldBe "https://jellyfin.example.com/jf/Items/1%2F2?limit=10&fields=A&fields=B"
		request.headers[HttpHeaders.Authorization]!! shouldStartWith "MediaBrowser "
		request.headers[HttpHeaders.Authorization]!! shouldContain """Token="secret-token""""
		request.headers[HttpHeaders.UserAgent]!! shouldStartWith "Test/1.2.3 via jellyfin-sdk-kotlin"
		request.headers[HttpHeaders.AcceptLanguage] shouldBe "en-US,nl"
	}

	test("Sends POST requests with text body") {
		val fixture = Fixture { respond("", HttpStatusCode.NoContent) }

		fixture.client.request(HttpMethod.POST, "/Echo", requestBody = "text")

		val request = fixture.requests.single()
		request.method.value shouldBe "POST"
		request.body.contentType.toString() shouldBe "text/plain; charset=utf-8"
		request.body.toByteArray().decodeToString() shouldBe "text"
	}

	test("Sends POST requests without body as empty body") {
		val fixture = Fixture { respond("", HttpStatusCode.NoContent) }

		fixture.client.request(HttpMethod.POST, "/Empty")

		fixture.requests.single().body.toByteArray().size shouldBe 0
	}

	test("Throws InvalidStatusException for non successful status codes") {
		val fixture = Fixture { respond("nope", HttpStatusCode.Unauthorized) }

		val error = runCatching { fixture.client.request(HttpMethod.GET, "/Fail") }.exceptionOrNull()

		(error is InvalidStatusException) shouldBe true
		(error as InvalidStatusException).status shouldBe 401
	}

	test("Wraps connection failures") {
		val fixture = Fixture { throw io.ktor.client.network.sockets.ConnectTimeoutException("timeout", null) }

		val error = runCatching { fixture.client.request(HttpMethod.GET, "/Fail") }.exceptionOrNull()

		(error is TimeoutException) shouldBe true
	}

	test("Converts http urls to WebSocket urls") {
		"http://localhost:8096/socket".toWebSocketUrl() shouldBe "ws://localhost:8096/socket"
		"https://example.com/jf/socket".toWebSocketUrl() shouldBe "wss://example.com/jf/socket"
		"https://example.com:8920/socket".toWebSocketUrl() shouldBe "wss://example.com:8920/socket"
		"ws://example.com/socket".toWebSocketUrl() shouldBe "ws://example.com/socket"
	}
})
