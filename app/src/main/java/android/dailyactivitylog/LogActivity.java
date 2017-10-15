package android.dailyactivitylog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;

import java.util.UUID;

public class LogActivity extends SingleFragmentActivity {

    private static final String EXTRA_LOG_ID = "android.dailyactivitylog.log_id";

    @Override
    protected Fragment createFragment() {
        android.util.Log.d("createFragment", "LogActivity");
        UUID logID = (UUID) getIntent().getSerializableExtra(EXTRA_LOG_ID);

        return LogFragment.newInstance(logID);
    }


    public static Intent newIntent(Context packageContext, UUID logId) {
        Intent intent = new Intent(packageContext, LogActivity.class);
        intent.putExtra(EXTRA_LOG_ID, logId);
        return intent;
    }


    /**
     * Method to replace LogFragtment with GMapFragment
     * @param newFragmentClass
     */
    public void replaceFragments(Class newFragmentClass) {
        Fragment newFragment = null;

        try {
            newFragment = (Fragment) newFragmentClass.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, newFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
