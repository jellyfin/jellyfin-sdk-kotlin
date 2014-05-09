package MediaBrowser.Model.Configuration;

public class LiveTvOptions
{
	private Integer privateGuideDays;
	public final Integer getGuideDays()
	{
		return privateGuideDays;
	}
	public final void setGuideDays(Integer value)
	{
		privateGuideDays = value;
	}
}