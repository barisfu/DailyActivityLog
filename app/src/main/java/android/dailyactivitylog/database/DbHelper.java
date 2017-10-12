package android.dailyactivitylog.database;


import android.content.Context;

import android.dailyactivitylog.database.LogDbSchema.LogTable;
import android.dailyactivitylog.database.LogDbSchema.UserTable;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mack on 04-Oct-17.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String LOG_DATABASE_NAME = "logDatabase.db";

    public DbHelper(Context context) {
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
        db.execSQL("create table " + LogDbSchema.UserTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                UserTable.Cols.UUID + ", " +
                UserTable.Cols.USERNAME + ", " +
                UserTable.Cols.USER_EMAIL + ", " +
                UserTable.Cols.USER_GENDER + ", " +
                UserTable.Cols.USER_ID + ", " +
                UserTable.Cols.USER_COMMENT + ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
