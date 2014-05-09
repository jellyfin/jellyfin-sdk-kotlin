package MediaBrowser.Model.LiveTv;

import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Library.*;

/** 
 Class ChannelInfoDto
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, Number = {Number}")] public class ChannelInfoDto : INotifyPropertyChanged, IItemDto
public class ChannelInfoDto implements INotifyPropertyChanged, IItemDto
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
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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

	private java.util.ArrayList<MediaSourceInfo> privateMediaSources;
	public final java.util.ArrayList<MediaSourceInfo> getMediaSources()
	{
		return privateMediaSources;
	}
	public final void setMediaSources(java.util.ArrayList<MediaSourceInfo> value)
	{
		privateMediaSources = value;
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
	 Gets or sets the number.
	 
	 <value>The number.</value>
	*/
	private String privateNumber;
	public final String getNumber()
	{
		return privateNumber;
	}
	public final void setNumber(String value)
	{
		privateNumber = value;
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
	 Gets or sets the type of the channel.
	 
	 <value>The type of the channel.</value>
	*/
	private ChannelType privateChannelType = getChannelType().values()[0];
	public final ChannelType getChannelType()
	{
		return privateChannelType;
	}
	public final void setChannelType(ChannelType value)
	{
		privateChannelType = value;
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
	 Gets or sets the now playing program.
	 
	 <value>The now playing program.</value>
	*/
	private ProgramInfoDto privateCurrentProgram;
	public final ProgramInfoDto getCurrentProgram()
	{
		return privateCurrentProgram;
	}
	public final void setCurrentProgram(ProgramInfoDto value)
	{
		privateCurrentProgram = value;
	}

	/** 
	 Gets or sets the primary image aspect ratio, after image enhancements.
	 
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
	 Gets or sets the primary image aspect ratio, before image enhancements.
	 
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