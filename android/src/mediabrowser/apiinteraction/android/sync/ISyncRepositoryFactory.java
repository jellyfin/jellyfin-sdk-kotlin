package mediabrowser.apiinteraction.android.sync;

import mediabrowser.apiinteraction.sync.data.IFileRepository;

/**
 * Created by Luke on 6/9/2015.
 */
public interface ISyncRepositoryFactory {
    public IFileRepository getFileRepository();
}
