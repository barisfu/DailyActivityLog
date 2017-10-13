package android.dailyactivitylog.database;

import android.dailyactivitylog.User;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.dailyactivitylog.database.DbSchemas.UserTable;

/**
 * Created by mdc010 on 9/10/2017.
 */

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super (cursor);
    }

    public User getUser() {

        String username = getString(getColumnIndex(UserTable.Cols.USERNAME));
        String id = getString(getColumnIndex(UserTable.Cols.USER_ID));
        String email = getString(getColumnIndex(UserTable.Cols.USER_EMAIL));
        String gender = getString(getColumnIndex(UserTable.Cols.USER_GENDER));
        String comment = getString(getColumnIndex(UserTable.Cols.USER_COMMENT));

        User user = new User(id);
        user.setUserName(username);
        user.setUserEmail(email);
        user.setUserGender(gender);
        user.setUserComment(comment);

        return user;
    }
}
