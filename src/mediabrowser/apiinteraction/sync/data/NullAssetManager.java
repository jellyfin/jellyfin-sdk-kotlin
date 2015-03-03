package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.dto.UserDto;

public class NullAssetManager implements ILocalAssetManager {
    @Override
    public boolean hasImage(UserDto user) {
        return false;
    }

    @Override
    public boolean hasImage(String serverId, String itemId, String imageId) {
        return false;
    }

    @Override
    public boolean hasImage(BaseItemDto item, String imageId) {
        return false;
    }

    @Override
    public void deleteImage(UserDto user) {

    }
}
