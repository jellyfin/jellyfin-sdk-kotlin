package mediabrowser.model.session;

import mediabrowser.model.dlna.*;

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

	private boolean SupportsContentUploading;
	public final boolean getSupportsContentUploading()
	{
		return SupportsContentUploading;
	}
	public final void setSupportsContentUploading(boolean value)
	{
		SupportsContentUploading = value;
	}
	private boolean SupportsUniqueIdentifier;
	public final boolean getSupportsUniqueIdentifier()
	{
		return SupportsUniqueIdentifier;
	}
	public final void setSupportsUniqueIdentifier(boolean value)
	{
		SupportsUniqueIdentifier = value;
	}
	private boolean SupportsSync;
	public final boolean getSupportsSync()
	{
		return SupportsSync;
	}
	public final void setSupportsSync(boolean value)
	{
		SupportsSync = value;
	}

	private DeviceProfile DeviceProfile;
	public final DeviceProfile getDeviceProfile()
	{
		return DeviceProfile;
	}
	public final void setDeviceProfile(DeviceProfile value)
	{
		DeviceProfile = value;
	}

	public ClientCapabilities()
	{
		setPlayableMediaTypes(new java.util.ArrayList<String>());
		setSupportedCommands(new java.util.ArrayList<String>());
		setSupportsUniqueIdentifier(true);
	}
}