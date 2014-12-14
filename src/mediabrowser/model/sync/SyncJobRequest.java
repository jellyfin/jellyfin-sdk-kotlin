package mediabrowser.model.sync;

public class SyncJobRequest
{
	/** 
	 Gets or sets the target identifier.
	 
	 <value>The target identifier.</value>
	*/
	private String TargetId;
	public final String getTargetId()
	{
		return TargetId;
	}
	public final void setTargetId(String value)
	{
		TargetId = value;
	}
	/** 
	 Gets or sets the item ids.
	 
	 <value>The item ids.</value>
	*/
	private java.util.ArrayList<String> ItemIds;
	public final java.util.ArrayList<String> getItemIds()
	{
		return ItemIds;
	}
	public final void setItemIds(java.util.ArrayList<String> value)
	{
		ItemIds = value;
	}
	/** 
	 Gets or sets the quality.
	 
	 <value>The quality.</value>
	*/
	private SyncQuality Quality = SyncQuality.values()[0];
	public final SyncQuality getQuality()
	{
		return Quality;
	}
	public final void setQuality(SyncQuality value)
	{
		Quality = value;
	}
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
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
	 Gets or sets a value indicating whether [unwatched only].
	 
	 <value><c>true</c> if [unwatched only]; otherwise, <c>false</c>.</value>
	*/
	private boolean UnwatchedOnly;
	public final boolean getUnwatchedOnly()
	{
		return UnwatchedOnly;
	}
	public final void setUnwatchedOnly(boolean value)
	{
		UnwatchedOnly = value;
	}
	/** 
	 Gets or sets a value indicating whether [remove when watched].
	 
	 <value><c>true</c> if [remove when watched]; otherwise, <c>false</c>.</value>
	*/
	private boolean RemoveWhenWatched;
	public final boolean getRemoveWhenWatched()
	{
		return RemoveWhenWatched;
	}
	public final void setRemoveWhenWatched(boolean value)
	{
		RemoveWhenWatched = value;
	}
	/** 
	 Gets or sets a value indicating whether [synchronize new content].
	 
	 <value><c>true</c> if [synchronize new content]; otherwise, <c>false</c>.</value>
	*/
	private boolean SyncNewContent;
	public final boolean getSyncNewContent()
	{
		return SyncNewContent;
	}
	public final void setSyncNewContent(boolean value)
	{
		SyncNewContent = value;
	}
	/** 
	 Gets or sets the limit.
	 
	 <value>The limit.</value>
	*/
	private Integer ItemLimit = null;
	public final Integer getItemLimit()
	{
		return ItemLimit;
	}
	public final void setItemLimit(Integer value)
	{
		ItemLimit = value;
	}

	public SyncJobRequest()
	{
		setItemIds(new java.util.ArrayList<String>());
		setSyncNewContent(true);
	}
}