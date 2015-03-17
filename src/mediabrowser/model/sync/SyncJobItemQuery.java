package mediabrowser.model.sync;

public class SyncJobItemQuery
{
	/** 
	 Gets or sets the start index.
	 
	 <value>The start index.</value>
	*/
	private Integer StartIndex = null;
	public final Integer getStartIndex()
	{
		return StartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		StartIndex = value;
	}
	/** 
	 Gets or sets the limit.
	 
	 <value>The limit.</value>
	*/
	private Integer Limit = null;
	public final Integer getLimit()
	{
		return Limit;
	}
	public final void setLimit(Integer value)
	{
		Limit = value;
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
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private SyncJobStatus[] Statuses;
	public final SyncJobStatus[] getStatuses()
	{
		return Statuses;
	}
	public final void setStatuses(SyncJobStatus[] value)
	{
		Statuses = value;
	}
	/** 
	 Gets or sets a value indicating whether [add metadata].
	 
	 <value><c>true</c> if [add metadata]; otherwise, <c>false</c>.</value>
	*/
	private boolean AddMetadata;
	public final boolean getAddMetadata()
	{
		return AddMetadata;
	}
	public final void setAddMetadata(boolean value)
	{
		AddMetadata = value;
	}

	public SyncJobItemQuery()
	{
		setStatuses(new SyncJobStatus[]{});
	}
}