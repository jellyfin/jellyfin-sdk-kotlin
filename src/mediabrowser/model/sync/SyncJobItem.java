package mediabrowser.model.sync;

public class SyncJobItem
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
	 Gets or sets the job identifier.
	 
	 <value>The job identifier.</value>
	*/
	private String JobId;
	public final String getJobId()
	{
		return JobId;
	}
	public final void setJobId(String value)
	{
		JobId = value;
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

	/** 
	 Gets or sets the name of the item.
	 
	 <value>The name of the item.</value>
	*/
	private String ItemName;
	public final String getItemName()
	{
		return ItemName;
	}
	public final void setItemName(String value)
	{
		ItemName = value;
	}

	/** 
	 Gets or sets the media source identifier.
	 
	 <value>The media source identifier.</value>
	*/
	private String MediaSourceId;
	public final String getMediaSourceId()
	{
		return MediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		MediaSourceId = value;
	}

	/** 
	 Gets or sets the target identifier.
	 
	 <value>The target identifier.</value>
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
	 Gets or sets the output path.
	 
	 <value>The output path.</value>
	*/
	private String OutputPath;
	public final String getOutputPath()
	{
		return OutputPath;
	}
	public final void setOutputPath(String value)
	{
		OutputPath = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private SyncJobItemStatus Status = SyncJobItemStatus.values()[0];
	public final SyncJobItemStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(SyncJobItemStatus value)
	{
		Status = value;
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
	 Gets or sets the primary image item identifier.
	 
	 <value>The primary image item identifier.</value>
	*/
	private String PrimaryImageItemId;
	public final String getPrimaryImageItemId()
	{
		return PrimaryImageItemId;
	}
	public final void setPrimaryImageItemId(String value)
	{
		PrimaryImageItemId = value;
	}
	/** 
	 Gets or sets the primary image tag.
	 
	 <value>The primary image tag.</value>
	*/
	private String PrimaryImageTag;
	public final String getPrimaryImageTag()
	{
		return PrimaryImageTag;
	}
	public final void setPrimaryImageTag(String value)
	{
		PrimaryImageTag = value;
	}
}