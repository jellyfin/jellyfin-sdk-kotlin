package org.jellyfin.sdk.api.sockets.helper

import org.jellyfin.sdk.api.sockets.SocketInstance
import org.jellyfin.sdk.api.sockets.data.SubscriptionType
import org.jellyfin.sdk.api.sockets.listener.SocketListener
import org.jellyfin.sdk.api.sockets.listener.SocketListenerDefinition
import org.jellyfin.sdk.model.socket.IncomingSocketMessage

internal class ListenerHelper {
	private var _listeners = mutableListOf<SocketListener>()
	val listeners: List<SocketListener> = _listeners

	val subscriptions get() = listeners.map { it.definition.subscribesTo }.flatten()
	var activeSubscriptions = mutableListOf<SubscriptionType<*>>()

	fun reportCredentialChangedReconnect() {
		_listeners.removeAll { listener -> listener.definition.stopOnCredentialsChange }
	}

	fun addListenerDefinition(instance: SocketInstance, definition: SocketListenerDefinition): SocketListener {
		val listener = SocketListener(definition, instance)
		_listeners.add(listener)
		return listener
	}

	fun removeListener(listener: SocketListener) {
		_listeners.remove(listener)
	}

	fun reset() {
		_listeners.clear()
	}

	fun forwardMessage(message: IncomingSocketMessage) {
		for (listener in listeners) {
			val acceptsMessage = listener.definition.filterTypes.any { type -> type.isInstance(message) }
			if (acceptsMessage) listener.definition.listener.onReceive(message)
		}
	}
}
