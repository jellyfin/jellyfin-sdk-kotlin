package mediabrowser.apiinteraction.android.mediabrowser;

/**
 * Created by Luke on 7/4/2015.
 */
public interface IPlaybackCallback {

    /**
     * On current media completed.
     */
    void onPlaybackCompletion();
    /**
     * on Playback status changed
     * Implementations can use this callback to update
     * playback state on the media sessions.
     */
    void onPlaybackStatusChanged(int state);

    /**
     * @param error to be added to the PlaybackState
     */
    void onPlaybackError(String error);
}
