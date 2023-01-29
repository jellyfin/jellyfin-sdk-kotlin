// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.api.operations

import kotlin.Any
import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.buildMap
import kotlin.collections.emptyMap
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.Response
import org.jellyfin.sdk.api.client.exception.MissingUserIdException
import org.jellyfin.sdk.api.client.extensions.`get`
import org.jellyfin.sdk.api.client.extensions.delete
import org.jellyfin.sdk.api.client.extensions.post
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.AuthenticateUserByName
import org.jellyfin.sdk.model.api.AuthenticationResult
import org.jellyfin.sdk.model.api.CreateUserByName
import org.jellyfin.sdk.model.api.ForgotPasswordDto
import org.jellyfin.sdk.model.api.ForgotPasswordPinDto
import org.jellyfin.sdk.model.api.ForgotPasswordResult
import org.jellyfin.sdk.model.api.PinRedeemResult
import org.jellyfin.sdk.model.api.QuickConnectDto
import org.jellyfin.sdk.model.api.UpdateUserEasyPassword
import org.jellyfin.sdk.model.api.UpdateUserPassword
import org.jellyfin.sdk.model.api.UserConfiguration
import org.jellyfin.sdk.model.api.UserDto
import org.jellyfin.sdk.model.api.UserPolicy

public class UserApi(
	private val api: ApiClient,
) : Api {
	/**
	 * Authenticates a user.
	 *
	 * @param userId The user id.
	 * @param pw The password as plain text.
	 * @param password The password sha1-hash.
	 */
	public suspend fun authenticateUser(
		userId: UUID = api.userId ?: throw MissingUserIdException(),
		pw: String,
		password: String? = null,
	): Response<AuthenticationResult> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = buildMap<String, Any?>(2) {
			put("pw", pw)
			put("password", password)
		}
		val data = null
		val response = api.post<AuthenticationResult>("/Users/{userId}/Authenticate", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Authenticates a user by name.
	 */
	public suspend fun authenticateUserByName(`data`: AuthenticateUserByName):
			Response<AuthenticationResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<AuthenticationResult>("/Users/AuthenticateByName", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Authenticates a user with quick connect.
	 */
	public suspend fun authenticateWithQuickConnect(`data`: QuickConnectDto):
			Response<AuthenticationResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<AuthenticationResult>("/Users/AuthenticateWithQuickConnect",
				pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Creates a user.
	 */
	public suspend fun createUserByName(`data`: CreateUserByName): Response<UserDto> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<UserDto>("/Users/New", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Deletes a user.
	 *
	 * @param userId The user id.
	 */
	public suspend fun deleteUser(userId: UUID = api.userId ?: throw MissingUserIdException()):
			Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.delete<Unit>("/Users/{userId}", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Initiates the forgot password process for a local user.
	 */
	public suspend fun forgotPassword(`data`: ForgotPasswordDto): Response<ForgotPasswordResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<ForgotPasswordResult>("/Users/ForgotPassword", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Redeems a forgot password pin.
	 */
	public suspend fun forgotPasswordPin(`data`: ForgotPasswordPinDto): Response<PinRedeemResult> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<PinRedeemResult>("/Users/ForgotPassword/Pin", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets the user based on auth token.
	 */
	public suspend fun getCurrentUser(): Response<UserDto> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<UserDto>("/Users/Me", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a list of publicly visible users for display on a login screen.
	 */
	public suspend fun getPublicUsers(): Response<List<UserDto>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<UserDto>>("/Users/Public", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a user by Id.
	 *
	 * @param userId The user id.
	 */
	public suspend fun getUserById(userId: UUID = api.userId ?: throw MissingUserIdException()):
			Response<UserDto> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<UserDto>("/Users/{userId}", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets a list of users.
	 *
	 * @param isHidden Optional filter by IsHidden=true or false.
	 * @param isDisabled Optional filter by IsDisabled=true or false.
	 */
	public suspend fun getUsers(isHidden: Boolean? = null, isDisabled: Boolean? = null):
			Response<List<UserDto>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = buildMap<String, Any?>(2) {
			put("isHidden", isHidden)
			put("isDisabled", isDisabled)
		}
		val data = null
		val response = api.`get`<List<UserDto>>("/Users", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Updates a user.
	 *
	 * @param userId The user id.
	 */
	public suspend fun updateUser(userId: UUID = api.userId ?: throw MissingUserIdException(),
			`data`: UserDto): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/Users/{userId}", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Updates a user configuration.
	 *
	 * @param userId The user id.
	 */
	public suspend fun updateUserConfiguration(userId: UUID = api.userId ?: throw
			MissingUserIdException(), `data`: UserConfiguration): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/Users/{userId}/Configuration", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Updates a user's easy password.
	 *
	 * @param userId The user id.
	 */
	public suspend fun updateUserEasyPassword(userId: UUID = api.userId ?: throw
			MissingUserIdException(), `data`: UpdateUserEasyPassword): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/Users/{userId}/EasyPassword", pathParameters, queryParameters,
				data)
		return response
	}

	/**
	 * Updates a user's password.
	 *
	 * @param userId The user id.
	 */
	public suspend fun updateUserPassword(userId: UUID = api.userId ?: throw MissingUserIdException(),
			`data`: UpdateUserPassword): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/Users/{userId}/Password", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Updates a user policy.
	 *
	 * @param userId The user id.
	 */
	public suspend fun updateUserPolicy(userId: UUID = api.userId ?: throw MissingUserIdException(),
			`data`: UserPolicy): Response<Unit> {
		val pathParameters = buildMap<String, Any?>(1) {
			put("userId", userId)
		}
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/Users/{userId}/Policy", pathParameters, queryParameters, data)
		return response
	}
}
