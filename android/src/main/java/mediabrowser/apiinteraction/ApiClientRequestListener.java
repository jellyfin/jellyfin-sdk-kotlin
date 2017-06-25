package mediabrowser.apiinteraction;

import mediabrowser.apiinteraction.ApiClient;
import mediabrowser.apiinteraction.IResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.model.net.HttpException;

public class ApiClientRequestListener extends Response<String> {

    private boolean fireGlobalEvents;
    private ApiClient apiClient;
    private Response<String> innerStringResponse;

    public ApiClientRequestListener(ApiClient apiClient, boolean fireGlobalEvents, Response<String> innerResponse) {
        super(innerResponse);
        this.fireGlobalEvents = fireGlobalEvents;
        this.apiClient = apiClient;
        innerStringResponse = innerResponse;
    }

    @Override
    public void onResponse(String response)
    {
        innerStringResponse.onResponse(response);
    }

    @Override
    public void onError(Exception ex) {

        if (ex instanceof HttpException) {

            HttpException httpError = (HttpException)ex;

            if (fireGlobalEvents && httpError.getStatusCode() != null && httpError.getStatusCode() == 401) {

                apiClient.OnRemoteLoggedOut(httpError);
            }
        }

        super.onError(ex);
    }
}
