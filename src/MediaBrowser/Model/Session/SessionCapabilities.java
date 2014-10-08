package MediaBrowser.Model.Session;

public class SessionCapabilities
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

	private boolean SupportsMediaControl;
	public final boolean getSupportsMediaControl()
	{
		return SupportsMediaControl;
	}
	public final void setSupportsMediaControl(boolean value)
	{
		SupportsMediaControl = value;
	}

	private String MessageCallbackUrl;
	public final String getMessageCallbackUrl()
	{
		return MessageCallbackUrl;
	}
	public final void setMessageCallbackUrl(String value)
	{
		MessageCallbackUrl = value;
	}

	public SessionCapabilities()
	{
		setPlayableMediaTypes(new java.util.ArrayList<String>());
		setSupportedCommands(new java.util.ArrayList<String>());
	}
}