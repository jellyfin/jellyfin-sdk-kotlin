package org.jellyfin.apiclient.interaction.http;

import org.jellyfin.apiclient.interaction.Response;

public interface IAsyncHttpClient {

    public void Send(HttpRequest request, Response<String> response);
}
