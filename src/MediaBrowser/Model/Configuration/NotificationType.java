package MediaBrowser.Model.Configuration;

public enum NotificationType
{
	ApplicationUpdateAvailable,
	ApplicationUpdateInstalled,
	AudioPlayback,
	GamePlayback,
	InstallationFailed,
	PluginError,
	PluginInstalled,
	PluginUpdateInstalled,
	PluginUninstalled,
	NewLibraryContent,
	NewLibraryContentMultiple,
	ServerRestartRequired,
	TaskFailed,
	VideoPlayback;

	public int getValue()
	{
		return this.ordinal();
	}

	public static NotificationType forValue(int value)
	{
		return values()[value];
	}
}