package org.jellyfin.apiclient.model.entities;

/** 
 Enum IsoType
*/
public enum IsoType
{
	/** 
	 The DVD
	*/
	Dvd,
	/** 
	 The blu ray
	*/
	BluRay;

	public int getValue()
	{
		return this.ordinal();
	}

	public static IsoType forValue(int value)
	{
		return values()[value];
	}
}