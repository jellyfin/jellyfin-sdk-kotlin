package org.jellyfin.apiclient.interaction;

public class EmptyResponse implements IResponse {

    private IResponse innerResponse;

    public EmptyResponse(IResponse innerResponse) {

        this.innerResponse = innerResponse;
    }

    public EmptyResponse() {

    }

    public void onResponse()
    {
        triggerInnerResponse();
    }

    protected void triggerInnerResponse() {
        if (innerResponse != null && innerResponse instanceof EmptyResponse) {

            EmptyResponse emptyResponse = (EmptyResponse)innerResponse;
            emptyResponse.onResponse();
        }
    }

    @Override
    public void onError(Exception ex)
    {
        if (innerResponse != null) {
            innerResponse.onError(ex);
        }
    }
}
