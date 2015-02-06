package mediabrowser.model.sync;

public class SyncDataResponse
{
	private java.util.ArrayList<String> ItemIdsToRemove;
	public final java.util.ArrayList<String> getItemIdsToRemove()
	{
		return ItemIdsToRemove;
	}
	public final void setItemIdsToRemove(java.util.ArrayList<String> value)
	{
		ItemIdsToRemove = value;
	}
	private java.util.HashMap<String, java.util.ArrayList<String>> ItemUserAccess;
	public final java.util.HashMap<String, java.util.ArrayList<String>> getItemUserAccess()
	{
		return ItemUserAccess;
	}
	public final void setItemUserAccess(java.util.HashMap<String, java.util.ArrayList<String>> value)
	{
		ItemUserAccess = value;
	}

	public SyncDataResponse()
	{
		setItemIdsToRemove(new java.util.ArrayList<String>());
		setItemUserAccess(new java.util.HashMap<String, java.util.ArrayList<String>>());
	}
}