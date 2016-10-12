package mediabrowser.model.configuration;

public class DlnaOptions
{
	private boolean EnablePlayTo;
	public final boolean getEnablePlayTo()
	{
		return EnablePlayTo;
	}
	public final void setEnablePlayTo(boolean value)
	{
		EnablePlayTo = value;
	}
	private boolean EnableServer;
	public final boolean getEnableServer()
	{
		return EnableServer;
	}
	public final void setEnableServer(boolean value)
	{
		EnableServer = value;
	}
	private boolean EnableDebugLog;
	public final boolean getEnableDebugLog()
	{
		return EnableDebugLog;
	}
	public final void setEnableDebugLog(boolean value)
	{
		EnableDebugLog = value;
	}
	private boolean BlastAliveMessages;
	public final boolean getBlastAliveMessages()
	{
		return BlastAliveMessages;
	}
	public final void setBlastAliveMessages(boolean value)
	{
		BlastAliveMessages = value;
	}
	private int ClientDiscoveryIntervalSeconds;
	public final int getClientDiscoveryIntervalSeconds()
	{
		return ClientDiscoveryIntervalSeconds;
	}
	public final void setClientDiscoveryIntervalSeconds(int value)
	{
		ClientDiscoveryIntervalSeconds = value;
	}
	private int BlastAliveMessageIntervalSeconds;
	public final int getBlastAliveMessageIntervalSeconds()
	{
		return BlastAliveMessageIntervalSeconds;
	}
	public final void setBlastAliveMessageIntervalSeconds(int value)
	{
		BlastAliveMessageIntervalSeconds = value;
	}
	private String DefaultUserId;
	public final String getDefaultUserId()
	{
		return DefaultUserId;
	}
	public final void setDefaultUserId(String value)
	{
		DefaultUserId = value;
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