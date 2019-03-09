package mediabrowser.apiinteraction;

import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;

public class SerializedResponse<T> extends Response<String> {

    private IJsonSerializer jsonSerializer;
    private Class<T> type;
    protected Response<T> innerResponse;
    private String url;
    private ILogger logger;

    public SerializedResponse(Response<T> innerResponse, IJsonSerializer jsonSerializer, Class<T> type) {
        super(innerResponse);
        this.jsonSerializer = jsonSerializer;
        this.type = type;
        this.innerResponse = innerResponse;
    }

    public SerializedResponse(Response<T> innerResponse, IJsonSerializer jsonSerializer, String url, ILogger logger, Class<T> type) {
        super(innerResponse);
        this.jsonSerializer = jsonSerializer;
        this.type = type;
        this.innerResponse = innerResponse;
        this.url = url;
        this.logger = logger;
    }


    @Override
    public void onResponse(String result) {

        if (url != null){
            logger.Debug("Received response from %s", url);
        }

        try {
            T obj = jsonSerializer.DeserializeFromString(result, type);
            onSerializedResponse(obj);
        } catch (Exception e) {
            innerResponse.onError(e);
        }
    }

    protected void onSerializedResponse(T obj){
        innerResponse.onResponse(obj);
    }

}
