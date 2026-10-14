package org.jellyfin.sdk.discovery

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.BoundDatagramSocket
import io.ktor.network.sockets.Datagram
import io.ktor.network.sockets.InetSocketAddress
import io.ktor.network.sockets.aSocket
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.writeFully
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.jellyfin.sdk.model.api.ServerDiscoveryInfo

private val logger = KotlinLogging.logger {}

/**
 * Local server discovery for platforms without `java.net`.
 */
internal class KtorLocalServerDiscovery(
	private val broadcastAddress: String = "255.255.255.255",
	private val port: Int = DISCOVERY_PORT,
) {
	companion object {
		const val DISCOVERY_MESSAGE: String = "who is JellyfinServer?"
		const val DISCOVERY_PORT: Int = 7359
		private const val IPV4_SIZE = 4
	}

	private val json = Json { ignoreUnknownKeys = true }

	private suspend fun receive(socket: BoundDatagramSocket, timeout: Int): ServerDiscoveryInfo? {
		logger.debug { "Reading reply..." }

		@Suppress("TooGenericExceptionCaught")
		return try {
			val datagram = withTimeoutOrNull(timeout.toLong()) { socket.receive() } ?: return null
			val message = datagram.packet.readBytes().decodeToString()
			logger.debug { """Received message "$message"""" }

			val address = datagram.address as? InetSocketAddress
			val bytes = address?.resolveAddress()
			val host = when (bytes?.size) {
				IPV4_SIZE -> bytes.joinToString(".") { (it.toInt() and 0xFF).toString() }
				else -> address?.hostname
			}

			json.decodeFromString<ServerDiscoveryInfo>(message).copy(endpointAddress = host)
		} catch (err: SerializationException) {
			logger.error(err) { "Unable to deserialize message" }
			null
		} catch (err: IllegalArgumentException) {
			logger.error(err) { "Unable to parse message" }
			null
		}
	}

	fun discover(timeout: Int, maxServers: Int): Flow<ServerDiscoveryInfo> = flow {
		logger.info { "Starting discovery with timeout of ${timeout}ms" }

		val selector = SelectorManager(Dispatchers.IO)
		val socket = aSocket(selector).udp().bind { broadcast = true }

		try {
			val message = DISCOVERY_MESSAGE.encodeToByteArray()
			socket.send(Datagram(buildPacket { writeFully(message) }, InetSocketAddress(broadcastAddress, port)))
			logger.debug { "Finished sending broadcast, listening for responses" }

			val foundServers = mutableSetOf<String>()

			@Suppress("UnusedPrivateMember")
			for (i in 0..maxServers) {
				if (!currentCoroutineContext().isActive) break

				val info = receive(socket, timeout) ?: continue

				if (info.id in foundServers) continue
				foundServers += info.id

				emit(info)
			}
		} finally {
			socket.close()
			selector.close()
		}

		logger.debug { "End" }
	}.flowOn(Dispatchers.IO)
}
