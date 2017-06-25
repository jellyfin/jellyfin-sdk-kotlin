package mediabrowser.model.apiclient;

public class ServerUserInfo
{
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private boolean IsSignedInOffline;
	public final boolean getIsSignedInOffline()
	{
		return IsSignedInOffline;
	}
	public final void setIsSignedInOffline(boolean value)
	{
		IsSignedInOffline = value;
	}
}