package MediaBrowser.Model.Session;

public class ClientCapabilities
{
	private java.util.ArrayList<String> PlayableMediaTypes;
	public final java.util.ArrayList<String> getPlayableMediaTypes()
	{
		return PlayableMediaTypes;
	}
	public final void setPlayableMediaTypes(java.util.ArrayList<String> value)
	{
		PlayableMediaTypes = value;
	}
	private java.util.ArrayList<String> SupportedCommands;
	public final java.util.ArrayList<String> getSupportedCommands()
	{
		return SupportedCommands;
	}
	public final void setSupportedCommands(java.util.ArrayList<String> value)
	{
		SupportedCommands = value;
	}

	public ClientCapabilities()
	{
		setPlayableMediaTypes(new java.util.ArrayList<String>());
		setSupportedCommands(new java.util.ArrayList<String>());
	}
}