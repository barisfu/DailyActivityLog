package android.dailyactivitylog;

import android.content.ContentValues;
import android.content.Context;
import android.dailyactivitylog.database.DbHelper;
import android.dailyactivitylog.database.UserCursorWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.dailyactivitylog.database.DbSchemas.UserTable;

/**
 * Created by mdc010 on 12/10/2017.
 */

public class UserStore {
    private static UserStore sUserList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static UserStore get(Context context) {
        if (sUserList == null) {
            sUserList = new UserStore(context);
        }
        return sUserList;
    }

    private UserStore(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DbHelper(mContext).getWritableDatabase();
    }


    public User getUser(String id) {
        UserCursorWrapper cursor = queryUsers(
                UserTable.Cols.USER_ID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUser();
        } finally {
            cursor.close();
        }
    }

    public void addUser(User u) {
        ContentValues values = getContentValues(u);
        mDatabase.insert(UserTable.NAME, null, values);
    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserTable.Cols.USER_EMAIL, user.getUserEmail());
        values.put(UserTable.Cols.USER_GENDER, user.getUserGender());
        values.put(UserTable.Cols.USER_COMMENT, user.getUserComment());
        values.put(UserTable.Cols.USER_ID, user.getUserId());

        return values;
    }

    public void updateUser(User user) {
        String uuidString = user.getUserId();
        ContentValues values = getContentValues(user);
        mDatabase.update(UserTable.NAME, values,
                UserTable.Cols.USER_ID + " = ?",
                new String[] { uuidString });
    }

    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null
        );
        return new UserCursorWrapper(cursor);
    }

}
