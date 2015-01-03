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

	public SyncDataResponse()
	{
		setItemIdsToRemove(new java.util.ArrayList<String>());
	}
}