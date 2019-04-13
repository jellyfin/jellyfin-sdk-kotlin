package org.jellyfin.apiclient.interaction.connectionmanager;

import org.jellyfin.apiclient.model.apiclient.ServerInfo;

import java.util.Comparator;

public class ServerInfoDateComparator implements Comparator<ServerInfo> {

    @Override
    public int compare(ServerInfo p1, ServerInfo p2) {

        long p1Date= p1.getDateLastAccessed().getTime();
        long p2Date = p2.getDateLastAccessed().getTime();

        if (p1Date > p2Date){
            return 1;
        }
        if (p1Date < p2Date){
            return -1;
        }

        return 0;
    }
}
