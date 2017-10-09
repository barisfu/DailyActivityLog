package android.dailyactivitylog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import java.util.UUID;

public class LogActivity extends SingleFragmentActivity {

    private static final String EXTRA_LOG_ID = "android.dailyactivitylog.log_id";

    @Override
    protected Fragment createFragment() {
        UUID logID = (UUID) getIntent().getSerializableExtra(EXTRA_LOG_ID);

        return LogFragment.newInstance(logID);
    }
}
