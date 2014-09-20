package MediaBrowser.Model.Dto;

import MediaBrowser.Model.Configuration.*;
import MediaBrowser.Model.Connect.*;
import MediaBrowser.Model.Extensions.*;

/** 
 Class UserDto
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, ID = {Id}, HasPassword = {HasPassword}")] public class UserDto : IHasPropertyChangedEvent, IItemDto
public class UserDto implements IHasPropertyChangedEvent, IItemDto
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
	 Gets or sets the name of the connect user.
	 
	 <value>The name of the connect user.</value>
	*/
	private String privateConnectUserName;
	public final String getConnectUserName()
	{
		return privateConnectUserName;
	}
	public final void setConnectUserName(String value)
	{
		privateConnectUserName = value;
	}
	/** 
	 Gets or sets the connect user identifier.
	 
	 <value>The connect user identifier.</value>
	*/
	private String privateConnectUserId;
	public final String getConnectUserId()
	{
		return privateConnectUserId;
	}
	public final void setConnectUserId(String value)
	{
		privateConnectUserId = value;
	}
	/** 
	 Gets or sets the type of the connect link.
	 
	 <value>The type of the connect link.</value>
	*/
	private UserLinkType privateConnectLinkType = UserLinkType.values()[0];
	public final UserLinkType getConnectLinkType()
	{
		return privateConnectLinkType;
	}
	public final void setConnectLinkType(UserLinkType value)
	{
		privateConnectLinkType = value;
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
	 Gets or sets a value indicating whether this instance has password.
	 
	 <value><c>true</c> if this instance has password; otherwise, <c>false</c>.</value>
	*/
	private boolean privateHasPassword;
	public final boolean getHasPassword()
	{
		return privateHasPassword;
	}
	public final void setHasPassword(boolean value)
	{
		privateHasPassword = value;
	}

	private boolean privateHasConfiguredPassword;
	public final boolean getHasConfiguredPassword()
	{
		return privateHasConfiguredPassword;
	}
	public final void setHasConfiguredPassword(boolean value)
	{
		privateHasConfiguredPassword = value;
	}

	/** 
	 Gets or sets the last login date.
	 
	 <value>The last login date.</value>
	*/
	private java.util.Date privateLastLoginDate = null;
	public final java.util.Date getLastLoginDate()
	{
		return privateLastLoginDate;
	}
	public final void setLastLoginDate(java.util.Date value)
	{
		privateLastLoginDate = value;
	}

	/** 
	 Gets or sets the last activity date.
	 
	 <value>The last activity date.</value>
	*/
	private java.util.Date privateLastActivityDate = null;
	public final java.util.Date getLastActivityDate()
	{
		return privateLastActivityDate;
	}
	public final void setLastActivityDate(java.util.Date value)
	{
		privateLastActivityDate = value;
	}

	/** 
	 Gets or sets the configuration.
	 
	 <value>The configuration.</value>
	*/
	private UserConfiguration privateConfiguration;
	public final UserConfiguration getConfiguration()
	{
		return privateConfiguration;
	}
	public final void setConfiguration(UserConfiguration value)
	{
		privateConfiguration = value;
	}

	/** 
	 Gets or sets the primary image aspect ratio.
	 
	 <value>The primary image aspect ratio.</value>
	*/
	private Double privatePrimaryImageAspectRatio = null;
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
	private Double privateOriginalPrimaryImageAspectRatio = null;
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
		return getPrimaryImageTag() != null;
	}

	/** 
	 Initializes a new instance of the <see cref="UserDto"/> class.
	*/
	public UserDto()
	{
		setConfiguration(new UserConfiguration());
	}

	/** 
	 Occurs when [property changed].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;

	@Override
	public String toString()
	{
		String tempVar = getName();
		return (tempVar != null) ? tempVar : super.toString();
	}
}