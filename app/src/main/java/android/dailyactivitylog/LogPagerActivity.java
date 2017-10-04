package android.dailyactivitylog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Mack on 27-Sep-17.
 */

public class LogPagerActivity extends AppCompatActivity {
    private static final String EXTRA_LOG_ID = "android.dailyactivitylog.log_id";
    private ViewPager mViewPager;
    private List<Log> mLogs;

    public static Intent newIntent(Context packageContext, UUID logId) {
        Intent intent = new Intent(packageContext, LogPagerActivity.class);
        intent.putExtra(EXTRA_LOG_ID, logId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_pager);

        UUID logId = (UUID) getIntent().getSerializableExtra(EXTRA_LOG_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_log_pager_view_pager);

        mLogs = LogStore.get(this).getLogs();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Log log = mLogs.get(position);
                return LogFragment.newInstance(log.getId());
            }

            @Override
            public int getCount() {
                return mLogs.size();
            }
        });

        for(int i = 0; i < mLogs.size(); i++) {
            if(mLogs.get(i).getId().equals(logId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
