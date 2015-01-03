package mediabrowser.model.sync;

import mediabrowser.model.dto.*;

public class SyncedItem
{
	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
	*/
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}
	/** 
	 Gets or sets the synchronize job identifier.
	 
	 <value>The synchronize job identifier.</value>
	*/
	private String SyncJobId;
	public final String getSyncJobId()
	{
		return SyncJobId;
	}
	public final void setSyncJobId(String value)
	{
		SyncJobId = value;
	}
	/** 
	 Gets or sets the synchronize job item identifier.
	 
	 <value>The synchronize job item identifier.</value>
	*/
	private String SyncJobItemId;
	public final String getSyncJobItemId()
	{
		return SyncJobItemId;
	}
	public final void setSyncJobItemId(String value)
	{
		SyncJobItemId = value;
	}
	/** 
	 Gets or sets the name of the original file.
	 
	 <value>The name of the original file.</value>
	*/
	private String OriginalFileName;
	public final String getOriginalFileName()
	{
		return OriginalFileName;
	}
	public final void setOriginalFileName(String value)
	{
		OriginalFileName = value;
	}
	/** 
	 Gets or sets the item.
	 
	 <value>The item.</value>
	*/
	private BaseItemDto Item;
	public final BaseItemDto getItem()
	{
		return Item;
	}
	public final void setItem(BaseItemDto value)
	{
		Item = value;
	}
	/** 
	 Gets or sets the user identifier.
	 
	 <value>The user identifier.</value>
	*/
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}
}