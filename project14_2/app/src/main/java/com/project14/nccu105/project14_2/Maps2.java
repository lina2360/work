package com.project14.nccu105.project14_2;

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
import android.support.v4.app.NotificationCompat;
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
import android.widget.RelativeLayout;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static android.graphics.Color.rgb;

public class Maps2 extends FragmentActivity implements OnMapReadyCallback,LocationListener,GeoQueryEventListener {
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
    private List<LatLng> shoppingArea;
    private GeoFire geoFire;

    private DatabaseReference myLocationRef;
    private Marker currentUser;
    //13
    private void setupWindowAnimations() {
        Slide slide = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            slide = new Slide();

            getWindow().setEnterTransition(slide);
        }
    }
//    private void initArea() {
//        shoppingArea = new ArrayList<>();
//        shoppingArea.add(new LatLng(25.0365, 121.5439));
//        shoppingArea.add(new LatLng(25.0365, 121.5329));
//        shoppingArea.add(new LatLng(24.9910, 121.5759));
////
//    }
    private void settingGeoFire() {
        myLocationRef = FirebaseDatabase.getInstance().getReference("MyLocation");
        geoFire = new GeoFire(myLocationRef);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        setupWindowAnimations();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        mapView = mapFragment.getView();

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Maps2.this);

        btn131 = (Button) findViewById(R.id.btn70_1);
//        btn132 = (Button) findViewById(R.id.btn13_2);
        btn133 = (Button) findViewById(R.id.btn70_3);
        btn135 = (Button) findViewById(R.id.btn70_5);
        btn136 = (Button) findViewById(R.id.btn70_6);


        geoCoder= new Geocoder(Maps2.this);
        materialSearchBar = findViewById(R.id.searchBar2);
        btnFind = findViewById(R.id.btn_find2);
        btnFind.setText("切換至尋找商品");
//        btnFind.setBackground(R.drawable.btn_selector);

//        AIzaSyADKCSeewapIVgthrlmHf9cwmdTvA7grmI
        Places.initialize(Maps2.this, "AIzaSyADKCSeewapIVgthrlmHf9cwmdTvA7grmI");
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
                intent7.setClass(Maps2.this, Maps.class);
                startActivity(intent7);
                Maps2.this.finish();

            }
        });
