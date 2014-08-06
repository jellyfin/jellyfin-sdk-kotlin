package MediaBrowser.Model.LiveTv;

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
	private String privateActiveService;
	public final String getActiveService()
	{
		return privateActiveService;
	}
	public final void setActiveService(String value)
	{
		privateActiveService = value;
	}
}