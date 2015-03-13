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
public class GetRegistrationInfoInnerResponse extends Response<RegistrationInfo> {

    private  Response<RegistrationInfo> innerResponse;
    private GetRegistrationInfoFindServersResponse parentResponse;
    private ILogger logger;

    public GetRegistrationInfoInnerResponse(Response<RegistrationInfo> innerResponse, GetRegistrationInfoFindServersResponse parentResponse, ILogger logger) {
        this.innerResponse = innerResponse;
        this.parentResponse = parentResponse;
        this.logger = logger;
    }

    @Override
    public void onResponse(RegistrationInfo result){

        if (result.getIsRegistered() || result.getIsTrial()){
            innerResponse.onResponse(result);
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
