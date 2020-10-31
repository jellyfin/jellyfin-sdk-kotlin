// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.api.operations

import java.util.UUID
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import org.jellyfin.apiclient.api.client.KtorClient
import org.jellyfin.apiclient.api.client.Response
import org.jellyfin.apiclient.model.api.ClientCapabilities
import org.jellyfin.apiclient.model.api.GeneralCommand
import org.jellyfin.apiclient.model.api.GeneralCommandType
import org.jellyfin.apiclient.model.api.NameIdPair
import org.jellyfin.apiclient.model.api.PlayCommand
import org.jellyfin.apiclient.model.api.PlaystateCommand
import org.jellyfin.apiclient.model.api.SessionInfo

public class SessionApi(
	private val api: KtorClient
) {
	/**
	 * Get all password reset providers.
	 */
	public suspend fun getPasswordResetProviders(): Response<List<NameIdPair>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<NameIdPair>>("/Auth/PasswordResetProviders", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Get all auth providers.
	 */
	public suspend fun getAuthProviders(): Response<List<NameIdPair>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<NameIdPair>>("/Auth/Providers", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Gets a list of sessions.
	 *
	 * @param controllableByUserId Filter by sessions that a given user is allowed to remote control.
	 * @param deviceId Filter by device Id.
	 * @param activeWithinSeconds Optional. Filter by sessions that were active in the last n seconds.
	 */
	public suspend fun getSessions(
		controllableByUserId: UUID? = null,
		deviceId: String? = null,
		activeWithinSeconds: Int? = null
	): Response<List<SessionInfo>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["controllableByUserId"] = controllableByUserId
		queryParameters["deviceId"] = deviceId
		queryParameters["activeWithinSeconds"] = activeWithinSeconds
		val data = null
		val response = api.`get`<List<SessionInfo>>("/Sessions", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Issues a full general command to a client.
	 *
	 * @param sessionId The session id.
	 */
	public suspend fun sendFullGeneralCommand(sessionId: String, `data`: GeneralCommand):
			Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/Sessions/{sessionId}/Command", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Issues a general command to a client.
	 *
	 * @param sessionId The session id.
	 * @param command The command to send.
	 */
	public suspend fun sendGeneralCommand(sessionId: String, command: GeneralCommandType):
			Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		pathParameters["command"] = command
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/Command/{command}", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Issues a command to a client to display a message to the user.
	 *
	 * @param sessionId The session id.
	 * @param text The message test.
	 * @param header The message header.
	 * @param timeoutMs The message timeout. If omitted the user will have to confirm viewing the
	 * message.
	 */
	public suspend fun sendMessageCommand(
		sessionId: String,
		text: String,
		header: String? = null,
		timeoutMs: Long? = null
	): Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["text"] = text
		queryParameters["header"] = header
		queryParameters["timeoutMs"] = timeoutMs
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/Message", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Instructs a session to play an item.
	 *
	 * @param sessionId The session id.
	 * @param playCommand The type of play command to issue (PlayNow, PlayNext, PlayLast). Clients who
	 * have not yet implemented play next and play last may play now.
	 * @param itemIds The ids of the items to play, comma delimited.
	 * @param startPositionTicks The starting position of the first item.
	 */
	public suspend fun play(
		sessionId: String,
		playCommand: PlayCommand,
		itemIds: String,
		startPositionTicks: Long? = null
	): Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["playCommand"] = playCommand
		queryParameters["itemIds"] = itemIds
		queryParameters["startPositionTicks"] = startPositionTicks
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/Playing", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Issues a playstate command to a client.
	 *
	 * @param sessionId The session id.
	 * @param command The MediaBrowser.Model.Session.PlaystateCommand.
	 * @param seekPositionTicks The optional position ticks.
	 * @param controllingUserId The optional controlling user id.
	 */
	public suspend fun sendPlaystateCommand(
		sessionId: String,
		command: PlaystateCommand,
		seekPositionTicks: Long? = null,
		controllingUserId: String? = null
	): Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		pathParameters["command"] = command
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["seekPositionTicks"] = seekPositionTicks
		queryParameters["controllingUserId"] = controllingUserId
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/Playing/{command}", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Issues a system command to a client.
	 *
	 * @param sessionId The session id.
	 * @param command The command to send.
	 */
	public suspend fun sendSystemCommand(sessionId: String, command: GeneralCommandType):
			Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		pathParameters["command"] = command
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/System/{command}", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Adds an additional user to a session.
	 *
	 * @param sessionId The session id.
	 * @param userId The user id.
	 */
	public suspend fun addUserToSession(sessionId: String, userId: UUID): Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		pathParameters["userId"] = userId
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/User/{userId}", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Removes an additional user from a session.
	 *
	 * @param sessionId The session id.
	 * @param userId The user id.
	 */
	public suspend fun removeUserFromSession(sessionId: String, userId: UUID): Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		pathParameters["userId"] = userId
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.delete<Unit>("/Sessions/{sessionId}/User/{userId}", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Instructs a session to browse to an item or view.
	 *
	 * @param sessionId The session Id.
	 * @param itemType The type of item to browse to.
	 * @param itemId The Id of the item.
	 * @param itemName The name of the item.
	 */
	public suspend fun displayContent(
		sessionId: String,
		itemType: String,
		itemId: String,
		itemName: String
	): Response<Unit> {
		val pathParameters = mutableMapOf<String, Any?>()
		pathParameters["sessionId"] = sessionId
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["itemType"] = itemType
		queryParameters["itemId"] = itemId
		queryParameters["itemName"] = itemName
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/Viewing", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Updates capabilities for a device.
	 *
	 * @param id The session id.
	 * @param playableMediaTypes A list of playable media types, comma delimited. Audio, Video, Book,
	 * Photo.
	 * @param supportedCommands A list of supported remote control commands, comma delimited.
	 * @param supportsMediaControl Determines whether media can be played remotely..
	 * @param supportsSync Determines whether sync is supported.
	 * @param supportsPersistentIdentifier Determines whether the device supports a unique identifier.
	 */
	public suspend fun postCapabilities(
		id: String? = null,
		playableMediaTypes: String? = null,
		supportedCommands: List<GeneralCommandType>? = emptyList(),
		supportsMediaControl: Boolean = false,
		supportsSync: Boolean = false,
		supportsPersistentIdentifier: Boolean = true
	): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["id"] = id
		queryParameters["playableMediaTypes"] = playableMediaTypes
		queryParameters["supportedCommands"] = supportedCommands
		queryParameters["supportsMediaControl"] = supportsMediaControl
		queryParameters["supportsSync"] = supportsSync
		queryParameters["supportsPersistentIdentifier"] = supportsPersistentIdentifier
		val data = null
		val response = api.post<Unit>("/Sessions/Capabilities", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Updates capabilities for a device.
	 *
	 * @param id The session id.
	 */
	public suspend fun postFullCapabilities(id: String? = null, `data`: ClientCapabilities):
			Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["id"] = id
		val response = api.post<Unit>("/Sessions/Capabilities/Full", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Reports that a session has ended.
	 */
	public suspend fun reportSessionEnded(): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.post<Unit>("/Sessions/Logout", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Reports that a session is viewing an item.
	 *
	 * @param sessionId The session id.
	 * @param itemId The item id.
	 */
	public suspend fun reportViewing(sessionId: String? = null, itemId: String): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["sessionId"] = sessionId
		queryParameters["itemId"] = itemId
		val data = null
		val response = api.post<Unit>("/Sessions/Viewing", pathParameters, queryParameters, data)
		return response
	}
}
