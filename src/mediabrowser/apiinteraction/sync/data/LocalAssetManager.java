package mediabrowser.apiinteraction.sync.data;

import mediabrowser.apiinteraction.cryptography.Md5;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.entities.CollectionType;
import mediabrowser.model.extensions.ListHelper;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.sync.DeviceFileInfo;
import mediabrowser.model.sync.ItemFileInfo;
import mediabrowser.model.sync.ItemFileType;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.users.UserAction;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Luke on 3/18/2015.
 */
public class LocalAssetManager implements ILocalAssetManager {

    private IUserActionRepository userActionRepository;
    private IItemRepository itemRepository;
    private IFileRepository fileRepository;
    private IUserRepository userRepository;
    private IImageRepository imageRepository;

    public LocalAssetManager(IUserActionRepository userActionRepository, IItemRepository itemRepository, IFileRepository fileRepository, IUserRepository userRepository, IImageRepository imageRepository) {
        this.userActionRepository = userActionRepository;
        this.itemRepository = itemRepository;
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public void recordUserAction(UserAction action) {

        action.setId(UUID.randomUUID().toString());

        userActionRepository.create(action);
    }

    @Override
    public void delete(UserAction action) {
        userActionRepository.delete(action);
    }

    @Override
    public void delete(LocalItem item) {
        itemRepository.delete(item.getId());
    }

    @Override
    public ArrayList<UserAction> getUserActions(String serverId) {
        return userActionRepository.get(serverId);
    }

    @Override
    public void addOrUpdate(LocalItem item) {
        itemRepository.addOrUpdate(item);
    }

    @Override
    public ArrayList<ItemFileInfo> getFiles(LocalItem item) {

        String path = item.getLocalPath();
        path = fileRepository.getParentDirectoryPath(path);

        ArrayList<DeviceFileInfo> list = fileRepository.getFileSystemEntries(path);

        ArrayList<ItemFileInfo> itemFiles = new ArrayList<ItemFileInfo>();

        String name = FilenameUtils.getBaseName(item.getLocalPath());

        for (DeviceFileInfo file : list)
        {
            if (StringHelper.IndexOfIgnoreCase(file.getName(), name) == -1){
                continue;
            }
            ItemFileInfo itemFile = new ItemFileInfo();
            itemFile.setPath(file.getPath());
            itemFile.setName(file.getName());

            if (isSubtitleFile(file.getName()))
            {
                itemFile.setType(ItemFileType.Subtitles);
            }
            else if (!isImageFile(file.getName()))
            {
                itemFile.setType(ItemFileType.Media);
            }

            itemFiles.add(itemFile);
        }

        return itemFiles;
    }

    private static String[] SupportedImageExtensions = { ".png", ".jpg", ".jpeg", ".webp" };
    private boolean isImageFile(String path)
    {
        String ext = FilenameUtils.getExtension(path);

        return ext != null && ListHelper.ContainsIgnoreCase(SupportedImageExtensions, ext);
    }

    private static String[] SupportedSubtitleExtensions = { ".srt", ".vtt" };
    private boolean isSubtitleFile(String path)
    {
        String ext = FilenameUtils.getExtension(path);

        return ext != null && ListHelper.ContainsIgnoreCase(SupportedSubtitleExtensions, ext);
    }

    @Override
    public void deleteFile(String path) {
        fileRepository.deleteFile(path);
    }

    @Override
    public String saveSubtitles(InputStream stream, String format, LocalItem item, String language, boolean isForced) {

        String path = item.getLocalPath();

        String filename = getSubtitleSaveFileName(item, language, isForced) + "." + format.toLowerCase();

        String parentPath = fileRepository.getParentDirectoryPath(path);

        path = new File(parentPath, filename).getPath();

        fileRepository.saveFile(stream, path);

        return path;
    }

    private String getSubtitleSaveFileName(LocalItem item, String language, boolean isForced)
    {
        String path = item.getLocalPath();

        String name = FilenameUtils.getBaseName(path);

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(language))
        {
            name += "." + language.toLowerCase();
        }

        if (isForced)
        {
            name += ".foreign";
        }

        return name;
    }

    @Override
    public LocalItem createLocalItem(BaseItemDto libraryItem, ServerInfo server, String originalFileName) {
        LocalItem item = new LocalItem();

        item.setServerId(server.getId());
        item.setItem(libraryItem);
        item.setItemId(libraryItem.getId());
        return item;
    }

    @Override
    public LocalItem getLocalItem(String localId) {
        return itemRepository.get(localId);
    }

    @Override
    public LocalItem getLocalItem(String serverId, String itemId) {
        return getLocalItem(getLocalId(serverId, itemId));
    }

    @Override
    public boolean fileExists(String path) {
        return fileRepository.fileExists(path);
    }

