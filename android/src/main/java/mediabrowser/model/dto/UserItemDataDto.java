package mediabrowser.model.dto;

/** 
 Class UserItemDataDto
*/
public class UserItemDataDto
{
	/** 
	 Gets or sets the rating.
	 
	 <value>The rating.</value>
	*/
	private Double Rating = null;
	public final Double getRating()
	{
		return Rating;
	}
	public final void setRating(Double value)
	{
		Rating = value;
	}

	/** 
	 Gets or sets the played percentage.
	 
	 <value>The played percentage.</value>
	*/
	private Double PlayedPercentage = null;
	public final Double getPlayedPercentage()
	{
		return PlayedPercentage;
	}
	public final void setPlayedPercentage(Double value)
	{
		PlayedPercentage = value;
	}

	/** 
	 Gets or sets the unplayed item count.
	 
	 <value>The unplayed item count.</value>
	*/
	private Integer UnplayedItemCount = null;
	public final Integer getUnplayedItemCount()
	{
		return UnplayedItemCount;
	}
	public final void setUnplayedItemCount(Integer value)
	{
		UnplayedItemCount = value;
	}

	/** 
	 Gets or sets the playback position ticks.
	 
	 <value>The playback position ticks.</value>
	*/
	private long PlaybackPositionTicks;
	public final long getPlaybackPositionTicks()
	{
		return PlaybackPositionTicks;
	}
	public final void setPlaybackPositionTicks(long value)
	{
		PlaybackPositionTicks = value;
	}

	/** 
	 Gets or sets the play count.
	 
	 <value>The play count.</value>
	*/
	private int PlayCount;
	public final int getPlayCount()
	{
		return PlayCount;
	}
	public final void setPlayCount(int value)
	{
		PlayCount = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is favorite.
	 
	 <value><c>true</c> if this instance is favorite; otherwise, <c>false</c>.</value>
	*/
	private boolean IsFavorite;
	public final boolean getIsFavorite()
	{
		return IsFavorite;
	}
	public final void setIsFavorite(boolean value)
	{
		IsFavorite = value;
	}

	/** 
	 Gets or sets a value indicating whether this <see cref="UserItemDataDto" /> is likes.
	 
	 <value><c>null</c> if [likes] contains no value, <c>true</c> if [likes]; otherwise, <c>false</c>.</value>
	*/
	private Boolean Likes = null;
	public final Boolean getLikes()
	{
		return Likes;
	}
	public final void setLikes(Boolean value)
	{
		Likes = value;
	}

	/** 
	 Gets or sets the last played date.
	 
	 <value>The last played date.</value>
	*/
	private java.util.Date LastPlayedDate = null;
	public final java.util.Date getLastPlayedDate()
	{
		return LastPlayedDate;
	}
	public final void setLastPlayedDate(java.util.Date value)
	{
		LastPlayedDate = value;
	}

	/** 
	 Gets or sets a value indicating whether this <see cref="UserItemDataDto" /> is played.
	 
	 <value><c>true</c> if played; otherwise, <c>false</c>.</value>
	*/
	private boolean Played;
	public final boolean getPlayed()
	{
		return Played;
	}
	public final void setPlayed(boolean value)
	{
		Played = value;
	}

	/** 
	 Gets or sets the key.
	 
	 <value>The key.</value>
	*/
	private String Key;
	public final String getKey()
	{
		return Key;
	}
	public final void setKey(String value)
	{
		Key = value;
	}

	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}
}