package mediabrowser.model.livetv;

import mediabrowser.model.entities.*;

public enum KeepUntil
{
	UntilDeleted,
	UntilSpaceNeeded,
	UntilWatched,
	UntilDate;

	public int getValue()
	{
		return this.ordinal();
	}

	public static KeepUntil forValue(int value)
	{
		return values()[value];
	}
}