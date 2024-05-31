package org.jellyfin.sdk.model

/**
 * Information about a file used to for uploading.
 */
public class FileInfo(
	/**
	 * The binary content of the file.
	 */
	public val content: ByteArray,

	/**
	 * The media type of the file. Defaults to "
	 */
	public val mediaType: String = "application/octet-stream",
)

/**
 * Create a [FileInfo] from this byte array.
 */
public fun ByteArray.toFileInfo(
	mediaType: String = "application/octet-stream",
): FileInfo = FileInfo(this, mediaType)
