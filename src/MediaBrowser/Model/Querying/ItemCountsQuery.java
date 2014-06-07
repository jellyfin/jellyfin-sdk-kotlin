package MediaBrowser.Model.Querying;

/** 
 Class ItemCountsQuery
*/
public class ItemCountsQuery
{
	/** 
	 Gets or sets the user id.
	 
	 <value>The user id.</value>
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
	 Gets or sets a value indicating whether this instance is favorite.
	 
	 <value><c>null</c> if [is favorite] contains no value, <c>true</c> if [is favorite]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsFavorite;
	public final Boolean getIsFavorite()
	{
		return privateIsFavorite;
	}
	public final void setIsFavorite(Boolean value)
	{
		privateIsFavorite = value;
	}
}