package android.dailyactivitylog.database;

import android.content.Context;
import android.dailyactivitylog.database.LogDbSchema.LogTable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mack on 04-Oct-17.
 */

public class LogDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public LogDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LogTable.NAME+ "(" +
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
