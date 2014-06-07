package MediaBrowser.Model.Session;

public class SessionCapabilities
{
	private java.util.ArrayList<String> privatePlayableMediaTypes;
	public final java.util.ArrayList<String> getPlayableMediaTypes()
	{
		return privatePlayableMediaTypes;
	}
	public final void setPlayableMediaTypes(java.util.ArrayList<String> value)
	{
		privatePlayableMediaTypes = value;
	}

	private java.util.ArrayList<String> privateSupportedCommands;
	public final java.util.ArrayList<String> getSupportedCommands()
	{
		return privateSupportedCommands;
	}
	public final void setSupportedCommands(java.util.ArrayList<String> value)
	{
		privateSupportedCommands = value;
	}

	private boolean privateSupportsMediaControl;
	public final boolean getSupportsMediaControl()
	{
		return privateSupportsMediaControl;
	}
	public final void setSupportsMediaControl(boolean value)
	{
		privateSupportsMediaControl = value;
	}

	private String privateMessageCallbackUrl;
	public final String getMessageCallbackUrl()
	{
		return privateMessageCallbackUrl;
	}
	public final void setMessageCallbackUrl(String value)
	{
		privateMessageCallbackUrl = value;
	}

	public SessionCapabilities()
	{
		setPlayableMediaTypes(new java.util.ArrayList<String>());
		setSupportedCommands(new java.util.ArrayList<String>());
	}
}