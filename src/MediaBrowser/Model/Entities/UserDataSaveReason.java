package MediaBrowser.Model.Entities;

/** 
 Enum UserDataSaveReason
*/
public enum UserDataSaveReason
{
	/** 
	 The playback start
	*/
	PlaybackStart(1),
	/** 
	 The playback progress
	*/
	PlaybackProgress(2),
	/** 
	 The playback finished
	*/
	PlaybackFinished(3),
	/** 
	 The toggle played
	*/
	TogglePlayed(4),
	/** 
	 The update user rating
	*/
	UpdateUserRating(5),
	/** 
	 The import
	*/
	Import(6);

	private int intValue;
	private static java.util.HashMap<Integer, UserDataSaveReason> mappings;
	private static java.util.HashMap<Integer, UserDataSaveReason> getMappings()
	{
		if (mappings == null)
		{
			synchronized (UserDataSaveReason.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, UserDataSaveReason>();
				}
			}
		}
		return mappings;
	}

	private UserDataSaveReason(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static UserDataSaveReason forValue(int value)
	{
		return getMappings().get(value);
	}
}