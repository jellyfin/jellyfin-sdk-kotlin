package MediaBrowser.Model.Search;

/** 
 Class SearchHintResult
*/
public class SearchHint
{
	/** 
	 Gets or sets the item id.
	 
	 <value>The item id.</value>
	*/
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}

	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	/** 
	 Gets or sets the matched term.
	 
	 <value>The matched term.</value>
	*/
	private String privateMatchedTerm;
	public final String getMatchedTerm()
	{
		return privateMatchedTerm;
	}
	public final void setMatchedTerm(String value)
	{
		privateMatchedTerm = value;
	}

	/** 
	 Gets or sets the index number.
	 
	 <value>The index number.</value>
	*/
	private Integer privateIndexNumber = null;
	public final Integer getIndexNumber()
	{
		return privateIndexNumber;
	}
	public final void setIndexNumber(Integer value)
	{
		privateIndexNumber = value;
	}

	/** 
	 Gets or sets the production year.
	 
	 <value>The production year.</value>
	*/
	private Integer privateProductionYear = null;
	public final Integer getProductionYear()
	{
		return privateProductionYear;
	}
	public final void setProductionYear(Integer value)
	{
		privateProductionYear = value;
	}

	/** 
	 Gets or sets the parent index number.
	 
	 <value>The parent index number.</value>
	*/
	private Integer privateParentIndexNumber = null;
	public final Integer getParentIndexNumber()
	{
		return privateParentIndexNumber;
	}
	public final void setParentIndexNumber(Integer value)
	{
		privateParentIndexNumber = value;
	}

	/** 
	 Gets or sets the image tag.
	 
	 <value>The image tag.</value>
	*/
	private String privatePrimaryImageTag;
	public final String getPrimaryImageTag()
	{
		return privatePrimaryImageTag;
	}
	public final void setPrimaryImageTag(String value)
	{
		privatePrimaryImageTag = value;
	}

	/** 
	 Gets or sets the thumb image tag.
	 
	 <value>The thumb image tag.</value>
	*/
	private String privateThumbImageTag;
	public final String getThumbImageTag()
	{
		return privateThumbImageTag;
	}
	public final void setThumbImageTag(String value)
	{
		privateThumbImageTag = value;
	}

	/** 
	 Gets or sets the thumb image item identifier.
	 
	 <value>The thumb image item identifier.</value>
	*/
	private String privateThumbImageItemId;
	public final String getThumbImageItemId()
	{
		return privateThumbImageItemId;
	}
	public final void setThumbImageItemId(String value)
	{
		privateThumbImageItemId = value;
	}

	/** 
	 Gets or sets the backdrop image tag.
	 
	 <value>The backdrop image tag.</value>
	*/
	private String privateBackdropImageTag;
	public final String getBackdropImageTag()
	{
		return privateBackdropImageTag;
	}
	public final void setBackdropImageTag(String value)
	{
		privateBackdropImageTag = value;
	}

	/** 
	 Gets or sets the backdrop image item identifier.
	 
	 <value>The backdrop image item identifier.</value>
	*/
	private String privateBackdropImageItemId;
	public final String getBackdropImageItemId()
	{
		return privateBackdropImageItemId;
	}
	public final void setBackdropImageItemId(String value)
	{
		privateBackdropImageItemId = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private String privateType;
	public final String getType()
	{
		return privateType;
	}
	public final void setType(String value)
	{
		privateType = value;
	}

	/** 
	 Gets or sets the run time ticks.
	 
	 <value>The run time ticks.</value>
	*/
	private Long privateRunTimeTicks = null;
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

	/** 
	 Gets or sets the type of the media.
	 
	 <value>The type of the media.</value>
	*/
	private String privateMediaType;
	public final String getMediaType()
	{
		return privateMediaType;
	}
	public final void setMediaType(String value)
	{
		privateMediaType = value;
	}

	/** 
	 Gets or sets the display type of the media.
	 
	 <value>The display type of the media.</value>
	*/
	private String privateDisplayMediaType;
	public final String getDisplayMediaType()
	{
		return privateDisplayMediaType;
	}
	public final void setDisplayMediaType(String value)
	{
		privateDisplayMediaType = value;
	}

	/** 
	 Gets or sets the series.
	 
	 <value>The series.</value>
	*/
	private String privateSeries;
	public final String getSeries()
	{
		return privateSeries;
	}
	public final void setSeries(String value)
	{
		privateSeries = value;
	}

	/** 
	 Gets or sets the album.
	 
	 <value>The album.</value>
	*/
	private String privateAlbum;
	public final String getAlbum()
	{
		return privateAlbum;
	}
	public final void setAlbum(String value)
	{
		privateAlbum = value;
	}

	/** 
	 Gets or sets the album artist.
	 
	 <value>The album artist.</value>
	*/
	private String privateAlbumArtist;
	public final String getAlbumArtist()
	{
		return privateAlbumArtist;
	}
	public final void setAlbumArtist(String value)
	{
		privateAlbumArtist = value;
	}

	/** 
	 Gets or sets the artists.
	 
	 <value>The artists.</value>
	*/
	private String[] privateArtists;
	public final String[] getArtists()
	{
		return privateArtists;
	}
	public final void setArtists(String[] value)
	{
		privateArtists = value;
	}

	/** 
	 Gets or sets the song count.
	 
	 <value>The song count.</value>
	*/
	private Integer privateSongCount = null;
	public final Integer getSongCount()
	{
		return privateSongCount;
	}
	public final void setSongCount(Integer value)
	{
		privateSongCount = value;
	}

	/** 
	 Gets or sets the episode count.
	 
	 <value>The episode count.</value>
	*/
	private Integer privateEpisodeCount = null;
	public final Integer getEpisodeCount()
	{
		return privateEpisodeCount;
	}
	public final void setEpisodeCount(Integer value)
	{
		privateEpisodeCount = value;
	}
}