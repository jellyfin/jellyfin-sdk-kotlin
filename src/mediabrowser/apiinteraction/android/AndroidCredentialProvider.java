package mediabrowser.apiinteraction.android;

import mediabrowser.model.apiclient.ServerCredentials;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.apiinteraction.ICredentialProvider;
import android.content.Context;
import android.content.SharedPreferences;

public class AndroidCredentialProvider implements ICredentialProvider {

    private IJsonSerializer jsonSerializer;
    private Context context;

    public AndroidCredentialProvider(IJsonSerializer jsonSerializer, Context context) {
        this.jsonSerializer = jsonSerializer;
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {

        return context.getSharedPreferences("AndroidCredentialProvider", Context.MODE_PRIVATE);
    }

    @Override
    public ServerCredentials GetCredentials() {

        String json = getSharedPreferences().getString("serverCredentials", "{}");

        return jsonSerializer.DeserializeFromString(json, ServerCredentials.class);
    }

    @Override
    public void SaveCredentials(ServerCredentials credentials) {

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences();
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("serverCredentials", jsonSerializer.SerializeToString(credentials));

        // Commit the edits!
        editor.commit();
    }

}
