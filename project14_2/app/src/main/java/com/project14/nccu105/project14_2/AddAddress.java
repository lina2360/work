package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.util.Date;

public class AddAddress extends AppCompatActivity {
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    String mynum = "";
    int addnum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar54) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(AddAddress.this, Personal_setting_myaddress.class);
                startActivity(intent1);
                finish();
            }
        });

        final EditText et543 = (EditText)findViewById(R.id.et54_3);
        final EditText et544 = (EditText)findViewById(R.id.et54_4);
        Button btn541 = (Button)findViewById(R.id.btn54_1);

        DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts1[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("mail").getValue().equals(iam)){
                        mynum = ds.child("memberNum").getValue().toString();
                        addnum=(int)ds.child("address").getChildrenCount();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn541.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et543.getText().toString().isEmpty()||et544.getText().toString().isEmpty()){
                    Toast.makeText(AddAddress.this, "請填寫完整，謝謝！",
                            Toast.LENGTH_LONG)
                            .show();
                }else {
                    long messageTime = new Date().getTime();
                    String time=Long.toString(messageTime);
                    FirebaseDatabase.getInstance()
                            .getReference("buyer_file")
                            .child(mynum)
                            .child("address")
                            .child(Integer.toString(addnum+1))
                            .setValue(new AddressContainer(
                                    et543.getText().toString(),
                                    et544.getText().toString()));

                    Intent intent1 = new Intent();
                    intent1.setClass(AddAddress.this, Personal_setting_myaddress.class);
                    startActivity(intent1);
                    AddAddress.this.finish();


                }
            }
        });
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent1 = new Intent();
            intent1.setClass(AddAddress.this, Personal_setting_myaddress.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }

}
