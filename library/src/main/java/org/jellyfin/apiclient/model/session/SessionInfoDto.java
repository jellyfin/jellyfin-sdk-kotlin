package org.jellyfin.apiclient.model.session;

import org.jellyfin.apiclient.model.entities.*;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Client = {Client}, Username = {UserName}")] public class SessionInfoDto
public class SessionInfoDto
{
	/** 
	 Gets or sets the supported commands.
	 
	 <value>The supported commands.</value>
	*/
	private java.util.ArrayList<String> SupportedCommands;
	public final java.util.ArrayList<String> getSupportedCommands()
	{
		return SupportedCommands;
	}
	public final void setSupportedCommands(java.util.ArrayList<String> value)
	{
		SupportedCommands = value;
	}

	/** 
	 Gets or sets the queueable media types.
	 
	 <value>The queueable media types.</value>
	*/
	private java.util.ArrayList<String> QueueableMediaTypes;
	public final java.util.ArrayList<String> getQueueableMediaTypes()
	{
		return QueueableMediaTypes;
	}
	public final void setQueueableMediaTypes(java.util.ArrayList<String> value)
	{
		QueueableMediaTypes = value;
	}

	/** 
	 Gets or sets the playable media types.
	 
	 <value>The playable media types.</value>
	*/
	private java.util.ArrayList<String> PlayableMediaTypes;
	public final java.util.ArrayList<String> getPlayableMediaTypes()
	{
		return PlayableMediaTypes;
	}
	public final void setPlayableMediaTypes(java.util.ArrayList<String> value)
	{
		PlayableMediaTypes = value;
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
	 Gets or sets the user id.
	 
	 <value>The user id.</value>
	*/
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	/** 
	 Gets or sets the user primary image tag.
	 
	 <value>The user primary image tag.</value>
	*/
	private String UserPrimaryImageTag;
	public final String getUserPrimaryImageTag()
	{
		return UserPrimaryImageTag;
	}
	public final void setUserPrimaryImageTag(String value)
	{
		UserPrimaryImageTag = value;
	}

	/** 
	 Gets or sets the name of the user.
	 
	 <value>The name of the user.</value>
	*/
	private String UserName;
	public final String getUserName()
	{
		return UserName;
	}
	public final void setUserName(String value)
	{
		UserName = value;
	}

	/** 
	 Gets or sets the additional users present.
	 
	 <value>The additional users present.</value>
	*/
	private java.util.ArrayList<SessionUserInfo> AdditionalUsers;
	public final java.util.ArrayList<SessionUserInfo> getAdditionalUsers()
	{
		return AdditionalUsers;
	}
	public final void setAdditionalUsers(java.util.ArrayList<SessionUserInfo> value)
	{
		AdditionalUsers = value;
	}

	/** 
	 Gets or sets the application version.
	 
	 <value>The application version.</value>
	*/
	private String ApplicationVersion;
	public final String getApplicationVersion()
	{
		return ApplicationVersion;
	}
	public final void setApplicationVersion(String value)
	{
		ApplicationVersion = value;
	}

	/** 
	 Gets or sets the type of the client.
	 
	 <value>The type of the client.</value>
	*/
	private String Client;
	public final String getClient()
	{
		return Client;
	}
	public final void setClient(String value)
	{
		Client = value;
	}

	/** 
	 Gets or sets the last activity date.
	 
	 <value>The last activity date.</value>
	*/
	private java.util.Date LastActivityDate = new java.util.Date(0);
	public final java.util.Date getLastActivityDate()
	{
		return LastActivityDate;
	}
	public final void setLastActivityDate(java.util.Date value)
	{
		LastActivityDate = value;
	}

	/** 
	 Gets or sets the now viewing item.
	 
	 <value>The now viewing item.</value>
	*/
	private BaseItemInfo NowViewingItem;
	public final BaseItemInfo getNowViewingItem()
	{
		return NowViewingItem;
	}
	public final void setNowViewingItem(BaseItemInfo value)
	{
		NowViewingItem = value;
	}

	/** 
	 Gets or sets the name of the device.
	 
	 <value>The name of the device.</value>
	*/
	private String DeviceName;
	public final String getDeviceName()
	{
		return DeviceName;
	}
	public final void setDeviceName(String value)
	{
		DeviceName = value;
	}

	/** 
	 Gets or sets the now playing item.
	 
	 <value>The now playing item.</value>
	*/
	private BaseItemInfo NowPlayingItem;
	public final BaseItemInfo getNowPlayingItem()
	{
		return NowPlayingItem;
	}
	public final void setNowPlayingItem(BaseItemInfo value)
	{
		NowPlayingItem = value;
	}

	/** 
	 Gets or sets the device id.
	 
	 <value>The device id.</value>
	*/
	private String DeviceId;
	public final String getDeviceId()
	{
		return DeviceId;
	}
	public final void setDeviceId(String value)
	{
		DeviceId = value;
	}

	/** 
	 Gets or sets the application icon URL.
	 
	 <value>The application icon URL.</value>
	*/
	private String AppIconUrl;
	public final String getAppIconUrl()
	{
		return AppIconUrl;
	}
	public final void setAppIconUrl(String value)
	{
		AppIconUrl = value;
	}

	/** 
	 Gets or sets a value indicating whether [supports remote control].
	 
	 <value><c>true</c> if [supports remote control]; otherwise, <c>false</c>.</value>
	*/
	private boolean SupportsRemoteControl;
	public final boolean getSupportsRemoteControl()
	{
		return SupportsRemoteControl;
	}
	public final void setSupportsRemoteControl(boolean value)
	{
		SupportsRemoteControl = value;
	}

	private PlayerStateInfo PlayState;
	public final PlayerStateInfo getPlayState()
	{
		return PlayState;
	}
	public final void setPlayState(PlayerStateInfo value)
	{
		PlayState = value;
	}

	private TranscodingInfo TranscodingInfo;
	public final TranscodingInfo getTranscodingInfo()
	{
		return TranscodingInfo;
	}
	public final void setTranscodingInfo(TranscodingInfo value)
	{
		TranscodingInfo = value;
	}

	public SessionInfoDto()
	{
		setAdditionalUsers(new java.util.ArrayList<SessionUserInfo>());

		setPlayableMediaTypes(new java.util.ArrayList<String>());
		setQueueableMediaTypes(new java.util.ArrayList<String>());
		setSupportedCommands(new java.util.ArrayList<String>());
	}
}