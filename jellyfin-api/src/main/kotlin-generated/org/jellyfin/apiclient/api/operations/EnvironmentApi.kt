// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.api.operations

import kotlin.Any
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import org.jellyfin.apiclient.api.client.KtorClient
import org.jellyfin.apiclient.api.client.Response
import org.jellyfin.apiclient.model.api.DefaultDirectoryBrowserInfoDto
import org.jellyfin.apiclient.model.api.FileSystemEntryInfo
import org.jellyfin.apiclient.model.api.ValidatePathDto

public class EnvironmentApi(
	private val api: KtorClient
) {
	/**
	 * Get Default directory browser.
	 */
	public suspend fun getDefaultDirectoryBrowser(): Response<DefaultDirectoryBrowserInfoDto> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<DefaultDirectoryBrowserInfoDto>("/Environment/DefaultDirectoryBrowser",
				pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets the contents of a given directory in the file system.
	 *
	 * @param path The path.
	 * @param includeFiles An optional filter to include or exclude files from the results. true/false.
	 * @param includeDirectories An optional filter to include or exclude folders from the results.
	 * true/false.
	 */
	public suspend fun getDirectoryContents(
		path: String,
		includeFiles: Boolean = false,
		includeDirectories: Boolean = false
	): Response<List<FileSystemEntryInfo>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["path"] = path
		queryParameters["includeFiles"] = includeFiles
		queryParameters["includeDirectories"] = includeDirectories
		val data = null
		val response = api.`get`<List<FileSystemEntryInfo>>("/Environment/DirectoryContents",
				pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Gets available drives from the server's file system.
	 */
	public suspend fun getDrives(): Response<List<FileSystemEntryInfo>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<FileSystemEntryInfo>>("/Environment/Drives", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets network paths.
	 */
	@Deprecated("This member is deprecated and may be removed in the future")
	public suspend fun getNetworkShares(): Response<List<FileSystemEntryInfo>> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val data = null
		val response = api.`get`<List<FileSystemEntryInfo>>("/Environment/NetworkShares", pathParameters,
				queryParameters, data)
		return response
	}

	/**
	 * Gets the parent path of a given path.
	 *
	 * @param path The path.
	 */
	public suspend fun getParentPath(path: String): Response<String> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = mutableMapOf<String, Any?>()
		queryParameters["path"] = path
		val data = null
		val response = api.`get`<String>("/Environment/ParentPath", pathParameters, queryParameters, data)
		return response
	}

	/**
	 * Validates path.
	 */
	public suspend fun validatePath(`data`: ValidatePathDto): Response<Unit> {
		val pathParameters = emptyMap<String, Any?>()
		val queryParameters = emptyMap<String, Any?>()
		val response = api.post<Unit>("/Environment/ValidatePath", pathParameters, queryParameters, data)
		return response
	}
}
