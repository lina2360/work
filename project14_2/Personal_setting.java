package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

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

public class Personal_setting extends AppCompatActivity {
    Button btn91,btn92,btn93,btn94,btn95,btn96,btn97;
    ImageButton ib91;
    ListView lv91,lv92,lv93;
    String isseller = "";
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        btn91 = (Button) findViewById(R.id.btn9_1);
//        btn92 = (Button) findViewById(R.id.btn9_2);
        btn93 = (Button) findViewById(R.id.btn9_3);
        btn94 = (Button) findViewById(R.id.btn9_4);
        btn95 = (Button) findViewById(R.id.btn9_5);
        btn96 = (Button) findViewById(R.id.btn9_6);
        btn97 = (Button) findViewById(R.id.btn9_7);
//        ib91 = (ImageButton) findViewById(R.id.ib9_1);
        lv91=(ListView)findViewById(R.id.lv9_1);
//        lv92=(ListView)findViewById(R.id.lv9_2);
        lv93=(ListView)findViewById(R.id.lv9_3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting.this, Personal.class);
                startActivity(intent1);
                finish();
            }
        });

        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(v instanceof Button){
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn9_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting.this,Forum.class);
                            startActivity(intent1);
//                            Toast.makeText(Personal_setting.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting.this.finish();
                            break;
//                        case R.id.btn9_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting.this,Buyer.class);
//                            startActivity(intent2);
////                            Toast.makeText(Personal_setting.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting.this.finish();
//                            break;
                        case R.id.btn9_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting.this,Seller.class);
                            startActivity(intent3);
//                            Toast.makeText(Personal_setting.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting.this.finish();

                            break;
                        case R.id.btn9_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting.this,Maps.class);
                            startActivity(intent4);
//                            Toast.makeText(Personal_setting.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting.this.finish();
                            break;
                        case R.id.btn9_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting.this,Notify.class);
                            startActivity(intent5);
//                            Toast.makeText(Personal_setting.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting.this.finish();
                            break;
                        case R.id.btn9_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting.this,Personal.class);
                            startActivity(intent6);
//                            Toast.makeText(Personal_setting.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting.this.finish();
                            break;
                        case R.id.btn9_7:
                            String msg7 = "登出";
                            FirebaseAuth.getInstance().signOut();
                            Intent intent7 = new Intent();
                            intent7.setClass(Personal_setting.this,MainPage.class);
                            startActivity(intent7);
//                            Toast.makeText(Personal_setting.this, msg7, Toast.LENGTH_SHORT).show();
                            Personal_setting.this.finish();
                            break;



                    }}
//                if(v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib9_1:
//                            String msg6 = "返回";
//                            Intent intent6 = new Intent();
//                            intent6.setClass(Personal_setting.this,Personal.class);
//                            startActivity(intent6);
////                            Toast.makeText(Personal_setting.this, msg6, Toast.LENGTH_SHORT).show();
//                            Personal_setting.this.finish();
//                            break;
//                    }}



            }
        };
        btn91.setOnClickListener(OCL);
//        btn92.setOnClickListener(OCL);
        btn93.setOnClickListener(OCL);
        btn94.setOnClickListener(OCL);
        btn95.setOnClickListener(OCL);
        btn96.setOnClickListener(OCL);
        btn97.setOnClickListener(OCL);
//        ib91.setOnClickListener(OCL);


        ListView listView1=(ListView)findViewById(R.id.lv9_1);
        String[] value1=new String[]{"我的檔案","我的地址","我的匯款帳號","我的收據","申請當賣家"};
        int[] img1 = { R.drawable.ic_set_my, R.drawable.ic_set_addr, R.drawable.ic_set_acco, R.drawable.ic_set_recei, R.drawable.ic_set_sell};

        List<Map<String, Object>> items1 = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < value1.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", img1[i]);
            item.put("text", value1[i]);
            items1.add(item);
        }
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        SimpleAdapter adapter1 = new SimpleAdapter(this, items1,
                R.layout.listview4,new String[]{"image", "text"}, new int[]{R.id.notifyimg, R.id.notifytext}
        );
        // ListAdapter adapter1=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,value1);
        listView1.setAdapter(adapter1);
        Utility.setListViewHeightBasedOnChildren(listView1);


        lv91.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                switch(index){
                    case 0:
                        String msg7 = "我的檔案";
                        Intent intent7 = new Intent();
                        intent7.setClass(Personal_setting.this,Personal_setting_myinfo_info.class);
                        startActivity(intent7);
//                        Toast.makeText(Personal_setting.this, msg7, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
                        break;
                    case 1:
                        String msg8 = "我的地址";
                        Intent intent8 = new Intent();
                        intent8.setClass(Personal_setting.this,Personal_setting_myaddress.class);
                        startActivity(intent8);
//                        Toast.makeText(Personal_setting.this, msg8, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
                        break;
                    case 2:
                        String msg9 = "銀行帳號/信用卡";
                        Intent intent9 = new Intent();
                        intent9.setClass(Personal_setting.this,Personal_setting_mycard.class);
                        startActivity(intent9);
//                        Toast.makeText(Personal_setting.this, msg9, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
                        break;
                    case 3:
                        String msg11 = "我的收據";
                        Intent intent11 = new Intent();
                        intent11.setClass(Personal_setting.this,Personal_setting_myinvoice.class);
                        startActivity(intent11);
//                        Toast.makeText(Personal_setting.this, msg11, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
                        break;
                    case 4:
                        String msg10 = "申請當賣家";

                        DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                        reference_contacts1[0].addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                                    if(ds.child("mail").getValue().equals(iam)){
                                        isseller = ds.child("isSeller").getValue().toString();

                                    }


                                }
                                if(isseller.equals("0")){
                                    Intent intent10 = new Intent();
                                    intent10.setClass(Personal_setting.this,Personal_setting_becomeSeller.class);
                                    startActivity(intent10);
                                }
                                else if(isseller.equals("2")){
                                    Toast.makeText(Personal_setting.this, "還在驗證中，請稍等唷！", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Personal_setting.this, "您已經申請過了唷！", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        break;
                }




            }});

        //ListView listView2=(ListView)findViewById(R.id.lv9_2);
        //String[] value2=new String[]{"我的收據","通知設定"};
        //ListAdapter adapter2=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,value2);
        //listView2.setAdapter(adapter2);

