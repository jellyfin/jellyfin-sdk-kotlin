package mediabrowser.model.entities;

/** 
 Enum MetadataFields
*/
public enum MetadataFields
{
	/** 
	 The cast
	*/
	Cast,
	/** 
	 The genres
	*/
	Genres,
	/** 
	 The keywords
	*/
	Keywords,
	/** 
	 The production locations
	*/
	ProductionLocations,
	/** 
	 The studios
	*/
	Studios,
	/** 
	 The tags
	*/
	Tags,
	/** 
	 The name
	*/
	Name,
	/** 
	 The overview
	*/
	Overview,
	/** 
	 The runtime
	*/
	Runtime,
	/** 
	 The official rating
	*/
	OfficialRating,
	/** 
	 The images
	*/
	Images,
	/** 
	 The backdrops
	*/
	Backdrops,
	/** 
	 The screenshots
	*/
	Screenshots;

	public int getValue()
	{
		return this.ordinal();
	}

	public static MetadataFields forValue(int value)
	{
		return values()[value];
	}
}