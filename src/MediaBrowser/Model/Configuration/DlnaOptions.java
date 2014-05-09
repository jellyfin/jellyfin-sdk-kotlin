package MediaBrowser.Model.Configuration;

public class DlnaOptions
{
	private boolean privateEnablePlayTo;
	public final boolean getEnablePlayTo()
	{
		return privateEnablePlayTo;
	}
	public final void setEnablePlayTo(boolean value)
	{
		privateEnablePlayTo = value;
	}
	private boolean privateEnableServer;
	public final boolean getEnableServer()
	{
		return privateEnableServer;
	}
	public final void setEnableServer(boolean value)
	{
		privateEnableServer = value;
	}
	private boolean privateEnableDebugLogging;
	public final boolean getEnableDebugLogging()
	{
		return privateEnableDebugLogging;
	}
	public final void setEnableDebugLogging(boolean value)
	{
		privateEnableDebugLogging = value;
	}
	private boolean privateBlastAliveMessages;
	public final boolean getBlastAliveMessages()
	{
		return privateBlastAliveMessages;
	}
	public final void setBlastAliveMessages(boolean value)
	{
		privateBlastAliveMessages = value;
	}
	private int privateClientDiscoveryIntervalSeconds;
	public final int getClientDiscoveryIntervalSeconds()
	{
		return privateClientDiscoveryIntervalSeconds;
	}
	public final void setClientDiscoveryIntervalSeconds(int value)
	{
		privateClientDiscoveryIntervalSeconds = value;
	}
	private int privateBlastAliveMessageIntervalSeconds;
	public final int getBlastAliveMessageIntervalSeconds()
	{
		return privateBlastAliveMessageIntervalSeconds;
	}
	public final void setBlastAliveMessageIntervalSeconds(int value)
	{
		privateBlastAliveMessageIntervalSeconds = value;
	}
	private String privateDefaultUserId;
	public final String getDefaultUserId()
	{
		return privateDefaultUserId;
	}
	public final void setDefaultUserId(String value)
	{
		privateDefaultUserId = value;
	}

	public DlnaOptions()
	{
		setEnablePlayTo(true);
		setEnableServer(true);
		setBlastAliveMessages(true);
		setClientDiscoveryIntervalSeconds(60);
		setBlastAliveMessageIntervalSeconds(30);
	}
}