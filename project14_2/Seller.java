package com.project14.nccu105.project14_2;



import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class Seller extends Activity {
    FloatingActionButton mFab;
//    HorizontalListView listOfMessages;
    Gallery buyer1 ;
    Gallery buyer2 ;
    Gallery buyer3 ;
    Gallery buyer4 ;
    Gallery seller1 ;
    ListView seller2;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    SearchView search1,search2;
    SearchAdapter searchAdapter;


private void setupWindowAnimations() {
    Slide slide = null;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        slide = new Slide();

        getWindow().setEnterTransition(slide);
    }

}

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.seller);
            final ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.vs8_1);


            setupWindowAnimations();
//            getSupportActionBar().hide();
            //設定隱藏狀態
            search1=(SearchView)findViewById(R.id.sv8_1);
            search2=(SearchView)findViewById(R.id.sv8_2);

            FirebaseStorage storage = FirebaseStorage.getInstance();
            mStorageRef = storage.getReference();


            Button btn81 = (Button) findViewById(R.id.btn8_1);
//            Button btn82 = (Button) findViewById(R.id.btn8_2);
            Button btn84 = (Button) findViewById(R.id.btn8_4);
            Button btn85 = (Button) findViewById(R.id.btn8_5);
            Button btn86 = (Button) findViewById(R.id.btn8_6);
            Button btn89 = (Button) findViewById(R.id.btn8_9);
            Button btn810 = (Button) findViewById(R.id.btn8_10);
            final Button buttonPrev = (Button) findViewById(R.id.btn8_7);
            final Button buttonNext = (Button) findViewById(R.id.btn8_8);
            final ScrollView sv8 = (ScrollView) findViewById(R.id.sv8);

            EditText searchEditText1 = (EditText) search1.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            EditText searchEditText2 = (EditText) search2.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchEditText1.setTextColor(getResources().getColor(R.color.quantum_grey700));
            searchEditText2.setTextColor(getResources().getColor(R.color.quantum_grey700));


            search1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {  // 點選軟體盤搜尋按鈕會彈出 吐司
                    Intent intent =new Intent();
                    intent.setClass(Seller.this, SoSearch.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("單詞",s);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return false;
                }
                // 搜尋框文字改變事件
                @Override
                public boolean onQueryTextChange(String s) {
                    // 文字內容是空就讓 recyclerView 填充全部資料 // 可以是其他容器 如listView
                    if (TextUtils.isEmpty(s)) {  // 文字工具 檢測是否為空，檢測空，是輸入文字改變 並且為空時觸發，剛點選時候雖然為空，但是文字內容沒有改變
                        // 設定 容器 的更新
                    }
                    return false;
                }
            });


//            searchEditText.setHintTextColor(getResources().getColor(R.color.browser_actions_bg_grey));

            search2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {  // 點選軟體盤搜尋按鈕會彈出 吐司
                    Intent intent =new Intent();
                    intent.setClass(Seller.this, BoSearch.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("單詞",s);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return false;
                }
                // 搜尋框文字改變事件
                @Override
                public boolean onQueryTextChange(String s) {
                    // 文字內容是空就讓 recyclerView 填充全部資料 // 可以是其他容器 如listView
                    if (TextUtils.isEmpty(s)) {  // 文字工具 檢測是否為空，檢測空，是輸入文字改變 並且為空時觸發，剛點選時候雖然為空，但是文字內容沒有改變
                        // 設定 容器 的更新
                    }
                    return false;
                }
            });




            displayChatMessages();



            Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
            Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

            viewSwitcher.setInAnimation(slide_in_left);
            viewSwitcher.setOutAnimation(slide_out_right);





            View.OnClickListener OCL = new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int id = ((Button) v).getId();
                    switch (id) {

                        case R.id.btn8_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Seller.this,Forum.class);
                            startActivity(intent1);
                            Seller.this.finish();

                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
