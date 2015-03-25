package mediabrowser.apiinteraction.android.sync.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import mediabrowser.apiinteraction.sync.data.IItemRepository;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.sync.LocalItemInfo;
import mediabrowser.model.sync.LocalItemQuery;

import java.util.ArrayList;

/**
 * Created by Luke on 3/24/2015.
 */
public class ItemRepository extends SQLiteOpenHelper implements IItemRepository {

    private static final String DATABASE_NAME = "Items";
    private static final int DATABASE_VERSION = 1;
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table Items ( Id text primary key, ItemId text not null, ItemType text not null, MediaType text, ServerId text not null, LocalPath text not null, UserIdsWithAccess text, AlbumId text, AlbumName text, SeriesId text, SeriesName text, Json text not null);";
    private IJsonSerializer jsonSerializer;

    public ItemRepository(Context context, IJsonSerializer jsonSerializer) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.jsonSerializer = jsonSerializer;
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){


    }

    @Override
    public void addOrUpdateItem(LocalItem item) {

        ContentValues values = new ContentValues();
        values.put("Id", item.getId());
        values.put("ItemId", item.getItemId());
        values.put("ItemType", item.getItem().getType());
        values.put("MediaType", item.getItem().getMediaType());
        values.put("ServerId", item.getServerId());
        values.put("LocalPath", item.getLocalPath());

        String userIdsWithAccess = tangible.DotNetToJavaStringHelper.join(",", item.getUserIdsWithAccess().toArray(new String[]{}));

        values.put("UserIdsWithAccess", userIdsWithAccess);

        values.put("AlbumId", item.getItem().getAlbumId());
        values.put("AlbumName", item.getItem().getAlbum());
        values.put("SeriesId", item.getItem().getSeriesId());
        values.put("SeriesName", item.getItem().getSeriesName());
        values.put("Json", jsonSerializer.SerializeToString(item));

        try (SQLiteDatabase db = getWritableDatabase()){
            db.replace("Items", null, values);
        }
    }

    @Override
    public LocalItem getItem(String id) {
        String[] cols = new String[] {"Json"};
        String where = "Id=?";
        String[] args = new String[]{id};

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "Item", cols, where, args, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    return jsonSerializer.DeserializeFromString(cursor.getString(0), LocalItem.class);
                }
            }
        }

        return null;
    }

    @Override
    public void deleteItem(String id) {

        try (SQLiteDatabase db = getWritableDatabase()){
            db.delete("Items", "Id=?", new String[]{id});
        }
    }

    @Override
    public ArrayList<String> getServerItemIds(String serverId) {
        ArrayList<String> list = new ArrayList<String>();

        String[] cols = new String[] {"ItemId"};
        String where = "ServerId=?";
        String[] args = new String[]{serverId};

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "Items", cols, where, args, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    list.add(cursor.getString(0));
                }
            }
        }

        return list;
    }

    @Override
    public ArrayList<String> getItemTypes(String serverId, String userId) {

        ArrayList<String> list = new ArrayList<String>();

        String[] cols = new String[] {"ItemType"};
        String where = "ServerId=? and UserIdsWithAccess like %?%";
        String[] args = new String[]{serverId, userId};

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "Items", cols, where, args, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    list.add(cursor.getString(0));
                }
            }
        }

        return list;
    }

    @Override
    public ArrayList<LocalItem> getItems(LocalItemQuery query) {

        ArrayList<LocalItem> list = new ArrayList<LocalItem>();

        String[] cols = new String[] {"Json"};

        ArrayList<String> whereClauses = new ArrayList<>();
        ArrayList<String> whereArgs = new ArrayList<>();

        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getAlbumId()))
        {
            whereClauses.add("AlbumId=?");
            whereArgs.add(query.getAlbumId());
        }
        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getMediaType()))
        {
            whereClauses.add("MediaType=?");
            whereArgs.add(query.getMediaType());
        }
        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getSeriesId()))
        {
            whereClauses.add("SeriesId=?");
            whereArgs.add(query.getSeriesId());
        }
        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getServerId()))
        {
            whereClauses.add("ServerId=?");
            whereArgs.add(query.getServerId());
        }
        if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(query.getType()))
        {
            whereClauses.add("ItemType=?");
            whereArgs.add(query.getType());
        }
        if (query.getExcludeTypes().length > 0)
        {
            // Definitely a poor man's method but this query is unlikely to ever be large enough for it to matter
            for (String excludeType : query.getExcludeTypes()){
                whereClauses.add("ItemType<>?");
                whereArgs.add(excludeType);
            }
        }

        String where = null;
        String[] args = null;

        if (whereClauses.size() > 0){
            where = tangible.DotNetToJavaStringHelper.join(" and ", whereClauses.toArray(new String[]{}));
            args = whereArgs.toArray(new String[]{});
        }

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "Items", cols, where, args, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    LocalItem item = jsonSerializer.DeserializeFromString(cursor.getString(0), LocalItem.class);
                    list.add(item);
                }
            }
        }

        return list;
    }

    @Override
    public ArrayList<LocalItemInfo> getAlbumArtists(String serverId, String userId) {
        return null;
    }

    @Override
    public ArrayList<LocalItemInfo> getTvSeries(String serverId, String userId) {
        ArrayList<LocalItemInfo> list = new ArrayList<LocalItemInfo>();

        String[] cols = new String[] {"ServerId", "SeriesId", "SeriesName"};
        String where = "ServerId=" + serverId + " and SeriesId is not null and UserIdsWithAccess like %?%";
        String[] args = new String[]{serverId, userId};

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "Items", cols, where, args, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    list.add(GetLocalItemInfo(cursor));
                }
            }
        }

        return list;
    }

    @Override
    public ArrayList<LocalItemInfo> getPhotoAlbums(String serverId, String userId) {
        ArrayList<LocalItemInfo> list = new ArrayList<LocalItemInfo>();

        String[] cols = new String[] {"ServerId", "AlbumId", "AlbumName"};
        String where = "ServerId=" + serverId + " and AlbumId is not null and MediaType=? and UserIdsWithAccess like %?%";
        String[] args = new String[]{serverId, "Photo", userId};

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "Items", cols, where, args, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    list.add(GetLocalItemInfo(cursor));
                }
            }
        }

        return list;
    }

    private LocalItemInfo GetLocalItemInfo(Cursor cursor){

        // serverId
        // Id
        // name
        // primary image tag
        LocalItemInfo info = new LocalItemInfo();

        info.setServerId(cursor.getString(0));
        info.setId(cursor.getString(1));
        info.setName(cursor.getString(2));

        return info;
    }
}