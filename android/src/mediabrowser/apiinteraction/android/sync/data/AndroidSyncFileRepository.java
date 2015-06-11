package mediabrowser.apiinteraction.android.sync.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import mediabrowser.model.logging.ILogger;

import java.io.File;

/**
 * Created by Luke on 6/11/2015.
 */
public class AndroidSyncFileRepository extends  AndroidFileRepository {
    public AndroidSyncFileRepository(Context context, ILogger logger) {
        super(context, logger);
    }

    @Override
    protected String getBasePath() {

        SharedPreferences syncPreferences = context.getSharedPreferences("Sync", Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        String syncPath = syncPreferences.getString("syncPath", null);

        if (syncPath != null && syncPath.length() > 0){
            return syncPath;
        }

        return super.getBasePath();
    }
}
