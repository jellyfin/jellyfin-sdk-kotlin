package MediaBrowser.Model.Chapters;

public class RemoteChapterInfo
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
}