package mediabrowser.apiinteraction.android.sync.data;

import android.content.Context;
import android.os.Environment;
import mediabrowser.apiinteraction.sync.data.FileRepository;
import mediabrowser.model.logging.ILogger;

import java.io.File;

/**
 * Created by Luke on 3/25/2015.
 */
public class AndroidFileRepository extends FileRepository {

    protected Context context;

    public AndroidFileRepository(Context context, ILogger logger) {
        super(logger);
        this.context = context;
    }

    @Override
    protected String getBasePath() {

        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "emby");
        File file = new File(directory, "sync");

        return file.getPath();
    }
}
