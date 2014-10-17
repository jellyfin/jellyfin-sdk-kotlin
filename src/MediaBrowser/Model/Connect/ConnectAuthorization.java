package MediaBrowser.Model.Connect;

public class ConnectAuthorization
{
	private String ConnectUserId;
	public final String getConnectUserId()
	{
		return ConnectUserId;
	}
	public final void setConnectUserId(String value)
	{
		ConnectUserId = value;
	}
	private String UserName;
	public final String getUserName()
	{
		return UserName;
	}
	public final void setUserName(String value)
	{
		UserName = value;
	}
	private String ImageUrl;
	public final String getImageUrl()
	{
		return ImageUrl;
	}
	public final void setImageUrl(String value)
	{
		ImageUrl = value;
	}
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
}