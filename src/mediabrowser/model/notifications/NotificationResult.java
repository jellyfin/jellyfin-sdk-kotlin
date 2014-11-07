package mediabrowser.model.notifications;

public class NotificationResult
{
	private Notification[] Notifications;
	public final Notification[] getNotifications()
	{
		return Notifications;
	}
	public final void setNotifications(Notification[] value)
	{
		Notifications = value;
	}
	private int TotalRecordCount;
	public final int getTotalRecordCount()
	{
		return TotalRecordCount;
	}
	public final void setTotalRecordCount(int value)
	{
		TotalRecordCount = value;
	}
}