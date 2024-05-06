package org.jellyfin.sdk.discovery

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import org.jellyfin.sdk.JellyfinOptions
import org.jellyfin.sdk.model.api.ServerDiscoveryInfo
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketTimeoutException
import kotlin.coroutines.coroutineContext

private val logger = KotlinLogging.logger {}

/**
 * Used to discover Jellyfin servers in the local network.
 *
 * Use the [discover] function to retrieve a flow of servers until the timeout is exceeded or
 * the maximum amount of servers has been retrieved.
 */
public actual class LocalServerDiscovery actual constructor(jellyfinOptions: JellyfinOptions) {
	public actual companion object {
		public const val DISCOVERY_MESSAGE: String = "who is JellyfinServer?"
		public const val DISCOVERY_PORT: Int = 7359
		public const val DISCOVERY_RECEIVE_BUFFER: Int = 1024 // bytes
		public actual val DISCOVERY_TIMEOUT: Int = 500 // ms
		public actual val DISCOVERY_MAX_SERVERS: Int = 15
	}

	private val json = Json {
		ignoreUnknownKeys = true
	}

	/**
	 * Send our broadcast message to a given address
	 */
	private fun discoverAddress(socket: DatagramSocket, address: InetAddress): Unit = try {
		val message = DISCOVERY_MESSAGE.toByteArray()
		val packet = DatagramPacket(message, message.size, address, DISCOVERY_PORT)
		socket.send(packet)

		logger.debug { "Discovering via $address" }
	} catch (err: IOException) {
		logger.error(err) { "Unable to send discovery message to $address" }
	}

	/**
	 * Try reading and parsing messages sent to a given socket
	 */
	private fun receive(socket: DatagramSocket): ServerDiscoveryInfo? {
		logger.debug { "Reading reply..." }

		val buffer = ByteArray(DISCOVERY_RECEIVE_BUFFER) // Buffer to receive message in
		val packet = DatagramPacket(buffer, buffer.size)

		@Suppress("SwallowedException")
		return try {
			socket.receive(packet)

			// Convert message to string
			val message = String(packet.data, 0, packet.length)
			logger.debug { """Received message "$message"""" }

			// Read as JSON
			val info = json.decodeFromString(ServerDiscoveryInfo.serializer(), message)

			info
		} catch (err: SocketTimeoutException) {
			// Unable to receive due too timeout, which is common for non-Jellyfin devices
			// Just ignore
			null
		} catch (err: IOException) {
			// Unable to receive
			logger.error(err) { "Unable to receive message" }
			null
		} catch (err: SerializationException) {
			// Unable to parse
			logger.error(err) { "Unable to deserialize message" }
			null
		}
	}

	public actual fun discover(
		timeout: Int,
		maxServers: Int,
	): Flow<ServerDiscoveryInfo> = flow {
		logger.info { "Starting discovery with timeout of ${timeout}ms" }

		val socket = DatagramSocket().apply {
			soTimeout = timeout
		}

		// Send
		val broadcastAddress = InetAddress.getByAddress(byteArrayOf(255.toByte(), 255.toByte(), 255.toByte(), 255.toByte()))
		discoverAddress(socket, broadcastAddress)

		logger.debug { "Finished sending broadcast, listening for responses" }

		// Try reading incoming messages but with a maximum
		val foundServers = mutableSetOf<String>()

		@Suppress("UnusedPrivateMember")
		for (i in 0..maxServers) {
			if (socket.isClosed || !coroutineContext.isActive) break

			val info = receive(socket) ?: continue

			// Filter duplicates
			if (info.id in foundServers) continue
			foundServers += info.id

			emit(info)
		}

		// End
		socket.close()

		logger.debug { "End" }
	}.flowOn(Dispatchers.IO)
}
