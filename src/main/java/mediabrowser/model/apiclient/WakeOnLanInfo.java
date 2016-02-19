package mediabrowser.model.apiclient;

public class WakeOnLanInfo
{
	private String MacAddress;
	public final String getMacAddress()
	{
		return MacAddress;
	}
	public final void setMacAddress(String value)
	{
		MacAddress = value;
	}
	private int Port;
	public final int getPort()
	{
		return Port;
	}
	public final void setPort(int value)
	{
		Port = value;
	}

	public WakeOnLanInfo()
	{
		setPort(9);
	}
}