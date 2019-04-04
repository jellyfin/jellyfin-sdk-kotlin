package org.jellyfin.apiclient.model.entities;

/** 
 Enum SortOrder
*/
public enum SortOrder
{
	/** 
	 The ascending
	*/
	Ascending,
	/** 
	 The descending
	*/
	Descending;

	public int getValue()
	{
		return this.ordinal();
	}

	public static SortOrder forValue(int value)
	{
		return values()[value];
	}
}