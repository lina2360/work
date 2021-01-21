package com.project14.nccu105.project14_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.io.IOException;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maps extends FragmentActivity implements OnMapReadyCallback,LocationListener {
    Button btn131,btn132,btn133,btn135,btn136 ,btnFind;
    private GoogleMap mMap;
    private HandlerThread mThread;
    float zoom;
    private LocationManager locMgr;
    String bestProv;
    LatLng Point,Point1;
    String x;
    String y;
    List<Address> addresses=null;
    Geocoder geoCoder;
    private MaterialSearchBar materialSearchBar;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlacesClient placesClient;
    private List<AutocompletePrediction> predictionList;
    private LocationCallback locationCallback;
    private View mapView;
    private Location mLastKnownLocation;
    private final float DEFAULT_ZOOM = 18;
    CharSequence texts="";
//13
private void setupWindowAnimations() {
    Slide slide = null;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        slide = new Slide();

        getWindow().setEnterTransition(slide);
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        setupWindowAnimations();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapView = mapFragment.getView();

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Maps.this);

        btn131 = (Button) findViewById(R.id.btn13_1);
//        btn132 = (Button) findViewById(R.id.btn13_2);
        btn133 = (Button) findViewById(R.id.btn13_3);
        btn135 = (Button) findViewById(R.id.btn13_5);
        btn136 = (Button) findViewById(R.id.btn13_6);


        geoCoder= new Geocoder(Maps.this);
        materialSearchBar = findViewById(R.id.searchBar);
        btnFind = findViewById(R.id.btn_find);
        btnFind.setText("切換至尋找請求");
//        AIzaSyADKCSeewapIVgthrlmHf9cwmdTvA7grmI
        Places.initialize(Maps.this, "AIzaSyADKCSeewapIVgthrlmHf9cwmdTvA7grmI");
//        AIzaSyClVfq9Ej98wotE5GCl2zz0G8MW0zpTT48
        placesClient = Places.createClient(this);



        final AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

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

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent();
                intent7.setClass(Maps.this, Maps2.class);
                startActivity(intent7);
                Maps.this.finish();

            }
        });

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
                        test="kr";
                    else if(i==3)
                        test="sg";
                    else if(i==4)
                        test="fr";

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
//                    Log.d("test", "OnItemClickListener: "+placeId);
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





        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int id = ((Button) v).getId();
                switch (id) {
                    case R.id.btn13_1:
                        String msg1 = "往論壇頁";
                        Intent intent1 = new Intent();
                        intent1.setClass(Maps.this,Forum.class);
                        Maps.this.finish();
                        startActivity(intent1);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case R.id.btn13_3:
                        String msg3 = "往接單頁";
                        Intent intent3 = new Intent();
                        intent3.setClass(Maps.this,Seller.class);
                        Maps.this.finish();
                        startActivity(intent3);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case R.id.btn13_5:
                        String msg5 = "往通知頁";
                        Intent intent5 = new Intent();
                        intent5.setClass(Maps.this,Notify.class);
                        Maps.this.finish();
                        startActivity(intent5);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case R.id.btn13_6:
                        String msg6 = "個人";
                        Intent intent6 = new Intent();
                        intent6.setClass(Maps.this,Personal.class);
                        Maps.this.finish();
                        startActivity(intent6);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;


                }
            }
        };
        btn131.setOnClickListener(OCL);
//        btn132.setOnClickListener(OCL);
        btn133.setOnClickListener(OCL);
        btn135.setOnClickListener(OCL);
        btn136.setOnClickListener(OCL);

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

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
//        mMap.setMyLocationEnabled(true);//
//        mMap.getUiSettings().setMyLocatisonButtonEnabled(true);


        if(mapView != null && mapView.findViewById(Integer.parseInt("1")) != null){
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0,0,40,180);
        }

        //check if gps is enabled or not and then request user to enable it
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

        SettingsClient settingsClient = LocationServices.getSettingsClient(Maps.this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(Maps.this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                getDeviceLocation();
            }
        });

        task.addOnFailureListener(Maps.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof ResolvableApiException){
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(Maps.this, 51);
                    } catch (IntentSender.SendIntentException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        });


