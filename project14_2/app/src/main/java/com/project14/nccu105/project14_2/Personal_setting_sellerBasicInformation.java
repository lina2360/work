package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;

import java.io.File;

public class Personal_setting_sellerBasicInformation extends AppCompatActivity {

    String count1="";
    int count = 0;
    String mynum = "";
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    String phone="";
    String mail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_setting_seller_basic_information);

//        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar49);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Personal_setting_sellerBasicInformation.this, Personal_setting_becomeSeller.class);
                startActivity(intent1);
                finish();
            }
        });

        final EditText et491 = (EditText)findViewById(R.id.et49_1);
        final EditText et492 = (EditText)findViewById(R.id.et49_2);
        final EditText et493 = (EditText)findViewById(R.id.et49_3);
        EditText et494 = (EditText)findViewById(R.id.et49_4);
        final EditText et495 = (EditText)findViewById(R.id.et49_5);
        final EditText et496 = (EditText)findViewById(R.id.et49_6);
        final EditText et497 = (EditText)findViewById(R.id.et49_7);
        final EditText et498 = (EditText)findViewById(R.id.et49_8);
        final EditText et499 = (EditText)findViewById(R.id.et49_9);
        Button btn491 = (Button)findViewById(R.id.btn49_1);
        DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts1[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("mail").getValue().equals(iam)){
                        mynum = ds.child("memberNum").getValue().toString();
                      phone=ds.child("phoneNum").getValue().toString();
                      mail=ds.child("mail").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn491.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( et493.getText().toString().matches("")||et496.getText().toString().matches("")||et495.getText().toString().matches("")||
                        et497.getText().toString().matches("")|| et498.getText().toString().matches("")||
                        et499.getText().toString().matches("")){

                    Toast.makeText(Personal_setting_sellerBasicInformation.this, "請確認資料是否有無缺漏，謝謝！", Toast.LENGTH_SHORT).show();

                }
                else if(!et492.getText().toString().matches(mail)){
                    Toast.makeText(Personal_setting_sellerBasicInformation.this, "請填寫您的信箱(帳號)，謝謝！", Toast.LENGTH_SHORT).show();

                }
                else if(!et491.getText().toString().matches(phone)){
                    Toast.makeText(Personal_setting_sellerBasicInformation.this, "請填寫您的電話，謝謝！", Toast.LENGTH_SHORT).show();

                }

                else{

                    final int[] temp = new int[1];
                    boolean p=false;
                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("seller_file")};
                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                temp[0] = count+1;
                                count++;
                            }

                            if((int)dataSnapshot.getChildrenCount()==temp[0]){

                                count1=Integer.toString(temp[0]);

                                FirebaseDatabase.getInstance()
                                        .getReference("seller_file")
                                        .child(count1)
                                        .setValue(new SellerFile_Container(
                                                "no",
                                                        FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                                        et493.getText().toString(),
                                                        "null",
                                                        count1,
                                                        et496.getText().toString(),
                                                        et495.getText().toString(),
                                                        et497.getText().toString(),
                                                        et498.getText().toString(),
                                                        et499.getText().toString()

                                                )

                                        );

                                FirebaseDatabase.getInstance()
                                        .getReference("buyer_file")
                                        .child(mynum)
                                        .child("isSeller")
                                        .setValue(2);



                                Toast.makeText(Personal_setting_sellerBasicInformation.this, "申請成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(Personal_setting_sellerBasicInformation.this, Personal_setting_sellerFinish.class);
                                startActivity(intent);

                           }



                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }


                    });


                }



            }

        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(Personal_setting_sellerBasicInformation.this, Personal_setting_becomeSeller.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}
