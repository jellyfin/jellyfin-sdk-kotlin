package org.jellyfin.sdk.model.constant

// https://github.com/jellyfin/jellyfin/blob/v10.8.4/MediaBrowser.Model/Entities/PersonType.cs
public object PersonType {
	/**
	 * A person whose profession is acting on the stage, in films, or on television.
	 */
	public const val Actor: String = "Actor"

	/**
	 * A person who supervises the actors and other staff in a film, play, or similar production.
	 */
	public const val Director: String = "Director"

	/**
	 * A person who writes music, especially as a professional occupation.
	 */
	public const val Composer: String = "Composer"

	/**
	 * A writer of a book, article, or document. Can also be used as a generic term for music writer if there is a lack
	 * of specificity.
	 */
	public const val Writer: String = "Writer"

	/**
	 * A well-known actor or other performer who appears in a work in which they do not have a regular role.
	 */
	public const val GuestStar: String = "GuestStar"

	/**
	 * A person responsible for the financial and managerial aspects of the making of a film or broadcast or for staging
	 * a play, opera, etc.
	 */
	public const val Producer: String = "Producer"

	/**
	 * A person who directs the performance of an orchestra or choir.
	 */
	public const val Conductor: String = "Conductor"

	/**
	 * A person who writes the words to a song or musical.
	 */
	public const val Lyricist: String = "Lyricist"

	/**
	 * A person who adapts a musical composition for performance.
	 */
	public const val Arranger: String = "Arranger"

	/**
	 * An audio engineer who performed a general engineering role.
	 */
	public const val Engineer: String = "Engineer"

	/**
	 * An engineer responsible for using a mixing console to mix a recorded track into a single piece of music suitable
	 * for release.
	 */
	public const val Mixer: String = "Mixer"

	/**
	 * A person who remixed a recording by taking one or more other tracks, substantially altering them and mixing them
	 * together with other material.
	 */
	public const val Remixer: String = "Remixer"
}
