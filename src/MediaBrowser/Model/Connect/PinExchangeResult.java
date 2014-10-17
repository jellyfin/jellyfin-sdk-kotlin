package MediaBrowser.Model.Connect;

public class PinExchangeResult
{
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}
	private String UserAccessToken;
	public final String getUserAccessToken()
	{
		return UserAccessToken;
	}
	public final void setUserAccessToken(String value)
	{
		UserAccessToken = value;
	}
}