package org.jellyfin.apiclient

import com.google.gson.Gson
import org.jellyfin.apiclient.discovery.IDiscoveryBroadcastAddressesProvider
import org.jellyfin.apiclient.discovery.ServerDiscovery
import org.jellyfin.apiclient.logging.ILogger
import java.util.regex.Pattern

class DiscoveryService(
	private val gson: Gson,
	private val logger: ILogger,
	private val discoveryBroadcastAddressesProvider: IDiscoveryBroadcastAddressesProvider
) {
	private val serverDiscovery by lazy {
		// Create instance
		ServerDiscovery(gson, logger, discoveryBroadcastAddressesProvider)
	}

	// Address helper
	fun addressCandidates(input: String): Iterable<String> {
		val candidates = mutableListOf<String>()

		// Check for protocol
		val containsProtocol = Pattern.compile("^https?://.*$", Pattern.CASE_INSENSITIVE).matcher(input).matches()
		if (!containsProtocol) {
			candidates.add("https://$input")
			candidates.add("http://$input")
		} else {
			candidates.add(input)
		}

		return candidates
	}

	// Discovery
	fun discover(
		timeout: Int = ServerDiscovery.DISCOVERY_TIMEOUT,
		maxServers: Int = ServerDiscovery.DISCOVERY_MAX_SERVERS
	) = serverDiscovery.discover(
		timeout,
		maxServers
	)
}
