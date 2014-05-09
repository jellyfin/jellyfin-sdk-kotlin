package MediaBrowser.Model.Entities;

/** 
 Enum UserDataSaveReason
*/
public enum UserDataSaveReason
{
	/** 
	 The playback start
	*/
	PlaybackStart,
	/** 
	 The playback progress
	*/
	PlaybackProgress,
	/** 
	 The playback finished
	*/
	PlaybackFinished,
	/** 
	 The toggle played
	*/
	TogglePlayed,
	/** 
	 The update user rating
	*/
	UpdateUserRating;

	public int getValue()
	{
		return this.ordinal();
	}

	public static UserDataSaveReason forValue(int value)
	{
		return values()[value];
	}
}