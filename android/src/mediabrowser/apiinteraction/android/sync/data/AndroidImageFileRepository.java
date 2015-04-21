package mediabrowser.apiinteraction.android.sync.data;

import android.content.Context;
import mediabrowser.apiinteraction.sync.data.ImageFileRepository;
import mediabrowser.model.logging.ILogger;

import java.io.File;

/**
 * Created by Luke on 3/25/2015.
 */
public class AndroidImageFileRepository extends ImageFileRepository {

    private Context context;

    public AndroidImageFileRepository(Context context, ILogger logger) {
        super(logger);
        this.context = context;
    }

    @Override
    protected String getBasePath() {
        File file = context.getFilesDir();

        return new File(file, "images").getPath();
    }
}
