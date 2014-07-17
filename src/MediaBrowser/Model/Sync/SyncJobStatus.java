package MediaBrowser.Model.Sync;

public enum SyncJobStatus
{
	/** 
	 The queued
	*/
	Queued(0),
	/** 
	 The transcoding
	*/
	Transcoding(1),
	/** 
	 The transcoding failed
	*/
	TranscodingFailed(2),
	/** 
	 The transcoding completed
	*/
	TranscodingCompleted(3),
	/** 
	 The transfering
	*/
	Transfering(4),
	/** 
	 The transfer failed
	*/
	TransferFailed(4),
	/** 
	 The completed
	*/
	Completed(6);

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