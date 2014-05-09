package MediaBrowser.Model.Tasks;

/** 
 Class TaskExecutionInfo
*/
public class TaskResult
{
	/** 
	 Gets or sets the start time UTC.
	 
	 <value>The start time UTC.</value>
	*/
	private java.util.Date privateStartTimeUtc = new java.util.Date(0);
	public final java.util.Date getStartTimeUtc()
	{
		return privateStartTimeUtc;
	}
	public final void setStartTimeUtc(java.util.Date value)
	{
		privateStartTimeUtc = value;
	}

	/** 
	 Gets or sets the end time UTC.
	 
	 <value>The end time UTC.</value>
	*/
	private java.util.Date privateEndTimeUtc = new java.util.Date(0);
	public final java.util.Date getEndTimeUtc()
	{
		return privateEndTimeUtc;
	}
	public final void setEndTimeUtc(java.util.Date value)
	{
		privateEndTimeUtc = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private TaskCompletionStatus privateStatus = TaskCompletionStatus.values()[0];
	public final TaskCompletionStatus getStatus()
	{
		return privateStatus;
	}
	public final void setStatus(TaskCompletionStatus value)
	{
		privateStatus = value;
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
	 Gets or sets the id.
	 
	 <value>The id.</value>
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
	 Gets or sets the error message.
	 
	 <value>The error message.</value>
	*/
	private String privateErrorMessage;
	public final String getErrorMessage()
	{
		return privateErrorMessage;
	}
	public final void setErrorMessage(String value)
	{
		privateErrorMessage = value;
	}
}