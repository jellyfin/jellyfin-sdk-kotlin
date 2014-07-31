package MediaBrowser.Model.Sync;

public class SyncJobItem
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
	 Gets or sets the job identifier.
	 
	 <value>The job identifier.</value>
	*/
	private String privateJobId;
	public final String getJobId()
	{
		return privateJobId;
	}
	public final void setJobId(String value)
	{
		privateJobId = value;
	}

	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}

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
	 Gets or sets the output path.
	 
	 <value>The output path.</value>
	*/
	private String privateOutputPath;
	public final String getOutputPath()
	{
		return privateOutputPath;
	}
	public final void setOutputPath(String value)
	{
		privateOutputPath = value;
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
	 Gets or sets the current progress.
	 
	 <value>The current progress.</value>
	*/
	private Double privateCurrentProgress;
	public final Double getCurrentProgress()
	{
		return privateCurrentProgress;
	}
	public final void setCurrentProgress(Double value)
	{
		privateCurrentProgress = value;
	}
}