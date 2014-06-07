package MediaBrowser.Model.LiveTv;

import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Library.*;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, StartTime = {StartDate}, EndTime = {EndDate}")] public class ProgramInfoDto : INotifyPropertyChanged, IItemDto
public class ProgramInfoDto implements INotifyPropertyChanged, IItemDto
{
	/** 
	 Id of the program.
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
	 Gets or sets the primary image aspect ratio.
	 
	 <value>The primary image aspect ratio.</value>
	*/
	private Double privatePrimaryImageAspectRatio = new Double();
	public final Double getPrimaryImageAspectRatio()
	{
		return privatePrimaryImageAspectRatio;
	}
	public final void setPrimaryImageAspectRatio(Double value)
	{
		privatePrimaryImageAspectRatio = value;
	}

	/** 
	 Gets or sets the original primary image aspect ratio.
	 
	 <value>The original primary image aspect ratio.</value>
	*/
	private Double privateOriginalPrimaryImageAspectRatio = new Double();
	public final Double getOriginalPrimaryImageAspectRatio()
	{
		return privateOriginalPrimaryImageAspectRatio;
	}
	public final void setOriginalPrimaryImageAspectRatio(Double value)
	{
		privateOriginalPrimaryImageAspectRatio = value;
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
	 Gets or sets the timer identifier.
	 
	 <value>The timer identifier.</value>
	*/
	private String privateTimerId;
	public final String getTimerId()
	{
		return privateTimerId;
	}
	public final void setTimerId(String value)
	{
		privateTimerId = value;
	}

	/** 
	 Gets or sets the series timer identifier.
	 
	 <value>The series timer identifier.</value>
	*/
	private String privateSeriesTimerId;
	public final String getSeriesTimerId()
	{
		return privateSeriesTimerId;
	}
	public final void setSeriesTimerId(String value)
	{
		privateSeriesTimerId = value;
	}

	/** 
	 Gets or sets the external identifier.
	 
	 <value>The external identifier.</value>
	*/
	private String privateExternalId;
	public final String getExternalId()
	{
		return privateExternalId;
	}
	public final void setExternalId(String value)
	{
		privateExternalId = value;
	}

	/** 
	 Gets or sets the channel identifier.
	 
	 <value>The channel identifier.</value>
	*/
	private String privateChannelId;
	public final String getChannelId()
	{
		return privateChannelId;
	}
	public final void setChannelId(String value)
	{
		privateChannelId = value;
	}

	/** 
	 Gets or sets the channel primary image tag.
	 
	 <value>The channel primary image tag.</value>
	*/
	private String privateChannelPrimaryImageTag;
	public final String getChannelPrimaryImageTag()
	{
		return privateChannelPrimaryImageTag;
	}
	public final void setChannelPrimaryImageTag(String value)
	{
		privateChannelPrimaryImageTag = value;
	}

	/** 
	 Gets or sets the play access.
	 
	 <value>The play access.</value>
	*/
	private PlayAccess privatePlayAccess = getPlayAccess().values()[0];
	public final PlayAccess getPlayAccess()
	{
		return privatePlayAccess;
	}
	public final void setPlayAccess(PlayAccess value)
	{
		privatePlayAccess = value;
	}

	/** 
	 Gets or sets the name of the channel.
	 
	 <value>The name of the channel.</value>
	*/
	private String privateChannelName;
	public final String getChannelName()
	{
		return privateChannelName;
	}
	public final void setChannelName(String value)
	{
		privateChannelName = value;
	}

	/** 
	 Gets or sets the community rating.
	 
	 <value>The community rating.</value>
	*/
	private Float privateCommunityRating = new Float();
	public final Float getCommunityRating()
	{
		return privateCommunityRating;
	}
	public final void setCommunityRating(Float value)
	{
		privateCommunityRating = value;
	}

	/** 
	 Gets or sets the official rating.
	 
	 <value>The official rating.</value>
	*/
	private String privateOfficialRating;
	public final String getOfficialRating()
	{
		return privateOfficialRating;
	}
	public final void setOfficialRating(String value)
	{
		privateOfficialRating = value;
	}

	/** 
	 Gets or sets the name of the service.
	 
	 <value>The name of the service.</value>
	*/
	private String privateServiceName;
	public final String getServiceName()
	{
		return privateServiceName;
	}
	public final void setServiceName(String value)
	{
		privateServiceName = value;
	}

	/** 
	 Name of the program
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
	 Overview of the recording.
	*/
	private String privateOverview;
	public final String getOverview()
	{
		return privateOverview;
	}
	public final void setOverview(String value)
	{
		privateOverview = value;
	}

	/** 
	 The start date of the program, in UTC.
	*/
	private java.util.Date privateStartDate = new java.util.Date(0);
	public final java.util.Date getStartDate()
	{
		return privateStartDate;
	}
	public final void setStartDate(java.util.Date value)
	{
		privateStartDate = value;
	}

	/** 
	 The end date of the program, in UTC.
	*/
	private java.util.Date privateEndDate = new java.util.Date(0);
	public final java.util.Date getEndDate()
	{
		return privateEndDate;
	}
	public final void setEndDate(java.util.Date value)
	{
		privateEndDate = value;
	}

	/** 
	 Genre of the program.
	*/
	private java.util.ArrayList<String> privateGenres;
	public final java.util.ArrayList<String> getGenres()
	{
		return privateGenres;
	}
	public final void setGenres(java.util.ArrayList<String> value)
	{
		privateGenres = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is hd.
	 
	 <value><c>true</c> if this instance is hd; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsHD = new Boolean();
	public final Boolean getIsHD()
	{
		return privateIsHD;
	}
	public final void setIsHD(Boolean value)
	{
		privateIsHD = value;
	}

	/** 
	 Gets or sets the audio.
	 
	 <value>The audio.</value>
	*/
	private ProgramAudio privateAudio = new ProgramAudio();
	public final ProgramAudio getAudio()
	{
		return privateAudio;
	}
	public final void setAudio(ProgramAudio value)
	{
		privateAudio = value;
	}

	/** 
	 Gets or sets the original air date.
	 
	 <value>The original air date.</value>
	*/
	private java.util.Date privateOriginalAirDate = new java.util.Date();
	public final java.util.Date getOriginalAirDate()
	{
		return privateOriginalAirDate;
	}
	public final void setOriginalAirDate(java.util.Date value)
	{
		privateOriginalAirDate = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is repeat.
	 
	 <value><c>true</c> if this instance is repeat; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsRepeat;
	public final boolean getIsRepeat()
	{
		return privateIsRepeat;
	}
	public final void setIsRepeat(boolean value)
	{
		privateIsRepeat = value;
	}

	/** 
	 Gets or sets the episode title.
	 
	 <value>The episode title.</value>
	*/
	private String privateEpisodeTitle;
	public final String getEpisodeTitle()
	{
		return privateEpisodeTitle;
	}
	public final void setEpisodeTitle(String value)
	{
		privateEpisodeTitle = value;
	}

	/** 
	 Gets or sets the image tags.
	 
	 <value>The image tags.</value>
	*/
	private java.util.HashMap<ImageType, String> privateImageTags;
	public final java.util.HashMap<ImageType, String> getImageTags()
	{
		return privateImageTags;
	}
	public final void setImageTags(java.util.HashMap<ImageType, String> value)
	{
		privateImageTags = value;
	}

	/** 
	 Gets or sets the user data.
	 
	 <value>The user data.</value>
	*/
	private UserItemDataDto privateUserData;
	public final UserItemDataDto getUserData()
	{
		return privateUserData;
	}
	public final void setUserData(UserItemDataDto value)
	{
		privateUserData = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is movie.
	 
	 <value><c>true</c> if this instance is movie; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsMovie;
	public final boolean getIsMovie()
	{
		return privateIsMovie;
	}
	public final void setIsMovie(boolean value)
	{
		privateIsMovie = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is sports.
	 
	 <value><c>true</c> if this instance is sports; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsSports;
	public final boolean getIsSports()
	{
		return privateIsSports;
	}
	public final void setIsSports(boolean value)
	{
		privateIsSports = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is series.
	 
	 <value><c>true</c> if this instance is series; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsSeries;
	public final boolean getIsSeries()
	{
		return privateIsSeries;
	}
	public final void setIsSeries(boolean value)
	{
		privateIsSeries = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is live.
	 
	 <value><c>true</c> if this instance is live; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsLive;
	public final boolean getIsLive()
	{
		return privateIsLive;
	}
	public final void setIsLive(boolean value)
	{
		privateIsLive = value;
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
	private Long privateRunTimeTicks = new Long();
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is news.
	 
	 <value><c>true</c> if this instance is news; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsNews;
	public final boolean getIsNews()
	{
		return privateIsNews;
	}
	public final void setIsNews(boolean value)
	{
		privateIsNews = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is kids.
	 
	 <value><c>true</c> if this instance is kids; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsKids;
	public final boolean getIsKids()
	{
		return privateIsKids;
	}
	public final void setIsKids(boolean value)
	{
		privateIsKids = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is premiere.
	 
	 <value><c>true</c> if this instance is premiere; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsPremiere;
	public final boolean getIsPremiere()
	{
		return privateIsPremiere;
	}
	public final void setIsPremiere(boolean value)
	{
		privateIsPremiere = value;
	}

	/** 
	 Gets a value indicating whether this instance has primary image.
	 
	 <value><c>true</c> if this instance has primary image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasPrimaryImage
	public final boolean getHasPrimaryImage()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Primary);
	}

	public ProgramInfoDto()
	{
		setGenres(new java.util.ArrayList<String>());
		setImageTags(new java.util.HashMap<ImageType, String>());
	}

//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;
}