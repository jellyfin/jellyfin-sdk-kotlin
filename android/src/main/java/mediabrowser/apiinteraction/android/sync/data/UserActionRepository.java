package mediabrowser.apiinteraction.android.sync.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import mediabrowser.apiinteraction.sync.data.IUserActionRepository;
import mediabrowser.model.serialization.IJsonSerializer;
import mediabrowser.model.users.UserAction;

import java.util.ArrayList;

/**
 * Created by Luke on 3/24/2015.
 */
public class UserActionRepository extends SQLiteOpenHelper implements IUserActionRepository {

    private IJsonSerializer jsonSerializer;
    private static final String DATABASE_NAME = "UserActions";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table UserActions ( Id text primary key, ServerId text not null, Json text not null);";

    public UserActionRepository(Context context, IJsonSerializer jsonSerializer) {
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
    public void createUserAction(UserAction action) {

        ContentValues values = new ContentValues();
        values.put("Id", action.getId());
        values.put("ServerId", action.getServerId());
        values.put("Json", jsonSerializer.SerializeToString(action));

        try (SQLiteDatabase db = getWritableDatabase()){
            db.insert("UserActions", null, values);
        }
    }

    @Override
    public void deleteUserAction(UserAction action) {

        try (SQLiteDatabase db = getWritableDatabase()){
            db.delete("UserActions", "Id=?", new String[]{action.getId()});
        }
    }

    @Override
    public ArrayList<UserAction> getUserActions(String serverId) {

        ArrayList<UserAction> list = new ArrayList<UserAction>();

        String[] cols = new String[] {"Json"};
        String where = "ServerId=?";
        String[] args = new String[]{serverId};

        try (SQLiteDatabase db = getReadableDatabase()){
            Cursor cursor = db.query(true, "UserActions", cols, where, args, null, null, null, null);

            if (cursor != null) {
                while (cursor.moveToNext()){

                    UserAction action = jsonSerializer.DeserializeFromString(cursor.getString(0), UserAction.class);

                    list.add(action);
                }
            }
        }

        return list;
    }
}