//        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
//            @Override
//            public boolean onMyLocationButtonClick() {
//                if(materialSearchBar.isSuggestionsVisible())
//                    materialSearchBar.clearSuggestions();
//                if(materialSearchBar.isSearchEnabled())
//                    materialSearchBar.disableSearch();
//                return false;
//            }
//        });

        double[][]position = {
//                {25.049256, 121.518110},//北車1
//                {25.047526, 121.515337},
//
//                {34.432436, 135.230608},//日本機場
//                {34.796835, 135.436340},
//
//                {34.699257, 135.496760},//大阪
//                {34.699280, 135.493242},
//
//                {35.005150, 135.772903},//京都7
//                {35.005256, 135.775124},
//                {35.007093, 135.769588},
//
//                { 43.060472, 141.355714},//札幌
//                {43.063625, 141.351413},
//                {43.067869, 141.352414},
//                {43.059239, 141.354325},
//
//                {32.802520, 130.708795},//熊本14
//                {32.803608, 130.709989},
//                {32.803555, 130.711220},
//
//
//                { 25.0336962, 121.5643673 },//台北信義
////                { 25.0333698, 118.5641564 },//中國福建
////                { 25.033899, 115.564329 },//中國江西
////                {24.0338407,121.5645269},//花蓮
////                {23.0336377, 121.5645727},//菲律賓
//
//                {35.678152, 139.768733},
//                {35.686089, 139.773222},//東京12
//                {35.681283, 139.773541},
//                {35.680852, 139.774391},
//                {35.674925, 139.763905},
//
//                {35.682262, 139.768935},//東京22
//                {35.672738, 139.766420},
//                {35.671788, 139.765959},
//                {35.674493, 139.762878},
//                {35.674809, 139.762984},
//
//                {37.561927, 126.979823},//南韓
//                {37.566515, 126.978073},
//                {37.563096, 126.977863},
//                {37.562398, 126.975514},
//                {37.566391, 126.974396}

                {35.672231, 139.765912},//1.萬用陶瓷刀
                {35.675842, 139.762801},//2,令和杯麵
                {35.680877, 139.766889},//3.帆布兩用包
                {35.683379, 139.768346},//4.若元胃腸錠

                {35.004205, 135.766490},//5.井筒八ッ橋　化粧箱入り
                {35.006737, 135.767399},//6.黃金果凍面膜(玫瑰)
                {35.006289, 135.767420},//7.水潤保濕修護面膜

                {37.562011, 126.983951},//8.保濕精華液
                {37.496247, 126.894520},//9.艾多美炸醬麵
                {37.482653, 126.893018},//10.素面毛料側拉鏈魚尾短褲裙
                {37.556725, 126.935368},//11.繡花LOGO帽T 黑
                {37.556723, 126.970041},//12.中腰直筒六袋工作長褲 綠
                {37.570797, 127.008162},//13.長袖連身工作裙 卡其


                {35.157849, 129.057567},//14.燒酒

                {24.986427, 121.575523},//15.深山版帽t
                {24.998532, 121.581036},//16.熊貓背包

                {22.631109, 120.300722},//17.真芋頭
                {22.886361, 120.663236},//18.有機脫殼紅藜
                {22.682608, 120.288572},//19.雪藏棋餅

                {48.869163, 2.322918},//20.黑色“Gucci Signature”錢包
                {1.361118, 103.872499},//21.金盞花植物精華化妝水
                {40.737021, -73.987969},//22.亞馬遜白泥淨緻毛孔面膜
                {40.725579, -73.996209},//23.酪梨精粹修護保濕面膜
                {40.772386, -73.965978},//24.激光極淨白淡斑精華

//                {},//25.時尚腕錶



        };

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(Maps.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }

        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
