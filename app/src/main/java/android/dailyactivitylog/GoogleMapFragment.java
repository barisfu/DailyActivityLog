package android.dailyactivitylog;

import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.location.Address;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.Manifest.permission;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * Created by Mack on 14-Oct-17.
 */

public class GoogleMapFragment extends SupportMapFragment implements OnMapReadyCallback,
                                GoogleApiClient.ConnectionCallbacks,
                                GoogleApiClient.OnConnectionFailedListener,
                                LocationListener {

    private GoogleMap mGoogleMap;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private Log mLog;
    private Geocoder mGeocoder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Retrieves mLog object from LogFragment.
        Bundle bundle = getArguments();
        if(bundle != null) {
            mLog = (Log)bundle.getSerializable("mLog");
        }
        android.util.Log.d("onCreate", "FOR GOOGLEMAP");
    }

    @Override
    public void onResume() {
        super.onResume();

        setUpMapIfNeeded();
        }


    /**
     * Sets up the map if mGooglemap is null.
     */
    private void setUpMapIfNeeded() {
        if (mGoogleMap == null) {
        getMapAsync(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //Stops location updates when no longer active.
        if (mGoogleApiClient != null) {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            }
        }
            else {
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            }
    }

    /**
     * Builds the google API Client
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(100);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(getActivity(), permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        mGeocoder = new Geocoder(this.getContext(), Locale.getDefault());
        List<Address> addresses = null;

        if (mCurrLocationMarker != null) {
             mCurrLocationMarker.remove();
        }

        //Checks to see if Latitude and Longitude are already saved in Log object
        // and then retrieves location of the saved Lat and Lon to display on map.
        if(mLog.getLocationLat() != 0.0 && mLog.getLocationLon() != 0.0) {
            mLastLocation.setLatitude(mLog.getLocationLat());
            mLastLocation.setLongitude(mLog.getLocationLon());
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //Move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));
        mLog.setLocationLat(mLastLocation.getLatitude());
        mLog.setLocationLon(mLastLocation.getLongitude());

        //Adds the address to Log instance.
        try {
            addresses = mGeocoder.getFromLocation(mLastLocation.getLatitude(),
                    mLastLocation.getLongitude(), 1);
        } catch (IOException ioException) {
            android.util.Log.e("Geocoder", "error");
        }
        mLog.setAddress(addresses.get(0).getAddressLine(0));
        android.util.Log.d("AddressSet", mLog.getAddress());
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_LOCATION: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // Checks permission was granted and the google map can be built.
                        if (ContextCompat.checkSelfPermission(getActivity(),
                        permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                            if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                            }
                        mGoogleMap.setMyLocationEnabled(true);
                        }

                    } else {
                    // Permission is denied.
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                    }
                    return;
                }
            }
        }

}