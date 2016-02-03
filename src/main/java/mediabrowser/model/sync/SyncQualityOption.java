package mediabrowser.model.sync;

public class SyncQualityOption
{
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
	 Gets or sets the description.
	 
	 <value>The description.</value>
	*/
	private String Description;
	public final String getDescription()
	{
		return Description;
	}
	public final void setDescription(String value)
	{
		Description = value;
	}
	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is default.
	 
	 <value><c>true</c> if this instance is default; otherwise, <c>false</c>.</value>
	*/
	private boolean IsDefault;
	public final boolean getIsDefault()
	{
		return IsDefault;
	}
	public final void setIsDefault(boolean value)
	{
		IsDefault = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is original quality.
	 
	 <value><c>true</c> if this instance is original quality; otherwise, <c>false</c>.</value>
	*/
	private boolean IsOriginalQuality;
	public final boolean getIsOriginalQuality()
	{
		return IsOriginalQuality;
	}
	public final void setIsOriginalQuality(boolean value)
	{
		IsOriginalQuality = value;
	}
}