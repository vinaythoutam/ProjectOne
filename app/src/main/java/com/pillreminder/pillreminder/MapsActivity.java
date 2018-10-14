package com.pillreminder.pillreminder;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pillreminder.pillreminder.helper.DataParser;
import com.pillreminder.pillreminder.helper.DownloadUrl;
import com.pillreminder.pillreminder.helper.GPSTracker;
import com.pillreminder.pillreminder.helper.GetNearbyPlacesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private GoogleMap.OnCameraMoveStartedListener onCameraMoveStartedListener;
    private TextView resutText, address_tv, labelText, delLoc;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    SharedPreferences sharedPref;

    Button next;
    GPSTracker gpsTracker;

    //
    private GoogleMap map;
    private Marker mPositionMarker;
    private LatLng mLatLng;

    Button pharmacies,physicians;
    private int PROXIMITY_RADIUS = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        gpsTracker=new GPSTracker(this);

        next = (Button) findViewById(R.id.next_button);
//        resutText = (TextView) findViewById(R.id.addtTV);
        address_tv = (TextView) findViewById(R.id.addressTV);
        labelText = (TextView) findViewById(R.id.toolbarTV);
        delLoc = (TextView) findViewById(R.id.delLocTV);

        pharmacies=(Button)findViewById(R.id.pharmaciesBtn);
        physicians=(Button)findViewById(R.id.physiciansBtn);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView toolImageView = (ImageView) findViewById(R.id.toolImage);
        toolImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        configureCameraMove();
//        configureCameraIdle();

        final Intent i = getIntent();

        address_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPlace(view);
            }
        });


    }

    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

//                resutText.setVisibility(View.VISIBLE);
                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(MapsActivity.this);

                try {
                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    Log.e("Address", String.valueOf(addressList));

                    if (addressList != null && addressList.size() > 0) {
                        String locality = addressList.get(0).getAddressLine(0);
                        String country = addressList.get(0).getCountryName();
//                        Log.e("addressLine0 ", "" + addressList.get(0).getAddressLine(0));
//                        Log.e("addressLine1 ", "" + addressList.get(0).getAddressLine(1));
//                        Log.e("addressLine2 ", "" + addressList.get(0).getAddressLine(2));
                        Log.e("region ", "" + addressList.get(0).getAdminArea());
                        Log.e("locality ", "" + addressList.get(0).getFeatureName());
                        Log.e("country ", "" + addressList.get(0).getCountryName());
//                        Log.e("locality ", String.valueOf(addressList.get(4)));
//                        Log.e("country ", String.valueOf(addressList.get(8)));
                        if (!locality.isEmpty() && !country.isEmpty())
                            //Toast.makeText(MapsActivity.this, "" + locality, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MapsActivity.this, "" + country, Toast.LENGTH_SHORT).show();
                            address_tv.setText(locality + "  " + country);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }

    private void configureCameraMove() {
        onCameraMoveStartedListener = new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
//                resutText.setVisibility(View.GONE);
            }
        };
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        ProgressDialog dialog;
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.show();

        mMap = googleMap;

        mMap.setOnCameraIdleListener(onCameraIdleListener);
        mMap.setOnCameraMoveStartedListener(onCameraMoveStartedListener);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        gpsTracker = new GPSTracker(this);
        LatLng latLng = new LatLng(this.gpsTracker.getLatitude(), this.gpsTracker.getLongitude());
        Log.d("location", ""+gpsTracker.getLatitude()+""+gpsTracker.getLongitude());
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
        dialog.dismiss();

        pharmacies.setOnClickListener(new View.OnClickListener() {
            String Pharmacies = "pharmacy";
            @Override
            public void onClick(View v) {

                pharmacies.setTextColor(getResources().getColor(R.color.white));
                pharmacies.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                physicians.setTextColor(getResources().getColor(R.color.colorPrimary));
                physicians.setBackgroundColor(getResources().getColor(R.color.gray_light));

                Log.d("onClick", ""+gpsTracker.getLatitude()+""+gpsTracker.getLongitude());
                mMap.clear();
                String url = getUrl(gpsTracker.getLatitude(), gpsTracker.getLongitude(), Pharmacies);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

            }
        });

        physicians.setOnClickListener(new View.OnClickListener() {
            String Physicians = "physician";
            @Override
            public void onClick(View v) {

                physicians.setTextColor(getResources().getColor(R.color.white));
                physicians.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                pharmacies.setTextColor(getResources().getColor(R.color.colorPrimary));
                pharmacies.setBackgroundColor(getResources().getColor(R.color.gray_light));


                Log.d("onClick", ""+gpsTracker.getLatitude()+""+gpsTracker.getLongitude());
                mMap.clear();
                String url = getUrl(gpsTracker.getLatitude(), gpsTracker.getLongitude(), Physicians);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);

            }
        });
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
//
//        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        //markerOptions.title("Current Position");
        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);
