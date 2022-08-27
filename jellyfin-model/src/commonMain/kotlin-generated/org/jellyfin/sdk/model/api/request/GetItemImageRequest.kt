// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
@file:UseSerializers(UUIDSerializer::class)

package org.jellyfin.sdk.model.api.request

import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.ImageFormat
import org.jellyfin.sdk.model.api.ImageType
import org.jellyfin.sdk.model.serializer.UUIDSerializer

/**
 * Gets the item's image.
 */
@Serializable
public data class GetItemImageRequest(
	/**
	 * Item id.
	 */
	@SerialName("itemId")
	public val itemId: UUID,
	/**
	 * Image type.
	 */
	@SerialName("imageType")
	public val imageType: ImageType,
	/**
	 * The maximum image width to return.
	 */
	@SerialName("maxWidth")
	public val maxWidth: Int? = null,
	/**
	 * The maximum image height to return.
	 */
	@SerialName("maxHeight")
	public val maxHeight: Int? = null,
	/**
	 * The fixed image width to return.
	 */
	@SerialName("width")
	public val width: Int? = null,
	/**
	 * The fixed image height to return.
	 */
	@SerialName("height")
	public val height: Int? = null,
	/**
	 * Optional. Quality setting, from 0-100. Defaults to 90 and should suffice in most cases.
	 */
	@SerialName("quality")
	public val quality: Int? = null,
	/**
	 * Width of box to fill.
	 */
	@SerialName("fillWidth")
	public val fillWidth: Int? = null,
	/**
	 * Height of box to fill.
	 */
	@SerialName("fillHeight")
	public val fillHeight: Int? = null,
	/**
	 * Optional. Supply the cache tag from the item object to receive strong caching headers.
	 */
	@SerialName("tag")
	public val tag: String? = null,
	/**
	 * Optional. The MediaBrowser.Model.Drawing.ImageFormat of the returned image.
	 */
	@SerialName("format")
	public val format: ImageFormat? = null,
	/**
	 * Optional. Add a played indicator.
	 */
	@SerialName("addPlayedIndicator")
	public val addPlayedIndicator: Boolean? = null,
	/**
	 * Optional. Percent to render for the percent played overlay.
	 */
	@SerialName("percentPlayed")
	public val percentPlayed: Double? = null,
	/**
	 * Optional. Unplayed count overlay to render.
	 */
	@SerialName("unplayedCount")
	public val unplayedCount: Int? = null,
	/**
	 * Optional. Blur image.
	 */
	@SerialName("blur")
	public val blur: Int? = null,
	/**
	 * Optional. Apply a background color for transparent images.
	 */
	@SerialName("backgroundColor")
	public val backgroundColor: String? = null,
	/**
	 * Optional. Apply a foreground layer on top of the image.
	 */
	@SerialName("foregroundLayer")
	public val foregroundLayer: String? = null,
	/**
	 * Image index.
	 */
	@SerialName("imageIndex")
	public val imageIndex: Int? = null,
)
