package MediaBrowser.Model.Drawing;

/** 
 Enum ImageOutputFormat
*/
public enum ImageOutputFormat
{
	/** 
	 The original
	*/
	Original,
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