package MediaBrowser.Model.ApiClient;

public class WakeOnLanInfo
{
	private String privateMacAddress;
	public final String getMacAddress()
	{
		return privateMacAddress;
	}
	public final void setMacAddress(String value)
	{
		privateMacAddress = value;
	}
	private int privatePort;
	public final int getPort()
	{
		return privatePort;
	}
	public final void setPort(int value)
	{
		privatePort = value;
	}

	public WakeOnLanInfo()
	{
		setPort(9);
	}
}