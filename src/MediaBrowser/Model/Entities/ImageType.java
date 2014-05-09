package MediaBrowser.Model.Entities;

/** 
 Enum ImageType
*/
public enum ImageType
{
	/** 
	 The primary
	*/
	Primary,
	/** 
	 The art
	*/
	Art,
	/** 
	 The backdrop
	*/
	Backdrop,
	/** 
	 The banner
	*/
	Banner,
	/** 
	 The logo
	*/
	Logo,
	/** 
	 The thumb
	*/
	Thumb,
	/** 
	 The disc
	*/
	Disc,
	/** 
	 The box
	*/
	Box,
	/** 
	 The screenshot
	*/
	Screenshot,
	/** 
	 The menu
	*/
	Menu,
	/** 
	 The chapter image
	*/
	Chapter,
	/** 
	 The box rear
	*/
	BoxRear;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ImageType forValue(int value)
	{
		return values()[value];
	}
}