package android.dailyactivitylog.database;

import android.dailyactivitylog.Log;
import android.dailyactivitylog.database.LogDbSchema.LogTable;
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
        long date = getLong(getColumnIndex(LogTable.Cols.DATE));

        Log log = new Log(UUID.fromString(uuidString));
        log.setCommentSection(comment);
        log.setTitle(title);
        log.setDate(new Date(date));

        return log;
    }
}
