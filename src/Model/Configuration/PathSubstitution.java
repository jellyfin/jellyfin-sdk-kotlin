package MediaBrowser.Model.Configuration;

public class PathSubstitution
{
	private String privateFrom;
	public final String getFrom()
	{
		return privateFrom;
	}
	public final void setFrom(String value)
	{
		privateFrom = value;
	}
	private String privateTo;
	public final String getTo()
	{
		return privateTo;
	}
	public final void setTo(String value)
	{
		privateTo = value;
	}
}