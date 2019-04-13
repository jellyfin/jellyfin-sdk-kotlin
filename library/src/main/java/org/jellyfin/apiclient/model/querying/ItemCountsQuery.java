package org.jellyfin.apiclient.model.querying;

/** 
 Class ItemCountsQuery
*/
public class ItemCountsQuery
{
	/** 
	 Gets or sets the user id.
	 
	 <value>The user id.</value>
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
}