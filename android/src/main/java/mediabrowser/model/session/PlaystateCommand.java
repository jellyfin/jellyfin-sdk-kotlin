package mediabrowser.model.session;

/** 
 Enum PlaystateCommand
*/
public enum PlaystateCommand
{
	/** 
	 The stop
	*/
	Stop,
	/** 
	 The pause
	*/
	Pause,
	/** 
	 The unpause
	*/
	Unpause,
	/** 
	 The next track
	*/
	NextTrack,
	/** 
	 The previous track
	*/
	PreviousTrack,
	/** 
	 The seek
	*/
	Seek,
	/** 
	 The rewind
	*/
	Rewind,
	/** 
	 The fast forward
	*/
	FastForward,
	/**
	 The PlayPause
	 */
	PlayPause;

	public int getValue()
	{
		return this.ordinal();
	}

	public static PlaystateCommand forValue(int value)
	{
		return values()[value];
	}
}