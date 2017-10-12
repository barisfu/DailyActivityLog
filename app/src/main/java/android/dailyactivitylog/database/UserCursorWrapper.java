package android.dailyactivitylog.database;

import android.dailyactivitylog.User;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.dailyactivitylog.database.LogDbSchema.UserTable;

import java.util.UUID;

/**
 * Created by mdc010 on 9/10/2017.
 */

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super (cursor);
    }

    public User getUser() {
        String uuidString = getString(getColumnIndex(UserTable.Cols.UUID));
        String username = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String id = getString(getColumnIndex(UserTable.Cols.USER_ID));
        String email = getString(getColumnIndex(UserTable.Cols.USER_EMAIL));
        String gender = getString(getColumnIndex(UserTable.Cols.USER_GENDER));
        String comment = getString(getColumnIndex(UserTable.Cols.USER_COMMENT));

        User user = new User(UUID.fromString(uuidString));
        user.setUserName(username);
        user.setUserId(id);
        user.setUserEmail(email);
        user.setUserGender(gender);
        user.setUserComment(comment);

        return user;
    }
}
