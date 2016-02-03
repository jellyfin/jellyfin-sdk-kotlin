package mediabrowser.apiinteraction.sync.server.mediasync;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.apiinteraction.IResponse;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.model.users.UserAction;

import java.util.ArrayList;

/**
 * Created by Luke on 3/17/2015.
 */
public class UserActionsReportedResponse extends EmptyResponse {

    private ArrayList<UserAction> actions;
    private ILocalAssetManager localAssetManager;

    public UserActionsReportedResponse(IResponse innerResponse, ArrayList<UserAction> actions, ILocalAssetManager localAssetManager){

        super(innerResponse);
        this.actions = actions;
        this.localAssetManager = localAssetManager;
    }

    @Override
    public  void onResponse(){
        for (UserAction action : actions)
        {
            localAssetManager.delete(action);
        }
        triggerInnerResponse();
    }
}
