package MediaBrowser.ApiInteraction;

public class EmptyResponse implements IResponse {

    private IResponse innerResponse;

    public EmptyResponse(IResponse innerResponse){
        this.innerResponse = innerResponse;
    }

    public EmptyResponse(){

    }

    public void onResponse()
    {

    }

    @Override
    public void onError()
    {
        if (innerResponse != null){
            innerResponse.onError();
        }
    }
}
