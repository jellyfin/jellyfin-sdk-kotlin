package org.jellyfin.apiclient.model.entities;

/** 
 Enum VideoType
*/
public enum VideoType
{
	/** 
	 The video file
	*/
	VideoFile,
	/** 
	 The iso
	*/
	Iso,
	/** 
	 The DVD
	*/
	Dvd,
	/** 
	 The blu ray
	*/
	BluRay,
	/** 
	 The hd DVD
	*/
	HdDvd;

	public int getValue()
	{
		return this.ordinal();
	}

	public static VideoType forValue(int value)
	{
		return values()[value];
	}
}