package android.dailyactivitylog.database;

import android.dailyactivitylog.Log;
import android.dailyactivitylog.database.DbSchemas.LogTable;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Mack on 04-Oct-17.
 */

public class LogCursorWrapper extends CursorWrapper {
    public LogCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Log getLog() {
        String uuidString = getString(getColumnIndex(LogTable.Cols.UUID));
        String title = getString(getColumnIndex(LogTable.Cols.TITLE));
        String comment = getString(getColumnIndex(LogTable.Cols.COMMENT));
        int category = getInt(getColumnIndex(LogTable.Cols.CATEGORY));
        String address = getString(getColumnIndex(LogTable.Cols.LOCATION_ADDRESS));
        String date = getString(getColumnIndex(LogTable.Cols.DATE));
        String duration = getString(getColumnIndex(LogTable.Cols.DURATION));
        double latitude = getDouble(getColumnIndex(LogTable.Cols.LOCATION_LAT));
        double longitude = getDouble(getColumnIndex(LogTable.Cols.LOCATION_LON));

        Log log = new Log(UUID.fromString(uuidString));
        log.setCommentSection(comment);
        log.setTitle(title);
        log.setFormattedDate(date);
        log.setCategoryPosition(category);
        log.setLocationLat(latitude);
        log.setLocationLon(longitude);
        log.setAddress(address);
        log.setDuration(duration);

        return log;
    }
}
