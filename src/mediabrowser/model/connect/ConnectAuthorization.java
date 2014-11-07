package mediabrowser.model.connect;

public class ConnectAuthorization
{
	private String ConnectUserId;
	public final String getConnectUserId()
	{
		return ConnectUserId;
	}
	public final void setConnectUserId(String value)
	{
		ConnectUserId = value;
	}
	private String UserName;
	public final String getUserName()
	{
		return UserName;
	}
	public final void setUserName(String value)
	{
		UserName = value;
	}
	private String ImageUrl;
	public final String getImageUrl()
	{
		return ImageUrl;
	}
	public final void setImageUrl(String value)
	{
		ImageUrl = value;
	}
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private String[] ExcludedLibraries;
	public final String[] getExcludedLibraries()
	{
		return ExcludedLibraries;
	}
	public final void setExcludedLibraries(String[] value)
	{
		ExcludedLibraries = value;
	}
	private boolean EnableLiveTv;
	public final boolean getEnableLiveTv()
	{
		return EnableLiveTv;
	}
	public final void setEnableLiveTv(boolean value)
	{
		EnableLiveTv = value;
	}
	private String[] ExcludedChannels;
	public final String[] getExcludedChannels()
	{
		return ExcludedChannels;
	}
	public final void setExcludedChannels(String[] value)
	{
		ExcludedChannels = value;
	}

	public ConnectAuthorization()
	{
		setExcludedLibraries(new String[] { });
		setExcludedChannels(new String[] { });
	}
}