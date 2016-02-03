package mediabrowser.apiinteraction.sync.data;

import com.google.common.io.Files;
import mediabrowser.apiinteraction.cryptography.Md5;
import mediabrowser.apiinteraction.sync.data.comparators.SortNameComparator;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.dto.MediaSourceInfo;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.entities.CollectionType;
import mediabrowser.model.entities.ImageType;
import mediabrowser.model.entities.MediaType;
import mediabrowser.model.extensions.ListHelper;
import mediabrowser.model.extensions.StringHelper;
import mediabrowser.model.logging.ILogger;
import mediabrowser.model.mediainfo.MediaProtocol;
import mediabrowser.model.sync.*;
import mediabrowser.model.users.UserAction;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Luke on 3/18/2015.
 */
public class LocalAssetManager implements ILocalAssetManager {

    private IUserActionRepository userActionRepository;
    private IItemRepository itemRepository;
    private IFileRepository fileRepository;
    private IUserRepository userRepository;
    private IImageRepository imageRepository;
    private ILogger logger;

    public LocalAssetManager(IUserActionRepository userActionRepository, IItemRepository itemRepository, IFileRepository fileRepository, IUserRepository userRepository, IImageRepository imageRepository, ILogger logger) {
        this.userActionRepository = userActionRepository;
        this.itemRepository = itemRepository;
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.logger = logger;
    }

    @Override
    public void recordUserAction(UserAction action) {

        action.setId(UUID.randomUUID().toString());

        userActionRepository.createUserAction(action);
    }

    @Override
    public void delete(UserAction action) {
        userActionRepository.deleteUserAction(action);
    }

    @Override
    public void delete(LocalItem item) {
        itemRepository.deleteItem(item.getId());
    }

    @Override
    public ArrayList<UserAction> getUserActions(String serverId) {
        return userActionRepository.getUserActions(serverId);
    }

    @Override
    public void addOrUpdate(LocalItem item) {
        itemRepository.addOrUpdateItem(item);
    }

