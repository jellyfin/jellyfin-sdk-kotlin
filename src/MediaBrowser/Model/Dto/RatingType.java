package MediaBrowser.Model.Dto;

public enum RatingType
{
	Score,
	Likes;

	public int getValue()
	{
		return this.ordinal();
	}

	public static RatingType forValue(int value)
	{
		return values()[value];
	}
}