//        lv92.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
//
//                switch(index){
//                    case 0:
//                        String msg11 = "我的收據";
//                        Intent intent11 = new Intent();
//                        intent11.setClass(Personal_setting.this,Personal_setting_myinvoice.class);
//                        startActivity(intent11);
//                        Toast.makeText(Personal_setting.this, msg11, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
//                        break;
//                    case 1:
//                        String msg12 = "聊天室設定";
//                        Intent intent12 = new Intent();
//                        intent12.setClass(Personal_setting.this,Personal.class);
//                        startActivity(intent12);
////                        Toast.makeText(Personal_setting.this, msg12, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
//                        break;
//                    case 1:
//                        String msg13 = "通知設定";
//                        Intent intent13 = new Intent();
//                        intent13.setClass(Personal_setting.this,Personal_setting_setnotify.class);
//                        startActivity(intent13);
//                        Toast.makeText(Personal_setting.this, msg13, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
//                        break;
//                    case 3:
//                        String msg14 = "隱私設定";
//                        Intent intent14 = new Intent();
//                        intent14.setClass(Personal_setting.this,Personal_setting_setprivacy.class);
//                        startActivity(intent14);
////                        Toast.makeText(Personal_setting.this, msg14, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
//                        break;
//                    case 2:
//                        String msg15 = "封鎖的使用者";
//                        Intent intent15 = new Intent();
//                        intent15.setClass(Personal_setting.this,Personal.class);
//                        startActivity(intent15);
//                        Toast.makeText(Personal_setting.this, msg15, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
//                        break;
//                    case 5:
//                        String msg16 = "語言設定";
//                        Intent intent16 = new Intent();
//                        intent16.setClass(Personal_setting.this,Personal_setting_setlanguage.class);
//                        startActivity(intent16);
////                        Toast.makeText(Personal_setting.this, msg16, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
//                        break;
//                }
//
//
//
//
//            }});

        ListView listView3=(ListView)findViewById(R.id.lv9_3);
        String[] value3=new String[]{"幫助中心","使用規範","使用回饋","問題回報"};
        int[] img3 = { R.drawable.ic_set_help, R.drawable.ic_set_info, R.drawable.ic_bag, R.drawable.ic_set_survey};

        List<Map<String, Object>> items3 = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < value3.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", img3[i]);
            item.put("text", value3[i]);
            items3.add(item);
        }
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        SimpleAdapter adapter3 = new SimpleAdapter(this, items3,
                R.layout.listview4,new String[]{"image", "text"}, new int[]{R.id.notifyimg, R.id.notifytext}
        );

        listView3.setAdapter(adapter3);
        Utility.setListViewHeightBasedOnChildren(listView3);

        lv93.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {

                switch(index){
                    case 0:
                        String msg17 = "幫助中心";
                        Intent intent17 = new Intent();
                        intent17.setClass(Personal_setting.this,Personal_setting_help.class);
                        startActivity(intent17);
//                        Toast.makeText(Personal_setting.this, msg17, Toast.LENGTH_SHORT).show();
                        Personal_setting.this.finish();
                        break;
                    case 1:
                        String msg18 = "使用規範";
                        Intent intent18 = new Intent();
                        intent18.setClass(Personal_setting.this,Personal_setting_usage.class);
                        startActivity(intent18);
//                        Toast.makeText(Personal_setting.this, msg18, Toast.LENGTH_SHORT).show();
                        Personal_setting.this.finish();
                        break;
                    case 2:
//                        String msg19 = "清除暫存";
//                        Intent intent19 = new Intent();
//                        intent19.setClass(Personal_setting.this,Personal_setting_clean.class);
//                        startActivity(intent19);
////                        Toast.makeText(Personal_setting.this, msg19, Toast.LENGTH_SHORT).show();
//                        Personal_setting.this.finish();
                        String msg20 = "使用回饋";
                        Intent intent20 = new Intent();
                        intent20.setClass(Personal_setting.this,Personal_setting_feedback.class);
                        startActivity(intent20);

                        Personal_setting.this.finish();

                        break;

                    case 3:
//                        String msg21 = "問題回報";
                        Intent intent21 = new Intent();
                        intent21.setClass(Personal_setting.this,Personal_setting_prob.class);
                        startActivity(intent21);

                        Personal_setting.this.finish();
                        break;

                }




            }});


    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting.this, Personal.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}

