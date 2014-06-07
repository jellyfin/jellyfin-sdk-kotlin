package MediaBrowser.Model.Querying;

/** 
 Enum ItemFilter
*/
public enum ItemFilter
{
	/** 
	 The item is a folder
	*/
	IsFolder(1),
	/** 
	 The item is not folder
	*/
	IsNotFolder(2),
	/** 
	 The item is unplayed
	*/
	IsUnplayed(3),
	/** 
	 The item is played
	*/
	IsPlayed(4),
	/** 
	 The item is a favorite
	*/
	IsFavorite(5),
	/** 
	 The is recently added
	*/
	IsRecentlyAdded(6),
	/** 
	 The item is resumable
	*/
	IsResumable(7),
	/** 
	 The likes
	*/
	Likes(8),
	/** 
	 The dislikes
	*/
	Dislikes(9),
	/** 
	 The is favorite or likes
	*/
	IsFavoriteOrLikes(10);

	private int intValue;
	private static java.util.HashMap<Integer, ItemFilter> mappings;
	private static java.util.HashMap<Integer, ItemFilter> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ItemFilter.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ItemFilter>();
				}
			}
		}
		return mappings;
	}

	private ItemFilter(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ItemFilter forValue(int value)
	{
		return getMappings().get(value);
	}
}