package mediabrowser.model.entities;

/** 
 Enum MetadataProviders
*/
public enum MetadataProviders
{
	Gamesdb(1),
	/** 
	 The imdb
	*/
	Imdb(2),
	/** 
	 The TMDB
	*/
	Tmdb(3),
	/** 
	 The TVDB
	*/
	Tvdb(4),
	/** 
	 The tvcom
	*/
	Tvcom(5),
	/** 
	 The rotten tomatoes
	*/
	RottenTomatoes(6),
	/** 
	 Tmdb Collection Id
	*/
	TmdbCollection(7),
	MusicBrainzAlbum(8),
	MusicBrainzAlbumArtist(9),
	MusicBrainzArtist(10),
	MusicBrainzReleaseGroup(11),
	Zap2It(12),
	NesBox(13),
	NesBoxRom(14),
	TvRage(15),
	AudioDbArtist(16),
	AudioDbAlbum(17),
	MusicBrainzTrack(18);

	private int intValue;
	private static java.util.HashMap<Integer, MetadataProviders> mappings;
	private static java.util.HashMap<Integer, MetadataProviders> getMappings()
	{
		if (mappings == null)
		{
			synchronized (MetadataProviders.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, MetadataProviders>();
				}
			}
		}
		return mappings;
	}

	private MetadataProviders(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static MetadataProviders forValue(int value)
	{
		return getMappings().get(value);
	}
}