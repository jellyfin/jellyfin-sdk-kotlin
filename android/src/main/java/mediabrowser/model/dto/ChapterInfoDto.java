package mediabrowser.model.dto;

/** 
 Class ChapterInfo
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}")] public class ChapterInfoDto
public class ChapterInfoDto
{
	/** 
	 Gets or sets the start position ticks.
	 
	 <value>The start position ticks.</value>
	*/
	private long StartPositionTicks;
	public final long getStartPositionTicks()
	{
		return StartPositionTicks;
	}
	public final void setStartPositionTicks(long value)
	{
		StartPositionTicks = value;
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
	 Gets or sets the image tag.
	 
	 <value>The image tag.</value>
	*/
	private String ImageTag;
	public final String getImageTag()
	{
		return ImageTag;
	}
	public final void setImageTag(String value)
	{
		ImageTag = value;
	}

	/** 
	 Gets a value indicating whether this instance has image.
	 
	 <value><c>true</c> if this instance has image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasImage
	public final boolean getHasImage()
	{
		return getImageTag() != null;
	}
}