package mediabrowser.model.entities;

import mediabrowser.model.dto.*;

/** 
 This is a stub class containing only basic information about an item
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, ID = {Id}, Type = {Type}")] public class BaseItemInfo
public class BaseItemInfo
{
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
	 Gets or sets the id.
	 
	 <value>The id.</value>
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
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
	 Gets or sets the primary image tag.
	 
	 <value>The primary image tag.</value>
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
	 Gets or sets the primary image item identifier.
	 
	 <value>The primary image item identifier.</value>
	*/
	private String PrimaryImageItemId;
	public final String getPrimaryImageItemId()
	{
		return PrimaryImageItemId;
	}
	public final void setPrimaryImageItemId(String value)
	{
		PrimaryImageItemId = value;
	}

	/** 
	 Gets or sets the logo image tag.
	 
	 <value>The logo image tag.</value>
	*/
	private String LogoImageTag;
	public final String getLogoImageTag()
	{
		return LogoImageTag;
	}
	public final void setLogoImageTag(String value)
	{
		LogoImageTag = value;
	}

	/** 
	 Gets or sets the logo item identifier.
	 
	 <value>The logo item identifier.</value>
	*/
	private String LogoItemId;
	public final String getLogoItemId()
	{
		return LogoItemId;
	}
	public final void setLogoItemId(String value)
	{
		LogoItemId = value;
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
	 Gets or sets the thumb item identifier.
	 
	 <value>The thumb item identifier.</value>
	*/
	private String ThumbItemId;
	public final String getThumbItemId()
	{
		return ThumbItemId;
	}
	public final void setThumbItemId(String value)
	{
		ThumbItemId = value;
	}

	/** 
	 Gets or sets the thumb image tag.
	 
	 <value>The thumb image tag.</value>
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
	 Gets or sets the thumb item identifier.
	 
	 <value>The thumb item identifier.</value>
	*/
	private String BackdropItemId;
	public final String getBackdropItemId()
	{
		return BackdropItemId;
	}
	public final void setBackdropItemId(String value)
	{
		BackdropItemId = value;
	}

	/** 
	 Gets or sets the premiere date.
	 
	 <value>The premiere date.</value>
	*/
	private java.util.Date PremiereDate = null;
	public final java.util.Date getPremiereDate()
	{
		return PremiereDate;
	}
	public final void setPremiereDate(java.util.Date value)
	{
		PremiereDate = value;
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
	 Gets or sets the index number end.
	 
	 <value>The index number end.</value>
	*/
	private Integer IndexNumberEnd = null;
	public final Integer getIndexNumberEnd()
	{
		return IndexNumberEnd;
	}
	public final void setIndexNumberEnd(Integer value)
	{
		IndexNumberEnd = value;
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
	 Gets or sets the name of the series.
	 
	 <value>The name of the series.</value>
	*/
	private String SeriesName;
	public final String getSeriesName()
	{
		return SeriesName;
	}
	public final void setSeriesName(String value)
	{
		SeriesName = value;
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
	 Gets or sets the artists.
	 
	 <value>The artists.</value>
	*/
	private java.util.ArrayList<String> Artists;
	public final java.util.ArrayList<String> getArtists()
	{
		return Artists;
	}
	public final void setArtists(java.util.ArrayList<String> value)
	{
		Artists = value;
	}

	/** 
	 Gets or sets the media streams.
	 
	 <value>The media streams.</value>
	*/
	private java.util.ArrayList<MediaStream> MediaStreams;
	public final java.util.ArrayList<MediaStream> getMediaStreams()
	{
		return MediaStreams;
	}
	public final void setMediaStreams(java.util.ArrayList<MediaStream> value)
	{
		MediaStreams = value;
	}

	/** 
	 Gets or sets the chapter images item identifier.
	 
	 <value>The chapter images item identifier.</value>
	*/
	private String ChapterImagesItemId;
	public final String getChapterImagesItemId()
	{
		return ChapterImagesItemId;
	}
	public final void setChapterImagesItemId(String value)
	{
		ChapterImagesItemId = value;
	}

	/** 
	 Gets or sets the chapters.
	 
	 <value>The chapters.</value>
	*/
	private java.util.ArrayList<ChapterInfoDto> Chapters;
	public final java.util.ArrayList<ChapterInfoDto> getChapters()
	{
		return Chapters;
	}
	public final void setChapters(java.util.ArrayList<ChapterInfoDto> value)
	{
		Chapters = value;
	}

	/** 
	 Gets a value indicating whether this instance has primary image.
	 
	 <value><c>true</c> if this instance has primary image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasPrimaryImage
	public final boolean getHasPrimaryImage()
	{
		return getPrimaryImageTag() != null;
	}

	public BaseItemInfo()
	{
		setArtists(new java.util.ArrayList<String>());
		setMediaStreams(new java.util.ArrayList<MediaStream>());
		setChapters(new java.util.ArrayList<ChapterInfoDto>());
	}
}