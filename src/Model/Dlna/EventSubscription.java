package MediaBrowser.Model.Dlna;

public class EventSubscription
{
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}
	private String privateCallbackUrl;
	public final String getCallbackUrl()
	{
		return privateCallbackUrl;
	}
	public final void setCallbackUrl(String value)
	{
		privateCallbackUrl = value;
	}
	private String privateNotificationType;
	public final String getNotificationType()
	{
		return privateNotificationType;
	}
	public final void setNotificationType(String value)
	{
		privateNotificationType = value;
	}

	private java.util.Date privateSubscriptionTime = new java.util.Date(0);
	public final java.util.Date getSubscriptionTime()
	{
		return privateSubscriptionTime;
	}
	public final void setSubscriptionTime(java.util.Date value)
	{
		privateSubscriptionTime = value;
	}
	private int privateTimeoutSeconds;
	public final int getTimeoutSeconds()
	{
		return privateTimeoutSeconds;
	}
	public final void setTimeoutSeconds(int value)
	{
		privateTimeoutSeconds = value;
	}

	private long privateTriggerCount;
	public final long getTriggerCount()
	{
		return privateTriggerCount;
	}
	public final void setTriggerCount(long value)
	{
		privateTriggerCount = value;
	}

	public final void IncrementTriggerCount()
	{
		if (getTriggerCount() == Long.MAX_VALUE)
		{
			setTriggerCount(0);
		}

		setTriggerCount(getTriggerCount() + 1);
	}

	public final boolean getIsExpired()
	{
		return getSubscriptionTime().AddSeconds(getTimeoutSeconds()) >= java.util.Date.UtcNow;
	}
}