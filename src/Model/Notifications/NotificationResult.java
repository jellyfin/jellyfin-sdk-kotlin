package MediaBrowser.Model.Notifications;

public class NotificationResult
{
	private Notification[] privateNotifications;
	public final Notification[] getNotifications()
	{
		return privateNotifications;
	}
	public final void setNotifications(Notification[] value)
	{
		privateNotifications = value;
	}
	private int privateTotalRecordCount;
	public final int getTotalRecordCount()
	{
		return privateTotalRecordCount;
	}
	public final void setTotalRecordCount(int value)
	{
		privateTotalRecordCount = value;
	}
}