package mediabrowser.model.connect;

public class PinCreationResult
{
	private String Pin;
	public final String getPin()
	{
		return Pin;
	}
	public final void setPin(String value)
	{
		Pin = value;
	}
	private String DeviceId;
	public final String getDeviceId()
	{
		return DeviceId;
	}
	public final void setDeviceId(String value)
	{
		DeviceId = value;
	}
	private boolean IsConfirmed;
	public final boolean getIsConfirmed()
	{
		return IsConfirmed;
	}
	public final void setIsConfirmed(boolean value)
	{
		IsConfirmed = value;
	}
	private boolean IsExpired;
	public final boolean getIsExpired()
	{
		return IsExpired;
	}
	public final void setIsExpired(boolean value)
	{
		IsExpired = value;
	}
}