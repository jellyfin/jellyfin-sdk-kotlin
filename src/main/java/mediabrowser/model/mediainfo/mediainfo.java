package mediabrowser.model.mediainfo;

import mediabrowser.model.dto.*;
import mediabrowser.model.entities.*;

public class mediainfo extends MediaSourceInfo implements IHasProviderIds
{
	private java.util.ArrayList<ChapterInfo> Chapters;
	public final java.util.ArrayList<ChapterInfo> getChapters()
	{
		return Chapters;
	}
	public final void setChapters(java.util.ArrayList<ChapterInfo> value)
	{
		Chapters = value;
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
	 Gets or sets the album artists.
	 
	 <value>The album artists.</value>
	*/
	private java.util.ArrayList<String> AlbumArtists;
	public final java.util.ArrayList<String> getAlbumArtists()
	{
		return AlbumArtists;
	}
	public final void setAlbumArtists(java.util.ArrayList<String> value)
	{
		AlbumArtists = value;
	}
	/** 
	 Gets or sets the studios.
	 
	 <value>The studios.</value>
	*/
	private java.util.ArrayList<String> Studios;
	public final java.util.ArrayList<String> getStudios()
	{
		return Studios;
	}
	public final void setStudios(java.util.ArrayList<String> value)
	{
		Studios = value;
	}
	private java.util.ArrayList<String> Genres;
	public final java.util.ArrayList<String> getGenres()
	{
		return Genres;
	}
	public final void setGenres(java.util.ArrayList<String> value)
	{
		Genres = value;
	}
	private Integer IndexNumber = null;
	public final Integer getIndexNumber()
	{
		return IndexNumber;
	}
	public final void setIndexNumber(Integer value)
	{
		IndexNumber = value;
	}
	private Integer ParentIndexNumber = null;
	public final Integer getParentIndexNumber()
	{
		return ParentIndexNumber;
	}
	public final void setParentIndexNumber(Integer value)
	{
		ParentIndexNumber = value;
	}
	private Integer ProductionYear = null;
	public final Integer getProductionYear()
	{
		return ProductionYear;
	}
	public final void setProductionYear(Integer value)
	{
		ProductionYear = value;
	}
	private java.util.Date PremiereDate = null;
	public final java.util.Date getPremiereDate()
	{
		return PremiereDate;
	}
	public final void setPremiereDate(java.util.Date value)
	{
		PremiereDate = value;
	}
	private java.util.ArrayList<BaseItemPerson> People;
	public final java.util.ArrayList<BaseItemPerson> getPeople()
	{
		return People;
	}
	public final void setPeople(java.util.ArrayList<BaseItemPerson> value)
	{
		People = value;
	}
	private java.util.HashMap<String, String> ProviderIds;
	public final java.util.HashMap<String, String> getProviderIds()
	{
		return ProviderIds;
	}
	public final void setProviderIds(java.util.HashMap<String, String> value)
	{
		ProviderIds = value;
	}
	/** 
	 Gets or sets the official rating.
	 
	 <value>The official rating.</value>
	*/
	private String OfficialRating;
	public final String getOfficialRating()
	{
		return OfficialRating;
	}
	public final void setOfficialRating(String value)
	{
		OfficialRating = value;
	}
	/** 
	 Gets or sets the official rating description.
	 
	 <value>The official rating description.</value>
	*/
	private String OfficialRatingDescription;
	public final String getOfficialRatingDescription()
	{
		return OfficialRatingDescription;
	}
	public final void setOfficialRatingDescription(String value)
	{
		OfficialRatingDescription = value;
	}
	/** 
	 Gets or sets the overview.
	 
	 <value>The overview.</value>
	*/
	private String Overview;
	public final String getOverview()
	{
		return Overview;
	}
	public final void setOverview(String value)
	{
		Overview = value;
	}
	/** 
	 Gets or sets the short overview.
	 
	 <value>The short overview.</value>
	*/
	private String ShortOverview;
	public final String getShortOverview()
	{
		return ShortOverview;
	}
	public final void setShortOverview(String value)
	{
		ShortOverview = value;
	}

	public mediainfo()
	{
		setChapters(new java.util.ArrayList<ChapterInfo>());
		setArtists(new java.util.ArrayList<String>());
		setAlbumArtists(new java.util.ArrayList<String>());
		setStudios(new java.util.ArrayList<String>());
		setGenres(new java.util.ArrayList<String>());
		setPeople(new java.util.ArrayList<BaseItemPerson>());
		setProviderIds(new java.util.HashMap<String, String>());
	}
}