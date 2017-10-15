package android.dailyactivitylog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;
import java.util.UUID;

public class LogActivity extends SingleFragmentActivity {

    GoogleMapFragment mapFragment;
    private static final String EXTRA_LOG_ID = "android.dailyactivitylog.log_id";
    private static final String LOG_DETAILS = "Log";


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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == GoogleMapFragment.MY_PERMISSIONS_REQUEST_LOCATION){
            mapFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
