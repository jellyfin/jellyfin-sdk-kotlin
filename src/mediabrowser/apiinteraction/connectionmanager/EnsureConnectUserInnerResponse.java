package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.apiinteraction.Response;
import mediabrowser.model.connect.ConnectUserServer;

/**
 * Created by Luke on 2/15/2015.
 */
public class EnsureConnectUserInnerResponse extends Response<ConnectUserServer[]> {

    private EnsureConnectUserResponse parent;

    public EnsureConnectUserInnerResponse(EnsureConnectUserResponse parent) {
        this.parent = parent;
    }

    @Override
    public void onResponse(ConnectUserServer[] response) {

        parent.OnAny(response);
    }

    @Override
    public void onError(Exception ex) {

        parent.OnAny(new ConnectUserServer[]{});
    }
}
