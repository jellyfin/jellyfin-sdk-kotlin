// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the server configuration.
 */
@Serializable
public data class ServerConfiguration(
	/**
	 * The number of days we should retain log files.
	 */
	@SerialName("LogFileRetentionDays")
	public val logFileRetentionDays: Int,
	/**
	 * A value indicating whether this instance is first run.
	 */
	@SerialName("IsStartupWizardCompleted")
	public val isStartupWizardCompleted: Boolean,
	/**
	 * The cache path.
	 */
	@SerialName("CachePath")
	public val cachePath: String? = null,
	/**
	 * The last known version that was ran using the configuration.
	 */
	@SerialName("PreviousVersion")
	public val previousVersion: String? = null,
	/**
	 * Gets or sets the stringified PreviousVersion to be stored/loaded,
	 * because System.Version itself isn't xml-serializable.
	 */
	@SerialName("PreviousVersionStr")
	public val previousVersionStr: String? = null,
	/**
	 * A value indicating whether to enable prometheus metrics exporting.
	 */
	@SerialName("EnableMetrics")
	public val enableMetrics: Boolean,
	@SerialName("EnableNormalizedItemByNameIds")
	public val enableNormalizedItemByNameIds: Boolean,
	/**
	 * A value indicating whether this instance is port authorized.
	 */
	@SerialName("IsPortAuthorized")
	public val isPortAuthorized: Boolean,
	/**
	 * A value indicating whether quick connect is available for use on this server.
	 */
	@SerialName("QuickConnectAvailable")
	public val quickConnectAvailable: Boolean,
	/**
	 * A value indicating whether [enable case-sensitive item ids].
	 */
	@SerialName("EnableCaseSensitiveItemIds")
	public val enableCaseSensitiveItemIds: Boolean,
	@SerialName("DisableLiveTvChannelUserDataName")
	public val disableLiveTvChannelUserDataName: Boolean,
	/**
	 * The metadata path.
	 */
	@SerialName("MetadataPath")
	public val metadataPath: String,
	/**
	 * The preferred metadata language.
	 */
	@SerialName("PreferredMetadataLanguage")
	public val preferredMetadataLanguage: String,
	/**
	 * The metadata country code.
	 */
	@SerialName("MetadataCountryCode")
	public val metadataCountryCode: String,
	/**
	 * Characters to be replaced with a ' ' in strings to create a sort name.
	 */
	@SerialName("SortReplaceCharacters")
	public val sortReplaceCharacters: List<String>,
	/**
	 * Characters to be removed from strings to create a sort name.
	 */
	@SerialName("SortRemoveCharacters")
	public val sortRemoveCharacters: List<String>,
	/**
	 * Words to be removed from strings to create a sort name.
	 */
	@SerialName("SortRemoveWords")
	public val sortRemoveWords: List<String>,
	/**
	 * The minimum percentage of an item that must be played in order for playstate to be updated.
	 */
	@SerialName("MinResumePct")
	public val minResumePct: Int,
	/**
	 * The maximum percentage of an item that can be played while still saving playstate. If this percentage is crossed playstate will be reset to the beginning and the item will be marked watched.
	 */
	@SerialName("MaxResumePct")
	public val maxResumePct: Int,
	/**
	 * The minimum duration that an item must have in order to be eligible for playstate updates..
	 */
	@SerialName("MinResumeDurationSeconds")
	public val minResumeDurationSeconds: Int,
	/**
	 * The minimum minutes of a book that must be played in order for playstate to be updated.
	 */
	@SerialName("MinAudiobookResume")
	public val minAudiobookResume: Int,
	/**
	 * The remaining minutes of a book that can be played while still saving playstate. If this percentage is crossed playstate will be reset to the beginning and the item will be marked watched.
	 */
	@SerialName("MaxAudiobookResume")
	public val maxAudiobookResume: Int,
	/**
	 * Gets or sets the threshold in minutes after a inactive session gets closed automatically.
	 * If set to 0 the check for inactive sessions gets disabled.
	 */
	@SerialName("InactiveSessionThreshold")
	public val inactiveSessionThreshold: Int,
	/**
	 * Gets or sets the delay in seconds that we will wait after a file system change to try and discover what has been added/removed
	 * Some delay is necessary with some items because their creation is not atomic.  It involves the creation of several
	 * different directories and files.
	 */
	@SerialName("LibraryMonitorDelay")
	public val libraryMonitorDelay: Int,
	/**
	 * The duration in seconds that we will wait after a library updated event before executing the library changed notification.
	 */
	@SerialName("LibraryUpdateDuration")
	public val libraryUpdateDuration: Int,
	/**
	 * The maximum amount of items to cache.
	 */
	@SerialName("CacheSize")
	public val cacheSize: Int,
	/**
	 * The image saving convention.
	 */
	@SerialName("ImageSavingConvention")
	public val imageSavingConvention: ImageSavingConvention,
	@SerialName("MetadataOptions")
	public val metadataOptions: List<MetadataOptions>,
	@SerialName("SkipDeserializationForBasicTypes")
	public val skipDeserializationForBasicTypes: Boolean,
	@SerialName("ServerName")
	public val serverName: String,
	@SerialName("UICulture")
	public val uiCulture: String,
	@SerialName("SaveMetadataHidden")
	public val saveMetadataHidden: Boolean,
	@SerialName("ContentTypes")
	public val contentTypes: List<NameValuePair>,
	@SerialName("RemoteClientBitrateLimit")
	public val remoteClientBitrateLimit: Int,
	@SerialName("EnableFolderView")
	public val enableFolderView: Boolean,
	@SerialName("EnableGroupingMoviesIntoCollections")
	public val enableGroupingMoviesIntoCollections: Boolean,
	@SerialName("EnableGroupingShowsIntoCollections")
	public val enableGroupingShowsIntoCollections: Boolean,
	@SerialName("DisplaySpecialsWithinSeasons")
	public val displaySpecialsWithinSeasons: Boolean,
	@SerialName("CodecsUsed")
	public val codecsUsed: List<String>,
	@SerialName("PluginRepositories")
	public val pluginRepositories: List<RepositoryInfo>,
	@SerialName("EnableExternalContentInSuggestions")
	public val enableExternalContentInSuggestions: Boolean,
	@SerialName("ImageExtractionTimeoutMs")
	public val imageExtractionTimeoutMs: Int,
	@SerialName("PathSubstitutions")
	public val pathSubstitutions: List<PathSubstitution>,
	/**
	 * A value indicating whether slow server responses should be logged as a warning.
	 */
	@SerialName("EnableSlowResponseWarning")
	public val enableSlowResponseWarning: Boolean,
	/**
	 * The threshold for the slow response time warning in ms.
	 */
	@SerialName("SlowResponseThresholdMs")
	public val slowResponseThresholdMs: Long,
	/**
	 * The cors hosts.
	 */
	@SerialName("CorsHosts")
	public val corsHosts: List<String>,
	/**
	 * The number of days we should retain activity logs.
	 */
	@SerialName("ActivityLogRetentionDays")
	public val activityLogRetentionDays: Int? = null,
	/**
	 * The how the library scan fans out.
	 */
	@SerialName("LibraryScanFanoutConcurrency")
	public val libraryScanFanoutConcurrency: Int,
	/**
	 * The how many metadata refreshes can run concurrently.
	 */
	@SerialName("LibraryMetadataRefreshConcurrency")
	public val libraryMetadataRefreshConcurrency: Int,
	/**
	 * A value indicating whether clients should be allowed to upload logs.
	 */
	@SerialName("AllowClientLogUpload")
	public val allowClientLogUpload: Boolean,
	/**
	 * The dummy chapter duration in seconds, use 0 (zero) or less to disable generation altogether.
	 */
	@SerialName("DummyChapterDuration")
	public val dummyChapterDuration: Int,
	/**
	 * The chapter image resolution.
	 */
	@SerialName("ChapterImageResolution")
	public val chapterImageResolution: ImageResolution,
	/**
	 * The limit for parallel image encoding.
	 */
	@SerialName("ParallelImageEncodingLimit")
	public val parallelImageEncodingLimit: Int,
	/**
	 * The list of cast receiver applications.
	 */
	@SerialName("CastReceiverApplications")
	public val castReceiverApplications: List<CastReceiverApplication>,
	/**
	 * The trickplay options.
	 */
	@SerialName("TrickplayOptions")
	public val trickplayOptions: TrickplayOptions,
	/**
	 * A value indicating whether old authorization methods are allowed.
	 */
	@SerialName("EnableLegacyAuthorization")
	public val enableLegacyAuthorization: Boolean,
)
