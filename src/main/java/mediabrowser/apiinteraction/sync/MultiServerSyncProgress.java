package mediabrowser.apiinteraction.sync;

import mediabrowser.apiinteraction.tasks.CancellationToken;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.devices.LocalFileInfo;

import java.util.ArrayList;

public class MultiServerSyncProgress extends SyncProgress {

    private MultiServerSync multiServerSync;
    private ArrayList<ServerInfo> servers;
    private CancellationToken cancellationToken;
    private int index;
    private double numServers;
    private double[] numComplete;
    private SyncProgress outerProgress;

    public MultiServerSyncProgress(MultiServerSync multiServerSync, ArrayList<ServerInfo> servers, CancellationToken cancellationToken, int index, double numServers, double[] numComplete, SyncProgress outerProgress) {
        this.multiServerSync = multiServerSync;
        this.servers = servers;
        this.cancellationToken = cancellationToken;
        this.index = index;
        this.numServers = numServers;
        this.numComplete = numComplete;
        this.outerProgress = outerProgress;
    }

    private void OnServerDone(){

        numComplete[0]++;
        double percent = numComplete[0] / numServers;
        outerProgress.report(percent * 100);

        multiServerSync.SyncNext(servers, index + 1, cancellationToken, outerProgress);
    }

    @Override
    public void onProgress(Double serverPercent) {

        double percent = numComplete[0] / numServers;
        percent += (serverPercent / 100);
        outerProgress.report(percent * 100);
    }

    @Override
    public void onComplete() {

        OnServerDone();
    }

    @Override
    public void onFileUploaded(LocalFileInfo file) {

        outerProgress.onFileUploaded(file);
    }

    @Override
    public void onCancelled() {

        OnServerDone();
    }

    @Override
    public void onError(Exception ex) {

        OnServerDone();
    }

    @Override
    public void onFileUploadError(LocalFileInfo file, Exception ex) {

        outerProgress.onFileUploadError(file, ex);
    }}
