package MediaBrowser.Model.ApiClient;

public class ServerInfo
{
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
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
	private String LocalAddress;
	public final String getLocalAddress()
	{
		return LocalAddress;
	}
	public final void setLocalAddress(String value)
	{
		LocalAddress = value;
	}
	private String RemoteAddress;
	public final String getRemoteAddress()
	{
		return RemoteAddress;
	}
	public final void setRemoteAddress(String value)
	{
		RemoteAddress = value;
	}
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}
	private String AccessToken;
	public final String getAccessToken()
	{
		return AccessToken;
	}
	public final void setAccessToken(String value)
	{
		AccessToken = value;
	}
	private java.util.ArrayList<WakeOnLanInfo> WakeOnLanInfos;
	public final java.util.ArrayList<WakeOnLanInfo> getWakeOnLanInfos()
	{
		return WakeOnLanInfos;
	}
	public final void setWakeOnLanInfos(java.util.ArrayList<WakeOnLanInfo> value)
	{
		WakeOnLanInfos = value;
	}

	public ServerInfo()
	{
		setWakeOnLanInfos(new java.util.ArrayList<WakeOnLanInfo>());
		setLocalAddress("http://localhost:8096");
	}
}