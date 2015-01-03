package mediabrowser.model.sync;

public enum SyncJobItemStatus
{
	Queued(0),
	Converting(1),
	Transferring(2),
	Synced(3),
	RemovedFromDevice(4),
	Failed(5),
	Cancelled(6);

	private int intValue;
	private static java.util.HashMap<Integer, SyncJobItemStatus> mappings;
	private static java.util.HashMap<Integer, SyncJobItemStatus> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SyncJobItemStatus.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SyncJobItemStatus>();
				}
			}
		}
		return mappings;
	}

	private SyncJobItemStatus(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SyncJobItemStatus forValue(int value)
	{
		return getMappings().get(value);
	}
}