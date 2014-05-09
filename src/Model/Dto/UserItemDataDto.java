package MediaBrowser.Model.Dto;

/** 
 Class UserItemDataDto
*/
public class UserItemDataDto implements INotifyPropertyChanged
{
	/** 
	 Gets or sets the rating.
	 
	 <value>The rating.</value>
	*/
	private Double privateRating = new Double();
	public final Double getRating()
	{
		return privateRating;
	}
	public final void setRating(Double value)
	{
		privateRating = value;
	}

	/** 
	 Gets or sets the playback position ticks.
	 
	 <value>The playback position ticks.</value>
	*/
	private long privatePlaybackPositionTicks;
	public final long getPlaybackPositionTicks()
	{
		return privatePlaybackPositionTicks;
	}
	public final void setPlaybackPositionTicks(long value)
	{
		privatePlaybackPositionTicks = value;
	}

	/** 
	 Gets or sets the play count.
	 
	 <value>The play count.</value>
	*/
	private int privatePlayCount;
	public final int getPlayCount()
	{
		return privatePlayCount;
	}
	public final void setPlayCount(int value)
	{
		privatePlayCount = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is favorite.
	 
	 <value><c>true</c> if this instance is favorite; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsFavorite;
	public final boolean getIsFavorite()
	{
		return privateIsFavorite;
	}
	public final void setIsFavorite(boolean value)
	{
		privateIsFavorite = value;
	}

	/** 
	 Gets or sets a value indicating whether this <see cref="UserItemDataDto" /> is likes.
	 
	 <value><c>null</c> if [likes] contains no value, <c>true</c> if [likes]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateLikes = new Boolean();
	public final Boolean getLikes()
	{
		return privateLikes;
	}
	public final void setLikes(Boolean value)
	{
		privateLikes = value;
	}

	/** 
	 Gets or sets the last played date.
	 
	 <value>The last played date.</value>
	*/
	private java.util.Date privateLastPlayedDate = new java.util.Date();
	public final java.util.Date getLastPlayedDate()
	{
		return privateLastPlayedDate;
	}
	public final void setLastPlayedDate(java.util.Date value)
	{
		privateLastPlayedDate = value;
	}

	/** 
	 Gets or sets a value indicating whether this <see cref="UserItemDataDto" /> is played.
	 
	 <value><c>true</c> if played; otherwise, <c>false</c>.</value>
	*/
	private boolean privatePlayed;
	public final boolean getPlayed()
	{
		return privatePlayed;
	}
	public final void setPlayed(boolean value)
	{
		privatePlayed = value;
	}

	/** 
	 Gets or sets the key.
	 
	 <value>The key.</value>
	*/
	private String privateKey;
	public final String getKey()
	{
		return privateKey;
	}
	public final void setKey(String value)
	{
		privateKey = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;
}