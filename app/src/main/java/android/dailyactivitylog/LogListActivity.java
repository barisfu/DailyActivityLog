package android.dailyactivitylog;

import android.support.v4.app.Fragment;

/**
 * Created by Mack on 26-Sep-17.
 */

public class LogListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LogListFragment();
    }
}
