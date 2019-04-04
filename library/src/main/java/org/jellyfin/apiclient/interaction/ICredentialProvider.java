package org.jellyfin.apiclient.interaction;

import org.jellyfin.apiclient.model.apiclient.ServerCredentials;

public interface ICredentialProvider {

    ServerCredentials GetCredentials();

    void SaveCredentials(ServerCredentials credentials);
}
