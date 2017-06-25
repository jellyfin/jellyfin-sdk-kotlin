package mediabrowser.model.users;

import mediabrowser.model.configuration.*;

public class UserPolicy
{
	/** 
	 Gets or sets a value indicating whether this instance is administrator.
	 
	 <value><c>true</c> if this instance is administrator; otherwise, <c>false</c>.</value>
	*/
	private boolean IsAdministrator;
	public final boolean getIsAdministrator()
	{
		return IsAdministrator;
	}
	public final void setIsAdministrator(boolean value)
	{
		IsAdministrator = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is hidden.
	 
	 <value><c>true</c> if this instance is hidden; otherwise, <c>false</c>.</value>
	*/
	private boolean IsHidden;
	public final boolean getIsHidden()
	{
		return IsHidden;
	}
	public final void setIsHidden(boolean value)
	{
		IsHidden = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is disabled.
	 
	 <value><c>true</c> if this instance is disabled; otherwise, <c>false</c>.</value>
	*/
	private boolean IsDisabled;
	public final boolean getIsDisabled()
	{
		return IsDisabled;
	}
	public final void setIsDisabled(boolean value)
	{
		IsDisabled = value;
	}

	/** 
	 Gets or sets the max parental rating.
	 
	 <value>The max parental rating.</value>
	*/
	private Integer MaxParentalRating;
	public final Integer getMaxParentalRating()
	{
		return MaxParentalRating;
	}
	public final void setMaxParentalRating(Integer value)
	{
		MaxParentalRating = value;
	}

	private String[] BlockedTags;
	public final String[] getBlockedTags()
	{
		return BlockedTags;
	}
	public final void setBlockedTags(String[] value)
	{
		BlockedTags = value;
	}
	private boolean EnableUserPreferenceAccess;
	public final boolean getEnableUserPreferenceAccess()
	{
		return EnableUserPreferenceAccess;
	}
	public final void setEnableUserPreferenceAccess(boolean value)
	{
		EnableUserPreferenceAccess = value;
	}
	private AccessSchedule[] AccessSchedules;
	public final AccessSchedule[] getAccessSchedules()
	{
		return AccessSchedules;
	}
	public final void setAccessSchedules(AccessSchedule[] value)
	{
		AccessSchedules = value;
	}
	private UnratedItem[] BlockUnratedItems;
	public final UnratedItem[] getBlockUnratedItems()
	{
		return BlockUnratedItems;
	}
	public final void setBlockUnratedItems(UnratedItem[] value)
	{
		BlockUnratedItems = value;
	}
	private boolean EnableRemoteControlOfOtherUsers;
	public final boolean getEnableRemoteControlOfOtherUsers()
	{
		return EnableRemoteControlOfOtherUsers;
	}
	public final void setEnableRemoteControlOfOtherUsers(boolean value)
	{
		EnableRemoteControlOfOtherUsers = value;
	}
	private boolean EnableSharedDeviceControl;
	public final boolean getEnableSharedDeviceControl()
	{
		return EnableSharedDeviceControl;
	}
	public final void setEnableSharedDeviceControl(boolean value)
	{
		EnableSharedDeviceControl = value;
	}

	private boolean EnableLiveTvManagement;
	public final boolean getEnableLiveTvManagement()
	{
		return EnableLiveTvManagement;
	}
	public final void setEnableLiveTvManagement(boolean value)
	{
		EnableLiveTvManagement = value;
	}
	private boolean EnableLiveTvAccess;
	public final boolean getEnableLiveTvAccess()
	{
		return EnableLiveTvAccess;
	}
	public final void setEnableLiveTvAccess(boolean value)
	{
		EnableLiveTvAccess = value;
	}

	private boolean EnableMediaPlayback;
	public final boolean getEnableMediaPlayback()
	{
		return EnableMediaPlayback;
	}
	public final void setEnableMediaPlayback(boolean value)
	{
		EnableMediaPlayback = value;
	}
	private boolean EnableAudioPlaybackTranscoding;
	public final boolean getEnableAudioPlaybackTranscoding()
	{
		return EnableAudioPlaybackTranscoding;
	}
	public final void setEnableAudioPlaybackTranscoding(boolean value)
	{
		EnableAudioPlaybackTranscoding = value;
	}
	private boolean EnableVideoPlaybackTranscoding;
	public final boolean getEnableVideoPlaybackTranscoding()
	{
		return EnableVideoPlaybackTranscoding;
	}
	public final void setEnableVideoPlaybackTranscoding(boolean value)
	{
		EnableVideoPlaybackTranscoding = value;
	}
	private boolean EnablePlaybackRemuxing;
	public final boolean getEnablePlaybackRemuxing()
	{
		return EnablePlaybackRemuxing;
	}
	public final void setEnablePlaybackRemuxing(boolean value)
	{
		EnablePlaybackRemuxing = value;
	}

	private boolean EnableContentDeletion;
	public final boolean getEnableContentDeletion()
	{
		return EnableContentDeletion;
	}
	public final void setEnableContentDeletion(boolean value)
	{
		EnableContentDeletion = value;
	}
	private boolean EnableContentDownloading;
	public final boolean getEnableContentDownloading()
	{
		return EnableContentDownloading;
	}
	public final void setEnableContentDownloading(boolean value)
	{
		EnableContentDownloading = value;
	}

	private boolean EnableSyncTranscoding;
	public final boolean getEnableSyncTranscoding()
	{
		return EnableSyncTranscoding;
	}
	public final void setEnableSyncTranscoding(boolean value)
	{
		EnableSyncTranscoding = value;
	}

	private String[] EnabledDevices;
	public final String[] getEnabledDevices()
	{
		return EnabledDevices;
	}
	public final void setEnabledDevices(String[] value)
	{
		EnabledDevices = value;
	}
	private boolean EnableAllDevices;
	public final boolean getEnableAllDevices()
	{
		return EnableAllDevices;
	}
	public final void setEnableAllDevices(boolean value)
	{
		EnableAllDevices = value;
	}

	private String[] EnabledChannels;
	public final String[] getEnabledChannels()
	{
		return EnabledChannels;
	}
	public final void setEnabledChannels(String[] value)
	{
		EnabledChannels = value;
	}
	private boolean EnableAllChannels;
	public final boolean getEnableAllChannels()
	{
		return EnableAllChannels;
	}
	public final void setEnableAllChannels(boolean value)
	{
		EnableAllChannels = value;
	}

	private String[] EnabledFolders;
	public final String[] getEnabledFolders()
	{
		return EnabledFolders;
	}
	public final void setEnabledFolders(String[] value)
	{
		EnabledFolders = value;
	}
	private boolean EnableAllFolders;
	public final boolean getEnableAllFolders()
	{
		return EnableAllFolders;
	}
	public final void setEnableAllFolders(boolean value)
	{
		EnableAllFolders = value;
	}

	private int InvalidLoginAttemptCount;
	public final int getInvalidLoginAttemptCount()
	{
		return InvalidLoginAttemptCount;
	}
	public final void setInvalidLoginAttemptCount(int value)
	{
		InvalidLoginAttemptCount = value;
	}

	private boolean EnablePublicSharing;
	public final boolean getEnablePublicSharing()
	{
		return EnablePublicSharing;
	}
	public final void setEnablePublicSharing(boolean value)
	{
		EnablePublicSharing = value;
	}

	private String[] BlockedMediaFolders;
	public final String[] getBlockedMediaFolders()
	{
		return BlockedMediaFolders;
	}
	public final void setBlockedMediaFolders(String[] value)
	{
		BlockedMediaFolders = value;
	}
	private String[] BlockedChannels;
	public final String[] getBlockedChannels()
	{
		return BlockedChannels;
	}
	public final void setBlockedChannels(String[] value)
	{
		BlockedChannels = value;
	}

	public UserPolicy()
	{
		setEnableSyncTranscoding(true);

		setEnableMediaPlayback(true);
		setEnableAudioPlaybackTranscoding(true);
		setEnableVideoPlaybackTranscoding(true);
		setEnablePlaybackRemuxing(true);

		setEnableLiveTvManagement(true);
		setEnableLiveTvAccess(true);

		// Without this on by default, admins won't be able to do this
		// Improve in the future
		setEnableLiveTvManagement(true);

		setEnableSharedDeviceControl(true);

		setBlockedTags(new String[] { });
		setBlockUnratedItems(new UnratedItem[] { });

		setEnableUserPreferenceAccess(true);

		setAccessSchedules(new AccessSchedule[] { });

		setEnableAllChannels(true);
		setEnabledChannels(new String[] { });

		setEnableAllFolders(true);
		setEnabledFolders(new String[] { });

		setEnabledDevices(new String[] { });
		setEnableAllDevices(true);

		setEnableContentDownloading(true);
		setEnablePublicSharing(true);
	}
}