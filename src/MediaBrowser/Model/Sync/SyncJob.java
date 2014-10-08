package MediaBrowser.Model.Sync;

public class SyncJob
{
	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	/** 
	 Gets or sets the device identifier.
	 
	 <value>The device identifier.</value>
	*/
	private String TargetId;
	public final String getTargetId()
	{
		return TargetId;
	}
	public final void setTargetId(String value)
	{
		TargetId = value;
	}
	/** 
	 Gets or sets the quality.
	 
	 <value>The quality.</value>
	*/
	private SyncQuality Quality = SyncQuality.values()[0];
	public final SyncQuality getQuality()
	{
		return Quality;
	}
	public final void setQuality(SyncQuality value)
	{
		Quality = value;
	}
	/** 
	 Gets or sets the current progress.
	 
	 <value>The current progress.</value>
	*/
	private Double Progress = null;
	public final Double getProgress()
	{
		return Progress;
	}
	public final void setProgress(Double value)
	{
		Progress = value;
	}
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private SyncJobStatus Status = SyncJobStatus.values()[0];
	public final SyncJobStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(SyncJobStatus value)
	{
		Status = value;
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
	 Gets or sets a value indicating whether [unwatched only].
	 
	 <value><c>true</c> if [unwatched only]; otherwise, <c>false</c>.</value>
	*/
	private boolean UnwatchedOnly;
	public final boolean getUnwatchedOnly()
	{
		return UnwatchedOnly;
	}
	public final void setUnwatchedOnly(boolean value)
	{
		UnwatchedOnly = value;
	}
	/** 
	 Gets or sets the limit.
	 
	 <value>The limit.</value>
	*/
	private Long Limit = null;
	public final Long getLimit()
	{
		return Limit;
	}
	public final void setLimit(Long value)
	{
		Limit = value;
	}
	/** 
	 Gets or sets the type of the limit.
	 
	 <value>The type of the limit.</value>
	*/
	private SyncLimitType LimitType = null;
	public final SyncLimitType getLimitType()
	{
		return LimitType;
	}
	public final void setLimitType(SyncLimitType value)
	{
		LimitType = value;
	}
	/** 
	 Gets or sets the requested item ids.
	 
	 <value>The requested item ids.</value>
	*/
	private java.util.ArrayList<String> RequestedItemIds;
	public final java.util.ArrayList<String> getRequestedItemIds()
	{
		return RequestedItemIds;
	}
	public final void setRequestedItemIds(java.util.ArrayList<String> value)
	{
		RequestedItemIds = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is dynamic.
	 
	 <value><c>true</c> if this instance is dynamic; otherwise, <c>false</c>.</value>
	*/
	private boolean IsDynamic;
	public final boolean getIsDynamic()
	{
		return IsDynamic;
	}
	public final void setIsDynamic(boolean value)
	{
		IsDynamic = value;
	}
	/** 
	 Gets or sets the date created.
	 
	 <value>The date created.</value>
	*/
	private java.util.Date DateCreated = new java.util.Date(0);
	public final java.util.Date getDateCreated()
	{
		return DateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		DateCreated = value;
	}
	/** 
	 Gets or sets the date last modified.
	 
	 <value>The date last modified.</value>
	*/
	private java.util.Date DateLastModified = new java.util.Date(0);
	public final java.util.Date getDateLastModified()
	{
		return DateLastModified;
	}
	public final void setDateLastModified(java.util.Date value)
	{
		DateLastModified = value;
	}
	/** 
	 Gets or sets the item count.
	 
	 <value>The item count.</value>
	*/
	private int ItemCount;
	public final int getItemCount()
	{
		return ItemCount;
	}
	public final void setItemCount(int value)
	{
		ItemCount = value;
	}

	private String ParentName;
	public final String getParentName()
	{
		return ParentName;
	}
	public final void setParentName(String value)
	{
		ParentName = value;
	}
	private String PrimaryImageItemId;
	public final String getPrimaryImageItemId()
	{
		return PrimaryImageItemId;
	}
	public final void setPrimaryImageItemId(String value)
	{
		PrimaryImageItemId = value;
	}
	private String PrimaryImageTag;
	public final String getPrimaryImageTag()
	{
		return PrimaryImageTag;
	}
	public final void setPrimaryImageTag(String value)
	{
		PrimaryImageTag = value;
	}
	private Double PrimaryImageAspectRatio = null;
	public final Double getPrimaryImageAspectRatio()
	{
		return PrimaryImageAspectRatio;
	}
	public final void setPrimaryImageAspectRatio(Double value)
	{
		PrimaryImageAspectRatio = value;
	}

	public SyncJob()
	{
		setRequestedItemIds(new java.util.ArrayList<String>());
	}
}