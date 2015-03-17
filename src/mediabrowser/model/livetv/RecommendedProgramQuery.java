package mediabrowser.model.livetv;

public class RecommendedProgramQuery
{
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
	 Gets or sets a value indicating whether this instance is airing.
	 
	 <value><c>true</c> if this instance is airing; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsAiring;
	public final Boolean getIsAiring()
	{
		return IsAiring;
	}
	public final void setIsAiring(Boolean value)
	{
		IsAiring = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has aired.
	 
	 <value><c>null</c> if [has aired] contains no value, <c>true</c> if [has aired]; otherwise, <c>false</c>.</value>
	*/
	private Boolean HasAired;
	public final Boolean getHasAired()
	{
		return HasAired;
	}
	public final void setHasAired(Boolean value)
	{
		HasAired = value;
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
	 Gets or sets a value indicating whether this instance is movie.
	 
	 <value><c>null</c> if [is movie] contains no value, <c>true</c> if [is movie]; otherwise, <c>false</c>.</value>
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
}