package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Personal_setting_myaddress extends AppCompatActivity {
    Button btn181, btn182, btn183, btn184, btn185, btn186;
    ImageButton ib181;
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    ListView lv181;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_address, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add_address){
            Intent intent = new Intent();
            intent.setClass(Personal_setting_myaddress.this, AddAddress.class);
            startActivity(intent);
            Personal_setting_myaddress.this.finish();
        }
        if(item.getItemId()==R.id.action_add_shop_address){
            Intent intent = new Intent();
            intent.setClass(Personal_setting_myaddress.this, AddShopAddress.class);
            startActivity(intent);
            Personal_setting_myaddress.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_myaddress);

//        getSupportActionBar().hide();
        //設定隱藏狀態

        btn181 = (Button) findViewById(R.id.btn18_1);
//        btn182 = (Button) findViewById(R.id.btn18_2);
        btn183 = (Button) findViewById(R.id.btn18_3);
        btn184 = (Button) findViewById(R.id.btn18_4);
        btn185 = (Button) findViewById(R.id.btn18_5);
        btn186 = (Button) findViewById(R.id.btn18_6);
//        ib181 = (ImageButton) findViewById(R.id.ib18_1);


        //https://spicyboyd.blogspot.com/2018/04/app-toolbar-actionbar.html 參考
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar18);
        //右上角menu
        toolbar.inflateMenu(R.menu.menu_address);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//                                               @Override
//                                               public boolean onMenuItemClick(MenuItem item) {
//
//                                               }
//                                           });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_myaddress.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });


//        List<CardViewMember> memberList = new ArrayList<>();
//        memberList.add(new CardViewMember(1, R.drawable.japan, "台北文山區"));
//        memberList.add(new CardViewMember(2, R.drawable.america, "台北信義區"));



//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new CardViewMemberAdapter(this, memberList));
        lv181 = (ListView) findViewById(R.id.lv18_1);
        final String[][] values = new String[10][10];


        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究


        DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts1[0].addValueEventListener(new ValueEventListener() {
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("mail").getValue().equals(iam)){
                        if(ds.child("address").hasChildren())
                        {
                            for(int i=1;i<=(int)ds.child("address").getChildrenCount();i++){
                                Map<String, Object> item = new HashMap<String, Object>();
                                item.put("text", ds.child("address").child(Integer.toString(i)).child("city").getValue().toString()+
                                        ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString());
                                items.add(item);
                            }
                            SimpleAdapter adapter = new SimpleAdapter(Personal_setting_myaddress.this, items,
                                    R.layout.listview3,new String[]{"text"}, new int[]{R.id.article_title60}
                            );
                            lv181.setAdapter(adapter);

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //丟入你要顯示的文字

//        ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 ,values);
        //使用ListAdapter來顯示你輸入的文字


        //將ListAdapter設定至ListView裡面



        View.OnClickListener OCL = new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                if (v instanceof Button) {
                                    int id = ((Button) v).getId();
                                    switch (id) {
                                        case R.id.btn18_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_myaddress.this, Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_myaddress.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_myaddress.this.finish();
                            break;
//                        case R.id.btn18_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_myaddress.this, Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_myaddress.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_myaddress.this.finish();
//                            break;
                        case R.id.btn18_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_myaddress.this, Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_myaddress.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_myaddress.this.finish();

                            break;
                        case R.id.btn18_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_myaddress.this, Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_myaddress.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_myaddress.this.finish();
                            break;
                        case R.id.btn18_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_myaddress.this, Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_myaddress.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_myaddress.this.finish();
                            break;
                        case R.id.btn18_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_myaddress.this, Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_myaddress.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_myaddress.this.finish();
                            break;

                    }

                }

//                if (v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib18_1:
//                            Intent intent7 = new Intent();
//                            intent7.setClass(Personal_setting_myaddress.this, Personal_setting.class);
//                            startActivity(intent7);
//                            Personal_setting_myaddress.this.finish();
//                            break;
//                    }
//                }
            }
        };
        btn181.setOnClickListener(OCL);
//        btn182.setOnClickListener(OCL);
        btn183.setOnClickListener(OCL);
        btn184.setOnClickListener(OCL);
        btn185.setOnClickListener(OCL);
        btn186.setOnClickListener(OCL);
//        ib181.setOnClickListener(OCL);



    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_myaddress.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}
