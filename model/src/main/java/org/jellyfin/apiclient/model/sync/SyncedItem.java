package org.jellyfin.apiclient.model.sync;

import org.jellyfin.apiclient.model.dto.*;

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
	 Gets or sets the name of the synchronize job.
	 
	 <value>The name of the synchronize job.</value>
	*/
	private String SyncJobName;
	public final String getSyncJobName()
	{
		return SyncJobName;
	}
	public final void setSyncJobName(String value)
	{
		SyncJobName = value;
	}
	/** 
	 Gets or sets the synchronize job date created.
	 
	 <value>The synchronize job date created.</value>
	*/
	private java.util.Date SyncJobDateCreated = new java.util.Date(0);
	public final java.util.Date getSyncJobDateCreated()
	{
		return SyncJobDateCreated;
	}
	public final void setSyncJobDateCreated(java.util.Date value)
	{
		SyncJobDateCreated = value;
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
	/** 
	 Gets or sets the additional files.
	 
	 <value>The additional files.</value>
	*/
	private java.util.ArrayList<ItemFileInfo> AdditionalFiles;
	public final java.util.ArrayList<ItemFileInfo> getAdditionalFiles()
	{
		return AdditionalFiles;
	}
	public final void setAdditionalFiles(java.util.ArrayList<ItemFileInfo> value)
	{
		AdditionalFiles = value;
	}

	public SyncedItem()
	{
		setAdditionalFiles(new java.util.ArrayList<ItemFileInfo>());
	}
}