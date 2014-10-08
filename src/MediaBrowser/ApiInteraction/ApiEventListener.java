package MediaBrowser.ApiInteraction;

import MediaBrowser.Model.ApiClient.SessionUpdatesEventArgs;
import MediaBrowser.Model.Dto.UserDto;
import MediaBrowser.Model.Session.*;

public class ApiEventListener {

    public void onRemoteLoggedOut(){

    }

    public void onUserUpdated(UserDto userDto){

    }

    public void onBrowseCommand(BrowseRequest command){

    }

    public void onPlayCommand(PlayRequest command){

    }

    public void onPlaystateCommand(PlaystateRequest command)
    {

    }

    public void onMessageCommand(MessageCommand command)
    {

    }

    public void onGeneralCommand(GeneralCommand command)
    {

    }

    public void onSendStringCommand(String value)
    {

    }

    public void onSetVolumeCommand(int value)
    {

    }

    public void onSetAudioStreamIndexCommand(int value)
    {

    }

    public void onSetSubtitleStreamIndexCommand(int value)
    {

    }

    public void onUserDataChanged(UserDataChangeInfo info)
    {

    }

    public void onSessionsUpdated(SessionUpdatesEventArgs args)
    {

    }

    public void onPlaybackStart(SessionInfoDto info)
    {

    }

    public void onPlaybackStopped(SessionInfoDto info)
    {

    }

    public void onSessionEnded(SessionInfoDto info)
    {

    }
}
