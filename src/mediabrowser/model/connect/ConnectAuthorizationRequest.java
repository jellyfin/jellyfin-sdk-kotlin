package mediabrowser.model.connect;

public class ConnectAuthorizationRequest
{
	private String SendingUserId;
	public final String getSendingUserId()
	{
		return SendingUserId;
	}
	public final void setSendingUserId(String value)
	{
		SendingUserId = value;
	}
	private String ConnectUserName;
	public final String getConnectUserName()
	{
		return ConnectUserName;
	}
	public final void setConnectUserName(String value)
	{
		ConnectUserName = value;
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
	private String[] EnabledChannels;
	public final String[] getEnabledChannels()
	{
		return EnabledChannels;
	}
	public final void setEnabledChannels(String[] value)
	{
		EnabledChannels = value;
	}

	public ConnectAuthorizationRequest()
	{
		setExcludedLibraries(new String[] { });
		setEnabledChannels(new String[] { });
	}
}