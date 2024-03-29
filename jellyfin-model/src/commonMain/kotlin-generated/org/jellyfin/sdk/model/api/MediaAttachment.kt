// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Int
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class MediaAttachment.
 */
@Serializable
public data class MediaAttachment(
	/**
	 * The codec.
	 */
	@SerialName("Codec")
	public val codec: String? = null,
	/**
	 * The codec tag.
	 */
	@SerialName("CodecTag")
	public val codecTag: String? = null,
	/**
	 * The comment.
	 */
	@SerialName("Comment")
	public val comment: String? = null,
	/**
	 * The index.
	 */
	@SerialName("Index")
	public val index: Int,
	/**
	 * The filename.
	 */
	@SerialName("FileName")
	public val fileName: String? = null,
	/**
	 * The MIME type.
	 */
	@SerialName("MimeType")
	public val mimeType: String? = null,
	/**
	 * The delivery URL.
	 */
	@SerialName("DeliveryUrl")
	public val deliveryUrl: String? = null,
)
