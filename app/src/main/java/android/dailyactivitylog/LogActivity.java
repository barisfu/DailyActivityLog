package android.dailyactivitylog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;


//YOU ARE UPO TO P 141
public class LogActivity extends SingleFragmentActivity {

    private static final String EXTRA_LOG_ID = "android.dailyactivitylog.log_id";

    public static Intent newIntent(Context packageContext, UUID logId) {
        Intent intent = new Intent(packageContext, LogActivity.class);
        intent.putExtra(EXTRA_LOG_ID, logId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID logID = (UUID) getIntent().getSerializableExtra(EXTRA_LOG_ID);

        return LogFragment.newInstance(logID);
    }
}
