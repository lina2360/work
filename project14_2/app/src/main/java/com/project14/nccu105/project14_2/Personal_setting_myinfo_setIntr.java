package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Personal_setting_myinfo_setIntr extends AppCompatActivity {
    Button btn601,btn603,btn604,btn605,btn606,btn607;
    EditText et601;
    String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_myinfo_setintr);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        et601 = (EditText) findViewById(R.id.et60_1);
        btn601 = (Button) findViewById(R.id.btn60_1);

        btn603 = (Button) findViewById(R.id.btn60_3);
        btn604 = (Button) findViewById(R.id.btn60_4);
        btn605 = (Button) findViewById(R.id.btn60_5);
        btn606 = (Button) findViewById(R.id.btn60_6);
        btn607 = (Button) findViewById(R.id.btn60_7);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar60);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_myinfo_setIntr.this, Personal_setting_myinfo.class);
                startActivity(intent1);
                finish();
            }
        });


        final String[] mynum = new String[1];

        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("mail").getValue().equals(iam)){
                        mynum[0] = ds.child("memberNum").getValue().toString();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        View.OnClickListener OCL = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn60_1:
                            String msg1 = "往論壇頁";
                            Intent intent1 = new Intent();
                            intent1.setClass(Personal_setting_myinfo_setIntr.this,Forum.class);
                            startActivity(intent1);
//                        Toast.makeText(Personal_setting_myinfo_setaccount.this, msg1, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setIntr.this.finish();
                            break;
//                    case R.id.btn11_2:
//                        String msg2 = "往逛逛頁";
//                        Intent intent2 = new Intent();
//                        intent2.setClass(Personal_setting_myinfo_setaccount.this,Buyer.class);
//                        startActivity(intent2);
////                        Toast.makeText(Personal_setting_myinfo_setaccount.this, msg2, Toast.LENGTH_SHORT).show();
//                        Personal_setting_myinfo_setaccount.this.finish();
//                        break;
                        case R.id.btn60_3:
                            String msg3 = "往接單頁";
                            Intent intent3 = new Intent();
                            intent3.setClass(Personal_setting_myinfo_setIntr.this, Seller.class);
                            startActivity(intent3);
//                        Toast.makeText(Personal_setting_myinfo_setaccount.this, msg3, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setIntr.this.finish();
                            break;
                        case R.id.btn60_4:
                            String msg4 = "往地圖頁";
                            Intent intent4 = new Intent();
                            intent4.setClass(Personal_setting_myinfo_setIntr.this,Maps.class);
                            startActivity(intent4);
//                        Toast.makeText(Personal_setting_myinfo_setaccount.this, msg4, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setIntr.this.finish();
                            break;
                        case R.id.btn60_5:
                            String msg5 = "往通知頁";
                            Intent intent5 = new Intent();
                            intent5.setClass(Personal_setting_myinfo_setIntr.this,Notify.class);
                            startActivity(intent5);
//                        Toast.makeText(Personal_setting_myinfo_setaccount.this, msg5, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setIntr.this.finish();
                            break;

                        case R.id.btn60_6:
                            String msg6 = "往個人頁";
                            Intent intent6 = new Intent();
                            intent6.setClass(Personal_setting_myinfo_setIntr.this,Personal.class);
                            startActivity(intent6);
//                        Toast.makeText(Personal_setting_myinfo_setaccount.this, msg6, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setIntr.this.finish();
                            break;
                        case R.id.btn60_7:
                            String msg7 = "確認送出";

                            FirebaseDatabase.getInstance()
                                    .getReference("buyer_file")
                                    .child(mynum[0])
                                    .child("personalArticle")
                                    .setValue(et601.getText().toString());

                            Intent intent7 = new Intent();
                            intent7.setClass(Personal_setting_myinfo_setIntr.this,Personal_setting_myinfo.class);
                            startActivity(intent7);
//                        Toast.makeText(Personal_setting_myinfo_setaccount.this, msg7, Toast.LENGTH_SHORT).show();
                            Personal_setting_myinfo_setIntr.this.finish();
                            break;

                    }
                }
//                if (v instanceof ImageButton) {
//                    int id2 = ((ImageButton) v).getId();
//                    switch (id2) {
//                        case R.id.ib11_1:
//                            Intent intent8 = new Intent();
//                            intent8.setClass(Personal_setting_myinfo_setaccount.this, Personal_setting_myinfo.class);
//                            startActivity(intent8);
//                            Personal_setting_myinfo_setaccount.this.finish();
//                            break;
//                    }
//                }
            }
        };
        btn601.setOnClickListener(OCL);
        btn603.setOnClickListener(OCL);
        btn604.setOnClickListener(OCL);
        btn605.setOnClickListener(OCL);
        btn606.setOnClickListener(OCL);
        btn607.setOnClickListener(OCL);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_myinfo_setIntr.this, Personal_setting_myinfo.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}
