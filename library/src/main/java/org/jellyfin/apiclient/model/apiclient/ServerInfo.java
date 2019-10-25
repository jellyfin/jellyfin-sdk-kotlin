package org.jellyfin.apiclient.model.apiclient;

import org.jellyfin.apiclient.model.system.PublicSystemInfo;

import java.util.Date;

public class ServerInfo {
    private String Name;
    private String Id;
    private String Address;
    private String Version;
    private String UserId;
    private String AccessToken;
    private Date DateLastAccessed = new Date(0);

    public final String getName() {
        return Name;
    }

    public final void setName(String value) {
        Name = value;
    }

    public final String getId() {
        return Id;
    }

    public final void setId(String value) {
        Id = value;
    }

    public final String getAddress() {
        return Address;
    }

    public final void setAddress(String value) {
        Address = value;
    }

    public final String getVersion() {
        return Version;
    }

    public final void setVersion(String version) {
        this.Version = version;
    }

    public final String getUserId() {
        return UserId;
    }

    public final void setUserId(String value) {
        UserId = value;
    }

    public final String getAccessToken() {
        return AccessToken;
    }

    public final void setAccessToken(String value) {
        AccessToken = value;
    }

    public final Date getDateLastAccessed() {
        return DateLastAccessed;
    }

    public final void setDateLastAccessed(Date value) {
        DateLastAccessed = value;
    }

    public final void ImportInfo(PublicSystemInfo systemInfo) {
        if (systemInfo == null) {
            throw new IllegalArgumentException();
        }
        setName(systemInfo.getServerName());
        setId(systemInfo.getId());
        setVersion(systemInfo.getVersion());
    }
}