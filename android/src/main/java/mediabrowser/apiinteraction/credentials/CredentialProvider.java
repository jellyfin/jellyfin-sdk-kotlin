package mediabrowser.apiinteraction.credentials;

import mediabrowser.apiinteraction.ICredentialProvider;
import mediabrowser.model.apiclient.ServerCredentials;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;

/**
 * Created by Luke on 4/5/2015.
 */
public class CredentialProvider implements ICredentialProvider {

    private IJsonSerializer jsonSerializer;
    private String filePath;

    public CredentialProvider(IJsonSerializer jsonSerializer, String filePath) {
        this.jsonSerializer = jsonSerializer;
        this.filePath = filePath;
    }

    @Override
    public ServerCredentials GetCredentials() {

        return (ServerCredentials)jsonSerializer.DeserializeFromFile(ServerCredentials.class, filePath);
    }

    @Override
    public void SaveCredentials(ServerCredentials credentials) {

        jsonSerializer.SerializeToFile(credentials, filePath);
    }

}
