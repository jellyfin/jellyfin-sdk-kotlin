package mediabrowser.apiinteraction.sync.data;

import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.sync.LocalItemInfo;
import mediabrowser.model.sync.LocalItemQuery;

import java.util.ArrayList;

public interface IItemRepository {

    void addOrUpdate(LocalItem item);

    LocalItem get(String id);

    void delete(String id);

    ArrayList<String> getServerItemIds(String serverId);

    ArrayList<String> getItemTypes(String serverId, String userId);

    ArrayList<LocalItem> getItems(LocalItemQuery query);

    ArrayList<String> getAlbumArtists(String serverId, String userId);

    ArrayList<LocalItemInfo> getTvSeries(String serverId, String userId);

    ArrayList<LocalItemInfo> getPhotoAlbums(String serverId, String userId);

}
