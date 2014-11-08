package mediabrowser.apiinteraction;

import mediabrowser.apiinteraction.Response;
import mediabrowser.model.serialization.IJsonSerializer;

public class SerializedResponse<T> extends Response<String> {

    private IJsonSerializer jsonSerializer;
    private Class type;
    Response<T> innerResponse;

    public SerializedResponse(Response<T> innerResponse, IJsonSerializer jsonSerializer, Class type) {
        super(innerResponse);
        this.jsonSerializer = jsonSerializer;
        this.type = type;
        this.innerResponse = innerResponse;
    }

    @Override
    public void onResponse(String result) {

        T obj = jsonSerializer.DeserializeFromString(result, type);

        innerResponse.onResponse(obj);
    }
}
