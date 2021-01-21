package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

public class Personal_setting_mycard extends AppCompatActivity {
    Button btn191, btn192, btn193, btn194, btn195, btn196;
    ImageButton ib191;
    ListView lv19;
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            Intent intent = new Intent();
            intent.setClass(Personal_setting_mycard.this, CreditCard.class);
            startActivity(intent);
            Personal_setting_mycard.this.finish();
        }


        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_mycard);

//        getSupportActionBar().hide();
        //設定隱藏狀態





        btn191 = (Button) findViewById(R.id.btn19_1);
//        btn192 = (Button) findViewById(R.id.btn19_2);
        btn193 = (Button) findViewById(R.id.btn19_3);
        btn194 = (Button) findViewById(R.id.btn19_4);
        btn195 = (Button) findViewById(R.id.btn19_5);
        btn196 = (Button) findViewById(R.id.btn19_6);
//        ib191 = (ImageButton) findViewById(R.id.ib19_1);

        //https://spicyboyd.blogspot.com/2018/04/app-toolbar-actionbar.html 參考
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar19);


        //右上角menu
        toolbar.inflateMenu(R.menu.menu_main);

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


        lv19 = (ListView) findViewById(R.id.lv19);
        final String[][] values = new String[10][10];


        DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts1[0].addValueEventListener(new ValueEventListener() {
            List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("mail").getValue().equals(iam)){
                        if(ds.child("creditCard").hasChildren())
                        {
                            for(int i=1;i<=(int)ds.child("creditCard").getChildrenCount();i++){
                                Map<String, Object> item = new HashMap<String, Object>();
                                item.put("text", ds.child("creditCard").child(Integer.toString(i)).child("city").getValue().toString()+
                                        ds.child("creditCard").child(Integer.toString(i)).child("myaddress").getValue().toString());
                                items.add(item);
                            }
                            SimpleAdapter adapter = new SimpleAdapter(Personal_setting_mycard.this, items,
                                    R.layout.listview3,new String[]{"text"}, new int[]{R.id.article_title60}
                            );
                            lv19.setAdapter(adapter);

                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn19_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_mycard.this, Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting_mycard.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_mycard.this.finish();
                            break;
//                        case R.id.btn19_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_mycard.this, Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting_mycard.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_mycard.this.finish();
//                            break;
                        case R.id.btn19_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_mycard.this, Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting_mycard.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_mycard.this.finish();

                            break;
                        case R.id.btn19_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_mycard.this, Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting_mycard.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_mycard.this.finish();
                            break;
                        case R.id.btn19_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_mycard.this, Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting_mycard.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_mycard.this.finish();
                            break;
                        case R.id.btn19_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_mycard.this, Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_mycard.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_mycard.this.finish();
                            break;

                    }

                }

            }
        };
        btn191.setOnClickListener(OCL);
        btn193.setOnClickListener(OCL);
        btn194.setOnClickListener(OCL);
        btn195.setOnClickListener(OCL);
        btn196.setOnClickListener(OCL);


    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_mycard.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}
