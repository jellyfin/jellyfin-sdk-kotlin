package mediabrowser.model.sync;

public class SyncDataRequest
{
	private java.util.ArrayList<String> LocalItemIds;
	public final java.util.ArrayList<String> getLocalItemIds()
	{
		return LocalItemIds;
	}
	public final void setLocalItemIds(java.util.ArrayList<String> value)
	{
		LocalItemIds = value;
	}

	private String TargetId;
	public final String getTargetId()
	{
		return TargetId;
	}
	public final void setTargetId(String value)
	{
		TargetId = value;
	}

	public SyncDataRequest()
	{
		setLocalItemIds(new java.util.ArrayList<String>());
	}
}