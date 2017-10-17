package android.dailyactivitylog;

import android.support.v4.app.Fragment;


public class UserActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new UserFragment();
    }

}

