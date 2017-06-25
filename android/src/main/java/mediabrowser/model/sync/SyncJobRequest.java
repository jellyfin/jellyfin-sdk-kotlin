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
	 Gets or sets the category.
	 
	 <value>The category.</value>
	*/
	private SyncCategory Category = null;
	public final SyncCategory getCategory()
	{
		return Category;
	}
	public final void setCategory(SyncCategory value)
	{
		Category = value;
	}
	/** 
	 Gets or sets the parent identifier.
	 
	 <value>The parent identifier.</value>
	*/
	private String ParentId;
	public final String getParentId()
	{
		return ParentId;
	}
	public final void setParentId(String value)
	{
		ParentId = value;
	}
	/** 
	 Gets or sets the quality.
	 
	 <value>The quality.</value>
	*/
	private String Quality;
	public final String getQuality()
	{
		return Quality;
	}
	public final void setQuality(String value)
	{
		Quality = value;
	}
	/** 
	 Gets or sets the profile.
	 
	 <value>The profile.</value>
	*/
	private String Profile;
	public final String getProfile()
	{
		return Profile;
	}
	public final void setProfile(String value)
	{
		Profile = value;
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
	/** 
	 Gets or sets the bitrate.
	 
	 <value>The bitrate.</value>
	*/
	private Integer Bitrate = null;
	public final Integer getBitrate()
	{
		return Bitrate;
	}
	public final void setBitrate(Integer value)
	{
		Bitrate = value;
	}

	public SyncJobRequest()
	{
		setItemIds(new java.util.ArrayList<String>());
		setSyncNewContent(true);
	}
}