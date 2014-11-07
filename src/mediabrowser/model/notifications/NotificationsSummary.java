package mediabrowser.model.notifications;

public class NotificationsSummary
{
	private int UnreadCount;
	public final int getUnreadCount()
	{
		return UnreadCount;
	}
	public final void setUnreadCount(int value)
	{
		UnreadCount = value;
	}
	private NotificationLevel MaxUnreadNotificationLevel = NotificationLevel.values()[0];
	public final NotificationLevel getMaxUnreadNotificationLevel()
	{
		return MaxUnreadNotificationLevel;
	}
	public final void setMaxUnreadNotificationLevel(NotificationLevel value)
	{
		MaxUnreadNotificationLevel = value;
	}
}