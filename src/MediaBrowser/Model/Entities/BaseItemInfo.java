package MediaBrowser.Model.Entities;

import MediaBrowser.Model.Dto.*;

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
	 Gets or sets the id.
	 
	 <value>The id.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
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
	 Gets or sets the primary image tag.
	 
	 <value>The primary image tag.</value>
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
	 Gets or sets the primary image item identifier.
	 
	 <value>The primary image item identifier.</value>
	*/
	private String privatePrimaryImageItemId;
	public final String getPrimaryImageItemId()
	{
		return privatePrimaryImageItemId;
	}
	public final void setPrimaryImageItemId(String value)
	{
		privatePrimaryImageItemId = value;
	}

	/** 
	 Gets or sets the logo image tag.
	 
	 <value>The logo image tag.</value>
	*/
	private String privateLogoImageTag;
	public final String getLogoImageTag()
	{
		return privateLogoImageTag;
	}
	public final void setLogoImageTag(String value)
	{
		privateLogoImageTag = value;
	}

	/** 
	 Gets or sets the logo item identifier.
	 
	 <value>The logo item identifier.</value>
	*/
	private String privateLogoItemId;
	public final String getLogoItemId()
	{
		return privateLogoItemId;
	}
	public final void setLogoItemId(String value)
	{
		privateLogoItemId = value;
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
	 Gets or sets the thumb item identifier.
	 
	 <value>The thumb item identifier.</value>
	*/
	private String privateThumbItemId;
	public final String getThumbItemId()
	{
		return privateThumbItemId;
	}
	public final void setThumbItemId(String value)
	{
		privateThumbItemId = value;
	}

	/** 
	 Gets or sets the thumb image tag.
	 
	 <value>The thumb image tag.</value>
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
	 Gets or sets the thumb item identifier.
	 
	 <value>The thumb item identifier.</value>
	*/
	private String privateBackdropItemId;
	public final String getBackdropItemId()
	{
		return privateBackdropItemId;
	}
	public final void setBackdropItemId(String value)
	{
		privateBackdropItemId = value;
	}

	/** 
	 Gets or sets the premiere date.
	 
	 <value>The premiere date.</value>
	*/
	private java.util.Date privatePremiereDate = null;
	public final java.util.Date getPremiereDate()
	{
		return privatePremiereDate;
	}
	public final void setPremiereDate(java.util.Date value)
	{
		privatePremiereDate = value;
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
	 Gets or sets the index number end.
	 
	 <value>The index number end.</value>
	*/
	private Integer privateIndexNumberEnd = null;
	public final Integer getIndexNumberEnd()
	{
		return privateIndexNumberEnd;
	}
	public final void setIndexNumberEnd(Integer value)
	{
		privateIndexNumberEnd = value;
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
	 Gets or sets the name of the series.
	 
	 <value>The name of the series.</value>
	*/
	private String privateSeriesName;
	public final String getSeriesName()
	{
		return privateSeriesName;
	}
	public final void setSeriesName(String value)
	{
		privateSeriesName = value;
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
	 Gets or sets the artists.
	 
	 <value>The artists.</value>
	*/
	private java.util.ArrayList<String> privateArtists;
	public final java.util.ArrayList<String> getArtists()
	{
		return privateArtists;
	}
	public final void setArtists(java.util.ArrayList<String> value)
	{
		privateArtists = value;
	}

	/** 
	 Gets or sets the media streams.
	 
	 <value>The media streams.</value>
	*/
	private java.util.ArrayList<MediaStream> privateMediaStreams;
	public final java.util.ArrayList<MediaStream> getMediaStreams()
	{
		return privateMediaStreams;
	}
	public final void setMediaStreams(java.util.ArrayList<MediaStream> value)
	{
		privateMediaStreams = value;
	}

	/** 
	 Gets or sets the chapter images item identifier.
	 
	 <value>The chapter images item identifier.</value>
	*/
	private String privateChapterImagesItemId;
	public final String getChapterImagesItemId()
	{
		return privateChapterImagesItemId;
	}
	public final void setChapterImagesItemId(String value)
	{
		privateChapterImagesItemId = value;
	}

	/** 
	 Gets or sets the chapters.
	 
	 <value>The chapters.</value>
	*/
	private java.util.ArrayList<ChapterInfoDto> privateChapters;
	public final java.util.ArrayList<ChapterInfoDto> getChapters()
	{
		return privateChapters;
	}
	public final void setChapters(java.util.ArrayList<ChapterInfoDto> value)
	{
		privateChapters = value;
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