//        initArea();
        settingGeoFire();

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
                    case R.id.btn70_1:
                        String msg1 = "往論壇頁";
                        Intent intent1 = new Intent();
                        intent1.setClass(Maps2.this,Forum.class);
                        Maps2.this.finish();
                        startActivity(intent1);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case R.id.btn70_3:
                        String msg3 = "往接單頁";
                        Intent intent3 = new Intent();
                        intent3.setClass(Maps2.this,Seller.class);
                        Maps2.this.finish();
                        startActivity(intent3);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case R.id.btn70_5:
                        String msg5 = "往通知頁";
                        Intent intent5 = new Intent();
                        intent5.setClass(Maps2.this,Notify.class);
                        Maps2.this.finish();
                        startActivity(intent5);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    case R.id.btn70_6:
                        String msg6 = "個人";
                        Intent intent6 = new Intent();
                        intent6.setClass(Maps2.this,Personal.class);
                        Maps2.this.finish();
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

        SettingsClient settingsClient = LocationServices.getSettingsClient(Maps2.this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(Maps2.this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                getDeviceLocation();
            }
        });

        task.addOnFailureListener(Maps2.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof ResolvableApiException){
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(Maps2.this, 51);
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
//                { 24.986427, 121.575523},//政大1
//                { 24.998532, 121.581036},//動物園
//
//
//                {25.047818, 121.517026},//中心
//                {25.046292, 121.515202},//北車
//                {25.048177, 121.516640},
//
//
//                {35.775987, 140.389556},//日本機場6
//                {35.549952, 139.779833},
//
//                {34.702502, 135.495929},//中心
//                {34.703072, 135.494890},//大阪
//                {34.704279, 135.500417},
//                {34.703413, 135.500874},
//
//                {35.686089, 139.773222},//東京12
//                {35.681283, 139.773541},
//                {35.680852, 139.774391},
//                {35.674925, 139.763905},
//
//
//                {35.006382, 135.769804},//中心16
//                {35.004238, 135.765936},//京都
//                {35.004623, 135.768859},
//                {35.008948, 135.768764},
//
//
//                {43.062032, 141.354430},//中心20
//                {43.061116, 141.354505},//札幌
//                {43.061596, 141.352533},
//                {43.059021, 141.353250},
//                {43.066516, 141.349442},
//                {43.068251, 141.351787},
//
//                {32.803418, 130.707928},//中心27
//                {32.801021, 130.702840},//熊本
//                {32.800430, 130.703845},
//                {32.805741, 130.711195},
//                {32.802457, 130.710981},
//                {32.801982, 130.712451}
                {35.683379, 139.768346},//1.蘋果護脣膏
                {35.671629, 139.763691},//2,鱈魚香絲
                {35.672231, 139.765912},//3.耐熱飯糰機
                {35.681279, 139.764561},//4.豆豆龍包
                {35.682850, 139.768412},//5.角落生物東京限定吊飾
                {35.702017, 139.771161},//6.助六轉蛋

                {35.020977, 135.779383},//7.聖・抹茶詰合
                {35.004667, 135.765567},//8.清喉直爽顆粒

                {37.564159, 126.980922},//9.美膚潔顏片
                {37.557254, 126.924545},//10.高麗人參糖
                {37.564929, 126.981655},//11.AM '92 中性慢跑鞋-黑 4-J526T-01
                {37.577484, 126.986518},//12.高麗蔘精Everytime PLUS 30入

                {35.157557, 129.059799},//13.玩轉色彩九色眼彩盤

                {23.043965, 120.667881},//14.原片立體茶包
                {24.988112, 121.575442},//15.一滴露鐵觀音
                {24.986427, 121.575523},//16.政大四維堂兩用便當袋

                {22.626026, 120.361267},//17.綠豆椪
                {22.582802, 120.312771},//18.珍芳三合一禮盒

                {48.862230, 2.335903},//19.情挑誘光水唇膏

//                {48.869163, 2.322918},//20.雙心項鍊"
//                {1.361118, 103.872499},//21.玫瑰金雙心皇冠戒指


        };


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(Maps2.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }

        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
//                Toast.makeText(Maps.this, "按了！", Toast.LENGTH_SHORT).show();
                final ArrayList<BuyerOrder_Container> list = new ArrayList<BuyerOrder_Container>();
                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_order")};
                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count=0;
                        for (DataSnapshot ds : dataSnapshot.getChildren() ) {


                            BuyerOrder_Container article = ds.getValue(BuyerOrder_Container.class);
                            list.add(article);

                        }
                        for(int i=0;i<list.size();i++){
                            if(marker.getTitle().equals(list.get(i).getName())){

                                Intent intent7 = new Intent();
                                intent7.setClass(Maps2.this, BuyerOrder.class);
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
                                bundle.putString("buyer", list.get(i).getUser());
                                bundle.putString("buyernick", list.get(i).getUsernick());
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
        String title="蘋果護脣膏";
        shoppingArea = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            Marker pinnedMarker=mMap.addMarker(new MarkerOptions().position(new LatLng(position[i][0],position[i][1]))
                    .title(title)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_maps_o))
                    .snippet("點擊查看資訊")
                    .zIndex(i)
            );


            shoppingArea.add(new LatLng(position[i][0],position[i][1]));


            if(i==0)//2
                title="鱈魚香絲";
            else if(i==1)//3
                title="耐熱飯糰機";
            else if(i==2)//4
                title="豆豆龍包";
            else if(i==3)//5
                title="角落生物東京限定吊飾";
            else if(i==4)//6
                title="助六轉蛋";
            else if(i==5)//7
                title="聖・抹茶詰合";
            else if(i==6)//8
                title="清喉直爽顆粒";
            else if(i==7)//9
                title="美膚潔顏片";
            else  if(i==8)//10
                title="高麗人參糖 ";
            else  if(i==9)//11
                title="AM '92 中性慢跑鞋-黑 4-J526T-01";
            else  if(i==10)//12
                title="高麗蔘精Everytime PLUS 30入";
            else  if(i==11)//13
                title="玩轉色彩九色眼彩盤";
            else  if(i==12)//14
                title="原片立體茶包";
            else  if(i==13)//15
                title="一滴露鐵觀音";
            else  if(i==14)//16
                title="政大四維堂兩用便當袋";
            else  if(i==15)//17
                title="綠豆椪";
            else  if(i==16)//18
                title="珍芳三合一禮盒";
            else  if(i==17)//19
                title="情挑誘光水唇膏";
