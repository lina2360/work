package com.project14.nccu105.project14_2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.pm.PackageInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentConfirmation extends AppCompatActivity {
    Spinner sp441;
    List<String> addressl;
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    ArrayAdapter<String> addressList;
    String atemp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_confirmation);

        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar44);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(true);

        TextView tv447 = (TextView)findViewById(R.id.tv44_7);
//        TextView tv4413 = (TextView)findViewById(R.id.tv44_13);
//        Button btn441 = (Button)findViewById(R.id.btn44_1);
        Button btn442 = (Button)findViewById(R.id.btn44_2);
        Bundle bundle=getIntent().getExtras();
        String ordernum=bundle.getString("ordernum").toString();
        tv447.setText(ordernum);

        sp441 = (Spinner)findViewById(R.id.sp44_1);

        addressl = new ArrayList<String>();

        DatabaseReference[] reference_contacts3 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts3[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("mail").getValue().equals(iam)){
                        if(ds.child("address").hasChildren()) {

                            for (int i=1;i<=ds.child("creditCard").getChildrenCount();i++){
                                addressl.add(ds.child("creditCard").child(Integer.toString(i)).child("city").getValue().toString()+
                                        ds.child("creditCard").child(Integer.toString(i)).child("myaddress").getValue().toString());
                            }


                        }else{

                            addressl.add("請新增地址");
                        }
                    }
                }
                addressList = new ArrayAdapter<String>(PaymentConfirmation.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        addressl
                );
                sp441.setAdapter(addressList);

                atemp= (String) addressList.getItem(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        btn441.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PaymentConfirmation.this.finish();
//            }
//
//        });
        btn442.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    long messageTime = new Date().getTime();
                    String time = Long.toString(messageTime);

                    // Clear the input
                    Bundle bundle = getIntent().getExtras();


                Intent intent = new Intent();
                intent.setClass(PaymentConfirmation.this, PaymentCompleted.class);
                startActivity(intent);
                PaymentConfirmation.this.finish();

            }

        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return false;
    }
}
