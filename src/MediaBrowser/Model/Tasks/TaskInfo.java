package MediaBrowser.Model.Tasks;

/** 
 Class TaskInfo
*/
public class TaskInfo
{
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
	 Gets or sets the state of the task.
	 
	 <value>The state of the task.</value>
	*/
	private TaskState privateState = TaskState.values()[0];
	public final TaskState getState()
	{
		return privateState;
	}
	public final void setState(TaskState value)
	{
		privateState = value;
	}

	/** 
	 Gets or sets the progress.
	 
	 <value>The progress.</value>
	*/
	private Double privateCurrentProgressPercentage = new Double();
	public final Double getCurrentProgressPercentage()
	{
		return privateCurrentProgressPercentage;
	}
	public final void setCurrentProgressPercentage(Double value)
	{
		privateCurrentProgressPercentage = value;
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
	 Gets or sets the last execution result.
	 
	 <value>The last execution result.</value>
	*/
	private TaskResult privateLastExecutionResult;
	public final TaskResult getLastExecutionResult()
	{
		return privateLastExecutionResult;
	}
	public final void setLastExecutionResult(TaskResult value)
	{
		privateLastExecutionResult = value;
	}

	/** 
	 Gets or sets the triggers.
	 
	 <value>The triggers.</value>
	*/
	private java.util.ArrayList<TaskTriggerInfo> privateTriggers;
	public final java.util.ArrayList<TaskTriggerInfo> getTriggers()
	{
		return privateTriggers;
	}
	public final void setTriggers(java.util.ArrayList<TaskTriggerInfo> value)
	{
		privateTriggers = value;
	}

	/** 
	 Gets or sets the description.
	 
	 <value>The description.</value>
	*/
	private String privateDescription;
	public final String getDescription()
	{
		return privateDescription;
	}
	public final void setDescription(String value)
	{
		privateDescription = value;
	}

	/** 
	 Gets or sets the category.
	 
	 <value>The category.</value>
	*/
	private String privateCategory;
	public final String getCategory()
	{
		return privateCategory;
	}
	public final void setCategory(String value)
	{
		privateCategory = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is hidden.
	 
	 <value><c>true</c> if this instance is hidden; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsHidden;
	public final boolean getIsHidden()
	{
		return privateIsHidden;
	}
	public final void setIsHidden(boolean value)
	{
		privateIsHidden = value;
	}

	/** 
	 Gets or sets the key.
	 
	 <value>The key.</value>
	*/
	private String privateKey;
	public final String getKey()
	{
		return privateKey;
	}
	public final void setKey(String value)
	{
		privateKey = value;
	}

	/** 
	 Initializes a new instance of the <see cref="TaskInfo"/> class.
	*/
	public TaskInfo()
	{
		setTriggers(new java.util.ArrayList<TaskTriggerInfo>());
	}
}