package mediabrowser.apiinteraction.android.sync.data;

import android.content.Context;
import mediabrowser.apiinteraction.sync.data.*;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.serialization.IJsonSerializer;

/**
 * Created by Luke on 3/24/2015.
 */
public class AndroidAssetManager extends LocalAssetManager {

    public AndroidAssetManager(Context context, ILogger logger, IJsonSerializer jsonSerializer, IFileRepository fileRepository) {

        super(new UserActionRepository(context, jsonSerializer),
                new ItemRepository(context, jsonSerializer),
                fileRepository,
                new UserRepository(context, jsonSerializer),
                new AndroidImageFileRepository(context, logger),
                logger);
    }
}
