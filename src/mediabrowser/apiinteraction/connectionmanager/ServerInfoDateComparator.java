package mediabrowser.apiinteraction.connectionmanager;

import mediabrowser.model.apiclient.ServerInfo;

import java.util.Comparator;

public class ServerInfoDateComparator implements Comparator<ServerInfo> {

    @Override
    public int compare(ServerInfo p1, ServerInfo p2) {
        // Descending
        return p2.getDateLastAccessed().compareTo(p1.getDateLastAccessed());
    }
}
