package mediabrowser.model.users;

public class UserAction
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
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}
	private UserActionType Type = UserActionType.values()[0];
	public final UserActionType getType()
	{
		return Type;
	}
	public final void setType(UserActionType value)
	{
		Type = value;
	}
	private java.util.Date Date = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return Date;
	}
	public final void setDate(java.util.Date value)
	{
		Date = value;
	}
	private Long PositionTicks = null;
	public final Long getPositionTicks()
	{
		return PositionTicks;
	}
	public final void setPositionTicks(Long value)
	{
		PositionTicks = value;
	}
}