package mediabrowser.model.drawing;

/** 
 Enum ImageOutputFormat
*/
public enum ImageOutputFormat
{
	/** 
	 The BMP
	*/
	Bmp,
	/** 
	 The GIF
	*/
	Gif,
	/** 
	 The JPG
	*/
	Jpg,
	/** 
	 The PNG
	*/
	Png,
	/** 
	 The webp
	*/
	Webp;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ImageOutputFormat forValue(int value)
	{
		return values()[value];
	}
}