package mediabrowser.apiinteraction.android.sync.data;

import android.content.Context;
import android.os.Environment;
import mediabrowser.apiinteraction.sync.data.FileRepository;

import java.io.File;

/**
 * Created by Luke on 3/25/2015.
 */
public class AndroidFileRepository extends FileRepository {

    private Context context;

    public AndroidFileRepository(Context context) {
        this.context = context;
    }

    @Override
    protected String getBasePath() {

        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "emby");
        File file = new File(directory, "sync");

        return file.getPath();
    }
}
