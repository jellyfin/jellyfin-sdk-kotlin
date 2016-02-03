package mediabrowser.apiinteraction.android.sync.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import mediabrowser.apiinteraction.sync.data.IUserRepository;
import mediabrowser.model.dto.UserDto;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.users.UserAction;

import java.util.ArrayList;

/**
 * Created by Luke on 3/24/2015.
 */
public class UserRepository extends SQLiteOpenHelper implements IUserRepository {

    private IJsonSerializer jsonSerializer;
    private static final String DATABASE_NAME = "Users";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table Users ( Id text primary key, ServerId text not null, Json text not null);";

    public UserRepository(Context context, IJsonSerializer jsonSerializer) {
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
    public void addOrUpdateUser(String id, UserDto user) {

        ContentValues values = new ContentValues();
        values.put("Id", user.getId());
        values.put("ServerId", user.getServerId());
        values.put("Json", jsonSerializer.SerializeToString(user));

        try (SQLiteDatabase db = getWritableDatabase()){
            db.replace("Users", null, values);
        }
    }

    @Override
    public void deleteUser(String id) {

        try (SQLiteDatabase db = getWritableDatabase()){
            db.delete("Users", "Id=?", new String[]{id});
        }
    }

    @Override
    public UserDto getUser(String id) {

        String[] cols = new String[] {"Json"};
        String where = "Id=?";
        String[] args = new String[]{id};

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "Users", cols, where, args, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    return jsonSerializer.DeserializeFromString(cursor.getString(0), UserDto.class);
                }
            }
        }

        return null;
    }

    @Override
    public ArrayList<UserDto> getAllUsers() {
        ArrayList<UserDto> list = new ArrayList<UserDto>();

        String[] cols = new String[] {"Json"};

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "Users", cols, null, null, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    UserDto item = jsonSerializer.DeserializeFromString(cursor.getString(0), UserDto.class);

                    list.add(item);
                }
            }
        }

        return list;
    }
}