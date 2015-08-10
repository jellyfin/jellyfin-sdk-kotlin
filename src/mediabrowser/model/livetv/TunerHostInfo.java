package mediabrowser.model.livetv;

public class TunerHostInfo
{
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private String Url;
	public final String getUrl()
	{
		return Url;
	}
	public final void setUrl(String value)
	{
		Url = value;
	}
	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
	}
	private boolean ImportFavoritesOnly;
	public final boolean getImportFavoritesOnly()
	{
		return ImportFavoritesOnly;
	}
	public final void setImportFavoritesOnly(boolean value)
	{
		ImportFavoritesOnly = value;
	}
	private boolean IsEnabled;
	public final boolean getIsEnabled()
	{
		return IsEnabled;
	}
	public final void setIsEnabled(boolean value)
	{
		IsEnabled = value;
	}

	public TunerHostInfo()
	{
		setIsEnabled(true);
	}
}