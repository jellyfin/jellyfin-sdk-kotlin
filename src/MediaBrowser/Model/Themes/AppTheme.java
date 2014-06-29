package MediaBrowser.Model.Themes;

public class AppTheme
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

	private java.util.HashMap<String, String> privateOptions;
	public final java.util.HashMap<String, String> getOptions()
	{
		return privateOptions;
	}
	public final void setOptions(java.util.HashMap<String, String> value)
	{
		privateOptions = value;
	}

	private java.util.ArrayList<ThemeImage> privateImages;
	public final java.util.ArrayList<ThemeImage> getImages()
	{
		return privateImages;
	}
	public final void setImages(java.util.ArrayList<ThemeImage> value)
	{
		privateImages = value;
	}

	public AppTheme()
	{
		setOptions(new java.util.HashMap<String, String>());

		setImages(new java.util.ArrayList<ThemeImage>());
	}
}