    @Override
    public ArrayList<ItemFileInfo> getFiles(LocalItem item) {

        String path = item.getLocalPath();
        path = fileRepository.getParentDirectoryPath(path);

        ArrayList<DeviceFileInfo> list = fileRepository.getFileSystemEntries(path);

        ArrayList<ItemFileInfo> itemFiles = new ArrayList<ItemFileInfo>();

        String name = Files.getNameWithoutExtension(item.getLocalPath());

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

    @Override
    public void saveMedia(InputStream stream, LocalItem localItem, ServerInfo server) throws IOException {

        logger.Debug("Saving media to " + localItem.getLocalPath());
        fileRepository.saveFile(stream, localItem.getLocalPath());
    }

    private static String[] SupportedImageExtensions = { ".png", ".jpg", ".jpeg", ".webp" };
    private boolean isImageFile(String path)
    {
        String ext = Files.getFileExtension(path);

        return ext != null && ListHelper.ContainsIgnoreCase(SupportedImageExtensions, ext);
    }

    private static String[] SupportedSubtitleExtensions = { ".srt", ".vtt" };
    private boolean isSubtitleFile(String path)
    {
        String ext = Files.getFileExtension(path);

        return ext != null && ListHelper.ContainsIgnoreCase(SupportedSubtitleExtensions, ext);
    }

    @Override
    public void deleteFile(String path) {
        fileRepository.deleteFile(path);
    }

    @Override
    public String saveSubtitles(InputStream stream, String format, LocalItem item, String language, boolean isForced) throws IOException {

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

        String name = Files.getNameWithoutExtension(path);

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

        ArrayList<String> path = getDirectoryPath(libraryItem, server);
        path.add(getLocalFileName(libraryItem, originalFileName));

        LocalItem item = new LocalItem();

        String localPath = fileRepository.getFullLocalPath(path);

        item.setLocalPath(localPath);

        for (MediaSourceInfo mediaSource : libraryItem.getMediaSources())
        {
            mediaSource.setPath(localPath);
            mediaSource.setProtocol(MediaProtocol.File);
        }

        item.setServerId(server.getId());
        item.setItem(libraryItem);
        item.setItemId(libraryItem.getId());
        item.setId(getLocalId(item.getServerId(), item.getItemId()));
        return item;
    }

    private ArrayList<String> getDirectoryPath(BaseItemDto item, ServerInfo server)
    {
        ArrayList<String> parts = new ArrayList<String>();
        parts.add(server.getName());

        if (item.IsType("episode"))
        {
            parts.add("TV");
            parts.add(item.getSeriesName());

            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(item.getSeasonName()))
            {
                parts.add(item.getSeasonName());
            }
        }
        else if (item.getIsVideo())
        {
            parts.add("Videos");
            parts.add(item.getName());
        }
        else if (item.getIsAudio())
        {
            parts.add("Music");

            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(item.getAlbumArtist()))
            {
                parts.add(item.getAlbumArtist());
            }

            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(item.getAlbumId()))
            {
                parts.add(item.getAlbum());
            }
        }
        else if (StringHelper.EqualsIgnoreCase(item.getMediaType(), MediaType.Photo))
        {
            parts.add("Photos");

            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(item.getAlbumId()))
            {
                parts.add(item.getAlbum());
            }
        }

        ArrayList<String> finalParts = new ArrayList<String>();

        for (String part : parts){
            finalParts.add(fileRepository.getValidFileName(part));
        }

        return finalParts;
    }

    private String getLocalFileName(BaseItemDto item, String originalFileName)
    {
        String filename = originalFileName;

        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(filename))
        {
            filename = item.getName();
        }

        return fileRepository.getValidFileName(filename);
    }

    @Override
    public LocalItem getLocalItem(String localId) {
        return itemRepository.getItem(localId);
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
        userRepository.addOrUpdateUser(user.getId(), user);
    }

    @Override
    public void deleteOfflineUser(String id) {
        userRepository.deleteUser(id);
    }

    @Override
    public void saveImage(UserDto user, InputStream stream) throws Exception {
        deleteImage(user);

        imageRepository.saveImage(getImageRepositoryId(user.getServerId(), user.getId()), user.getPrimaryImageTag(), stream);
    }

    @Override
    public void saveImage(String serverId, String itemId, String imageId, InputStream stream) throws Exception {
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
            return getMusicArtists(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "MusicArtist"))
        {
            return getMusicAlbums(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "MusicAlbum"))
        {
            return getAlbumSongs(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "PhotosView"))
        {
            return getPhotoAlbums(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "PhotoAlbum"))
        {
            return getPhotos(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "VideosView"))
        {
            return getVideos(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "TVView"))
        {
            return getTvSeries(user, parentItem);
        }
        if (StringHelper.EqualsIgnoreCase(parentItem.getType(), "Series"))
        {
            return getTvEpisodes(user, parentItem);
        }

        return new ArrayList<BaseItemDto>();
    }

    private ArrayList<BaseItemDto> getMusicArtists(UserDto user, BaseItemDto parentItem)
    {
        ArrayList<LocalItemInfo> artists = itemRepository.getAlbumArtists(user.getServerId(), user.getId());

        ArrayList<BaseItemDto> newList = new ArrayList<>();

        for (LocalItemInfo item : artists){

            BaseItemDto showItem = new BaseItemDto();
            showItem.setId(item.getId());
            showItem.setName(item.getName());
            showItem.setSortName(item.getName());
            showItem.setType("MusicArtist");
            showItem.setServerId(item.getServerId());

            showItem.setImageTags(new HashMap<ImageType, String>());
            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(item.getPrimaryImageTag())) {
                showItem.getImageTags().put(ImageType.Primary, item.getPrimaryImageTag());
            }

            newList.add(showItem);
        }

        Collections.sort(newList, new SortNameComparator());

        return newList;
    }

    private ArrayList<BaseItemDto> getMusicAlbums(UserDto user, BaseItemDto parentItem)
    {
        LocalItemQuery query = new LocalItemQuery();
        query.setServerId(user.getServerId());
        query.setAlbumArtistId(parentItem.getId());
        query.setType("Audio");

        ArrayList<LocalItem> items = itemRepository.getItems(query);

        items = filterByUserAccess(items, user);

        HashMap<String, List<BaseItemDto>> dict = new HashMap<>();

        for (LocalItem item : items)
        {
            String albumId = item.getItem().getAlbumId();

            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(albumId))
            {
                ArrayList<BaseItemDto> subItems;
                if (dict.containsValue(albumId))
                {
                    subItems = (ArrayList<BaseItemDto>)dict.get(albumId);
                }
                else{
                    subItems = new ArrayList<BaseItemDto>();
                    dict.put(albumId, subItems);
                }
                subItems.add(item.getItem());
            }
        }

        ArrayList<BaseItemDto> newList = new ArrayList<>();

        for (String albumId : dict.keySet()){

            ArrayList<BaseItemDto> subItems = (ArrayList<BaseItemDto>)dict.get(albumId);

            BaseItemDto showItem = new BaseItemDto();
            showItem.setId(albumId);
            showItem.setName(subItems.get(0).getAlbum());
            showItem.setSortName(showItem.getName());
            showItem.setType("MusicAlbum");
            showItem.setServerId(user.getServerId());

            showItem.setSongCount(subItems.size());
            showItem.setChildCount(showItem.getSongCount());

            showItem.setAlbumPrimaryImageTag(subItems.get(0).getAlbumPrimaryImageTag());

            showItem.setImageTags(new HashMap<ImageType, String>());
            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(showItem.getAlbumPrimaryImageTag()))
            {
                showItem.getImageTags().put(ImageType.Primary, showItem.getAlbumPrimaryImageTag());
            }

            ArrayList<String> genres = new ArrayList<>();
            ArrayList<String> artists = new ArrayList<>();

            for (BaseItemDto subItem : subItems){
                for (String text : subItem.getGenres()){
                    if (!ListHelper.ContainsIgnoreCase(genres, text)){
                        genres.add(text);
                    }
                }
                for (String text : subItem.getArtists()){
                    if (!ListHelper.ContainsIgnoreCase(artists, text)){
                        artists.add(text);
                    }
                }
            }

            showItem.setGenres(genres);
            showItem.setArtists(artists);

            newList.add(showItem);
        }

        Collections.sort(newList, new SortNameComparator());

        return newList;
    }

    private ArrayList<BaseItemDto> getAlbumSongs(UserDto user, BaseItemDto parentItem)
    {
        LocalItemQuery query = new LocalItemQuery();
        query.setServerId(user.getServerId());
        query.setAlbumId(parentItem.getId());
        query.setMediaType("Audio");

        ArrayList<LocalItem> items = itemRepository.getItems(query);

        items = filterByUserAccess(items, user);

        ArrayList<BaseItemDto> newList = new ArrayList<>();

        for (LocalItem item : items){

            newList.add(item.getItem());
        }

        Collections.sort(newList, new SortNameComparator());

        return newList;
    }

    private ArrayList<BaseItemDto> getPhotoAlbums(UserDto user, BaseItemDto parentItem)
    {
        ArrayList<LocalItemInfo> shows = itemRepository.getPhotoAlbums(user.getServerId(), user.getId());

        ArrayList<BaseItemDto> newList = new ArrayList<>();

        for (LocalItemInfo item : shows){

            BaseItemDto showItem = new BaseItemDto();
            showItem.setId(item.getId());
            showItem.setName(item.getName());
            showItem.setSortName(item.getName());
            showItem.setType("PhotoAlbum");
            showItem.setServerId(item.getServerId());

            showItem.setImageTags(new HashMap<ImageType, String>());
            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(item.getPrimaryImageTag()))
            {
                showItem.getImageTags().put(ImageType.Primary, item.getPrimaryImageTag());
            }

            newList.add(showItem);
        }

        Collections.sort(newList, new SortNameComparator());

        return newList;
    }

    private ArrayList<BaseItemDto> getPhotos(UserDto user, BaseItemDto parentItem)
    {
        LocalItemQuery query = new LocalItemQuery();
        query.setServerId(user.getServerId());
        query.setAlbumId(parentItem.getId());
        query.setMediaType("Photo");

        ArrayList<LocalItem> items = itemRepository.getItems(query);

        items = filterByUserAccess(items, user);

        ArrayList<BaseItemDto> newList = new ArrayList<>();

        for (LocalItem item : items){

            newList.add(item.getItem());
        }

        Collections.sort(newList, new SortNameComparator());

        return newList;
    }

    private ArrayList<BaseItemDto> getTvSeries(UserDto user, BaseItemDto parentItem)
    {
        ArrayList<LocalItemInfo> shows = itemRepository.getTvSeries(user.getServerId(), user.getId());

        ArrayList<BaseItemDto> newList = new ArrayList<>();

        for (LocalItemInfo item : shows){

            BaseItemDto showItem = new BaseItemDto();
            showItem.setId(item.getId());
            showItem.setName(item.getName());
            showItem.setSortName(item.getName());
            showItem.setType("Series");
            showItem.setServerId(item.getServerId());

            showItem.setImageTags(new HashMap<ImageType, String>());
            if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(item.getPrimaryImageTag()))
            {
                showItem.getImageTags().put(ImageType.Primary, item.getPrimaryImageTag());
            }

            newList.add(showItem);
        }

        Collections.sort(newList, new SortNameComparator());

        return newList;
    }

    private ArrayList<BaseItemDto> getTvEpisodes(UserDto user, BaseItemDto parentItem)
    {
        LocalItemQuery query = new LocalItemQuery();
        query.setServerId(user.getServerId());
        query.setSeriesId(parentItem.getId());
        query.setMediaType("Video");
        query.setType("Episode");

        ArrayList<LocalItem> items = itemRepository.getItems(query);

        items = filterByUserAccess(items, user);

        ArrayList<BaseItemDto> newList = new ArrayList<>();

        for (LocalItem item : items){

            newList.add(item.getItem());
        }

        Collections.sort(newList, new SortNameComparator());

        return newList;
    }

    private ArrayList<BaseItemDto> getVideos(UserDto user, BaseItemDto parentItem)
    {
        LocalItemQuery query = new LocalItemQuery();
        query.setServerId(user.getServerId());
        query.setMediaType("Video");
        query.setExcludeTypes(new String[] { "Episode" });

        ArrayList<LocalItem> items = itemRepository.getItems(query);

        items = filterByUserAccess(items, user);

        ArrayList<BaseItemDto> newList = new ArrayList<>();

        for (LocalItem item : items){

            newList.add(item.getItem());
        }

        Collections.sort(newList, new SortNameComparator());

        return newList;
    }

    private ArrayList<LocalItem> filterByUserAccess(ArrayList<LocalItem> items, UserDto user)
    {
        ArrayList<LocalItem> newList = new ArrayList<>();

        for (LocalItem item : items){

            if (ListHelper.ContainsIgnoreCase(item.getUserIdsWithAccess(), user.getId())) {
                newList.add(item);
            }
        }
        return newList;
    }

    @Override
    public UserDto getUser(String id) {
        return userRepository.getUser(id);
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
