package org.jellyfin.sdk.discovery

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.Datagram
import io.ktor.network.sockets.InetSocketAddress
import io.ktor.network.sockets.aSocket
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.writeFully
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.toList
import org.jellyfin.sdk.model.api.ServerDiscoveryInfo

class KtorLocalServerDiscoveryTests : FunSpec({
	suspend fun discoverWithReplies(vararg replies: String): Pair<String, List<ServerDiscoveryInfo>> = coroutineScope {
		val selector = SelectorManager(Dispatchers.IO)
		val responder = aSocket(selector).udp().bind(InetSocketAddress("127.0.0.1", 0))
		val port = (responder.localAddress as InetSocketAddress).port

		try {
			val request = async {
				val datagram = responder.receive()
				for (reply in replies) {
					responder.send(Datagram(buildPacket { writeFully(reply.encodeToByteArray()) }, datagram.address))
				}
				datagram.packet.readBytes().decodeToString()
			}

			val servers = KtorLocalServerDiscovery(broadcastAddress = "127.0.0.1", port = port)
				.discover(timeout = 500, maxServers = 5)
				.toList()

			request.await() to servers
		} finally {
			responder.close()
			selector.close()
		}
	}

	test("discover sends the discovery message and parses replies") {
		val (request, servers) = discoverWithReplies(
			"""{"Address":"http://192.168.1.10:8096","Id":"a","Name":"Server A","EndpointAddress":null}""",
			"""{"Address":"http://192.168.1.11:8096","Id":"b","Name":"Server B"}""",
		)

		request shouldBe KtorLocalServerDiscovery.DISCOVERY_MESSAGE
		servers shouldBe listOf(
			ServerDiscoveryInfo("http://192.168.1.10:8096", "a", "Server A", "127.0.0.1"),
			ServerDiscoveryInfo("http://192.168.1.11:8096", "b", "Server B", "127.0.0.1"),
		)
	}

	test("discover skips duplicate and invalid replies") {
		val (_, servers) = discoverWithReplies(
			"not json",
			"""{"Address":"http://192.168.1.10:8096","Id":"a","Name":"Server A"}""",
			"""{"Address":"http://192.168.1.10:8096","Id":"a","Name":"Server A"}""",
		)

		servers shouldBe listOf(ServerDiscoveryInfo("http://192.168.1.10:8096", "a", "Server A", "127.0.0.1"))
	}

	test("discover completes without replies") {
		val (_, servers) = discoverWithReplies()

		servers.shouldBeEmpty()
	}
})
