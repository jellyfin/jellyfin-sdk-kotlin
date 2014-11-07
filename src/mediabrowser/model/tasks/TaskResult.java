package mediabrowser.model.tasks;

/** 
 Class TaskExecutionInfo
*/
public class TaskResult
{
	/** 
	 Gets or sets the start time UTC.
	 
	 <value>The start time UTC.</value>
	*/
	private java.util.Date StartTimeUtc = new java.util.Date(0);
	public final java.util.Date getStartTimeUtc()
	{
		return StartTimeUtc;
	}
	public final void setStartTimeUtc(java.util.Date value)
	{
		StartTimeUtc = value;
	}

	/** 
	 Gets or sets the end time UTC.
	 
	 <value>The end time UTC.</value>
	*/
	private java.util.Date EndTimeUtc = new java.util.Date(0);
	public final java.util.Date getEndTimeUtc()
	{
		return EndTimeUtc;
	}
	public final void setEndTimeUtc(java.util.Date value)
	{
		EndTimeUtc = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private TaskCompletionStatus Status = TaskCompletionStatus.values()[0];
	public final TaskCompletionStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(TaskCompletionStatus value)
	{
		Status = value;
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
	 Gets or sets the id.
	 
	 <value>The id.</value>
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
	 Gets or sets the error message.
	 
	 <value>The error message.</value>
	*/
	private String ErrorMessage;
	public final String getErrorMessage()
	{
		return ErrorMessage;
	}
	public final void setErrorMessage(String value)
	{
		ErrorMessage = value;
	}

	/** 
	 Gets or sets the long error message.
	 
	 <value>The long error message.</value>
	*/
	private String LongErrorMessage;
	public final String getLongErrorMessage()
	{
		return LongErrorMessage;
	}
	public final void setLongErrorMessage(String value)
	{
		LongErrorMessage = value;
	}
}