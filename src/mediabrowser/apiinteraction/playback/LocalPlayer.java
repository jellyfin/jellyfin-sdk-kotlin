package mediabrowser.apiinteraction.playback;

import mediabrowser.model.dlna.ILocalPlayer;

/**
 * Created by Luke on 4/17/2015.
 */
public class LocalPlayer implements ILocalPlayer {

    @Override
    public boolean CanAccessFile(String path) {
        return false;
    }

    @Override
    public boolean CanAccessDirectory(String path) {
        return false;
    }

    @Override
    public boolean CanAccessUrl(String url, boolean requiresCustomRequestHeaders) {

        if (requiresCustomRequestHeaders){
            return false;
        }

        return true;
    }
}
