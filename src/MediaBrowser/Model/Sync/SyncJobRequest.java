package MediaBrowser.Model.Sync;

public class SyncJobRequest
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

	public SyncJobRequest()
	{
		setDeviceIds(new java.util.ArrayList<String>());
	}
}