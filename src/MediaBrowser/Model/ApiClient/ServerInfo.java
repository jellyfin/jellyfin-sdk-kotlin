package MediaBrowser.Model.ApiClient;

public class ServerInfo
{
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}
	private String privateLocalAddress;
	public final String getLocalAddress()
	{
		return privateLocalAddress;
	}
	public final void setLocalAddress(String value)
	{
		privateLocalAddress = value;
	}
	private String privateRemoteAddress;
	public final String getRemoteAddress()
	{
		return privateRemoteAddress;
	}
	public final void setRemoteAddress(String value)
	{
		privateRemoteAddress = value;
	}
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}
	private String privateAccessToken;
	public final String getAccessToken()
	{
		return privateAccessToken;
	}
	public final void setAccessToken(String value)
	{
		privateAccessToken = value;
	}
	private java.util.ArrayList<WakeOnLanInfo> privateWakeOnLanInfos;
	public final java.util.ArrayList<WakeOnLanInfo> getWakeOnLanInfos()
	{
		return privateWakeOnLanInfos;
	}
	public final void setWakeOnLanInfos(java.util.ArrayList<WakeOnLanInfo> value)
	{
		privateWakeOnLanInfos = value;
	}

	public ServerInfo()
	{
		setWakeOnLanInfos(new java.util.ArrayList<WakeOnLanInfo>());
		setLocalAddress("http://localhost:8096");
	}
}