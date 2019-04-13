package org.jellyfin.apiclient.interaction;

import org.jellyfin.apiclient.model.apiclient.ServerCredentials;
import org.jellyfin.apiclient.model.logging.ILogger;
import org.jellyfin.apiclient.model.serialization.IJsonSerializer;
import org.jellyfin.apiclient.interaction.ICredentialProvider;
import android.content.Context;
import android.content.SharedPreferences;

public class AndroidCredentialProvider implements ICredentialProvider {

    private IJsonSerializer jsonSerializer;
    private Context context;
    private ILogger logger;

    private static final String settingsKey = "serverCredentials";

    public AndroidCredentialProvider(IJsonSerializer jsonSerializer, Context context, ILogger logger) {
        this.jsonSerializer = jsonSerializer;
        this.context = context;
        this.logger = logger;
    }

    private static SharedPreferences getSharedPreferences(Context context) {

        return context.getSharedPreferences("AndroidCredentialProvider", Context.MODE_PRIVATE);
    }

    @Override
    public ServerCredentials GetCredentials() {

        String json = getSharedPreferences(context).getString(settingsKey, "{}");

        return jsonSerializer.DeserializeFromString(json, ServerCredentials.class);
    }

    @Override
    public void SaveCredentials(ServerCredentials credentials) {

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(settingsKey, jsonSerializer.SerializeToString(credentials));

        // Commit the edits!
        boolean saved = editor.commit();

        if (!saved){
            logger.Error("SharedPreferences.Editor failed to save credentials!");
        }
    }

    public static void Save(String credentialsJson, Context context) {

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(settingsKey, credentialsJson);

        // Commit the edits!
        boolean saved = editor.commit();
    }
}