//            else  if(i==18)//20
//                title="雙心項鍊";
//            else  if(i==19)//21
//                title="玫瑰金雙心皇冠戒指";




            startDropMarkerAnimation(pinnedMarker);


        }
//        shoppingArea.add(new LatLng(25.0365, 121.5329));
//        shoppingArea.add(new LatLng(24.9910, 121.5759));
//        shoppingArea.add(new LatLng(24.9910, 121.5759));

        for(LatLng latLng : shoppingArea){
            mMap.addCircle(new CircleOptions().center(latLng).radius(500)
                    .strokeColor(Color.TRANSPARENT)
                    .fillColor(Color.TRANSPARENT)
                    .strokeWidth(5.0f));
//            0x220000FF
            //Create GeoQuery when user is in shoppingArea
            GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(latLng.latitude, latLng.longitude), 0.5f);
            geoQuery.addGeoQueryEventListener(Maps2.this);
        }


        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
//        mUiSettings.setMyLocationButtonEnabled(true);
//        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Point1,8));
        requestPermission();
        Toast.makeText(Maps2.this,  "附近有可以代買的商品: 政大紀念T", Toast.LENGTH_LONG).show();



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
                            public void onLocationResult(final LocationResult locationResult) {
                                super.onLocationResult(locationResult);
                                if(locationResult == null){
                                    return;
                                }

                                geoFire.setLocation("您", new GeoLocation(locationResult.getLastLocation().getLatitude(),
                                        locationResult.getLastLocation().getLongitude()), new GeoFire.CompletionListener() {
                                    @Override
                                    public void onComplete(String key, DatabaseError error) {
                                        if (currentUser != null) currentUser.remove();
                                        currentUser = mMap.addMarker(new MarkerOptions()
                                                .position(new LatLng(locationResult.getLastLocation().getLatitude(),
                                                        locationResult.getLastLocation().getLongitude()))
                                                .title("您"));

                                    }
                                });

                                mLastKnownLocation = locationResult.getLastLocation();
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                                mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
                            }
                        };
                        mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

                    }
                } else{
                    Toast.makeText(Maps2.this, "unable to get last location", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void startDropMarkerAnimation(final Marker marker) {
        final LatLng target = marker.getPosition();
        final Handler handler = new Handler();

        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        android.graphics.Point targetPoint = proj.toScreenLocation(target);
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

//    @Override
//    protected void onStop() {
//        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
//        super.onStop();
//    }

    @Override
    public void onKeyEntered(String key, GeoLocation location) {
        sendNotification("代購", String.format("%s的附近有可以代買的商品", key));
        Toast.makeText(Maps2.this,   String.format("%s的附近有可以代買的商品", key), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onKeyExited(String key) {
        sendNotification("代購", String.format("%s的附近沒有商品", key));
        Toast.makeText(Maps2.this,  String.format("%s的附近沒有商品", key), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onKeyMoved(String key, GeoLocation location) {
        sendNotification("代購", String.format("%s正在有商品的區域移動", key));
        Toast.makeText(Maps2.this,  String.format("%s正在有商品的區域移動", key), Toast.LENGTH_SHORT).show();
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
                .setSmallIcon(R.drawable.notify4)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bag102));

        Notification notification = builder.build();
        notificationManager.notify(new Random().nextInt(), notification);
    }


}

