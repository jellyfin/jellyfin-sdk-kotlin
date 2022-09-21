// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class LibrarySummary.
 */
@Serializable
public data class ItemCounts(
	/**
	 * The movie count.
	 */
	@SerialName("MovieCount")
	public val movieCount: Int,
	/**
	 * The series count.
	 */
	@SerialName("SeriesCount")
	public val seriesCount: Int,
	/**
	 * The episode count.
	 */
	@SerialName("EpisodeCount")
	public val episodeCount: Int,
	/**
	 * The artist count.
	 */
	@SerialName("ArtistCount")
	public val artistCount: Int,
	/**
	 * The program count.
	 */
	@SerialName("ProgramCount")
	public val programCount: Int,
	/**
	 * The trailer count.
	 */
	@SerialName("TrailerCount")
	public val trailerCount: Int,
	/**
	 * The song count.
	 */
	@SerialName("SongCount")
	public val songCount: Int,
	/**
	 * The album count.
	 */
	@SerialName("AlbumCount")
	public val albumCount: Int,
	/**
	 * The music video count.
	 */
	@SerialName("MusicVideoCount")
	public val musicVideoCount: Int,
	/**
	 * The box set count.
	 */
	@SerialName("BoxSetCount")
	public val boxSetCount: Int,
	/**
	 * The book count.
	 */
	@SerialName("BookCount")
	public val bookCount: Int,
	/**
	 * The item count.
	 */
	@SerialName("ItemCount")
	public val itemCount: Int,
)
