package org.jellyfin.apiclient.discovery

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.jellyfin.apiclient.model.discovery.DiscoveryServerInfo
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketTimeoutException

/**
 * Used to discover Jellyfin servers in the local network.
 *
 * Use the [discover] function to retrieve a flow of servers until the timeout is exceeded or
 * the maximum amount of servers has been retrieved.
 */
public class LocalServerDiscovery(
	private val discoveryBroadcastAddressesProvider: DiscoveryBroadcastAddressesProvider = JavaNetBroadcastAddressesProvider()
) {
	public companion object {
		public const val DISCOVERY_MESSAGE: String = "who is JellyfinServer?"
		public const val DISCOVERY_PORT: Int = 7359
		public const val DISCOVERY_RECEIVE_BUFFER: Int = 1024 // bytes
		public const val DISCOVERY_TIMEOUT: Int = 30 // seconds
		public const val DISCOVERY_MAX_SERVERS: Int = 15
	}

	private val logger = LoggerFactory.getLogger("LocalServerDiscovery")
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

		logger.debug("Discovering via $address")
	} catch (err: IOException) {
		logger.error("Unable to send discovery message to $address", err)
	}

	/**
	 * Try reading and parsing messages sent to a given socket
	 */
	private fun receive(socket: DatagramSocket): DiscoveryServerInfo? {
		logger.debug("Reading reply...")

		val buffer = ByteArray(DISCOVERY_RECEIVE_BUFFER) // Buffer to receive message in
		val packet = DatagramPacket(buffer, buffer.size)

		return try {
			socket.receive(packet)

			// Convert message to string
			val message = String(packet.data, 0, packet.length)
			logger.debug("""Received message "$message"""")

			// Read as JSON
			val info = json.decodeFromString(DiscoveryServerInfo.serializer(), message)

			info
		} catch (err: SocketTimeoutException) {
			// Unable to receive due too timeout, which is common for non-Jellyfin devices
			// Just ignore
			null
		} catch (err: IOException) {
			// Unable to receive
			logger.error("Unable to receive message", err)
			null
		} catch (err: SerializationException) {
			// Unable to parse
			logger.error("Unable to deserialize message", err)
			null
		}
	}

	/**
	 * Discover servers on the local network
	 */
	public fun discover(
		timeout: Int = DISCOVERY_TIMEOUT,
		maxServers: Int = DISCOVERY_MAX_SERVERS
	): Flow<DiscoveryServerInfo> = flow {
		logger.info("Starting discovery with timeout of ${timeout}ms")

		val socket = DatagramSocket().apply {
			broadcast = true
			soTimeout = timeout
		}

		// Send
		val addresses = discoveryBroadcastAddressesProvider.getBroadcastAddresses()
		addresses.forEach { address ->
			discoverAddress(socket, address)
		}

		logger.debug("Finished sending broadcasts, listening for responses")

		// Try reading incoming messages but with a maximum
		val foundServers = mutableSetOf<String>()
		for (i in 0..maxServers) {
			if (socket.isClosed || !GlobalScope.isActive) break

			val info = receive(socket) ?: continue

			// Filter duplicates
			if (info.id in foundServers) continue
			foundServers += info.id

			emit(info)
		}

		// End
		socket.close()

		logger.debug("End")
	}
}
