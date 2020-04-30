package org.jellyfin.apiclient.model.entities;

/** 
 Enum ScrollDirection
*/
public enum ScrollDirection
{
	/** 
	 The horizontal
	*/
	Horizontal,
	/** 
	 The vertical
	*/
	Vertical;

	public int getValue()
	{
		return this.ordinal();
	}

	public static ScrollDirection forValue(int value)
	{
		return values()[value];
	}
}