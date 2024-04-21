// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Plugin installation cancelled message.
 */
@Serializable
@SerialName("PackageInstallationCancelled")
public data class PluginInstallationCancelledMessage(
	/**
	 * Class InstallationInfo.
	 */
	@SerialName("Data")
	public val `data`: InstallationInfo? = null,
	/**
	 * The message id.
	 */
	@SerialName("MessageId")
	override val messageId: UUID,
) : OutboundWebSocketMessage {
	/**
	 * The different kinds of messages that are used in the WebSocket api.
	 */
	override val messageType: SessionMessageType = SessionMessageType.PACKAGE_INSTALLATION_CANCELLED
}
