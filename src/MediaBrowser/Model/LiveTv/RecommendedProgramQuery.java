package MediaBrowser.Model.LiveTv;

public class RecommendedProgramQuery
{
	/** 
	 Gets or sets the user identifier.
	 
	 <value>The user identifier.</value>
	*/
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is airing.
	 
	 <value><c>true</c> if this instance is airing; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsAiring;
	public final Boolean getIsAiring()
	{
		return privateIsAiring;
	}
	public final void setIsAiring(Boolean value)
	{
		privateIsAiring = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has aired.
	 
	 <value><c>null</c> if [has aired] contains no value, <c>true</c> if [has aired]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateHasAired;
	public final Boolean getHasAired()
	{
		return privateHasAired;
	}
	public final void setHasAired(Boolean value)
	{
		privateHasAired = value;
	}

	/** 
	 The maximum number of items to return
	 
	 <value>The limit.</value>
	*/
	private Integer privateLimit;
	public final Integer getLimit()
	{
		return privateLimit;
	}
	public final void setLimit(Integer value)
	{
		privateLimit = value;
	}
}