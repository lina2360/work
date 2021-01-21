package com.project14.nccu105.project14_2;
//import androidx.core.app.NotificationCompat;
//import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback,LocationListener,GeoQueryEventListener {

    private GoogleMap mMap;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentUser;
    private DatabaseReference myLocationRef;
    private GeoFire geoFire;
    private List<LatLng> shoppingArea;
    private MaterialSearchBar materialSearchBar;
    CharSequence texts="";
    private PlacesClient placesClient;
    private List<AutocompletePrediction> predictionList;

    LatLng Point,Point1;
    String x;
    String y;
    List<Address> addresses=null;
    Geocoder geoCoder;
    float zoom;
    private LocationManager locMgr;
    String bestProv;
    Button btn701,btn703,btn705,btn706;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        Button btnFind = findViewById(R.id.btn_find2);
        btnFind.setText("尋找商品單");


        btn701 = (Button) findViewById(R.id.btn70_1);
        btn703 = (Button) findViewById(R.id.btn70_3);
        btn705 = (Button) findViewById(R.id.btn70_5);
        btn706 = (Button) findViewById(R.id.btn70_6);

        materialSearchBar = findViewById(R.id.searchBar2);

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(final CharSequence text) {
                texts=text;
                String texttt=texts.toString();
//                Toast.makeText("test",texttt,LENGTH_SHORT).show();
//                String texttt=texts.toString();
//
//                materialSearchBar.setText(texttt);
                startSearch(texts.toString(), true, null, true);

            }

            @Override
            public void onButtonClicked(int buttonCode) {
                if(buttonCode == MaterialSearchBar.BUTTON_NAVIGATION){
                    //opening or closing a navigation drawer
                } else if(buttonCode == MaterialSearchBar.BUTTON_BACK){
                    materialSearchBar.disableSearch();
                }
            }
        });

        final AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

        placesClient = Places.createClient(this);

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                startSearch(texts.toString(), true, null, true);

                String test="tw";
                for(int i=0;i<=3;i++){
                    FindAutocompletePredictionsRequest predictionsRequest = FindAutocompletePredictionsRequest.builder()
                            .setCountry(test)
                            .setTypeFilter(TypeFilter.CITIES)
                            .setSessionToken(token)
                            .setQuery(s.toString())
                            .build();
                    placesClient.findAutocompletePredictions(predictionsRequest).addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
                            if(task.isSuccessful()){
                                FindAutocompletePredictionsResponse predictionsResponse = task.getResult();
                                if(predictionsResponse != null){
                                    predictionList = predictionsResponse.getAutocompletePredictions();
                                    List<String> suggestionsList = new ArrayList<>();
                                    for(int i = 0; i < predictionList.size(); i++){
                                        AutocompletePrediction prediction = predictionList.get(i);
                                        suggestionsList.add(prediction.getFullText(null).toString());
                                    }
                                    materialSearchBar.updateLastSuggestions(suggestionsList);
                                    if(!materialSearchBar.isSuggestionsVisible()){
                                        materialSearchBar.showSuggestionsList();
                                    }
                                }
                            } else {
                                Log.i("mytag", "prediction fetching task unsuccessful");
                            }
                        }
                    });
                    if(i==0)
                        test="us";
                    else if(i==1)
                        test="jp";
                    else if(i==2)
                        test="ks";

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setSuggstionsClickListener(new SuggestionsAdapter.OnItemViewClickListener() {
            @Override
            public void OnItemClickListener(int position, View v) {
                if(position >= predictionList.size()){
                    return;
                }
                AutocompletePrediction selectedPrediction = predictionList.get(position);
                String suggestion= materialSearchBar.getLastSuggestions().get(position).toString();
                materialSearchBar.setText(suggestion);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialSearchBar.clearSuggestions();
                    }
                }, 1000);

                materialSearchBar.clearSuggestions();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(imm != null){
                    imm.hideSoftInputFromWindow(materialSearchBar.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                    String placeId = selectedPrediction.getPlaceId();
                    List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG);

                    final FetchPlaceRequest fetchPlaceRequest = FetchPlaceRequest.builder(placeId, placeFields).build();
                    placesClient.fetchPlace(fetchPlaceRequest).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                        @Override
                        public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                            Place place = fetchPlaceResponse.getPlace();
                            Log.i("mytag", "Place found: " + place.getName());
                            LatLng latLngOfPlace = place.getLatLng();
                            if(latLngOfPlace != null){
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngOfPlace, 15));
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(e instanceof ApiException){
                                ApiException apiException = (ApiException) e;
                                apiException.printStackTrace();
                                int statusCode = apiException.getStatusCode();
                                Log.i("mytag", "place not found: " + e.getMessage());
                                Log.i("mytag", "status code: " + statusCode);
                            }
                        }
                    });
                }
            }

            @Override
            public void OnItemDeleteListener(int position, View v) {

            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent();
                intent7.setClass(MapsActivity2.this, Maps.class);
                startActivity(intent7);
                MapsActivity2.this.finish();

            }
        });

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        buildLocationRequest();
                        buildLocationCallback();
                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapsActivity2.this);

                        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.map2);
                        mapFragment.getMapAsync(MapsActivity2.this);

                        initArea();
                        settingGeoFire();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MapsActivity2.this, "You must enable permission", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();




        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int id = ((Button) v).getId();
                switch (id) {
                    case R.id.btn70_1:
                        String msg1 = "往論壇頁";
                        Intent intent1 = new Intent();
                        intent1.setClass(MapsActivity2.this,Forum.class);
                        MapsActivity2.this.finish();
                        startActivity(intent1);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case R.id.btn70_3:
                        String msg3 = "往接單頁";
                        Intent intent3 = new Intent();
                        intent3.setClass(MapsActivity2.this,Seller.class);
                        MapsActivity2.this.finish();
                        startActivity(intent3);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case R.id.btn70_5:
                        String msg5 = "往通知頁";
                        Intent intent5 = new Intent();
                        intent5.setClass(MapsActivity2.this,Notify.class);
                        MapsActivity2.this.finish();
                        startActivity(intent5);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case R.id.btn70_6:
                        String msg6 = "個人";
                        Intent intent6 = new Intent();
                        intent6.setClass(MapsActivity2.this,Personal.class);
                        MapsActivity2.this.finish();
                        startActivity(intent6);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;


                }
            }
        };
        btn701.setOnClickListener(OCL);
        btn703.setOnClickListener(OCL);
        btn705.setOnClickListener(OCL);
        btn706.setOnClickListener(OCL);
    }



    private void initArea() {
        shoppingArea = new ArrayList<>();
        shoppingArea.add(new LatLng(25.0365, 121.5439));
        shoppingArea.add(new LatLng(25.0365, 121.5329));
        shoppingArea.add(new LatLng(24.9910, 121.5759));
//
    }

    private void settingGeoFire() {
        myLocationRef = FirebaseDatabase.getInstance().getReference("MyLocation");
        geoFire = new GeoFire(myLocationRef);
    }


    private void buildLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(final LocationResult locationResult) {

                if (mMap != null) {

                    geoFire.setLocation("您", new GeoLocation(locationResult.getLastLocation().getLatitude(),
                            locationResult.getLastLocation().getLongitude()), new GeoFire.CompletionListener() {
                        @Override
                        public void onComplete(String key, DatabaseError error) {

                            if (currentUser != null) currentUser.remove();
                            currentUser = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(locationResult.getLastLocation().getLatitude(),
                                            locationResult.getLastLocation().getLongitude()))
                                    .title("您"));
                            //After adding marker, move camera
//                            mLastKnownLocation = locationResult.getLastLocation();
//                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
//                            mFusedLocationProviderClient.removeLocationUpdates(locationCallback);

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentUser.getPosition(), 13f));
                        }
                    });
                }
            }
        };
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
//        locationRequest.setSmallestDisplacement(10f);
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
//        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);

        if (fusedLocationProviderClient != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,Looper.myLooper() );
