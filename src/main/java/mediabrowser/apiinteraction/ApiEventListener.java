package mediabrowser.apiinteraction;

import mediabrowser.model.apiclient.RemoteLogoutReason;
import mediabrowser.model.apiclient.SessionUpdatesEventArgs;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.session.*;

public class ApiEventListener {

    public void onRemoteLoggedOut(ApiClient client, RemoteLogoutReason reason){

    }

    public void onUserUpdated(ApiClient client, UserDto userDto){

    }

    public void onUserConfigurationUpdated(ApiClient client, UserDto userDto){

    }

    public void onBrowseCommand(ApiClient client, BrowseRequest command){

    }

    public void onPlayCommand(ApiClient client, PlayRequest command){

    }

    public void onPlaystateCommand(ApiClient client, PlaystateRequest command)
    {

    }

    public void onMessageCommand(ApiClient client, MessageCommand command)
    {

    }

    public void onGeneralCommand(ApiClient client, GeneralCommand command)
    {

    }

    public void onSendStringCommand(ApiClient client, String value)
    {

    }

    public void onSetVolumeCommand(ApiClient client, int value)
    {

    }

    public void onSetAudioStreamIndexCommand(ApiClient client, int value)
    {

    }

    public void onSetSubtitleStreamIndexCommand(ApiClient client, int value)
    {

    }

    public void onUserDataChanged(ApiClient client, UserDataChangeInfo info)
    {

    }

    public void onSessionsUpdated(ApiClient client, SessionUpdatesEventArgs args)
    {

    }

    public void onPlaybackStart(ApiClient client, SessionInfoDto info)
    {

    }

    public void onPlaybackStopped(ApiClient client, SessionInfoDto info)
    {

    }

    public void onSessionEnded(ApiClient client, SessionInfoDto info)
    {

    }
}
