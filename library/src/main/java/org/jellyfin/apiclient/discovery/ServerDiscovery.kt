package org.jellyfin.apiclient.discovery

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import org.jellyfin.apiclient.logging.ILogger
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 * A class used to discover Jellyfin servers in the local network.
 *
 * Use the [discover] function to retrieve a flow of servers until the timeout or maximum server count is hit,
 */
class ServerDiscovery(
	private val gson: Gson,
	private val logger: ILogger,
	private val discoveryBroadcastAddressesProvider: IDiscoveryBroadcastAddressesProvider = JavaNetBroadcastAddressesProvider()
) {
	companion object {
		const val DISCOVERY_MESSAGE = "who is JellyfinServer?"
		const val DISCOVERY_PORT = 7359
		const val DISCOVERY_RECEIVE_BUFFER = 1024 // bytes
		const val DISCOVERY_TIMEOUT = 30 // seconds
		const val DISCOVERY_MAX_SERVERS = 15
	}

	/**
	 * Send our broadcast message to a given address
	 */
	private fun discoverAddress(socket: DatagramSocket, address: InetAddress): Unit = try {
		val message = DISCOVERY_MESSAGE.toByteArray()
		val packet = DatagramPacket(message, message.size, address, DISCOVERY_PORT)
		socket.send(packet)

		logger.debug("Discovery: Discovering via %s", address)
	} catch (err: IOException) {
		logger.error("Discovery: Unable to send discovery message to %s", address, err)
	}

	/**
	 * Try reading and parsing messages sent to a given socket
	 */
	private fun receive(socket: DatagramSocket): DiscoveryServerInfo? {
		logger.debug("Discovery: Reading reply...")

		val buffer = ByteArray(DISCOVERY_RECEIVE_BUFFER) // Buffer to receive message in
		val packet = DatagramPacket(buffer, buffer.size)

		return try {
			socket.receive(packet)

			// Convert message to string
			val message = String(packet.data, 0, packet.length)
			logger.debug("""Discovery: Received message "%s"""", message)

			// Read as JSON
			var info = gson.fromJson(message, DiscoveryServerInfo::class.java)
			if (info.endpointAddress == null && socket.remoteSocketAddress != null)
				info = info.copy(endpointAddress = socket.remoteSocketAddress.toString())

			info
		} catch (err: IOException) {
			// Unable to receive
			logger.error("Discovery: Unable to receive message", err)
			null
		} catch (err: JsonSyntaxException) {
			// Unable to parse
			logger.error("Discovery: Unable to deserialize message", err)
			null
		}
	}

	/**
	 * Discover servers on the local network
	 */
	fun discover(
		timeout: Int = DISCOVERY_TIMEOUT,
		maxServers: Int = DISCOVERY_MAX_SERVERS
	) = flow {
		logger.info("Discovery: Starting discovery with timeout of %sms", timeout)

		val socket = DatagramSocket().apply {
			broadcast = true
			soTimeout = timeout
		}

		// Send
		val addresses = discoveryBroadcastAddressesProvider.getBroadcastAddresses()
		addresses.forEach { address ->
			discoverAddress(socket, address)
		}

		logger.debug("Discovery: Finished sending broadcasts, listening for responses")

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

		logger.debug("Discovery: End")
	}
}
