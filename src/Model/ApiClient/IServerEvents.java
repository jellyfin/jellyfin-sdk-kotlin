package MediaBrowser.Model.ApiClient;

import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Events.*;
import MediaBrowser.Model.Plugins.*;
import MediaBrowser.Model.Session.*;
import MediaBrowser.Model.Tasks.*;
import MediaBrowser.Model.Updates.*;

/** 
 Interface IServerEvents
*/
public interface IServerEvents
{
	/** 
	 Occurs when [user deleted].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<string>> UserDeleted;
	/** 
	 Occurs when [scheduled task ended].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<TaskResult>> ScheduledTaskEnded;
	/** 
	 Occurs when [package installing].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<InstallationInfo>> PackageInstalling;
	/** 
	 Occurs when [package installation failed].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<InstallationInfo>> PackageInstallationFailed;
	/** 
	 Occurs when [package installation completed].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<InstallationInfo>> PackageInstallationCompleted;
	/** 
	 Occurs when [package installation cancelled].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<InstallationInfo>> PackageInstallationCancelled;
	/** 
	 Occurs when [user updated].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<UserDto>> UserUpdated;
	/** 
	 Occurs when [plugin uninstalled].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<PluginInfo>> PluginUninstalled;
	/** 
	 Occurs when [library changed].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<LibraryUpdateInfo>> LibraryChanged;
	/** 
	 Occurs when [browse command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<BrowseRequest>> BrowseCommand;
	/** 
	 Occurs when [play command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<PlayRequest>> PlayCommand;
	/** 
	 Occurs when [playstate command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<PlaystateRequest>> PlaystateCommand;
	/** 
	 Occurs when [message command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<MessageCommand>> MessageCommand;
	/** 
	 Occurs when [system command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GeneralCommandEventArgs> GeneralCommand;
	/** 
	 Occurs when [notification added].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<EventArgs> NotificationAdded;
	/** 
	 Occurs when [notification updated].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<EventArgs> NotificationUpdated;
	/** 
	 Occurs when [notifications marked read].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<EventArgs> NotificationsMarkedRead;
	/** 
	 Occurs when [server restarting].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<EventArgs> ServerRestarting;
	/** 
	 Occurs when [server shutting down].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<EventArgs> ServerShuttingDown;
	/** 
	 Occurs when [send text command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<string>> SendStringCommand;
	/** 
	 Occurs when [set volume command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<int>> SetVolumeCommand;
	/** 
	 Occurs when [set audio stream index command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<int>> SetAudioStreamIndexCommand;
	/** 
	 Occurs when [set video stream index command].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<int>> SetSubtitleStreamIndexCommand;
	/** 
	 Occurs when [sessions updated].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<SessionUpdatesEventArgs> SessionsUpdated;
	/** 
	 Occurs when [restart required].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<EventArgs> RestartRequired;
	/** 
	 Occurs when [user data changed].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<GenericEventArgs<UserDataChangeInfo>> UserDataChanged;
	/** 
	 Occurs when [connected].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	event EventHandler<EventArgs> Connected;
	/** 
	 Gets a value indicating whether this instance is connected.
	 
	 <value><c>true</c> if this instance is connected; otherwise, <c>false</c>.</value>
	*/
	boolean getIsConnected();
}