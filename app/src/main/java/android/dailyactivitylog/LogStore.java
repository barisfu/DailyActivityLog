package android.dailyactivitylog;

import android.content.ContentValues;
import android.content.Context;
import android.dailyactivitylog.database.LogCursorWrapper;
import android.dailyactivitylog.database.DbHelper;
import android.dailyactivitylog.database.DbSchemas.LogTable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mack on 26-Sep-17.
 */
public class LogStore {
    private static LogStore sLogList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static LogStore get(Context context) {
        if(sLogList == null) {
            sLogList = new LogStore(context);
        }
        return sLogList;
    }

    private LogStore(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new DbHelper(mContext).getWritableDatabase();
    }

    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<>();
        LogCursorWrapper cursor = queryLogs(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                logs.add(cursor.getLog());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return logs;
    }

    public Log getLog(UUID id) {
        LogCursorWrapper cursor = queryLogs(
                LogTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLog();
        } finally {
            cursor.close();
        }
    }

    public void addLog(Log l) {
        ContentValues values = getContentValues(l);
        mDatabase.insert(LogTable.NAME, null, values);
    }

     private static ContentValues getContentValues(Log log) {
         ContentValues values = new ContentValues();
         values.put(LogTable.Cols.UUID, log.getId().toString());
         values.put(LogTable.Cols.CATEGORY, log.getCategoryPosition());
         values.put(LogTable.Cols.TITLE, log.getTitle());
         values.put(LogTable.Cols.DATE, log.getFormattedDate());
         values.put(LogTable.Cols.COMMENT, log.getCommentSection());
         values.put(LogTable.Cols.LOCATION_LAT, log.getLocationLat());
         values.put(LogTable.Cols.LOCATION_LON, log.getLocationLon());
         values.put(LogTable.Cols.LOCATION_ADDRESS, log.getAddress());
         values.put(LogTable.Cols.DURATION, log.getDuration());

         return values;
     }

     public void updateLog(Log log) {
         String uuidString = log.getId().toString();
         ContentValues values = getContentValues(log);
         mDatabase.update(LogTable.NAME, values,
                 LogTable.Cols.UUID + " = ?",
                 new String[] { uuidString });
     }

     private LogCursorWrapper queryLogs(String whereClause, String[] whereArgs) {
         Cursor cursor = mDatabase.query(
                 LogTable.NAME,
                 null,
                 whereClause,
                 whereArgs,
                 null,
                 null,
                 null,
                 null
         );
         return new LogCursorWrapper(cursor);
     }

     public File getPhotoFile(Log log) {
         File externalFilesDir = mContext
                 .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
         if (externalFilesDir == null) {
             return null;
         }
         return new File(externalFilesDir, log.getPhotoFilename());
     }

     public void deleteLog(UUID logId) {
         String uuid = logId.toString();
         mDatabase.delete(LogTable.NAME, LogTable.Cols.UUID + " =?", new String[]{uuid});
     }

}
