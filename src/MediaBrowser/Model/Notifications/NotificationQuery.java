package MediaBrowser.Model.Notifications;

public class NotificationQuery
{
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	private Boolean privateIsRead;
	public final Boolean getIsRead()
	{
		return privateIsRead;
	}
	public final void setIsRead(Boolean value)
	{
		privateIsRead = value;
	}

	private Integer privateStartIndex;
	public final Integer getStartIndex()
	{
		return privateStartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		privateStartIndex = value;
	}

	private Integer privateLimit;
	public final Integer getLimit()
	{
		return privateLimit;
	}
	public final void setLimit(Integer value)
	{
		privateLimit = value;
	}
}