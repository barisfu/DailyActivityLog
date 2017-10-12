package android.dailyactivitylog.database;

import android.content.ContentValues;
import android.content.Context;
import android.dailyactivitylog.User;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.util.Log;
import android.dailyactivitylog.database.LogDbSchema.UserTable;

/**
 * Created by mdc010 on 10/10/2017.
 */

public class UserDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userDatabase.db";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER_NAME = "username";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LogDbSchema.UserTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                UserTable.Cols.USERNAME + ", " +
                UserTable.Cols.USER_EMAIL + ", " +
                UserTable.Cols.USER_GENDER + ", " +
                UserTable.Cols.USER_ID + ", " +
                UserTable.Cols.USER_COMMENT + ")");
    }

    /**
     * Adds user details to the database.
     * @param user
     */
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserTable.Cols.USER_EMAIL, user.getUserEmail());
        values.put(UserTable.Cols.USER_GENDER, user.getUserGender());
        values.put(UserTable.Cols.USER_ID, user.getUserId());
        values.put(UserTable.Cols.USER_COMMENT, user.getUserComment());
        Log.d("addUser", user.toString() + " " + UserTable.Cols.USERNAME.toString());
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + UserTable.NAME);
    }

    public User assignUser() {
        User user = new User();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserTable.NAME, null);
        UserCursorWrapper cursorWrapper = new UserCursorWrapper(cursor);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            user = cursorWrapper.getUser();
        }
        return user;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.NAME);
        onCreate(db);
    }
}
