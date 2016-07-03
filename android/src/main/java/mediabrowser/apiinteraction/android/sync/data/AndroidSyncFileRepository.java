package mediabrowser.apiinteraction.android.sync.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;

import java.io.File;

/**
 * Created by Luke on 6/11/2015.
 */
public class AndroidSyncFileRepository extends  AndroidFileRepository {
    public AndroidSyncFileRepository(Context context, ILogger logger) {
        super(context, logger);

        logger.Debug("AndroidSyncFileRepository started. syncPath: %s", getBasePath());
    }

    @Override
    protected String getBasePath() {

        String path = getCustomSyncPath();

        if (path != null){
            return path;
        }

        return super.getBasePath();
    }

    private String getCustomSyncPath(){
        SharedPreferences syncPreferences = context.getSharedPreferences("Sync", Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        String syncPath = syncPreferences.getString("syncPath", null);

        if (syncPath != null && syncPath.length() > 0){
            return syncPath;
        }
        return null;
    }

    @Override
    protected boolean enableDocumentFile(String path){

        File directory = context.getExternalFilesDir(null);

        if (directory == null){
            directory = context.getFilesDir();
        }
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && StringHelper.IndexOfIgnoreCase(path, directory.getAbsolutePath()) != 0 ;
    }
}
