package mediabrowser.apiinteraction.android.sync.server.mediasync;

import mediabrowser.apiinteraction.android.sync.SyncProgress;
import mediabrowser.apiinteraction.tasks.Progress;

/**
 * Created by Luke on 3/12/2015.
 */
public class MediaSyncProgress extends Progress<Double> {

    private SyncProgress progress;
    private double initialProgressPercent;

    public MediaSyncProgress(SyncProgress progress, double initialProgressPercent) {
        this.progress = progress;
        this.initialProgressPercent = initialProgressPercent;
    }

    @Override
    public void onProgress(Double percent)
    {
        double cumulativeProgress = (initialProgressPercent * 100) + (percent * (1 - initialProgressPercent));
        progress.report(cumulativeProgress);
    }

    @Override
    public void onComplete()
    {
        progress.reportComplete();
    }

    @Override
    public void onCancelled()
    {
        progress.reportCancelled();
    }

    @Override
    public void onError(Exception exception)
    {
        progress.reportError(exception);
    }

}
