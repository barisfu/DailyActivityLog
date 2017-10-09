package android.dailyactivitylog.database;

import android.content.ContentValues;
import android.content.Context;
import android.dailyactivitylog.User;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        Log.d("addUser", user.toString());

        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserTable.Cols.USER_EMAIL, user.getUserEmail());
        values.put(UserTable.Cols.USER_GENDER, user.getUserGender());
        values.put(UserTable.Cols.USER_ID, user.getUserId());
        values.put(UserTable.Cols.USER_COMMENT, user.getUserComment());
    }

    public void deleteUser(String username) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + UserTable.NAME + " WHERE " + UserTable.Cols.USERNAME + "=\"" + username + "\";");
    }

    public String databaseToString() {
        String dbToString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM" + UserTable.NAME + "WHERE 1";


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.NAME);
        onCreate(db);
    }
}
