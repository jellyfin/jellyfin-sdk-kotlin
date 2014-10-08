package MediaBrowser.Model.Dlna;

public class ResolutionOptions
{
	private Integer privateMaxWidth = null;
	public final Integer getMaxWidth()
	{
		return privateMaxWidth;
	}
	public final void setMaxWidth(Integer value)
	{
		privateMaxWidth = value;
	}
	private Integer privateMaxHeight = null;
	public final Integer getMaxHeight()
	{
		return privateMaxHeight;
	}
	public final void setMaxHeight(Integer value)
	{
		privateMaxHeight = value;
	}
}