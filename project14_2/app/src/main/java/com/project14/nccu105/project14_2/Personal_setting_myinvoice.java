package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Personal_setting_myinvoice extends AppCompatActivity {
    Button btn201,btn202,btn203,btn204,btn205,btn206;
    ImageButton ib201;
    ListView lv201,lv202;
    RecyclerView recyclerView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            Intent intent = new Intent();
            intent.setClass(Personal_setting_myinvoice.this, CreditCard.class);
            startActivity(intent);
            Personal_setting_myinvoice.this.finish();
        }


        return super.onOptionsItemSelected(item);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_myinvoice);
//        getSupportActionBar().hide();
        //設定隱藏狀態

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar20);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_myinvoice.this, Personal_setting.class);
                startActivity(intent1);
                finish();
            }
        });

        List<CardViewMember> memberList = new ArrayList<>();
//        memberList.add(new CardViewMember(1, R.drawable.japan, "遠東銀行"));
//        memberList.add(new CardViewMember(2, R.drawable.america, "富邦銀行"));
//        memberList.add(new CardViewMember(3, R.drawable.korea, "第一銀行"));

        final boolean[] m = {false};
        final String iam= FirebaseAuth.getInstance()
                .getCurrentUser()
                .getEmail();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView20);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            List<CardViewMember>  memberList2 = new ArrayList<>();
            boolean have=false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getEmail())){
//                        mynum[0] = ds.child("memberNum").getValue().toString();
//                        mypic[0] = ds.child("picture").getValue().toString();
                        if(ds.child("receipt").exists()&&ds.child("receipt").hasChildren()){//想要商品
                            have=true;
                            for (DataSnapshot dds : ds.child("receipt").getChildren()){
                                memberList2.add(new CardViewMember(dds.child("db").getValue().toString(),"", dds.child("image").getValue().toString(),dds.child("name").getValue().toString()));
                            }
                        }
                        m[0] =true;

                    }

                    if(m[0]==true){
//                        String test=mypic[0];
//                        if(test.contains("null")){}
//                        else{
//
//                            riversRef = mStorageRef.child("member_picture").child(mypic[0]);
//                            Log.d("down", "onSuccess: ");
////
//                            downloadImg(riversRef);
//                        }
                        if(have==true){

                            recyclerView.setAdapter(new CardViewMemberAdapter(Personal_setting_myinvoice.this, memberList2));

                        }

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });






        btn201 = (Button) findViewById(R.id.btn20_1);
        btn203 = (Button) findViewById(R.id.btn20_3);
        btn204 = (Button) findViewById(R.id.btn20_4);
        btn205 = (Button) findViewById(R.id.btn20_5);

        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(v instanceof Button){
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn20_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_myinvoice.this,Forum.class);
                            startActivity(intent1);
                            Toast.makeText(Personal_setting_myinvoice.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinvoice.this.finish();
                            break;
//                        case R.id.btn20_2:
//                            String msg2 = "往逛逛頁";
//                            Intent intent2 = new Intent();
//                            intent2.setClass(Personal_setting_myinvoice.this,Seller.class);
//                            startActivity(intent2);
//                            Toast.makeText(Personal_setting_myinvoice.this, msg2, Toast.LENGTH_SHORT).show();
//                            Personal_setting_myinvoice.this.finish();
//                            break;
                        case R.id.btn20_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_myinvoice.this,Seller.class);
                            startActivity(intent3);
                            Toast.makeText(Personal_setting_myinvoice.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinvoice.this.finish();

                            break;
                        case R.id.btn20_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_myinvoice.this,Maps.class);
                            startActivity(intent4);
                            Toast.makeText(Personal_setting_myinvoice.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinvoice.this.finish();
                            break;
                        case R.id.btn20_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_myinvoice.this,Notify.class);
                            startActivity(intent5);
                            Toast.makeText(Personal_setting_myinvoice.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinvoice.this.finish();
                            break;
//                        case R.id.btn20_6:
//                            String msg6 = "往個人頁";
//                            Intent intent6 = new Intent();
//                            intent6.setClass(Personal_setting_myinvoice.this,Personal.class);
//                            startActivity(intent6);
//                            Toast.makeText(Personal_setting_myinvoice.this, msg6, Toast.LENGTH_SHORT).show();
//                            Personal_setting_myinvoice.this.finish();
//                            break;




                    }}




            }
        };
        btn201.setOnClickListener(OCL);
        btn203.setOnClickListener(OCL);
        btn204.setOnClickListener(OCL);
        btn205.setOnClickListener(OCL);

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_myinvoice.this, Personal_setting.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}
