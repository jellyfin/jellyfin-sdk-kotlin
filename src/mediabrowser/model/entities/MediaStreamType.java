package mediabrowser.model.entities;

/** 
 Enum MediaStreamType
*/
public enum MediaStreamType
{
	/** 
	 The audio
	*/
	Audio,
	/** 
	 The video
	*/
	Video,
	/** 
	 The subtitle
	*/
	Subtitle,
	/** 
	 The embedded image
	*/
	EmbeddedImage;

	public int getValue()
	{
		return this.ordinal();
	}

	public static MediaStreamType forValue(int value)
	{
		return values()[value];
	}
}