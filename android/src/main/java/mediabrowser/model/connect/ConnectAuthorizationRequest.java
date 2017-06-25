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

	public ConnectAuthorizationRequest()
	{
		setEnabledLibraries(new String[] { });
		setEnabledChannels(new String[] { });
	}
}