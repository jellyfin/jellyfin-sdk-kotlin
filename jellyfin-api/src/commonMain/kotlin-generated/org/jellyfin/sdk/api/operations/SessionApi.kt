// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.collections.buildMap
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.api.client.extensions.delete
import org.jellyfin.sdk.api.client.extensions.post
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemKind
import org.jellyfin.sdk.model.api.ClientCapabilitiesDto
import org.jellyfin.sdk.model.api.GeneralCommand
import org.jellyfin.sdk.model.api.GeneralCommandType
import org.jellyfin.sdk.model.api.MediaType
import org.jellyfin.sdk.model.api.MessageCommand
import org.jellyfin.sdk.model.api.NameIdPair
import org.jellyfin.sdk.model.api.PlayCommand
import org.jellyfin.sdk.model.api.PlaystateCommand
import org.jellyfin.sdk.model.api.SessionInfoDto
import org.jellyfin.sdk.model.api.request.PlayRequest
import org.jellyfin.sdk.model.api.request.PostCapabilitiesRequest

public class SessionApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Adds an additional user to a session.
	 *
	 * @param sessionId The session id.
	 * @param userId The user id.
	 */
	public suspend fun addUserToSession(sessionId: String, userId: UUID): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(2) {
			put("sessionId", sessionId)
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/User/{userId}", pathParameters,
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
		itemType: BaseItemKind,
		itemId: String,
		itemName: String,
	): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("sessionId", sessionId)
		}
		val queryParameters = buildMap<String, Any?>(3) {
			put("itemType", itemType)
			put("itemId", itemId)
			put("itemName", itemName)
		}
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/Viewing", pathParameters, queryParameters,
				data)
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
	 * Gets a list of sessions.
	 *
	 * @param controllableByUserId Filter by sessions that a given user is allowed to remote control.
	 * @param deviceId Filter by device Id.
	 * @param activeWithinSeconds Optional. Filter by sessions that were active in the last n seconds.
	 */
	public suspend fun getSessions(
		controllableByUserId: UUID? = null,
		deviceId: String? = null,
		activeWithinSeconds: Int? = null,
	): Response<List<SessionInfoDto>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(3) {
			put("controllableByUserId", controllableByUserId)
			put("deviceId", deviceId)
			put("activeWithinSeconds", activeWithinSeconds)
		}
		val data = null
		val response = api.`get`<List<SessionInfoDto>>("/Sessions", pathParameters, queryParameters, data)
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
	 * @param mediaSourceId Optional. The media source id.
	 * @param audioStreamIndex Optional. The index of the audio stream to play.
	 * @param subtitleStreamIndex Optional. The index of the subtitle stream to play.
	 * @param startIndex Optional. The start index.
	 */
	public suspend fun play(
		sessionId: String,
		playCommand: PlayCommand,
		itemIds: Collection<UUID> = emptyList(),
		startPositionTicks: Long? = null,
		mediaSourceId: String? = null,
		audioStreamIndex: Int? = null,
		subtitleStreamIndex: Int? = null,
		startIndex: Int? = null,
	): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("sessionId", sessionId)
		}
		val queryParameters = buildMap<String, Any?>(7) {
			put("playCommand", playCommand)
			put("itemIds", itemIds)
			put("startPositionTicks", startPositionTicks)
			put("mediaSourceId", mediaSourceId)
			put("audioStreamIndex", audioStreamIndex)
			put("subtitleStreamIndex", subtitleStreamIndex)
			put("startIndex", startIndex)
		}
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/Playing", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Instructs a session to play an item.
	 *
	 * @param request The request parameters
	 */
	public suspend fun play(request: PlayRequest): Response<Unit> = play(
		sessionId = request.sessionId,
		playCommand = request.playCommand,
		itemIds = request.itemIds,
		startPositionTicks = request.startPositionTicks,
		mediaSourceId = request.mediaSourceId,
		audioStreamIndex = request.audioStreamIndex,
		subtitleStreamIndex = request.subtitleStreamIndex,
		startIndex = request.startIndex,
	)

	/**
	 * Updates capabilities for a device.
	 *
	 * @param id The session id.
	 * @param playableMediaTypes A list of playable media types, comma delimited. Audio, Video, Book,
	 * Photo.
	 * @param supportedCommands A list of supported remote control commands, comma delimited.
	 * @param supportsMediaControl Determines whether media can be played remotely..
	 * @param supportsPersistentIdentifier Determines whether the device supports a unique identifier.
	 */
	public suspend fun postCapabilities(
		id: String? = null,
		playableMediaTypes: Collection<MediaType>? = emptyList(),
		supportedCommands: Collection<GeneralCommandType>? = emptyList(),
		supportsMediaControl: Boolean? = false,
		supportsPersistentIdentifier: Boolean? = true,
	): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(5) {
			put("id", id)
			put("playableMediaTypes", playableMediaTypes)
			put("supportedCommands", supportedCommands)
			put("supportsMediaControl", supportsMediaControl)
			put("supportsPersistentIdentifier", supportsPersistentIdentifier)
		}
		val data = null
		val response = api.post<Unit>("/Sessions/Capabilities", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Updates capabilities for a device.
	 *
	 * @param request The request parameters
	 */
	public suspend fun postCapabilities(request: PostCapabilitiesRequest = PostCapabilitiesRequest()):
			Response<Unit> = postCapabilities(
		id = request.id,
		playableMediaTypes = request.playableMediaTypes,
		supportedCommands = request.supportedCommands,
		supportsMediaControl = request.supportsMediaControl,
		supportsPersistentIdentifier = request.supportsPersistentIdentifier,
	)

	/**
	 * Updates capabilities for a device.
	 *
	 * @param id The session id.
	 */
	public suspend fun postFullCapabilities(id: String? = null, `data`: ClientCapabilitiesDto):
			Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(1) {
			put("id", id)
		}
		val response = api.post<Unit>("/Sessions/Capabilities/Full", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Removes an additional user from a session.
	 *
	 * @param sessionId The session id.
	 * @param userId The user id.
	 */
	public suspend fun removeUserFromSession(sessionId: String, userId: UUID): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(2) {
			put("sessionId", sessionId)
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.delete<Unit>("/Sessions/{sessionId}/User/{userId}", pathParameters,
				queryParameters, data)
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
		val queryParameters = buildMap<String, Any?>(2) {
			put("sessionId", sessionId)
			put("itemId", itemId)
		}
		val data = null
		val response = api.post<Unit>("/Sessions/Viewing", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Issues a full general command to a client.
	 *
	 * @param sessionId The session id.
	 */
	public suspend fun sendFullGeneralCommand(sessionId: String, `data`: GeneralCommand):
			Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("sessionId", sessionId)
		}
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
		val pathParameters = buildMap<String, Any?>(2) {
			put("sessionId", sessionId)
			put("command", command)
		}
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
	 */
	public suspend fun sendMessageCommand(sessionId: String, `data`: MessageCommand): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("sessionId", sessionId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/Sessions/{sessionId}/Message", pathParameters, queryParameters,
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
		controllingUserId: String? = null,
	): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(2) {
			put("sessionId", sessionId)
			put("command", command)
		}
		val queryParameters = buildMap<String, Any?>(2) {
			put("seekPositionTicks", seekPositionTicks)
			put("controllingUserId", controllingUserId)
		}
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
		val pathParameters = buildMap<String, Any?>(2) {
			put("sessionId", sessionId)
			put("command", command)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.post<Unit>("/Sessions/{sessionId}/System/{command}", pathParameters,
				queryParameters, data)
		return response
	}
}