//        mCurrLocationMarker.setVisible(false);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(21));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }


//        double latitude = location.getLatitude();
//        double longitude = location.getLongitude();
//        float accuracy = location.getAccuracy();
//
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//        final MarkerOptions positionMarkerOptions = new MarkerOptions()
//                .position(new LatLng(latitude, longitude));
//        mCurrLocationMarker = mMap.addMarker(positionMarkerOptions);
//
////        if (accuracyCircle != null) {
////            accuracyCircle.remove();
////        }
////        final CircleOptions accuracyCircleOptions = new CircleOptions()
////                .center(new LatLng(latitude, longitude))
////                .radius(accuracy)
////                .fillColor(accuracyFillColor)
////                .strokeColor(accuracyStrokeColor)
////                .strokeWidth(2.0f);
////        accuracyCircle = mMap.addCircle(accuracyCircleOptions);

    }


    //permissions request

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            //You can add here other case statements according to your requirement.
        }
    }


    public void findPlace(View view) {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
            // Indicates that Google Play Services is not available and the problem is not easily
            // resolvable.
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e("TAG", message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    // A place has been received; use requestCode to track the request.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
                Log.e("Tag", place.getAddress() + "");

                address_tv.setText(place.getName() + "" + place.getAddress() + "" + place.getPhoneNumber());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyAISP6NMb4mkd1ZNS7oyD3mg6141Saqouk");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

    public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

        String googlePlacesData;
        GoogleMap mMap;
        String url;

        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d("GetNearbyPlacesData", "doInBackground entered");
                mMap = (GoogleMap) params[0];
                url = (String) params[1];
                DownloadUrl downloadUrl = new DownloadUrl();
                googlePlacesData = downloadUrl.readUrl(url);
                Log.d("GooglePlacesReadTask", "doInBackground Exit");
            } catch (Exception e) {
                Log.d("GooglePlacesReadTask", e.toString());
            }
            return googlePlacesData;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("GooglePlacesReadTask", "onPostExecute Entered");
            List<HashMap<String, String>> nearbyPlacesList = null;
            DataParser dataParser = new DataParser();
            nearbyPlacesList =  dataParser.parse(result);
            ShowNearbyPlaces(nearbyPlacesList);
            Log.d("GooglePlacesReadTask", "onPostExecute Exit");
        }

        private void ShowNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList) {
            for (int i = 0; i < nearbyPlacesList.size(); i++) {
                Log.d("onPostExecute","Entered into showing locations");
                MarkerOptions markerOptions = new MarkerOptions();
                HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
                double lat = Double.parseDouble(googlePlace.get("lat"));
                double lng = Double.parseDouble(googlePlace.get("lng"));
                String placeName = googlePlace.get("place_name");
                String vicinity = googlePlace.get("vicinity");
                LatLng latLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
                markerOptions.position(latLng);
                markerOptions.title(placeName + " : " + vicinity);
                mMap.addMarker(markerOptions);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.mappin));
                //move map camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
            }
        }
    }


}
