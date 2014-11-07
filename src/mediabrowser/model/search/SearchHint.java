package mediabrowser.model.search;

/** 
 Class SearchHintResult
*/
public class SearchHint
{
	/** 
	 Gets or sets the item id.
	 
	 <value>The item id.</value>
	*/
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}

	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	/** 
	 Gets or sets the matched term.
	 
	 <value>The matched term.</value>
	*/
	private String MatchedTerm;
	public final String getMatchedTerm()
	{
		return MatchedTerm;
	}
	public final void setMatchedTerm(String value)
	{
		MatchedTerm = value;
	}

	/** 
	 Gets or sets the index number.
	 
	 <value>The index number.</value>
	*/
	private Integer IndexNumber = null;
	public final Integer getIndexNumber()
	{
		return IndexNumber;
	}
	public final void setIndexNumber(Integer value)
	{
		IndexNumber = value;
	}

	/** 
	 Gets or sets the production year.
	 
	 <value>The production year.</value>
	*/
	private Integer ProductionYear = null;
	public final Integer getProductionYear()
	{
		return ProductionYear;
	}
	public final void setProductionYear(Integer value)
	{
		ProductionYear = value;
	}

	/** 
	 Gets or sets the parent index number.
	 
	 <value>The parent index number.</value>
	*/
	private Integer ParentIndexNumber = null;
	public final Integer getParentIndexNumber()
	{
		return ParentIndexNumber;
	}
	public final void setParentIndexNumber(Integer value)
	{
		ParentIndexNumber = value;
	}

	/** 
	 Gets or sets the image tag.
	 
	 <value>The image tag.</value>
	*/
	private String PrimaryImageTag;
	public final String getPrimaryImageTag()
	{
		return PrimaryImageTag;
	}
	public final void setPrimaryImageTag(String value)
	{
		PrimaryImageTag = value;
	}

	/** 
	 Gets or sets the thumb image tag.
	 
	 <value>The thumb image tag.</value>
	*/
	private String ThumbImageTag;
	public final String getThumbImageTag()
	{
		return ThumbImageTag;
	}
	public final void setThumbImageTag(String value)
	{
		ThumbImageTag = value;
	}

	/** 
	 Gets or sets the thumb image item identifier.
	 
	 <value>The thumb image item identifier.</value>
	*/
	private String ThumbImageItemId;
	public final String getThumbImageItemId()
	{
		return ThumbImageItemId;
	}
	public final void setThumbImageItemId(String value)
	{
		ThumbImageItemId = value;
	}

	/** 
	 Gets or sets the backdrop image tag.
	 
	 <value>The backdrop image tag.</value>
	*/
	private String BackdropImageTag;
	public final String getBackdropImageTag()
	{
		return BackdropImageTag;
	}
	public final void setBackdropImageTag(String value)
	{
		BackdropImageTag = value;
	}

	/** 
	 Gets or sets the backdrop image item identifier.
	 
	 <value>The backdrop image item identifier.</value>
	*/
	private String BackdropImageItemId;
	public final String getBackdropImageItemId()
	{
		return BackdropImageItemId;
	}
	public final void setBackdropImageItemId(String value)
	{
		BackdropImageItemId = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
	}

	/** 
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long RunTimeTicks = null;
	public final Long getRunTimeTicks()
	{
		return RunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		RunTimeTicks = value;
	}

	/** 
	 Gets or sets the type of the media.
	 
	 <value>The type of the media.</value>
	*/
	private String MediaType;
	public final String getMediaType()
	{
		return MediaType;
	}
	public final void setMediaType(String value)
	{
		MediaType = value;
	}

	/** 
	 Gets or sets the display type of the media.
	 
	 <value>The display type of the media.</value>
	*/
	private String DisplayMediaType;
	public final String getDisplayMediaType()
	{
		return DisplayMediaType;
	}
	public final void setDisplayMediaType(String value)
	{
		DisplayMediaType = value;
	}

	/** 
	 Gets or sets the series.
	 
	 <value>The series.</value>
	*/
	private String Series;
	public final String getSeries()
	{
		return Series;
	}
	public final void setSeries(String value)
	{
		Series = value;
	}

	/** 
	 Gets or sets the album.
	 
	 <value>The album.</value>
	*/
	private String Album;
	public final String getAlbum()
	{
		return Album;
	}
	public final void setAlbum(String value)
	{
		Album = value;
	}

	/** 
	 Gets or sets the album artist.
	 
	 <value>The album artist.</value>
	*/
	private String AlbumArtist;
	public final String getAlbumArtist()
	{
		return AlbumArtist;
	}
	public final void setAlbumArtist(String value)
	{
		AlbumArtist = value;
	}

	/** 
	 Gets or sets the artists.
	 
	 <value>The artists.</value>
	*/
	private String[] Artists;
	public final String[] getArtists()
	{
		return Artists;
	}
	public final void setArtists(String[] value)
	{
		Artists = value;
	}

	/** 
	 Gets or sets the song count.
	 
	 <value>The song count.</value>
	*/
	private Integer SongCount = null;
	public final Integer getSongCount()
	{
		return SongCount;
	}
	public final void setSongCount(Integer value)
	{
		SongCount = value;
	}

	/** 
	 Gets or sets the episode count.
	 
	 <value>The episode count.</value>
	*/
	private Integer EpisodeCount = null;
	public final Integer getEpisodeCount()
	{
		return EpisodeCount;
	}
	public final void setEpisodeCount(Integer value)
	{
		EpisodeCount = value;
	}
}