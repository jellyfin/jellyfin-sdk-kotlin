package mediabrowser.model.themes;

public class AppTheme
{
	private String AppName;
	public final String getAppName()
	{
		return AppName;
	}
	public final void setAppName(String value)
	{
		AppName = value;
	}

	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	private java.util.HashMap<String, String> Options;
	public final java.util.HashMap<String, String> getOptions()
	{
		return Options;
	}
	public final void setOptions(java.util.HashMap<String, String> value)
	{
		Options = value;
	}

	private java.util.ArrayList<ThemeImage> Images;
	public final java.util.ArrayList<ThemeImage> getImages()
	{
		return Images;
	}
	public final void setImages(java.util.ArrayList<ThemeImage> value)
	{
		Images = value;
	}

	public AppTheme()
	{
		setOptions(new java.util.HashMap<String, String>());

		setImages(new java.util.ArrayList<ThemeImage>());
	}
}