//
                        case R.id.btn8_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Seller.this,Maps.class);
                            Seller.this.finish();
                            startActivity(intent4);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn8_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Seller.this,Notify.class);
                            Seller.this.finish();
                            startActivity(intent5);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn8_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Seller.this,Personal.class);
                            Seller.this.finish();
                            startActivity(intent6);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            break;
                        case R.id.btn8_9:
                            DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                            reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                                        if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                                .getCurrentUser()
                                                .getEmail())){
                                            if(ds.child("isSeller").getValue().toString().equals("0")||ds.child("isSeller").getValue().toString().equals("2")){
                                                Toast.makeText(Seller.this, "您還不是賣家喔！", Toast.LENGTH_SHORT).show();
                                            }else{

                                                Intent intent9 = new Intent();
                                                intent9.setClass(Seller.this,NewSellerOrder_1.class);
                                                Bundle bundle9 =new Bundle();
                                                bundle9.putString("mypic","null");
                                                bundle9.putString("from","Seller");
                                                intent9.putExtras(bundle9);
                                                startActivity(intent9);
//                            Toast.makeText(Seller.this, msg1, Toast.LENGTH_SHORT).show();
                                                Seller.this.finish();

                                            }

                                        }



                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }


                            });

                            break;
                        case R.id.btn8_10:
//                            String msg9 = "商品";
                            Intent intent10 = new Intent();
                            intent10.setClass(Seller.this,NewBuyerOrder_1.class);
                            Bundle bundle10 =new Bundle();
                            bundle10.putString("mypic","null");
                            bundle10.putString("from","Seller");
                            intent10.putExtras(bundle10);
                            startActivity(intent10);
//                            Toast.makeText(Seller.this, msg1, Toast.LENGTH_SHORT).show();
                            Seller.this.finish();
                            break;
                        case R.id.btn8_7:
                            // viewSwitcher.showPrevious();切换效果类似
                            viewSwitcher.setDisplayedChild(0);
                            sv8.post(new Runnable() {
                                @Override
                                public void run() {

                                    int[] location = new int[2];
                                    buttonPrev.getLocationOnScreen(location);
                                    int offset = location[1] - sv8.getMeasuredHeight();
                                    if (offset < 0) {
                                        offset = 0;
                                    }
                                    sv8.scrollTo(0, offset);
                                }
                            });
                            break;
                        case R.id.btn8_8:
                            // viewSwitcher.showNext();切换效果类似
                            viewSwitcher.setDisplayedChild(1);
                            seller2= (ListView)findViewById(R.id.lv8_3);
                            Utility.setListViewHeightBasedOnChildren(seller2);



                            sv8.post(new Runnable() {
                                @Override
                                public void run() {

                                    int[] location = new int[2];
                                    buttonNext.getLocationOnScreen(location);
                                    int offset = location[1] - sv8.getMeasuredHeight();
                                    if (offset < 0) {
                                        offset = 0;
                                    }
                                    sv8.scrollTo(0, offset);
                                }
                            });
                            break;

                    }
                }
            };
            btn81.setOnClickListener(OCL);
//            btn82.setOnClickListener(OCL);
            btn84.setOnClickListener(OCL);
            btn85.setOnClickListener(OCL);
            btn86.setOnClickListener(OCL);
            btn89.setOnClickListener(OCL);
            btn810.setOnClickListener(OCL);
            buttonPrev.setOnClickListener(OCL);
            buttonNext.setOnClickListener(OCL);


//
//
        }



    private FirebaseListAdapter<BuyerOrder_Container> adapter;
    private FirebaseListAdapter<BuyerOrder_Container> adapter1;
    private FirebaseListAdapter<SellerOrder_Container> adapter2;
