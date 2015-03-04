package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.sync.ItemFileInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.users.UserAction;

import java.io.InputStream;
import java.util.ArrayList;

public class NullAssetManager implements ILocalAssetManager {


    @Override
    public void recordUserAction(UserAction action) {

    }

    @Override
    public void delete(UserAction action) {

    }

    @Override
    public void delete(LocalItem item) {

    }

    @Override
    public ArrayList<UserAction> getUserActions(String serverId) {
        return null;
    }

    @Override
    public void addOrUpdate(LocalItem item) {

    }

    @Override
    public ArrayList<ItemFileInfo> getFiles(LocalItem item) {
        return null;
    }

    @Override
    public void deleteFile(String path) {

    }

    @Override
    public String saveSubtitles(InputStream stream, String format, LocalItem item, String language, boolean isForced) {
        return null;
    }

    @Override
    public void saveSubtitles(InputStream stream, LocalItem localItem, ServerInfo server) {

    }

    @Override
    public LocalItem createLocalItem(BaseItemDto libraryItem, ServerInfo server, String originalFileName) {
        return null;
    }

    @Override
    public LocalItem getLocalItem(String localId) {
        return null;
    }

    @Override
    public LocalItem getLocalItem(String serverId, String itemId) {
        return null;
    }

    @Override
    public boolean fileExists(String path) {
        return false;
    }

    @Override
    public ArrayList<String> getServerItemIds(String serverId) {
        return null;
    }

    @Override
    public void saveOfflineUser(UserDto user) {

    }

    @Override
    public void deleteOfflineUser(String id) {

    }

    @Override
    public void saveImage(UserDto user, InputStream stream) {

    }

    @Override
    public void deleteImage(UserDto user) {

    }

    @Override
    public boolean hasImage(UserDto user) {
        return false;
    }

    @Override
    public void saveImage(String serverId, String itemId, String imageId, InputStream stream) {

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
    public ArrayList<BaseItemDto> getViews(String serverId, String userId) {
        return null;
    }

    @Override
    public ArrayList<BaseItemDto> getItems(UserDto user, BaseItemDto parentItem) {
        return null;
    }

    @Override
    public UserDto getUser(String id) {
        return null;
    }
}
