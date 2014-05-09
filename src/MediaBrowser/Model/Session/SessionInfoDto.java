package MediaBrowser.Model.Session;

import MediaBrowser.Model.Entities.*;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Client = {Client}, Username = {UserName}")] public class SessionInfoDto : INotifyPropertyChanged
public class SessionInfoDto implements INotifyPropertyChanged
{
	/** 
	 Gets or sets a value indicating whether this instance can seek.
	 
	 <value><c>true</c> if this instance can seek; otherwise, <c>false</c>.</value>
	*/
	private boolean privateCanSeek;
	public final boolean getCanSeek()
	{
		return privateCanSeek;
	}
	public final void setCanSeek(boolean value)
	{
		privateCanSeek = value;
	}

	/** 
	 Gets or sets the supported commands.
	 
	 <value>The supported commands.</value>
	*/
	private java.util.ArrayList<String> privateSupportedCommands;
	public final java.util.ArrayList<String> getSupportedCommands()
	{
		return privateSupportedCommands;
	}
	public final void setSupportedCommands(java.util.ArrayList<String> value)
	{
		privateSupportedCommands = value;
	}

	/** 
	 Gets or sets the remote end point.
	 
	 <value>The remote end point.</value>
	*/
	private String privateRemoteEndPoint;
	public final String getRemoteEndPoint()
	{
		return privateRemoteEndPoint;
	}
	public final void setRemoteEndPoint(String value)
	{
		privateRemoteEndPoint = value;
	}

	/** 
	 Gets or sets the queueable media types.
	 
	 <value>The queueable media types.</value>
	*/
	private java.util.ArrayList<String> privateQueueableMediaTypes;
	public final java.util.ArrayList<String> getQueueableMediaTypes()
	{
		return privateQueueableMediaTypes;
	}
	public final void setQueueableMediaTypes(java.util.ArrayList<String> value)
	{
		privateQueueableMediaTypes = value;
	}

	/** 
	 Gets or sets the playable media types.
	 
	 <value>The playable media types.</value>
	*/
	private java.util.ArrayList<String> privatePlayableMediaTypes;
	public final java.util.ArrayList<String> getPlayableMediaTypes()
	{
		return privatePlayableMediaTypes;
	}
	public final void setPlayableMediaTypes(java.util.ArrayList<String> value)
	{
		privatePlayableMediaTypes = value;
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
	 Gets or sets the user id.
	 
	 <value>The user id.</value>
	*/
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	/** 
	 Gets or sets the user primary image tag.
	 
	 <value>The user primary image tag.</value>
	*/
	private String privateUserPrimaryImageTag;
	public final String getUserPrimaryImageTag()
	{
		return privateUserPrimaryImageTag;
	}
	public final void setUserPrimaryImageTag(String value)
	{
		privateUserPrimaryImageTag = value;
	}

	/** 
	 Gets or sets the name of the user.
	 
	 <value>The name of the user.</value>
	*/
	private String privateUserName;
	public final String getUserName()
	{
		return privateUserName;
	}
	public final void setUserName(String value)
	{
		privateUserName = value;
	}

	/** 
	 Gets or sets the additional users present.
	 
	 <value>The additional users present.</value>
	*/
	private java.util.ArrayList<SessionUserInfo> privateAdditionalUsers;
	public final java.util.ArrayList<SessionUserInfo> getAdditionalUsers()
	{
		return privateAdditionalUsers;
	}
	public final void setAdditionalUsers(java.util.ArrayList<SessionUserInfo> value)
	{
		privateAdditionalUsers = value;
	}

	/** 
	 Gets or sets the application version.
	 
	 <value>The application version.</value>
	*/
	private String privateApplicationVersion;
	public final String getApplicationVersion()
	{
		return privateApplicationVersion;
	}
	public final void setApplicationVersion(String value)
	{
		privateApplicationVersion = value;
	}

	/** 
	 Gets or sets the type of the client.
	 
	 <value>The type of the client.</value>
	*/
	private String privateClient;
	public final String getClient()
	{
		return privateClient;
	}
	public final void setClient(String value)
	{
		privateClient = value;
	}

	/** 
	 Gets or sets the last activity date.
	 
	 <value>The last activity date.</value>
	*/
	private java.util.Date privateLastActivityDate = new java.util.Date(0);
	public final java.util.Date getLastActivityDate()
	{
		return privateLastActivityDate;
	}
	public final void setLastActivityDate(java.util.Date value)
	{
		privateLastActivityDate = value;
	}

	/** 
	 Gets or sets the now viewing item.
	 
	 <value>The now viewing item.</value>
	*/
	private BaseItemInfo privateNowViewingItem;
	public final BaseItemInfo getNowViewingItem()
	{
		return privateNowViewingItem;
	}
	public final void setNowViewingItem(BaseItemInfo value)
	{
		privateNowViewingItem = value;
	}

	/** 
	 Gets or sets the name of the device.
	 
	 <value>The name of the device.</value>
	*/
	private String privateDeviceName;
	public final String getDeviceName()
	{
		return privateDeviceName;
	}
	public final void setDeviceName(String value)
	{
		privateDeviceName = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is paused.
	 
	 <value><c>true</c> if this instance is paused; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsPaused;
	public final boolean getIsPaused()
	{
		return privateIsPaused;
	}
	public final void setIsPaused(boolean value)
	{
		privateIsPaused = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is muted.
	 
	 <value><c>true</c> if this instance is muted; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsMuted;
	public final boolean getIsMuted()
	{
		return privateIsMuted;
	}
	public final void setIsMuted(boolean value)
	{
		privateIsMuted = value;
	}

	/** 
	 Gets or sets the now playing item.
	 
	 <value>The now playing item.</value>
	*/
	private BaseItemInfo privateNowPlayingItem;
	public final BaseItemInfo getNowPlayingItem()
	{
		return privateNowPlayingItem;
	}
	public final void setNowPlayingItem(BaseItemInfo value)
	{
		privateNowPlayingItem = value;
	}

	/** 
	 Gets or sets the now playing position ticks.
	 
	 <value>The now playing position ticks.</value>
	*/
	private Long privateNowPlayingPositionTicks = new Long();
	public final Long getNowPlayingPositionTicks()
	{
		return privateNowPlayingPositionTicks;
	}
	public final void setNowPlayingPositionTicks(Long value)
	{
		privateNowPlayingPositionTicks = value;
	}

	/** 
	 Gets or sets the device id.
	 
	 <value>The device id.</value>
	*/
	private String privateDeviceId;
	public final String getDeviceId()
	{
		return privateDeviceId;
	}
	public final void setDeviceId(String value)
	{
		privateDeviceId = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports remote control].
	 
	 <value><c>true</c> if [supports remote control]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateSupportsRemoteControl;
	public final boolean getSupportsRemoteControl()
	{
		return privateSupportsRemoteControl;
	}
	public final void setSupportsRemoteControl(boolean value)
	{
		privateSupportsRemoteControl = value;
	}

	private PlayerStateInfo privatePlayState;
	public final PlayerStateInfo getPlayState()
	{
		return privatePlayState;
	}
	public final void setPlayState(PlayerStateInfo value)
	{
		privatePlayState = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;

	public SessionInfoDto()
	{
		setAdditionalUsers(new java.util.ArrayList<SessionUserInfo>());

		setPlayableMediaTypes(new java.util.ArrayList<String>());
		setQueueableMediaTypes(new java.util.ArrayList<String>());
		setSupportedCommands(new java.util.ArrayList<String>());
	}
}