package MediaBrowser.Model.Notifications;

public class NotificationsSummary
{
	private int privateUnreadCount;
	public final int getUnreadCount()
	{
		return privateUnreadCount;
	}
	public final void setUnreadCount(int value)
	{
		privateUnreadCount = value;
	}
	private NotificationLevel privateMaxUnreadNotificationLevel = NotificationLevel.values()[0];
	public final NotificationLevel getMaxUnreadNotificationLevel()
	{
		return privateMaxUnreadNotificationLevel;
	}
	public final void setMaxUnreadNotificationLevel(NotificationLevel value)
	{
		privateMaxUnreadNotificationLevel = value;
	}
}