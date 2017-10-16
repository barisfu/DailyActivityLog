package android.dailyactivitylog.database;

/**
 * Created by Mack on 04-Oct-17.
 */

public class DbSchemas {
    public static final class LogTable {
        public static final String NAME = "logs";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String COMMENT = "comment";
            public static final String CATEGORY = "category";
            public static final String LOCATION_LAT = "locationLatitude";
            public static final String LOCATION_LON = "locationLongitude";
            public static final String LOCATION_ADDRESS = "locationAddress";
        }
    }

    public static final class UserTable {
        public static final String NAME = "users";

        public static final class Cols {
            public static final String USERNAME = "username";
            public static final String USER_ID = "id";
            public static final String USER_EMAIL = "email";
            public static final String USER_GENDER = "gender";
            public static final String USER_COMMENT = "comment";
        }
    }
}
