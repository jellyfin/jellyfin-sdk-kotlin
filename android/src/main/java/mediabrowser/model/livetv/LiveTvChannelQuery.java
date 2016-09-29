package mediabrowser.model.livetv;

/** 
 Class ChannelQuery.
*/
public class LiveTvChannelQuery
{
	/** 
	 Gets or sets the type of the channel.
	 
	 <value>The type of the channel.</value>
	*/
	private ChannelType ChannelType;
	public final ChannelType getChannelType()
	{
		return ChannelType;
	}
	public final void setChannelType(ChannelType value)
	{
		ChannelType = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is favorite.
	 
	 <value><c>null</c> if [is favorite] contains no value, <c>true</c> if [is favorite]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsFavorite;
	public final Boolean getIsFavorite()
	{
		return IsFavorite;
	}
	public final void setIsFavorite(Boolean value)
	{
		IsFavorite = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is liked.
	 
	 <value><c>null</c> if [is liked] contains no value, <c>true</c> if [is liked]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsLiked;
	public final Boolean getIsLiked()
	{
		return IsLiked;
	}
	public final void setIsLiked(Boolean value)
	{
		IsLiked = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is disliked.
	 
	 <value><c>null</c> if [is disliked] contains no value, <c>true</c> if [is disliked]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsDisliked;
	public final Boolean getIsDisliked()
	{
		return IsDisliked;
	}
	public final void setIsDisliked(Boolean value)
	{
		IsDisliked = value;
	}

	/** 
	 Gets or sets a value indicating whether [enable favorite sorting].
	 
	 <value><c>true</c> if [enable favorite sorting]; otherwise, <c>false</c>.</value>
	*/
	private boolean EnableFavoriteSorting;
	public final boolean getEnableFavoriteSorting()
	{
		return EnableFavoriteSorting;
	}
	public final void setEnableFavoriteSorting(boolean value)
	{
		EnableFavoriteSorting = value;
	}

	/** 
	 Gets or sets the user identifier.
	 
	 <value>The user identifier.</value>
	*/
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	/** 
	 Skips over a given number of items within the results. Use for paging.
	 
	 <value>The start index.</value>
	*/
	private Integer StartIndex;
	public final Integer getStartIndex()
	{
		return StartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		StartIndex = value;
	}

	/** 
	 The maximum number of items to return
	 
	 <value>The limit.</value>
	*/
	private Integer Limit;
	public final Integer getLimit()
	{
		return Limit;
	}
	public final void setLimit(Integer value)
	{
		Limit = value;
	}

	/** 
	 Gets or sets a value indicating whether [add current program].
	 
	 <value><c>true</c> if [add current program]; otherwise, <c>false</c>.</value>
	*/
	private boolean AddCurrentProgram;
	public final boolean getAddCurrentProgram()
	{
		return AddCurrentProgram;
	}
	public final void setAddCurrentProgram(boolean value)
	{
		AddCurrentProgram = value;
	}
	private boolean EnableUserData;
	public final boolean getEnableUserData()
	{
		return EnableUserData;
	}
	public final void setEnableUserData(boolean value)
	{
		EnableUserData = value;
	}

	/** 
	 Used to specific whether to return news or not
	 
	 If set to null, all programs will be returned
	*/
	private Boolean IsNews;
	public final Boolean getIsNews()
	{
		return IsNews;
	}
	public final void setIsNews(Boolean value)
	{
		IsNews = value;
	}

	/** 
	 Used to specific whether to return movies or not
	 
	 If set to null, all programs will be returned
	*/
	private Boolean IsMovie;
	public final Boolean getIsMovie()
	{
		return IsMovie;
	}
	public final void setIsMovie(Boolean value)
	{
		IsMovie = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is kids.
	 
	 <value><c>null</c> if [is kids] contains no value, <c>true</c> if [is kids]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsKids;
	public final Boolean getIsKids()
	{
		return IsKids;
	}
	public final void setIsKids(Boolean value)
	{
		IsKids = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is sports.
	 
	 <value><c>null</c> if [is sports] contains no value, <c>true</c> if [is sports]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsSports;
	public final Boolean getIsSports()
	{
		return IsSports;
	}
	public final void setIsSports(Boolean value)
	{
		IsSports = value;
	}
	private Boolean IsSeries;
	public final Boolean getIsSeries()
	{
		return IsSeries;
	}
	public final void setIsSeries(Boolean value)
	{
		IsSeries = value;
	}

	public LiveTvChannelQuery()
	{
		setEnableUserData(true);
	}
}