//    private BuyerOrder_Container adapter1;
    private void displayChatMessages() {
//        listOfMessages = (HorizontalListView) findViewById(R.id.lv8_1);
        buyer1= (Gallery) findViewById(R.id.lv8_1);
        buyer2= (Gallery) findViewById(R.id.lv8_5);
        buyer3= (Gallery) findViewById(R.id.lv8_6);
        buyer4= (Gallery) findViewById(R.id.lv8_4);
        seller1= (Gallery) findViewById(R.id.lv8_2);
        seller2= (ListView)findViewById(R.id.lv8_3);

        buyer1.setScrollbarFadingEnabled(false);
        buyer2.setScrollbarFadingEnabled(false);
        buyer3.setScrollbarFadingEnabled(false);
        buyer4.setScrollbarFadingEnabled(false);
        seller1.setScrollbarFadingEnabled(false);

        DatabaseReference[] reference_contacts21 = {FirebaseDatabase.getInstance().getReference("buyer_order")};//第二頁橫的
        reference_contacts21[0].addValueEventListener(new ValueEventListener() {
            boolean has=false;
            int j;
            ArrayList<BuyerOrder_Container>  list1 = new ArrayList<BuyerOrder_Container>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BuyerOrder_Container article = ds.getValue(BuyerOrder_Container.class);
                    list1.add(article);
                    has=true;
                }
                if(has==true){

                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_product_chat")};
                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                        boolean have2=false;
                        ArrayList<BuyerOrder_Container>  list2 = new ArrayList<BuyerOrder_Container>();
                        List<Map<String, Object>> num = new ArrayList<Map<String,Object>>();
                        List<Map<String, Object>> num2 = new ArrayList<Map<String,Object>>();
                        ArrayList<BuyerOrder_Container>  list3 = new ArrayList<BuyerOrder_Container>();
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot dds : dataSnapshot.getChildren()) {

                                for(j=0 ;j<list1.size();j++){

                                    if(dds.getKey().equals(Long.toString(list1.get(j).getMessageTime()))){
                                        list2.add(list1.get(j));
                                        have2=true;

                                        Map<String, Object> item = new HashMap<String, Object>();
                                        item.put("title",Long.toString(list1.get(j).getMessageTime()) );
                                        item.put("num",(int)dds.getChildrenCount());
                                        num.add(item);

                                    }

                                }

                            }


                            if(have2==true) {

                                num2=SelectionSort(num,num.size());


                                for(j=0 ;j<num2.size();j++){
                                    for(int k=0;k<num2.size();k++){
                                        if(num2.get(j).get("title").equals(Long.toString(list2.get(k).getMessageTime())))
                                            list3.add(list2.get(k));
                                    }

                                }

                                List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
                                for (int i = 0; i < list3.size(); i++) {

                                    Map<String, Object> item = new HashMap<String, Object>();
                                    item.put("from","Seller");
                                    item.put("image",list3.get(i).getPicture().toString() );
                                    item.put("text", list3.get(i).getName().toString());
                                    items.add(item);
                                }
                                if(items.size()!=0){
                                    searchAdapter=new SearchAdapter(Seller.this,items,"buyer_order");
                                    seller1.setAdapter(searchAdapter);

                                    seller1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                            Intent intent7 = new Intent();
                                            intent7.setClass(Seller.this, BuyerOrder.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("classify", list3.get(position).getClassify());
                                            bundle.putString("brand", list3.get(position).getBrand());
                                            bundle.putString("name", list3.get(position).getName());
                                            bundle.putString("standard", list3.get(position).getStandard());
                                            bundle.putString("model", list3.get(position).getModel());
                                            bundle.putString("url", list3.get(position).getUrl());
                                            bundle.putString("country", list3.get(position).getCountry());
                                            bundle.putString("place", list3.get(position).getPlace());
                                            bundle.putString("other", list3.get(position).getOther());
                                            bundle.putString("price", list3.get(position).getPrice());
                                            bundle.putString("num", list3.get(position).getNum());
                                            bundle.putString("fee", list3.get(position).getFee());
                                            bundle.putString("delivery", list3.get(position).getDelivery());
                                            bundle.putLong("time", list3.get(position).getMessageTime());
                                            bundle.putString("buyer", list3.get(position).getUser());
                                            bundle.putString("buyernick", list3.get(position).getUsernick());
                                            bundle.putString("receipt", list3.get(position).getReceipt());

                                            bundle.putString("from", "Seller");
                                            intent7.putExtras(bundle);
                                            startActivity(intent7);

                                        }
                                    });


                                }

                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference[] reference_contacts22 = {FirebaseDatabase.getInstance().getReference("buyer_order")};//第二頁直的
        reference_contacts22[0].addValueEventListener(new ValueEventListener() {
            boolean has=false;
            boolean have2=false;
            int j;
            ArrayList<BuyerOrder_Container>  list1 = new ArrayList<BuyerOrder_Container>();
            ArrayList<BuyerOrder_Container>  list2 = new ArrayList<BuyerOrder_Container>();
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BuyerOrder_Container article = ds.getValue(BuyerOrder_Container.class);
                    list1.add(article);
                    has=true;
                }
                if(has==true){

                    for(int i=list1.size()-1;i>=0;i--){
                        list2.add(list1.get(i));
                        have2=true;
                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("from","Seller22");
                        item.put("text",list1.get(i).getName() );
                        item.put("image",list1.get(i).getPicture());
                        item.put("fee",list1.get(i).getFee());
                        item.put("price",list1.get(i).getPrice());
                        item.put("num",list1.get(i).getNum());
                        items.add(item);
                    }

                    if(have2==true) {

                        if(items.size()!=0){
                            searchAdapter=new SearchAdapter(Seller.this,items,"buyer_order");
                            seller2.setAdapter(searchAdapter);

                            seller2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                    Intent intent7 = new Intent();
                                    intent7.setClass(Seller.this, BuyerOrder.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("classify", list2.get(position).getClassify());
                                    bundle.putString("brand", list2.get(position).getBrand());
                                    bundle.putString("name", list2.get(position).getName());
                                    bundle.putString("standard", list2.get(position).getStandard());
                                    bundle.putString("model", list2.get(position).getModel());
                                    bundle.putString("url", list2.get(position).getUrl());
                                    bundle.putString("country", list2.get(position).getCountry());
                                    bundle.putString("place", list2.get(position).getPlace());
                                    bundle.putString("other", list2.get(position).getOther());
                                    bundle.putString("price", list2.get(position).getPrice());
                                    bundle.putString("num", list2.get(position).getNum());
                                    bundle.putString("fee", list2.get(position).getFee());
                                    bundle.putString("delivery", list2.get(position).getDelivery());
                                    bundle.putLong("time", list2.get(position).getMessageTime());
                                    bundle.putString("buyer", list2.get(position).getUser());
                                    bundle.putString("buyernick", list2.get(position).getUsernick());
                                    bundle.putString("receipt", list2.get(position).getReceipt());

                                    bundle.putString("from", "Seller");
                                    intent7.putExtras(bundle);
                                    startActivity(intent7);

                                }
                            });


                        }

                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("seller_order")};//第一頁第二行
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            int num =0;
            boolean has=false;
            int j;
            ArrayList<SellerOrder_Container>  list1 = new ArrayList<SellerOrder_Container>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SellerOrder_Container article = ds.getValue(SellerOrder_Container.class);
                    list1.add(article);
                    has=true;
                }
                if(has==true){

                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("seller_product_chat")};
                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                        boolean have2=false;
                        ArrayList<SellerOrder_Container>  list2 = new ArrayList<SellerOrder_Container>();
                        List<Map<String, Object>> num = new ArrayList<Map<String,Object>>();
                        List<Map<String, Object>> num2 = new ArrayList<Map<String,Object>>();
                        ArrayList<SellerOrder_Container>  list3 = new ArrayList<SellerOrder_Container>();
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot dds : dataSnapshot.getChildren()) {

                                for(j=0 ;j<list1.size();j++){

                                    if(dds.getKey().equals(Long.toString(list1.get(j).getMessageTime()))){
                                        list2.add(list1.get(j));
                                        have2=true;

                                            Map<String, Object> item = new HashMap<String, Object>();
                                            item.put("title",Long.toString(list1.get(j).getMessageTime()) );
                                            item.put("num",(int)dds.getChildrenCount());
                                            num.add(item);

                                    }

                                }

                            }


                            if(have2==true) {

                               num2=SelectionSort(num,num.size());


                                for(j=0 ;j<num2.size();j++){
                                    for(int k=0;k<num2.size();k++){
                                        if(num2.get(j).get("title").equals(Long.toString(list2.get(k).getMessageTime())))
                                            list3.add(list2.get(k));
                                    }

                                }

                                List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
                                for (int i = 0; i < list3.size(); i++) {

                                    Map<String, Object> item = new HashMap<String, Object>();
                                    item.put("from","Seller");
                                    item.put("image",list3.get(i).getPicture().toString() );
                                    item.put("text", list3.get(i).getName().toString());
                                    items.add(item);
                                }
                                if(items.size()!=0){
                                    searchAdapter=new SearchAdapter(Seller.this,items,"seller_order");
                                    buyer2.setAdapter(searchAdapter);
                                    buyer2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                            Intent intent7 = new Intent();
                                            intent7.setClass(Seller.this, SellerOrder.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("classify", list3.get(position).getClassify());
                                            bundle.putString("brand", list3.get(position).getBrand());
                                            bundle.putString("name", list3.get(position).getName());
                                            bundle.putString("standard", list3.get(position).getStandard());
                                            bundle.putString("model", list3.get(position).getModel());
                                            bundle.putString("url", list3.get(position).getUrl());
                                            bundle.putString("country", list3.get(position).getCountry());
                                            bundle.putString("place", list3.get(position).getPlace());
                                            bundle.putString("other", list3.get(position).getOther());
                                            bundle.putString("price", list3.get(position).getPrice());
                                            bundle.putString("num", list3.get(position).getNum());
                                            bundle.putString("fee", list3.get(position).getFee());
                                            bundle.putString("delivery", list3.get(position).getDelivery());
                                            bundle.putLong("time", list3.get(position).getMessageTime());
                                            bundle.putString("seller", list3.get(position).getUser());
                                            bundle.putString("sellernick", list3.get(position).getUsernick());
                                            bundle.putString("receipt", list3.get(position).getReceipt());

                                            bundle.putString("from", "Seller");
                                            intent7.putExtras(bundle);
                                            startActivity(intent7);

                                        }
                                    });


                                }

                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DatabaseReference[] reference_contacts14 = {FirebaseDatabase.getInstance().getReference("seller_order")};//第一頁第四行
        reference_contacts14[0].addValueEventListener(new ValueEventListener() {
            boolean has=false;
            boolean have2=false;
            int j;
            ArrayList<SellerOrder_Container>  list1 = new ArrayList<SellerOrder_Container>();
            ArrayList<SellerOrder_Container>  list2 = new ArrayList<SellerOrder_Container>();
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SellerOrder_Container article = ds.getValue(SellerOrder_Container.class);
                    list1.add(article);
                    has=true;
                }
                if(has==true){

                    for(int i=list1.size()-1;i>=0;i--){
                        list2.add(list1.get(i));
                        have2=true;
                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("from","Seller");
                        item.put("image",list1.get(i).getPicture().toString() );
                        item.put("text", list1.get(i).getName().toString());
                        items.add(item);
                    }

                    if(have2==true) {

                        if(items.size()!=0){
                            searchAdapter=new SearchAdapter(Seller.this,items,"seller_order");
                            buyer4.setAdapter(searchAdapter);

                            buyer4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                    Intent intent7 = new Intent();
                                    intent7.setClass(Seller.this, SellerOrder.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("classify", list2.get(position).getClassify());
                                    bundle.putString("brand", list2.get(position).getBrand());
                                    bundle.putString("name", list2.get(position).getName());
                                    bundle.putString("standard", list2.get(position).getStandard());
                                    bundle.putString("model", list2.get(position).getModel());
                                    bundle.putString("url", list2.get(position).getUrl());
                                    bundle.putString("country", list2.get(position).getCountry());
                                    bundle.putString("place", list2.get(position).getPlace());
                                    bundle.putString("other", list2.get(position).getOther());
                                    bundle.putString("price", list2.get(position).getPrice());
                                    bundle.putString("num", list2.get(position).getNum());
                                    bundle.putString("fee", list2.get(position).getFee());
                                    bundle.putString("delivery", list2.get(position).getDelivery());
                                    bundle.putLong("time", list2.get(position).getMessageTime());
                                    bundle.putString("seller", list2.get(position).getUser());
                                    bundle.putString("sellernick", list2.get(position).getUsernick());
                                    bundle.putString("receipt", list2.get(position).getReceipt());

                                    bundle.putString("from", "Seller");
                                    intent7.putExtras(bundle);
                                    startActivity(intent7);

                                }
                            });


                        }

                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)buyer1.getLayoutParams();
        ViewGroup.MarginLayoutParams layoutParams1 = (ViewGroup.MarginLayoutParams)buyer2.getLayoutParams();
        ViewGroup.MarginLayoutParams layoutParams11 = (ViewGroup.MarginLayoutParams)buyer3.getLayoutParams();
        ViewGroup.MarginLayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams)buyer4.getLayoutParams();
        ViewGroup.MarginLayoutParams layoutParams3 = (ViewGroup.MarginLayoutParams)seller1.getLayoutParams();