//                Toast.makeText(Maps.this, "按了！", Toast.LENGTH_SHORT).show();
                final ArrayList<SellerOrder_Container> list = new ArrayList<SellerOrder_Container>();
                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("seller_order")};
                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count=0;
                        for (DataSnapshot ds : dataSnapshot.getChildren() ) {


                            SellerOrder_Container article = ds.getValue(SellerOrder_Container.class);
                            list.add(article);

                        }
                        for(int i=0;i<list.size();i++){
                            if(marker.getTitle().equals(list.get(i).getName())){

                                Intent intent7 = new Intent();
                                intent7.setClass(Maps.this, SellerOrder.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("classify", list.get(i).getClassify());
                                bundle.putString("brand", list.get(i).getBrand());
                                bundle.putString("name", list.get(i).getName());
                                bundle.putString("standard", list.get(i).getStandard());
                                bundle.putString("model", list.get(i).getModel());
                                bundle.putString("url", list.get(i).getUrl());
                                bundle.putString("country", list.get(i).getCountry());
                                bundle.putString("place", list.get(i).getPlace());
                                bundle.putString("other", list.get(i).getOther());
                                bundle.putString("price", list.get(i).getPrice());
                                bundle.putString("num", list.get(i).getNum());
                                bundle.putString("fee", list.get(i).getFee());
                                bundle.putString("delivery", list.get(i).getDelivery());
                                bundle.putLong("time", list.get(i).getMessageTime());
                                bundle.putString("seller", list.get(i).getUser());
                                bundle.putString("sellernick", list.get(i).getUsernick());
                                bundle.putString("receipt", list.get(i).getReceipt());
                                bundle.putString("from", "Result");
                                intent7.putExtras(bundle);
                                startActivity(intent7);

                            }

                        }




                    }






                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });


//        LatLng place1
//                = new LatLng(25.0336962, 121.5643673);
        String title="萬用陶瓷刀";//1
        for (int i = 0; i < position.length; i++) {
            Marker pinnedMarker=mMap.addMarker(new MarkerOptions().position(new LatLng(position[i][0],position[i][1]))
                    .title(title)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maps_y))
                    .snippet("點擊查看資訊")
                    .zIndex(i)
            );

            if(i==0)//2
                title="令和杯麵";

            else if(i==1)//3
                title="帆布兩用包";
            else if(i==2)//4
                title="若元胃腸錠";
            else if(i==3)//5
                title="井筒八ッ橋　化粧箱入り";
            else if(i==4)//6
                title="黃金果凍面膜(玫瑰)";
            else if(i==5)//7
                title="水潤保濕修護面膜";
            else if(i==6)//8
                title="保濕精華液";
            else  if(i==7)//9
                title="艾多美炸醬麵";
            else  if(i==8)//10
                title="素面毛料側拉鏈魚尾短褲裙";
            else  if(i==9)//11
                title="繡花LOGO帽T 黑";
            else  if(i==10)//12
                title="中腰直筒六袋工作長褲 綠";
            else  if(i==11)//13
                title="長袖連身工作裙 卡其";
            else  if(i==12)//14
                title="燒酒";
            else  if(i==13)//15
                title="深山版帽t";
            else  if(i==14)//16
                title="熊貓背包";
            else  if(i==15)//17
                title="真芋頭";
            else  if(i==16)//18
                title="有機脫殼紅藜";
            else  if(i==17)//19
                title="雪藏棋餅";
            else  if(i==18)//20
                title="黑色“Gucci Signature”錢包";
            else  if(i==19)//21
                title="金盞花植物精華化妝水";
            else  if(i==20)//22
                title="亞馬遜白泥淨緻毛孔面膜";
            else  if(i==21)//23
                title="酪梨精粹修護保濕面膜";
            else  if(i==22)//24
                title="激光極淨白淡斑精華";
            else  if(i==23)//25
                title="時尚腕錶";



            startDropMarkerAnimation(pinnedMarker);


        }


        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
