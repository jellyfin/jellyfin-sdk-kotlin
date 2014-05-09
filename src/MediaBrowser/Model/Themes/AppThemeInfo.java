package MediaBrowser.Model.Themes;

public class AppThemeInfo
{
	private String privateAppName;
	public final String getAppName()
	{
		return privateAppName;
	}
	public final void setAppName(String value)
	{
		privateAppName = value;
	}

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