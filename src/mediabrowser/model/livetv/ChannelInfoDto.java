package mediabrowser.model.livetv;

import mediabrowser.model.dto.*;
import mediabrowser.model.entities.*;
import mediabrowser.model.extensions.*;
import mediabrowser.model.library.*;

/** 
 Class ChannelInfoDto
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, Number = {Number}")] public class ChannelInfoDto : IHasPropertyChangedEvent, IItemDto, IHasServerId
public class ChannelInfoDto implements IHasPropertyChangedEvent, IItemDto, IHasServerId
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
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
	*/
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}

	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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
	 Gets or sets the external identifier.
	 
	 <value>The external identifier.</value>
	*/
	private String ExternalId;
	public final String getExternalId()
	{
		return ExternalId;
	}
	public final void setExternalId(String value)
	{
		ExternalId = value;
	}

	/** 
	 Gets or sets the media sources.
	 
	 <value>The media sources.</value>
	*/
	private java.util.ArrayList<MediaSourceInfo> MediaSources;
	public final java.util.ArrayList<MediaSourceInfo> getMediaSources()
	{
		return MediaSources;
	}
	public final void setMediaSources(java.util.ArrayList<MediaSourceInfo> value)
	{
		MediaSources = value;
	}

	/** 
	 Gets or sets the image tags.
	 
	 <value>The image tags.</value>
	*/
	private java.util.HashMap<ImageType, String> ImageTags;
	public final java.util.HashMap<ImageType, String> getImageTags()
	{
		return ImageTags;
	}
	public final void setImageTags(java.util.HashMap<ImageType, String> value)
	{
		ImageTags = value;
	}

	/** 
	 Gets or sets the number.
	 
	 <value>The number.</value>
	*/
	private String Number;
	public final String getNumber()
	{
		return Number;
	}
	public final void setNumber(String value)
	{
		Number = value;
	}

	/** 
	 Gets or sets the play access.
	 
	 <value>The play access.</value>
	*/
	private PlayAccess PlayAccess = getPlayAccess().values()[0];
	public final PlayAccess getPlayAccess()
	{
		return PlayAccess;
	}
	public final void setPlayAccess(PlayAccess value)
	{
		PlayAccess = value;
	}

	/** 
	 Gets or sets the name of the service.
	 
	 <value>The name of the service.</value>
	*/
	private String ServiceName;
	public final String getServiceName()
	{
		return ServiceName;
	}
	public final void setServiceName(String value)
	{
		ServiceName = value;
	}

	/** 
	 Gets or sets the type of the channel.
	 
	 <value>The type of the channel.</value>
	*/
	private ChannelType ChannelType = getChannelType().values()[0];
	public final ChannelType getChannelType()
	{
		return ChannelType;
	}
	public final void setChannelType(ChannelType value)
	{
		ChannelType = value;
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
	 Gets or sets the user data.
	 
	 <value>The user data.</value>
	*/
	private UserItemDataDto UserData;
	public final UserItemDataDto getUserData()
	{
		return UserData;
	}
	public final void setUserData(UserItemDataDto value)
	{
		UserData = value;
	}

	/** 
	 Gets or sets the now playing program.
	 
	 <value>The now playing program.</value>
	*/
	private BaseItemDto CurrentProgram;
	public final BaseItemDto getCurrentProgram()
	{
		return CurrentProgram;
	}
	public final void setCurrentProgram(BaseItemDto value)
	{
		CurrentProgram = value;
	}

	/** 
	 Gets or sets the primary image aspect ratio, after image enhancements.
	 
	 <value>The primary image aspect ratio.</value>
	*/
	private Double PrimaryImageAspectRatio = null;
	public final Double getPrimaryImageAspectRatio()
	{
		return PrimaryImageAspectRatio;
	}
	public final void setPrimaryImageAspectRatio(Double value)
	{
		PrimaryImageAspectRatio = value;
	}

	/** 
	 Gets or sets the primary image aspect ratio, before image enhancements.
	 
	 <value>The original primary image aspect ratio.</value>
	*/
	private Double OriginalPrimaryImageAspectRatio = null;
	public final Double getOriginalPrimaryImageAspectRatio()
	{
		return OriginalPrimaryImageAspectRatio;
	}
	public final void setOriginalPrimaryImageAspectRatio(Double value)
	{
		OriginalPrimaryImageAspectRatio = value;
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

	public ChannelInfoDto()
	{
		setImageTags(new java.util.HashMap<ImageType, String>());
		setMediaSources(new java.util.ArrayList<MediaSourceInfo>());
	}

//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;
}