package mediabrowser.apiinteraction.android.mediabrowser;

import android.annotation.TargetApi;
import android.media.session.MediaSession;

/**
 * Created by Luke on 7/4/2015.
 */
public interface IPlayback {
    int getState();
    void setState(int state);
    void setCallback(IPlaybackCallback callback);
    long getCurrentPositionMs();
    boolean isPlaying();
    void seek(long positionMs);
    void unPause();
    void pause();
    void stop(boolean notifyListeners);
    @TargetApi(21)
    void play(MediaSession.QueueItem item);
}