//        Looper.myLooper()

        //Adding circle for shopping area
        for(LatLng latLng : shoppingArea){
            mMap.addCircle(new CircleOptions().center(latLng).radius(500)
                    .strokeColor(Color.BLUE)
                    .fillColor(0x220000FF)
                    .strokeWidth(5.0f));
            //Create GeoQuery when user is in shoppingArea
            GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(latLng.latitude, latLng.longitude), 0.5f);
            geoQuery.addGeoQueryEventListener(MapsActivity2.this);
        }

        String title="測試";
        Marker pinnedMarker=mMap.addMarker(new MarkerOptions().position(new LatLng(25.0365, 121.5439))
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource( R.drawable.ic_map_marker))
                .snippet("place"+1)
                .zIndex(1)
        );
    }



    @Override
    protected void onResume() {
        super.onResume();
        // 取得定位服務
        locMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 取得最佳定位
        Criteria criteria = new Criteria();
        bestProv = locMgr.getBestProvider(criteria, true);

        // 如果GPS或網路定位開啟，更新位置
        if (locMgr.isProviderEnabled(LocationManager.GPS_PROVIDER) || locMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //  確認 ACCESS_FINE_LOCATION 權限是否授權
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locMgr.requestLocationUpdates(bestProv, 1000, 1, this);
            }
        } else {
            Toast.makeText(this, "請開啟定位服務", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //  確認 ACCESS_FINE_LOCATION 權限是否授權
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locMgr.removeUpdates(this);
        }
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Criteria criteria = new Criteria();
        bestProv = locMgr.getBestProvider(criteria, true);
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }




    public void onLocationChanged(Location location) {
        // 取得地圖座標值:緯度,經度
        x="緯=" + Double.toString(location.getLatitude());
        y="經=" + Double.toString(location.getLongitude());
        Point = new LatLng(location.getLatitude(), location.getLongitude());
        zoom=17; //設定放大倍率1(地球)-21(街景)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Point, 8));


//        Toast.makeText(this, x + "\n" + y, Toast.LENGTH_LONG).show();

//        //抓國家代碼



        try {

            addresses = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size() > 0) {
            String CountryID= addresses.get(0).getCountryCode();
            Toast.makeText(this, "國家碼:"+CountryID, Toast.LENGTH_LONG).show();

            Log.d("test", "國家碼:"+CountryID);
        }

    }

    @Override
    protected void onStop() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        super.onStop();
    }

    @Override
    public void onKeyEntered(String key, GeoLocation location) {
        sendNotification("代購", String.format("%s的附近有可以代買的商品", key));
    }

    @Override
    public void onKeyExited(String key) {
        sendNotification("代購", String.format("%s的附近沒有商品", key));
    }


    @Override
    public void onKeyMoved(String key, GeoLocation location) {
        sendNotification("代購", String.format("%s正在有商品的區域移動", key));
    }

    @Override
    public void onGeoQueryReady() {

    }

    @Override
    public void onGeoQueryError(DatabaseError error) {
        Toast.makeText(this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void sendNotification(String title, String content) {

        Toast.makeText(this, ""+content, Toast.LENGTH_SHORT).show();

        String NOTIFICATION_CHANNEL_ID = "代購_multiple_location";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

//            notificationChannel.setDescription();
            //Config
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(false)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        Notification notification = builder.build();
        notificationManager.notify(new Random().nextInt(), notification);
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return false;
    }


}