//        ViewGroup.MarginLayoutParams layoutParams4 = (ViewGroup.MarginLayoutParams)seller2.getLayoutParams();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        layoutParams.setMargins(-width*2/3, 0, 0, 0);
        layoutParams1.setMargins(-width*2/3, 0, 0, 0);
        layoutParams11.setMargins(-width*2/3, 0, 0, 0);
        layoutParams2.setMargins(-width*2/3, 0, 0, 0);
        layoutParams3.setMargins(-width*2/3, 0, 0, 0);
//        layoutParams4.setMargins(-width*2/3, 0, 0, 0);


        int[] image = {
                R.drawable.japan,
                R.drawable.korea,
                R.drawable.america,
                R.drawable.taiwan,
                R.drawable.french,
        };


        String[] str1 = {"日本","韓國","美國","台灣","法國"};
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < str1.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", image[i]);
            item.put("text", str1[i]);
            items.add(item);
        }
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        SimpleAdapter adapter = new SimpleAdapter(this, items,
                R.layout.product,new String[]{"image", "text"}, new int[]{R.id.img_list, R.id.text_list}
        );

        buyer1.setAdapter(adapter);

        buyer1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int position, long id) {//第一頁第一行
                Intent intent =new Intent();
                intent.setClass(Seller.this, Result.class);
                Bundle bundle = new Bundle();

                if(id==0){
//                    Toast.makeText(Seller.this, "日本", Toast.LENGTH_LONG).show();
                    bundle.putString("單詞", "日本");
                }
                if(id==1){
//                    Toast.makeText(Seller.this, "韓國", Toast.LENGTH_LONG).show();
                    bundle.putString("單詞", "韓國");
                }
                if(id==2){
//                    Toast.makeText(Seller.this, "美國", Toast.LENGTH_LONG).show();
                    bundle.putString("單詞", "美國");
                }

                if(id==3){
//                    Toast.makeText(Seller.this, "台灣", Toast.LENGTH_LONG).show();
                    bundle.putString("單詞", "台灣");
                }

                if(id==4){
//                    Toast.makeText(Seller.this, "西班牙", Toast.LENGTH_LONG).show();
                    bundle.putString("單詞", "法國");
                }

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


        DatabaseReference[] reference_contacts15 = {FirebaseDatabase.getInstance().getReference("seller_file")};//第一頁第三行
        reference_contacts15[0].addValueEventListener(new ValueEventListener() {
            boolean has=false;
            ArrayList<String>  acc = new ArrayList<String>();
            ArrayList<String>  who = new ArrayList<String>();
            ArrayList<String>  acc2 = new ArrayList<String>();//反序
            ArrayList<String>  who2 = new ArrayList<String>();

            ArrayList<String>  pic = new ArrayList<String>();
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getKey().equals("0")||ds.child("check").getValue().toString().equals("no")){

                    }else{
                        acc.add(ds.child("email").getValue().toString());
//                        Log.d("acc", "onDataChange: " + ds.child("email").getValue().toString());
                    }

                has=true;

                }
                if(has==true) {
                    int i;
                    for (i=0;i<acc.size();i++){
                        final String accs=acc.get(i);
                        final int w=i;
                        //找賣家本身的頭貼和名字
                        DatabaseReference[] reference_contacts16 = {FirebaseDatabase.getInstance().getReference("buyer_file")};//第一頁第三行
                        reference_contacts16[0].addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot dds : dataSnapshot.getChildren()) {
                                    if (accs.equals(dds.child("mail").getValue().toString())) {
                                        who.add(dds.child("nickname").getValue().toString());
                                        pic.add(dds.child("picture").getValue().toString());
                                        if (w==acc.size()-1) {

                                            for(int i=acc.size()-1;i>=0;i--){
                                                acc2.add(acc.get(i));
                                                who2.add(who.get(i));
                                                Map<String, Object> item = new HashMap<String, Object>();
                                                item.put("from","Seller");
                                                item.put("image",pic.get(i).toString() );
                                                item.put("text", who.get(i).toString());
                                                items.add(item);
                                            }

                                            if (items.size() != 0) {
                                                searchAdapter = new SearchAdapter(Seller.this, items, "member_picture");
                                                buyer3.setAdapter(searchAdapter);
                                                buyer3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                                        Intent intent7 = new Intent();
                                                        intent7.setClass(Seller.this, Member_page.class);
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("from", "Seller");
                                                        bundle.putString("useracc", acc2.get(position).toString());
                                                        bundle.putString("user", who2.get(position).toString());
                                                        intent7.putExtras(bundle);
                                                        startActivity(intent7);

                                                    }
                                                });

                                            }

                                        }
                                    }

                                }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return false;
    }

    public  List<Map<String, Object>> SelectionSort(  List<Map<String, Object>> list,int n){//只用num
        int max;
        int[] A=new int[list.size()];
        for(int i=0;i<list.size();i++)
        {
            A[i]=Integer.parseInt(list.get(i).get("num").toString());
        }
        for(int i=0 ;i<n-1;i++){
            max=i;
            for(int j=(i+1);j<n;j++){
                if(A[j]>A[max]){
                    max=j;
                }

            }
            if(max!=i){

                int temp=A[i];
                A[i]=A[max];
                A[max]=temp;
            }
        }
        List<Map<String, Object>> num2 = new ArrayList<Map<String,Object>>();
        for(int i=0;i<list.size();i++)
        {
            for(int k=0;k<list.size();k++){
                if (A[i]==Integer.parseInt(list.get(k).get("num").toString())){
                    boolean num2has=false;

                    for(int q=0;q<num2.size();q++){
                        if(num2.get(q).get("title").equals(list.get(k).get("title").toString()))
                           num2has=true;

                    }
                    if(num2has==false)
                    num2.add(list.get(k));

                }
            }

        }

        return  num2;


    }



}
