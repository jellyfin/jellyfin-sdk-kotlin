package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.dto.UserDto;

/**
 * Created by Luke on 3/3/2015.
 */
public interface ILocalAssetManager {

    boolean hasImage(UserDto user);

    boolean hasImage(String serverId, String itemId, String imageId);

    boolean hasImage(BaseItemDto item, String imageId);

    void deleteImage(UserDto user);
}
