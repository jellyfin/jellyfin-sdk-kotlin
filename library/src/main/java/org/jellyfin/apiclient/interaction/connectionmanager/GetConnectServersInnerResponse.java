package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.model.connect.ConnectUserServer;

/**
 * Created by Luke on 2/15/2015.
 */
public class GetConnectServersInnerResponse extends Response<ConnectUserServer[]> {

    private GetConnectServersResponse parent;

    public GetConnectServersInnerResponse(GetConnectServersResponse parent) {
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
