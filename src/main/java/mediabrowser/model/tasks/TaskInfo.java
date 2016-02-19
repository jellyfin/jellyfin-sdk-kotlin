package mediabrowser.model.tasks;

/** 
 Class TaskInfo
*/
public class TaskInfo
{
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
	 Gets or sets the state of the task.
	 
	 <value>The state of the task.</value>
	*/
	private TaskState State = TaskState.values()[0];
	public final TaskState getState()
	{
		return State;
	}
	public final void setState(TaskState value)
	{
		State = value;
	}

	/** 
	 Gets or sets the progress.
	 
	 <value>The progress.</value>
	*/
	private Double CurrentProgressPercentage = null;
	public final Double getCurrentProgressPercentage()
	{
		return CurrentProgressPercentage;
	}
	public final void setCurrentProgressPercentage(Double value)
	{
		CurrentProgressPercentage = value;
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
	 Gets or sets the last execution result.
	 
	 <value>The last execution result.</value>
	*/
	private TaskResult LastExecutionResult;
	public final TaskResult getLastExecutionResult()
	{
		return LastExecutionResult;
	}
	public final void setLastExecutionResult(TaskResult value)
	{
		LastExecutionResult = value;
	}

	/** 
	 Gets or sets the triggers.
	 
	 <value>The triggers.</value>
	*/
	private java.util.ArrayList<TaskTriggerInfo> Triggers;
	public final java.util.ArrayList<TaskTriggerInfo> getTriggers()
	{
		return Triggers;
	}
	public final void setTriggers(java.util.ArrayList<TaskTriggerInfo> value)
	{
		Triggers = value;
	}

	/** 
	 Gets or sets the description.
	 
	 <value>The description.</value>
	*/
	private String Description;
	public final String getDescription()
	{
		return Description;
	}
	public final void setDescription(String value)
	{
		Description = value;
	}

	/** 
	 Gets or sets the category.
	 
	 <value>The category.</value>
	*/
	private String Category;
	public final String getCategory()
	{
		return Category;
	}
	public final void setCategory(String value)
	{
		Category = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is hidden.
	 
	 <value><c>true</c> if this instance is hidden; otherwise, <c>false</c>.</value>
	*/
	private boolean IsHidden;
	public final boolean getIsHidden()
	{
		return IsHidden;
	}
	public final void setIsHidden(boolean value)
	{
		IsHidden = value;
	}

	/** 
	 Gets or sets the key.
	 
	 <value>The key.</value>
	*/
	private String Key;
	public final String getKey()
	{
		return Key;
	}
	public final void setKey(String value)
	{
		Key = value;
	}

	/** 
	 Initializes a new instance of the <see cref="TaskInfo"/> class.
	*/
	public TaskInfo()
	{
		setTriggers(new java.util.ArrayList<TaskTriggerInfo>());
	}
}