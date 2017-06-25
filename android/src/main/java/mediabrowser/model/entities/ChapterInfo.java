package mediabrowser.model.entities;

/** 
 Class ChapterInfo
*/
public class ChapterInfo
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
	 Gets or sets the image path.
	 
	 <value>The image path.</value>
	*/
	private String ImagePath;
	public final String getImagePath()
	{
		return ImagePath;
	}
	public final void setImagePath(String value)
	{
		ImagePath = value;
	}
	private java.util.Date ImageDateModified = new java.util.Date(0);
	public final java.util.Date getImageDateModified()
	{
		return ImageDateModified;
	}
	public final void setImageDateModified(java.util.Date value)
	{
		ImageDateModified = value;
	}
}