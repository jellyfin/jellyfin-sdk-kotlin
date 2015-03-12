package mediabrowser.apiinteraction.android.sync;

import mediabrowser.model.logging.ILogger;

/**
 * Created by Luke on 3/12/2015.
 */
public interface ISyncLoggerFactory {
    public ILogger getNewLogger();
}
