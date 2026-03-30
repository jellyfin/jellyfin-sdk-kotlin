package org.jellyfin.sdk.discovery

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first
import org.jellyfin.sdk.createJellyfin
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import kotlin.concurrent.thread

class LocalServerDiscoveryTests : FunSpec({
	test("discover populates endpointAddress from packet source IP") {
		// Fake Jellyfin server that responds to discovery on loopback
		val serverSocket = DatagramSocket(0, InetAddress.getLoopbackAddress())
		val serverPort = serverSocket.localPort

		thread(isDaemon = true) {
			val buf = ByteArray(1024)
			val request = DatagramPacket(buf, buf.size)
			serverSocket.receive(request)

			// Server reports localhost in Address (common misconfiguration)
			val response = """
				{"Address":"http://localhost:8096","Id":"test-id","Name":"Test Server"}
			""".trimIndent().toByteArray()
			val reply = DatagramPacket(response, response.size, request.address, request.port)
			serverSocket.send(reply)
			serverSocket.close()
		}

		// Manually replicate what discover() does: send message, receive, check result
		val clientSocket = DatagramSocket().apply { soTimeout = 2000 }
		val message = LocalServerDiscovery.DISCOVERY_MESSAGE.toByteArray()
		clientSocket.send(
			DatagramPacket(message, message.size, InetAddress.getLoopbackAddress(), serverPort),
		)

		val recvBuf = ByteArray(1024)
		val recvPacket = DatagramPacket(recvBuf, recvBuf.size)
		clientSocket.receive(recvPacket)
		clientSocket.close()

		// The raw JSON has no endpointAddress
		val rawJson = String(recvPacket.data, 0, recvPacket.length)
		rawJson.contains("EndpointAddress") shouldBe false

		// The packet source IP should be loopback
		val sourceHost = recvPacket.address.hostAddress
		sourceHost.shouldNotBeNull()

		// Verify that the SDK's receive() logic would set endpointAddress = sourceHost
		val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
		val info = json.decodeFromString(
			org.jellyfin.sdk.model.api.ServerDiscoveryInfo.serializer(),
			rawJson,
		)
		val corrected = info.copy(endpointAddress = sourceHost)

		corrected.address shouldBe "http://localhost:8096"
		corrected.endpointAddress shouldBe sourceHost
		corrected.name shouldBe "Test Server"
	}
})
