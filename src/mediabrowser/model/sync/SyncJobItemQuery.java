package mediabrowser.model.sync;

public class SyncJobItemQuery
{
	/** 
	 Gets or sets the start index.
	 
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
	 Gets or sets the limit.
	 
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
	private SyncJobItemStatus Status;
	public final SyncJobItemStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(SyncJobItemStatus value)
	{
		Status = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is completed.
	 
	 <value><c>null</c> if [is completed] contains no value, <c>true</c> if [is completed]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsCompleted;
	public final Boolean getIsCompleted()
	{
		return IsCompleted;
	}
	public final void setIsCompleted(Boolean value)
	{
		IsCompleted = value;
	}
}