package mediabrowser.model.drawing;

/** 
 Enum ImageOutputFormat
*/
public enum ImageFormat
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

	public static ImageFormat forValue(int value)
	{
		return values()[value];
	}
}