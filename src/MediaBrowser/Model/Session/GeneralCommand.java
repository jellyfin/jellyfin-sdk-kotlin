package MediaBrowser.Model.Session;

public class GeneralCommand
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

	private String privateControllingUserId;
	public final String getControllingUserId()
	{
		return privateControllingUserId;
	}
	public final void setControllingUserId(String value)
	{
		privateControllingUserId = value;
	}

	private java.util.HashMap<String, String> privateArguments;
	public final java.util.HashMap<String, String> getArguments()
	{
		return privateArguments;
	}
	public final void setArguments(java.util.HashMap<String, String> value)
	{
		privateArguments = value;
	}

	public GeneralCommand()
	{
		setArguments(new java.util.HashMap<String, String>(StringComparer.OrdinalIgnoreCase));
	}
}