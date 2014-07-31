package MediaBrowser.Model.Sync;

public class SyncJobRequest
{
	/** 
	 Gets or sets the target identifier.
	 
	 <value>The target identifier.</value>
	*/
	private String privateTargetId;
	public final String getTargetId()
	{
		return privateTargetId;
	}
	public final void setTargetId(String value)
	{
		privateTargetId = value;
	}
	/** 
	 Gets or sets the item ids.
	 
	 <value>The item ids.</value>
	*/
	private java.util.ArrayList<String> privateItemIds;
	public final java.util.ArrayList<String> getItemIds()
	{
		return privateItemIds;
	}
	public final void setItemIds(java.util.ArrayList<String> value)
	{
		privateItemIds = value;
	}
	/** 
	 Gets or sets the quality.
	 
	 <value>The quality.</value>
	*/
	private SyncQuality privateQuality = SyncQuality.values()[0];
	public final SyncQuality getQuality()
	{
		return privateQuality;
	}
	public final void setQuality(SyncQuality value)
	{
		privateQuality = value;
	}
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}
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
	 Gets or sets a value indicating whether [unwatched only].
	 
	 <value><c>true</c> if [unwatched only]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateUnwatchedOnly;
	public final boolean getUnwatchedOnly()
	{
		return privateUnwatchedOnly;
	}
	public final void setUnwatchedOnly(boolean value)
	{
		privateUnwatchedOnly = value;
	}
	/** 
	 Gets or sets the limit.
	 
	 <value>The limit.</value>
	*/
	private Long privateLimit = null;
	public final Long getLimit()
	{
		return privateLimit;
	}
	public final void setLimit(Long value)
	{
		privateLimit = value;
	}
	/** 
	 Gets or sets the type of the limit.
	 
	 <value>The type of the limit.</value>
	*/
	private SyncLimitType privateLimitType = null;
	public final SyncLimitType getLimitType()
	{
		return privateLimitType;
	}
	public final void setLimitType(SyncLimitType value)
	{
		privateLimitType = value;
	}

	public SyncJobRequest()
	{
		setItemIds(new java.util.ArrayList<String>());
	}
}