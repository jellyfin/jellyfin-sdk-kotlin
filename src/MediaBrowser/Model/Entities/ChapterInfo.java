package MediaBrowser.Model.Entities;

/** 
 Class ChapterInfo
*/
public class ChapterInfo
{
	/** 
	 Gets or sets the start position ticks.
	 
	 <value>The start position ticks.</value>
	*/
	private long privateStartPositionTicks;
	public final long getStartPositionTicks()
	{
		return privateStartPositionTicks;
	}
	public final void setStartPositionTicks(long value)
	{
		privateStartPositionTicks = value;
	}

	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	/** 
	 Gets or sets the image path.
	 
	 <value>The image path.</value>
	*/
	private String privateImagePath;
	public final String getImagePath()
	{
		return privateImagePath;
	}
	public final void setImagePath(String value)
	{
		privateImagePath = value;
	}
}