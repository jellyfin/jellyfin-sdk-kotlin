package mediabrowser.model.dto;

public enum RecommendationType
{
	SimilarToRecentlyPlayed(0),

	SimilarToLikedItem(1),

	HasDirectorFromRecentlyPlayed(2),

	HasActorFromRecentlyPlayed(3),

	HasLikedDirector(4),

	HasLikedActor(5);

	private int intValue;
	private static java.util.HashMap<Integer, RecommendationType> mappings;
	private static java.util.HashMap<Integer, RecommendationType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (RecommendationType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, RecommendationType>();
				}
			}
		}
		return mappings;
	}

	private RecommendationType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static RecommendationType forValue(int value)
	{
		return getMappings().get(value);
	}
}