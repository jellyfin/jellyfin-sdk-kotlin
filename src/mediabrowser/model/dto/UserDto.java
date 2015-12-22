package mediabrowser.model.dto;

import mediabrowser.model.configuration.*;
import mediabrowser.model.connect.*;
import mediabrowser.model.extensions.*;
import mediabrowser.model.users.*;

/** 
 Class UserDto
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, ID = {Id}, HasPassword = {HasPassword}")] public class UserDto : IHasPropertyChangedEvent, IItemDto, IHasServerId
public class UserDto implements IHasPropertyChangedEvent, IItemDto, IHasServerId
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
	 Gets or sets the name of the server.
	 This is not used by the server and is for client-side usage only.
	 
	 <value>The name of the server.</value>
	*/
	private String ServerName;
	public final String getServerName()
	{
		return ServerName;
	}
	public final void setServerName(String value)
	{
		ServerName = value;
	}

	/** 
	 Gets or sets the name of the connect user.
	 
	 <value>The name of the connect user.</value>
	*/
	private String ConnectUserName;
	public final String getConnectUserName()
	{
		return ConnectUserName;
	}
	public final void setConnectUserName(String value)
	{
		ConnectUserName = value;
	}
	/** 
	 Gets or sets the connect user identifier.
	 
	 <value>The connect user identifier.</value>
	*/
	private String ConnectUserId;
	public final String getConnectUserId()
	{
		return ConnectUserId;
	}
	public final void setConnectUserId(String value)
	{
		ConnectUserId = value;
	}
	/** 
	 Gets or sets the type of the connect link.
	 
	 <value>The type of the connect link.</value>
	*/
	private UserLinkType ConnectLinkType = null;
	public final UserLinkType getConnectLinkType()
	{
		return ConnectLinkType;
	}
	public final void setConnectLinkType(UserLinkType value)
	{
		ConnectLinkType = value;
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
	 Gets or sets the offline password.
	 
	 <value>The offline password.</value>
	*/
	private String OfflinePassword;
	public final String getOfflinePassword()
	{
		return OfflinePassword;
	}
	public final void setOfflinePassword(String value)
	{
		OfflinePassword = value;
	}

	/** 
	 Gets or sets the offline password salt.
	 
	 <value>The offline password salt.</value>
	*/
	private String OfflinePasswordSalt;
	public final String getOfflinePasswordSalt()
	{
		return OfflinePasswordSalt;
	}
	public final void setOfflinePasswordSalt(String value)
	{
		OfflinePasswordSalt = value;
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
	 Gets or sets a value indicating whether this instance has password.
	 
	 <value><c>true</c> if this instance has password; otherwise, <c>false</c>.</value>
	*/
	private boolean HasPassword;
	public final boolean getHasPassword()
	{
		return HasPassword;
	}
	public final void setHasPassword(boolean value)
	{
		HasPassword = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has configured password.
	 
	 <value><c>true</c> if this instance has configured password; otherwise, <c>false</c>.</value>
	*/
	private boolean HasConfiguredPassword;
	public final boolean getHasConfiguredPassword()
	{
		return HasConfiguredPassword;
	}
	public final void setHasConfiguredPassword(boolean value)
	{
		HasConfiguredPassword = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance has configured easy password.
	 
	 <value><c>true</c> if this instance has configured easy password; otherwise, <c>false</c>.</value>
	*/
	private boolean HasConfiguredEasyPassword;
	public final boolean getHasConfiguredEasyPassword()
	{
		return HasConfiguredEasyPassword;
	}
	public final void setHasConfiguredEasyPassword(boolean value)
	{
		HasConfiguredEasyPassword = value;
	}

	/** 
	 Gets or sets the last login date.
	 
	 <value>The last login date.</value>
	*/
	private java.util.Date LastLoginDate = null;
	public final java.util.Date getLastLoginDate()
	{
		return LastLoginDate;
	}
	public final void setLastLoginDate(java.util.Date value)
	{
		LastLoginDate = value;
	}

	/** 
	 Gets or sets the last activity date.
	 
	 <value>The last activity date.</value>
	*/
	private java.util.Date LastActivityDate = null;
	public final java.util.Date getLastActivityDate()
	{
		return LastActivityDate;
	}
	public final void setLastActivityDate(java.util.Date value)
	{
		LastActivityDate = value;
	}

	/** 
	 Gets or sets the configuration.
	 
	 <value>The configuration.</value>
	*/
	private UserConfiguration Configuration;
	public final UserConfiguration getConfiguration()
	{
		return Configuration;
	}
	public final void setConfiguration(UserConfiguration value)
	{
		Configuration = value;
	}

	/** 
	 Gets or sets the policy.
	 
	 <value>The policy.</value>
	*/
	private UserPolicy Policy;
	public final UserPolicy getPolicy()
	{
		return Policy;
	}
	public final void setPolicy(UserPolicy value)
	{
		Policy = value;
	}

	/** 
	 Gets or sets the primary image aspect ratio.
	 
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
		setPolicy(new UserPolicy());
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