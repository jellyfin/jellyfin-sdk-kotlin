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
	private String[] EnabledLibraries;
	public final String[] getEnabledLibraries()
	{
		return EnabledLibraries;
	}
	public final void setEnabledLibraries(String[] value)
	{
		EnabledLibraries = value;
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
	private String[] EnabledChannels;
	public final String[] getEnabledChannels()
	{
		return EnabledChannels;
	}
	public final void setEnabledChannels(String[] value)
	{
		EnabledChannels = value;
	}

	public ConnectAuthorization()
	{
		setEnabledLibraries(new String[] { });
		setEnabledChannels(new String[] { });
	}
}