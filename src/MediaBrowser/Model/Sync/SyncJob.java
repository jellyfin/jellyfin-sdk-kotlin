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
	private String privateDeviceId;
	public final String getDeviceId()
	{
		return privateDeviceId;
	}
	public final void setDeviceId(String value)
	{
		privateDeviceId = value;
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
	/** 
	 Gets or sets the synchronize rule identifier.
	 
	 <value>The synchronize rule identifier.</value>
	*/
	private String privateSyncScheduleId;
	public final String getSyncScheduleId()
	{
		return privateSyncScheduleId;
	}
	public final void setSyncScheduleId(String value)
	{
		privateSyncScheduleId = value;
	}
	/** 
	 Gets or sets the transcoded path.
	 
	 <value>The transcoded path.</value>
	*/
	private String privateTranscodedPath;
	public final String getTranscodedPath()
	{
		return privateTranscodedPath;
	}
	public final void setTranscodedPath(String value)
	{
		privateTranscodedPath = value;
	}
}