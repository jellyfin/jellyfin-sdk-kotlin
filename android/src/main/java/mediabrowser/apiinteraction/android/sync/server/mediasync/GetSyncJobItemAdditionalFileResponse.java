package mediabrowser.apiinteraction.android.sync.server.mediasync;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.ResponseStreamInfo;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.apiinteraction.tasks.IProgress;
import mediabrowser.model.dto.MediaSourceInfo;
import mediabrowser.model.entities.MediaStream;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.sync.ItemFileInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.sync.SyncedItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Luke on 4/20/2015.
 */
public class GetSyncJobItemAdditionalFileResponse extends Response<ResponseStreamInfo> {

    private ILogger logger;
    private ApiClient apiClient;
    private ILocalAssetManager localAssetManager;
    private SyncedItem jobItem;
    private MediaSourceInfo mediaSource;
    private MediaStream subtitleStream;
    private CancellationToken cancellationToken;
    private LocalItem item;
    private ArrayList<ItemFileInfo> files;
    private int index;
    private IProgress<Double> progress;
    private MediaSync mediaSync;

    public GetSyncJobItemAdditionalFileResponse(ILogger logger, ApiClient apiClient, ILocalAssetManager localAssetManager, SyncedItem jobItem, MediaSourceInfo mediaSource, MediaStream subtitleStream, CancellationToken cancellationToken, LocalItem item, ArrayList<ItemFileInfo> files, int index, IProgress<Double> progress, MediaSync mediaSync) {
        this.logger = logger;
        this.apiClient = apiClient;
        this.localAssetManager = localAssetManager;
        this.jobItem = jobItem;
        this.mediaSource = mediaSource;
        this.subtitleStream = subtitleStream;
        this.cancellationToken = cancellationToken;
        this.item = item;
        this.files = files;
        this.index = index;
        this.progress = progress;
        this.mediaSync = mediaSync;
    }

    private void onAny(){

        int numComplete = index + 1;
        double startingPercent = numComplete;
        startingPercent /= files.size();
        startingPercent *= 100;
        progress.report(startingPercent);

        mediaSync.GetNextSubtitle(files, index + 1, apiClient, jobItem, item, mediaSource, cancellationToken, progress);
    }

    @Override
    public void onResponse(ResponseStreamInfo responseStreamInfo) {

        try (InputStream copy = responseStreamInfo.Stream){

            String path = localAssetManager.saveSubtitles(copy, subtitleStream.getCodec(), item, subtitleStream.getLanguage(), subtitleStream.getIsForced());
            subtitleStream.setPath(path);
            localAssetManager.addOrUpdate(item);
        }
        catch (IOException ioException){
            logger.ErrorException("Error saving subtitles", ioException);
        }
        finally {
            onAny();
        }
    }

    @Override
    public void onError(Exception ex){
        logger.ErrorException("Error downloading subtitles", ex);
        onAny();
    }

}
