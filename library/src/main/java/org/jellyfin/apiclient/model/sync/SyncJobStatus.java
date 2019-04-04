package org.jellyfin.apiclient.model.sync;

public enum SyncJobStatus
{
	Queued(0),
	Converting(1),
	ReadyToTransfer(2),
	Transferring(3),
	Completed(4),
	CompletedWithError(5),
	Failed(6),
	Cancelled(7);

	private int intValue;
	private static java.util.HashMap<Integer, SyncJobStatus> mappings;
	private static java.util.HashMap<Integer, SyncJobStatus> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SyncJobStatus.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SyncJobStatus>();
				}
			}
		}
		return mappings;
	}

	private SyncJobStatus(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SyncJobStatus forValue(int value)
	{
		return getMappings().get(value);
	}
}