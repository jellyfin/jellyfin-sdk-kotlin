package MediaBrowser.Model.Sync;

public class SyncJob
{
	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}
	/** 
	 Gets or sets the device identifier.
	 
	 <value>The device identifier.</value>
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
	 Gets or sets the current progress.
	 
	 <value>The current progress.</value>
	*/
	private Double privateProgress = null;
	public final Double getProgress()
	{
		return privateProgress;
	}
	public final void setProgress(Double value)
	{
		privateProgress = value;
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
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private SyncJobStatus privateStatus = SyncJobStatus.values()[0];
	public final SyncJobStatus getStatus()
	{
		return privateStatus;
	}
	public final void setStatus(SyncJobStatus value)
	{
		privateStatus = value;
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
	/** 
	 Gets or sets the requested item ids.
	 
	 <value>The requested item ids.</value>
	*/
	private java.util.ArrayList<String> privateRequestedItemIds;
	public final java.util.ArrayList<String> getRequestedItemIds()
	{
		return privateRequestedItemIds;
	}
	public final void setRequestedItemIds(java.util.ArrayList<String> value)
	{
		privateRequestedItemIds = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is dynamic.
	 
	 <value><c>true</c> if this instance is dynamic; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsDynamic;
	public final boolean getIsDynamic()
	{
		return privateIsDynamic;
	}
	public final void setIsDynamic(boolean value)
	{
		privateIsDynamic = value;
	}
	/** 
	 Gets or sets the date created.
	 
	 <value>The date created.</value>
	*/
	private java.util.Date privateDateCreated = new java.util.Date(0);
	public final java.util.Date getDateCreated()
	{
		return privateDateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		privateDateCreated = value;
	}
	/** 
	 Gets or sets the date last modified.
	 
	 <value>The date last modified.</value>
	*/
	private java.util.Date privateDateLastModified = new java.util.Date(0);
	public final java.util.Date getDateLastModified()
	{
		return privateDateLastModified;
	}
	public final void setDateLastModified(java.util.Date value)
	{
		privateDateLastModified = value;
	}
	/** 
	 Gets or sets the item count.
	 
	 <value>The item count.</value>
	*/
	private int privateItemCount;
	public final int getItemCount()
	{
		return privateItemCount;
	}
	public final void setItemCount(int value)
	{
		privateItemCount = value;
	}

	private String privateParentName;
	public final String getParentName()
	{
		return privateParentName;
	}
	public final void setParentName(String value)
	{
		privateParentName = value;
	}
	private String privatePrimaryImageItemId;
	public final String getPrimaryImageItemId()
	{
		return privatePrimaryImageItemId;
	}
	public final void setPrimaryImageItemId(String value)
	{
		privatePrimaryImageItemId = value;
	}
	private String privatePrimaryImageTag;
	public final String getPrimaryImageTag()
	{
		return privatePrimaryImageTag;
	}
	public final void setPrimaryImageTag(String value)
	{
		privatePrimaryImageTag = value;
	}
	private Double privatePrimaryImageAspectRatio = null;
	public final Double getPrimaryImageAspectRatio()
	{
		return privatePrimaryImageAspectRatio;
	}
	public final void setPrimaryImageAspectRatio(Double value)
	{
		privatePrimaryImageAspectRatio = value;
	}

	public SyncJob()
	{
		setRequestedItemIds(new java.util.ArrayList<String>());
	}
}