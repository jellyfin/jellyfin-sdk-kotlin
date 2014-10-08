package MediaBrowser.Model.LiveTv;

public class LiveTvOptions
{
	private Integer GuideDays;
	public final Integer getGuideDays()
	{
		return GuideDays;
	}
	public final void setGuideDays(Integer value)
	{
		GuideDays = value;
	}
	private String ActiveService;
	public final String getActiveService()
	{
		return ActiveService;
	}
	public final void setActiveService(String value)
	{
		ActiveService = value;
	}
}