package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.interaction.EmptyResponse;
import org.jellyfin.apiclient.interaction.Response;
import org.jellyfin.apiclient.interaction.connect.ConnectService;
import org.jellyfin.apiclient.model.apiclient.ServerCredentials;
import org.jellyfin.apiclient.model.apiclient.ServerInfo;
import org.jellyfin.apiclient.model.connect.ConnectUserServer;
import org.jellyfin.apiclient.model.connect.UserLinkType;
import org.jellyfin.apiclient.model.logging.ILogger;

import java.util.ArrayList;

/**
 * Created by Luke on 2/15/2015.
 */
public class GetConnectServersResponse extends EmptyResponse {

    private ILogger logger;
    private ConnectService connectService;
    private ServerCredentials credentials;
    private ArrayList<ServerInfo> foundServers;
    private ArrayList<ServerInfo> connectServers;
    private int numTasks;
    private int[] numTasksCompleted;
    private Response<ArrayList<ServerInfo>> finalResponse;
    private ConnectionManager connectionManager;

    public GetConnectServersResponse(ILogger logger, ConnectService connectService, ServerCredentials credentials, ArrayList<ServerInfo> foundServers, ArrayList<ServerInfo> connectServers, int numTasks, int[] numTasksCompleted, Response<ArrayList<ServerInfo>> finalResponse, ConnectionManager connectionManager) {
        this.logger = logger;
        this.connectService = connectService;
        this.credentials = credentials;
        this.foundServers = foundServers;
        this.connectServers = connectServers;
        this.numTasks = numTasks;
        this.numTasksCompleted = numTasksCompleted;
        this.finalResponse = finalResponse;
        this.connectionManager = connectionManager;
    }

    void OnAny(ConnectUserServer[] servers){

        synchronized (credentials){

            connectServers.addAll(ConvertServerList(servers));

            numTasksCompleted[0]++;

            if (numTasksCompleted[0] >= numTasks) {
                connectionManager.OnGetServerResponse(credentials, foundServers, connectServers, finalResponse);
            }
        }
    }

    @Override
    public void onResponse() {

        logger.Debug("Getting connect servers");

        connectService.GetServers(credentials.getConnectUserId(), credentials.getConnectAccessToken(), new GetConnectServersInnerResponse(this));
    }

    @Override
    public void onError(Exception ex) {

        OnAny(new ConnectUserServer[]{});
    }

    private ArrayList<ServerInfo> ConvertServerList(ConnectUserServer[] userServers){

        ArrayList<ServerInfo> servers = new ArrayList<ServerInfo>();

        for(ConnectUserServer userServer : userServers){

            ServerInfo server = new ServerInfo();

            server.setExchangeToken(userServer.getAccessKey());
            server.setId(userServer.getSystemId());
            server.setName(userServer.getName());
            server.setLocalAddress(userServer.getLocalAddress());
            server.setRemoteAddress(userServer.getUrl());
            server.setConnectServerId(userServer.getId());

            if (userServer.getUserType().equalsIgnoreCase("guest"))
            {
                server.setUserLinkType(UserLinkType.Guest);
            }
            else{
                server.setUserLinkType(UserLinkType.LinkedUser);
            }

            servers.add(server);
        }

        return servers;
    }
}
