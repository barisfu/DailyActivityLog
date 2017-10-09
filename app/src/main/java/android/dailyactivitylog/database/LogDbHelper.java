package android.dailyactivitylog.database;

import android.content.ContentValues;
import android.content.Context;
import android.dailyactivitylog.User;
import android.dailyactivitylog.database.LogDbSchema.LogTable;
import android.dailyactivitylog.database.LogDbSchema.UserTable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mack on 04-Oct-17.
 */

public class LogDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String LOG_DATABASE_NAME = "logDatabase.db";
    private static final String USER_DATABASE_NAME = "userDatabase.db";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_GENDER = "user gender";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_COMMENT = "comment";

    public LogDbHelper(Context context) {
        super(context, LOG_DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LogTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                LogTable.Cols.UUID + ", " +
                LogTable.Cols.TITLE + ", " +
                LogTable.Cols.DATE + ", " +
                LogTable.Cols.CATEGORY + "," +
                LogTable.Cols.COMMENT +
                ")"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