//        mUiSettings.setMyLocationButtonEnabled(true);
//        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Point1,8));
        requestPermission();



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 51){
            if(resultCode == RESULT_OK){
                getDeviceLocation();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation(){
        mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    mLastKnownLocation = task.getResult();
                    if(mLastKnownLocation != null){
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                    } else{
                        final LocationRequest locationRequest = LocationRequest.create();
                        locationRequest.setInterval(10000);
                        locationRequest.setFastestInterval(5000);
                        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                        locationCallback = new LocationCallback(){
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                super.onLocationResult(locationResult);
                                if(locationResult == null){
                                    return;
                                }
                                mLastKnownLocation = locationResult.getLastLocation();
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                                mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
                            }
                        };
                        mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

                    }
                } else{
                    Toast.makeText(Maps.this, "unable to get last location", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void onLocationChanged(Location location) {
        // 取得地圖座標值:緯度,經度
        x="緯=" + Double.toString(location.getLatitude());
        y="經=" + Double.toString(location.getLongitude());
        Point = new LatLng(location.getLatitude(), location.getLongitude());
//        zoom=17; //設定放大倍率1(地球)-21(街景)
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Point, 8));


//        Toast.makeText(this, x + "\n" + y, Toast.LENGTH_LONG).show();

//        //抓國家代碼



        try {

           addresses = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size() > 0) {
            String CountryID= addresses.get(0).getCountryCode();
//            Toast.makeText(this, "國家碼:"+CountryID, Toast.LENGTH_LONG).show();

//            Log.d("test", "國家碼: 斯"+CountryID);
        }

    }

    private void startDropMarkerAnimation(final Marker marker) {
        final LatLng target = marker.getPosition();
        final Handler handler = new Handler();

        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        Point targetPoint = proj.toScreenLocation(target);
        final long duration = (long) (200 + (targetPoint.y * 3));
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        startPoint.y = 0;
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final Interpolator interpolator = new LinearOutSlowInInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * target.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * target.latitude + (1 - t) * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    // Post again 16ms later == 60 frames per second
                    handler.postDelayed(this, 500);
                }
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {  // Androis 6.0 以上
            // 判斷是否已取得授權
            int hasPermission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {  // 未取得授權
                ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return;
            }
        }
        // 如果裝置版本是 Androis 6.0 以下，
        // 或是裝置版本是6.0（包含）以上，使用者已經授權
        setMyLocation(); //  顯示定位圖層
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) { //按 允許 鈕
                setMyLocation(); //  顯示定位圖層
            }else{  //按 拒絕 鈕
                Toast.makeText(this, "未取得授權！", Toast.LENGTH_SHORT).show();
                finish();  // 結束應用程式
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //  顯示定位圖層
    private void setMyLocation() throws SecurityException {
        mMap.setMyLocationEnabled(true); // 顯示定位圖層
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        AutocompletePrediction selectedPrediction = predictionList.get(position);
//        String suggestion= materialSearchBar.getLastSuggestions().get(position).toString();
//        materialSearchBar.setText(suggestion);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                materialSearchBar.clearSuggestions();
            }
        }, 1000);

        materialSearchBar.clearSuggestions();

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromWindow(materialSearchBar.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
//            String placeId = selectedPrediction.getPlaceId();
            List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG);

            final FetchPlaceRequest fetchPlaceRequest = FetchPlaceRequest.builder("日本", placeFields).build();
            placesClient.fetchPlace(fetchPlaceRequest).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                @Override
                public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                    Place place = fetchPlaceResponse.getPlace();
                    Log.i("mytag", "Place found: " + place.getName());
                    LatLng latLngOfPlace = place.getLatLng();
                    if (latLngOfPlace != null) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngOfPlace, 15));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ApiException) {
                        ApiException apiException = (ApiException) e;
                        apiException.printStackTrace();
                        int statusCode = apiException.getStatusCode();
                        Log.i("mytag", "place not found: " + e.getMessage());
                        Log.i("mytag", "status code: " + statusCode);
                    }
                }
            });
        }
        return super.dispatchKeyEvent(event);
    }
}
