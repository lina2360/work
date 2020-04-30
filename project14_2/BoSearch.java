package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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

public class BoSearch extends AppCompatActivity {//買賣資料庫差和單詞差和返回差
    ListView lv62;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    ArrayList<BuyerOrder_Container> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        getSupportActionBar().hide();
         list = new ArrayList<BuyerOrder_Container>();
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
                intent1.setClass(BoSearch.this, Seller.class);
                startActivity(intent1);
                finish();
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();


        lv62= (ListView)findViewById(R.id.lv62);


//        lv62.setAdapter(adapter);

        //new一個Bundle物件，並將要傳遞的資料傳入
        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_order")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            boolean have=false;
            int count=0;
            ArrayList<Integer> num=new  ArrayList<Integer>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.getValue().toString().contains(bundle.getString("單詞"))){
                        BuyerOrder_Container article = ds.getValue(BuyerOrder_Container.class);
                        list.add(article);
                        num.add(count);
                    }
                    count++;
                    have=true;
                }
                if(have==true) {

                    List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
                    for (int i = 0; i < list.size(); i++) {

                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("from","BoSearch");
                        item.put("brand",list.get(i).getBrand().toString());
                        item.put("image",list.get(i).getPicture().toString() );
                        item.put("text", list.get(i).getName().toString());
                        item.put("price",list.get(i).getPrice().toString());
                        item.put("num",list.get(i).getNum().toString());
                        item.put("fee",list.get(i).getFee().toString());
                        items.add(item);
                    }
                    if(items.size()!=0){
                        SearchAdapter searchAdapter=new SearchAdapter(BoSearch.this,items,"buyer_order");
                        lv62.setAdapter(searchAdapter);
                        lv62.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                                Intent intent7 = new Intent();
                                intent7.setClass(BoSearch.this, BuyerOrder.class);
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
                                bundle.putString("buyer", list.get(position).getUser());
                                bundle.putString("buyernick", list.get(position).getUsernick());
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

        lv62.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Intent intent7 = new Intent();
                intent7.setClass(BoSearch.this, BuyerOrder.class);
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
                bundle.putString("buyer", list.get(position).getUser());
                bundle.putString("buyernick", list.get(position).getUsernick());
                bundle.putString("from", "Seller");
                intent7.putExtras(bundle);
                startActivity(intent7);


            }
        });


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(BoSearch.this, Seller.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}


