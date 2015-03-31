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
	private java.util.ArrayList<String> OfflineUserIds;
	public final java.util.ArrayList<String> getOfflineUserIds()
	{
		return OfflineUserIds;
	}
	public final void setOfflineUserIds(java.util.ArrayList<String> value)
	{
		OfflineUserIds = value;
	}
	private java.util.ArrayList<String> SyncJobItemIds;
	public final java.util.ArrayList<String> getSyncJobItemIds()
	{
		return SyncJobItemIds;
	}
	public final void setSyncJobItemIds(java.util.ArrayList<String> value)
	{
		SyncJobItemIds = value;
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
		setOfflineUserIds(new java.util.ArrayList<String>());
	}
}