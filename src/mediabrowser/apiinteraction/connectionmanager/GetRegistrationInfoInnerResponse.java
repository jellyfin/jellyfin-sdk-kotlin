package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.SerializedResponse;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.registration.RegistrationInfo;
import mediabrowser.model.serialization.IJsonSerializer;

import java.util.ArrayList;

/**
 * Created by Luke on 3/12/2015.
 */
public class GetRegistrationInfoInnerResponse extends SerializedResponse<RegistrationInfo> {

    private GetRegistrationInfoFindServersResponse parentResponse;
    private ILogger logger;

    public GetRegistrationInfoInnerResponse(Response<RegistrationInfo> innerResponse, IJsonSerializer jsonSerializer, Class type, GetRegistrationInfoFindServersResponse parentResponse, ILogger logger) {
        super(innerResponse, jsonSerializer, type);
        this.parentResponse = parentResponse;
        this.logger = logger;
    }

    @Override
    public void onSerializedResponse(RegistrationInfo result){

        if (result.getIsRegistered() || result.getIsTrial()){
            super.onSerializedResponse(result);
        }
        else{
            parentResponse.TestConnect(innerResponse);
        }
    }

    @Override
    public void onError(Exception ex){

        logger.ErrorException("Error getting registration info", ex);
        parentResponse.TestConnect(innerResponse);
    }
}
