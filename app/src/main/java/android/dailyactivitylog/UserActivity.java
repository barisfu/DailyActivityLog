package android.dailyactivitylog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class UserActivity extends SingleFragmentActivity {
    private static final String EXTRA_USER_ID = "android.dailyactivitylog.user_id";

    @Override
    protected Fragment createFragment() {
        return new UserFragment();
    }

    public static Intent newIntent(Context packageContext, UUID userId) {
        Intent intent = new Intent(packageContext, UserActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        return intent;
    }
}

