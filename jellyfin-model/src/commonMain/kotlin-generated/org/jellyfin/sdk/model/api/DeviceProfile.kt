// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Defines the MediaBrowser.Model.Dlna.DeviceProfile.
 */
@Serializable
public data class DeviceProfile(
	/**
	 * Gets or sets the Name.
	 */
	@SerialName("Name")
	public val name: String? = null,
	/**
	 * Gets or sets the Id.
	 */
	@SerialName("Id")
	public val id: String? = null,
	/**
	 * Gets or sets the Identification.
	 */
	@SerialName("Identification")
	public val identification: DeviceIdentification? = null,
	/**
	 * Gets or sets the FriendlyName.
	 */
	@SerialName("FriendlyName")
	public val friendlyName: String? = null,
	/**
	 * Gets or sets the Manufacturer.
	 */
	@SerialName("Manufacturer")
	public val manufacturer: String? = null,
	/**
	 * Gets or sets the ManufacturerUrl.
	 */
	@SerialName("ManufacturerUrl")
	public val manufacturerUrl: String? = null,
	/**
	 * Gets or sets the ModelName.
	 */
	@SerialName("ModelName")
	public val modelName: String? = null,
	/**
	 * Gets or sets the ModelDescription.
	 */
	@SerialName("ModelDescription")
	public val modelDescription: String? = null,
	/**
	 * Gets or sets the ModelNumber.
	 */
	@SerialName("ModelNumber")
	public val modelNumber: String? = null,
	/**
	 * Gets or sets the ModelUrl.
	 */
	@SerialName("ModelUrl")
	public val modelUrl: String? = null,
	/**
	 * Gets or sets the SerialNumber.
	 */
	@SerialName("SerialNumber")
	public val serialNumber: String? = null,
	/**
	 * Gets or sets a value indicating whether EnableAlbumArtInDidl.
	 */
	@SerialName("EnableAlbumArtInDidl")
	public val enableAlbumArtInDidl: Boolean,
	/**
	 * Gets or sets a value indicating whether EnableSingleAlbumArtLimit.
	 */
	@SerialName("EnableSingleAlbumArtLimit")
	public val enableSingleAlbumArtLimit: Boolean,
	/**
	 * Gets or sets a value indicating whether EnableSingleSubtitleLimit.
	 */
	@SerialName("EnableSingleSubtitleLimit")
	public val enableSingleSubtitleLimit: Boolean,
	/**
	 * Gets or sets the SupportedMediaTypes.
	 */
	@SerialName("SupportedMediaTypes")
	public val supportedMediaTypes: String? = null,
	/**
	 * Gets or sets the UserId.
	 */
	@SerialName("UserId")
	public val userId: String? = null,
	/**
	 * Gets or sets the AlbumArtPn.
	 */
	@SerialName("AlbumArtPn")
	public val albumArtPn: String? = null,
	/**
	 * Gets or sets the MaxAlbumArtWidth.
	 */
	@SerialName("MaxAlbumArtWidth")
	public val maxAlbumArtWidth: Int,
	/**
	 * Gets or sets the MaxAlbumArtHeight.
	 */
	@SerialName("MaxAlbumArtHeight")
	public val maxAlbumArtHeight: Int,
	/**
	 * Gets or sets the MaxIconWidth.
	 */
	@SerialName("MaxIconWidth")
	public val maxIconWidth: Int? = null,
	/**
	 * Gets or sets the MaxIconHeight.
	 */
	@SerialName("MaxIconHeight")
	public val maxIconHeight: Int? = null,
	/**
	 * Gets or sets the MaxStreamingBitrate.
	 */
	@SerialName("MaxStreamingBitrate")
	public val maxStreamingBitrate: Int? = null,
	/**
	 * Gets or sets the MaxStaticBitrate.
	 */
	@SerialName("MaxStaticBitrate")
	public val maxStaticBitrate: Int? = null,
	/**
	 * Gets or sets the MusicStreamingTranscodingBitrate.
	 */
	@SerialName("MusicStreamingTranscodingBitrate")
	public val musicStreamingTranscodingBitrate: Int? = null,
	/**
	 * Gets or sets the MaxStaticMusicBitrate.
	 */
	@SerialName("MaxStaticMusicBitrate")
	public val maxStaticMusicBitrate: Int? = null,
	/**
	 * Gets or sets the content of the aggregationFlags element in the urn:schemas-sonycom:av
	 * namespace.
	 */
	@SerialName("SonyAggregationFlags")
	public val sonyAggregationFlags: String? = null,
	/**
	 * Gets or sets the ProtocolInfo.
	 */
	@SerialName("ProtocolInfo")
	public val protocolInfo: String? = null,
	/**
	 * Gets or sets the TimelineOffsetSeconds.
	 */
	@SerialName("TimelineOffsetSeconds")
	public val timelineOffsetSeconds: Int,
	/**
	 * Gets or sets a value indicating whether RequiresPlainVideoItems.
	 */
	@SerialName("RequiresPlainVideoItems")
	public val requiresPlainVideoItems: Boolean,
	/**
	 * Gets or sets a value indicating whether RequiresPlainFolders.
	 */
	@SerialName("RequiresPlainFolders")
	public val requiresPlainFolders: Boolean,
	/**
	 * Gets or sets a value indicating whether EnableMSMediaReceiverRegistrar.
	 */
	@SerialName("EnableMSMediaReceiverRegistrar")
	public val enableMsMediaReceiverRegistrar: Boolean,
	/**
	 * Gets or sets a value indicating whether IgnoreTranscodeByteRangeRequests.
	 */
	@SerialName("IgnoreTranscodeByteRangeRequests")
	public val ignoreTranscodeByteRangeRequests: Boolean,
	/**
	 * Gets or sets the XmlRootAttributes.
	 */
	@SerialName("XmlRootAttributes")
	public val xmlRootAttributes: List<XmlAttribute>? = null,
	/**
	 * Gets or sets the direct play profiles.
	 */
	@SerialName("DirectPlayProfiles")
	public val directPlayProfiles: List<DirectPlayProfile>? = null,
	/**
	 * Gets or sets the transcoding profiles.
	 */
	@SerialName("TranscodingProfiles")
	public val transcodingProfiles: List<TranscodingProfile>? = null,
	/**
	 * Gets or sets the ContainerProfiles.
	 */
	@SerialName("ContainerProfiles")
	public val containerProfiles: List<ContainerProfile>? = null,
	/**
	 * Gets or sets the CodecProfiles.
	 */
	@SerialName("CodecProfiles")
	public val codecProfiles: List<CodecProfile>? = null,
	/**
	 * Gets or sets the ResponseProfiles.
	 */
	@SerialName("ResponseProfiles")
	public val responseProfiles: List<ResponseProfile>? = null,
	/**
	 * Gets or sets the SubtitleProfiles.
	 */
	@SerialName("SubtitleProfiles")
	public val subtitleProfiles: List<SubtitleProfile>? = null
)
