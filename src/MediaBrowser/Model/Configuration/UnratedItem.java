package MediaBrowser.Model.Configuration;

public enum UnratedItem
{
	Movie,
	Trailer,
	Series,
	Music,
	Game,
	Book,
	LiveTvChannel,
	LiveTvProgram,
	ChannelContent,
	Other;

	public int getValue()
	{
		return this.ordinal();
	}

	public static UnratedItem forValue(int value)
	{
		return values()[value];
	}
}