package android.dailyactivitylog.database;

/**
 * Created by Mack on 04-Oct-17.
 */

public class LogDbSchema {
    public static final class LogTable {
        public static final String NAME = "logs";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String COMMENT = "comment";
        }
    }
}
