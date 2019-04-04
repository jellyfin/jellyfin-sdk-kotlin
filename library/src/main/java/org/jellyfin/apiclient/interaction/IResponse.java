package org.jellyfin.apiclient.interaction;

public interface IResponse {
    void onError(Exception exception);
}
