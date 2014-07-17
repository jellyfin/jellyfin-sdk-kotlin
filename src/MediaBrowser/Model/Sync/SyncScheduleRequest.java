package MediaBrowser.Model.Sync;

public class SyncScheduleRequest
{
	/** 
	 Gets or sets the device identifier.
	 
	 <value>The device identifier.</value>
	*/
	private java.util.ArrayList<String> privateDeviceIds;
	public final java.util.ArrayList<String> getDeviceIds()
	{
		return privateDeviceIds;
	}
	public final void setDeviceIds(java.util.ArrayList<String> value)
	{
		privateDeviceIds = value;
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

	public SyncScheduleRequest()
	{
		setDeviceIds(new java.util.ArrayList<String>());
	}
}