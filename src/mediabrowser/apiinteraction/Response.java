package mediabrowser.apiinteraction;

public class Response<T> implements IResponse {

    private IResponse innerResponse;

    public Response(IResponse innerResponse){
        this.innerResponse = innerResponse;
    }

    public Response(){

    }

    public void onResponse(T response)
    {
        triggerInnerResponse();
    }

    protected void triggerInnerResponse(){
        if (innerResponse != null && innerResponse instanceof EmptyResponse){

            EmptyResponse emptyResponse = (EmptyResponse)innerResponse;
            emptyResponse.onResponse();
        }
    }

    @Override
    public void onError(Exception exception)
    {
        if (innerResponse != null){
            innerResponse.onError(exception);
        }
    }
}