    @Override
    public ArrayList<String> getServerItemIds(String serverId) {
        return itemRepository.getServerItemIds(serverId);
    }

    @Override
    public void saveOfflineUser(UserDto user) {
        userRepository.addOrUpdate(user.getId(), user);
    }

    @Override
    public void deleteOfflineUser(String id) {
        userRepository.delete(id);
    }

    @Override
    public void saveImage(UserDto user, InputStream stream) {
        deleteImage(user);

        imageRepository.saveImage(getImageRepositoryId(user.getServerId(), user.getId()), user.getPrimaryImageTag(), stream);
    }

    @Override
    public void saveImage(String serverId, String itemId, String imageId, InputStream stream) {
        imageRepository.saveImage(getImageRepositoryId(serverId, itemId), imageId, stream);
    }

    @Override
    public void deleteImage(UserDto user) {
        imageRepository.deleteImages(getImageRepositoryId(user.getServerId(), user.getId()));
    }

    @Override
    public boolean hasImage(UserDto user) {
        return imageRepository.hasImage(getImageRepositoryId(user.getServerId(), user.getId()), user.getPrimaryImageTag());
    }

    @Override
    public boolean hasImage(String serverId, String itemId, String imageId) {
        return imageRepository.hasImage(getImageRepositoryId(serverId, itemId), imageId);
    }

    @Override
    public boolean hasImage(BaseItemDto item, String imageId) {
        return hasImage(item.getServerId(), item.getId(), imageId);
    }

    @Override
    public ArrayList<BaseItemDto> getViews(String serverId, String userId) {

        ArrayList<BaseItemDto> list = new ArrayList<BaseItemDto>();

        ArrayList<String> types = itemRepository.getItemTypes(serverId, userId);

        if (ListHelper.ContainsIgnoreCase(types, "Audio"))
        {
            BaseItemDto item = new BaseItemDto();
            item.setName("Music");
            item.setServerId(serverId);
            item.setId("MusicView");
            item.setType("MusicView");
            item.setCollectionType(CollectionType.Music);
            list.add(item);
        }

        if (ListHelper.ContainsIgnoreCase(types, "Photo"))
        {
            BaseItemDto item = new BaseItemDto();
            item.setName("Photos");
            item.setServerId(serverId);
            item.setId("PhotosView");
            item.setType("PhotosView");
            item.setCollectionType(CollectionType.Photos);
            list.add(item);
        }

        if (ListHelper.ContainsIgnoreCase(types, "Episode"))
        {
            BaseItemDto item = new BaseItemDto();
            item.setName("TV");
            item.setServerId(serverId);
            item.setId("TVView");
            item.setType("TVView");
            item.setCollectionType(CollectionType.TvShows);
            list.add(item);
        }

        if (ListHelper.ContainsIgnoreCase(types, "Video") ||
                ListHelper.ContainsIgnoreCase(types, "Movie") ||
                ListHelper.ContainsIgnoreCase(types, "MusicVideo"))
        {
            BaseItemDto item = new BaseItemDto();
            item.setName("Videos");
            item.setServerId(serverId);
            item.setId("VideosView");
            item.setType("VideosView");
            item.setCollectionType(CollectionType.HomeVideos);
            list.add(item);
        }

        return list;
    }

    @Override
    public ArrayList<BaseItemDto> getItems(UserDto user, BaseItemDto parentItem) {

        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "MusicView"))
        {
            //return getMusicArtists(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "MusicArtist"))
        {
            //return GetMusicAlbums(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "MusicAlbum"))
        {
            //return GetAlbumSongs(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "PhotosView"))
        {
            //return GetPhotoAlbums(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "PhotoAlbum"))
        {
            //return GetPhotos(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "VideosView"))
        {
            //return GetVideos(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "TVView"))
        {
            //return GetTvSeries(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "Series"))
        {
            //return GetTvEpisodes(user, parentItem);
        }

        return new ArrayList<BaseItemDto>();
    }

/*    private ArrayList<BaseItemDto> getMusicArtists(UserDto user, BaseItemDto parentItem)
    {
        ArrayList<String> artists = itemRepository.getAlbumArtists(user.getServerId(), user.getId());

        return artists
                .OrderBy(i => i)
        .Select(i => new BaseItemDto
        {
            Name = i,
                    Id = i,
                    Type = "MusicArtist",
                    ServerId = user.ServerId
        })
        .ToList();
    }*/

    @Override
    public UserDto getUser(String id) {
        return userRepository.get(id);
    }

    private String getImageRepositoryId(String serverId, String itemId)
    {
        return getLocalId(serverId, itemId);
    }

    private String getLocalId(String serverId, String itemId)
    {
        try {
            return Md5.getHash(serverId + itemId);
        }
        catch (Exception ex){
            return null;
        }
    }
}
