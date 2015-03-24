package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.sync.ItemFileInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.users.UserAction;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Luke on 3/3/2015.
 */
public interface ILocalAssetManager {

    void recordUserAction(UserAction action);

    void delete(UserAction action);

    void delete(LocalItem item);

    ArrayList<UserAction> getUserActions(String serverId);

    void addOrUpdate(LocalItem item);

    ArrayList<ItemFileInfo> getFiles(LocalItem item);

    void deleteFile(String path);

    String saveSubtitles(InputStream stream,
                         String format,
                               LocalItem item,
                               String language,
                               boolean isForced);

    LocalItem createLocalItem(BaseItemDto libraryItem, ServerInfo server, String originalFileName);

    LocalItem getLocalItem(String localId);

    LocalItem getLocalItem(String serverId, String itemId);

    boolean fileExists(String path);

    void saveMedia(InputStream stream, LocalItem localItem, ServerInfo server);

    ArrayList<String> getServerItemIds(String serverId);

    void saveOfflineUser(UserDto user);

    void deleteOfflineUser(String id);

    void saveImage(UserDto user, InputStream stream);

    void deleteImage(UserDto user);

    boolean hasImage(UserDto user);

    void saveImage(String serverId, String itemId, String imageId, InputStream stream);

    boolean hasImage(String serverId, String itemId, String imageId);

    boolean hasImage(BaseItemDto item, String imageId);

    ArrayList<BaseItemDto> getViews(String serverId, String userId);

    ArrayList<BaseItemDto> getItems(UserDto user, BaseItemDto parentItem);

    UserDto getUser(String id);
}
