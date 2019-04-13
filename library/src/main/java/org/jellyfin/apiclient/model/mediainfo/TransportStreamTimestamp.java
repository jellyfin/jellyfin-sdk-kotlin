package org.jellyfin.apiclient.model.mediainfo;

public enum TransportStreamTimestamp
{
	None,
	Zero,
	Valid;

	public int getValue()
	{
		return this.ordinal();
	}

	public static TransportStreamTimestamp forValue(int value)
	{
		return values()[value];
	}
}