package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result extends AppCompatActivity {//買賣資料庫差和單詞差和返回差
    ListView lv62;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    ArrayList<SellerOrder_Container>  list = new ArrayList<SellerOrder_Container>();
    SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_62);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        final Bundle bundle=getIntent().getExtras();
        toolbar.setTitle(bundle.getString("單詞"));

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Result.this, Seller.class);
                startActivity(intent1);
                finish();
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();


        lv62= (ListView)findViewById(R.id.lv62);

        //new一個Bundle物件，並將要傳遞的資料傳入
        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("seller_order")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            boolean have=false;
            int count=0;
//            ArrayList<Integer> num=new  ArrayList<Integer>();


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("country").getValue().equals(bundle.getString("單詞"))){
                        SellerOrder_Container article = ds.getValue(SellerOrder_Container.class);
                        list.add(article);
//                        num.add(count);
                    }
                    count++;
                    have=true;
                }
                if(have==true) {

                    List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
                    for (int i = 0; i < list.size(); i++) {

                        Map<String, Object> item = new HashMap<String, Object>();
//                        riversRef = mStorageRef.child("seller_order").child(list.get(i).getPicture());
                        item.put("from","Result");
                        item.put("image",list.get(i).getPicture().toString() );
                        item.put("brand",list.get(i).getBrand().toString());
                        item.put("text", list.get(i).getName().toString());
                        item.put("price",list.get(i).getPrice().toString());
                        item.put("num",list.get(i).getNum().toString());
                        item.put("fee",list.get(i).getFee().toString());
                        items.add(item);
                    }
                    if(items.size()!=0){
                        searchAdapter=new SearchAdapter(Result.this,items,"seller_order");
                        lv62.setAdapter(searchAdapter);
                        lv62.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                        Intent intent7 = new Intent();
                                        intent7.setClass(Result.this, SellerOrder.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("classify", list.get(position).getClassify());
                                        bundle.putString("brand", list.get(position).getBrand());
                                        bundle.putString("name", list.get(position).getName());
                                        bundle.putString("standard", list.get(position).getStandard());
                                        bundle.putString("model", list.get(position).getModel());
                                        bundle.putString("url", list.get(position).getUrl());
                                        bundle.putString("country", list.get(position).getCountry());
                                        bundle.putString("place", list.get(position).getPlace());
                                        bundle.putString("other", list.get(position).getOther());
                                        bundle.putString("price", list.get(position).getPrice());
                                        bundle.putString("num", list.get(position).getNum());
                                        bundle.putString("fee", list.get(position).getFee());
                                        bundle.putString("delivery", list.get(position).getDelivery());
                                        bundle.putLong("time", list.get(position).getMessageTime());
                                        bundle.putString("seller", list.get(position).getUser());
                                        bundle.putString("sellernick", list.get(position).getUsernick());
                                        bundle.putString("receipt", list.get(position).getReceipt());

                                        bundle.putString("from", "Result");
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Result.this, Seller.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}
