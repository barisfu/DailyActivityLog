/**

package android.dailyactivitylog;

import android.content.ContentValues;
import android.content.Context;
import android.dailyactivitylog.database.LogDbHelper;
import android.dailyactivitylog.database.LogDbSchema;
import android.dailyactivitylog.database.UserCursorWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.dailyactivitylog.database.LogDbSchema.UserTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mdc010 on 9/10/2017.
 *
 * CLASS TO USE DATABASE TO STORE THE USERS.
 */
/**
public class UserStore {
    private static UserStore sUserList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static UserStore get(Context context) {
        if(sUserList == null) {
            sUserList = new UserStore(context);
        }
        return sUserList;
    }

    private UserStore (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new LogDbHelper(mContext).getWritableDatabase();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        UserCursorWrapper cursor = queryUsers(null, null);
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                users.add(cursor.getUser());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return users;
    }

    public User getUser(UUID id) {
        UserCursorWrapper cursor = queryUsers(
                UserTable.Cols.UUID + " = ?",
                new String [] { id.toString() }
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
        values.put(UserTable.Cols.UUID, user.getUserId().toString());
        values.put(UserTable.Cols.USERNAME, user.getUserName());
        values.put(UserTable.Cols.USER_EMAIL, user.getUserEmail());
        values.put(UserTable.Cols.USER_GENDER, user.getUserGender());
        values.put(UserTable.Cols.USER_COMMENT, user.getUserComment());

        return values;
    }

    public void updateUser(User user) {
        String uuidString = user.getId().toString();
        ContentValues values = getContentValues(user);
        mDatabase.update(UserTable.NAME, values,
                UserTable.Cols.UUID + " = ? ",
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

    public void deleteUser(UUID userId) {
        String uuid = userId.toString();
        mDatabase.delete(UserTable.NAME, UserTable.Cols.UUID + " =?", new String [] {uuid});
    }
}
**/