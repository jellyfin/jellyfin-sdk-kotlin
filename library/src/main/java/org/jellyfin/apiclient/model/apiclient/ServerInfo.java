package org.jellyfin.apiclient.model.apiclient;

import org.jellyfin.apiclient.model.system.PublicSystemInfo;

import java.util.Date;

public class ServerInfo {
    private String name;
    private String id;
    private String address;
    private String version;
    private String userId;
    private String accessToken;
    private Date dateLastAccessed = new Date(0);

    public final String getName() {
        return name;
    }

    public final void setName(String value) {
        name = value;
    }

    public final String getId() {
        return id;
    }

    public final void setId(String value) {
        id = value;
    }

    public final String getAddress() {
        return address;
    }

    public final void setAddress(String value) {
        address = value;
    }

    public final String getVersion() {
        return version;
    }

    public final void setVersion(String version) {
        this.version = version;
    }

    public final String getUserId() {
        return userId;
    }

    public final void setUserId(String value) {
        userId = value;
    }

    public final String getAccessToken() {
        return accessToken;
    }

    public final void setAccessToken(String value) {
        accessToken = value;
    }

    public final Date getDateLastAccessed() {
        return dateLastAccessed;
    }

    public final void setDateLastAccessed(Date value) {
        dateLastAccessed = value;
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