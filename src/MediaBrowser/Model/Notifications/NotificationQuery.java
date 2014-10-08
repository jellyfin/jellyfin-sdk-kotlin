package MediaBrowser.Model.Notifications;

public class NotificationQuery
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

	private Boolean IsRead;
	public final Boolean getIsRead()
	{
		return IsRead;
	}
	public final void setIsRead(Boolean value)
	{
		IsRead = value;
	}

	private Integer StartIndex;
	public final Integer getStartIndex()
	{
		return StartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		StartIndex = value;
	}

	private Integer Limit;
	public final Integer getLimit()
	{
		return Limit;
	}
	public final void setLimit(Integer value)
	{
		Limit = value;
	}
}