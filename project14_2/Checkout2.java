package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Checkout2 extends AppCompatActivity {
    String dtemp = "";
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    boolean m[] = {false,false};
    String[] mynum = new String[2];
    ImageView image;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String[] mypic =new String[1];
    String nickname="";
    String usernick="";
    ArrayAdapter<String>  deliverList;
    ArrayList<String> deliver;
    Spinner sp521;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout2);

//        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
        deliver = new ArrayList<String>();



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar46);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//
//        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        final Bundle bundle0=getIntent().getExtras();

        final String picture=bundle0.getString("picture");
//
        final String buyer=bundle0.getString("buyer");
        final Long productOrderTime=bundle0.getLong("productOrderTime");

        image = (ImageView) findViewById(R.id.iv52_1);

        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getEmail())){
                        nickname=ds.child("nickname").getValue().toString();

                    }
                    if(ds.child("mail").getValue().equals(buyer)){
                        usernick=ds.child("nickname").getValue().toString();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
//
        final TextView product = (TextView) findViewById(R.id.tv52_2);
        final TextView num = (TextView) findViewById(R.id.tv52_3);
        final TextView receipt = (TextView) findViewById(R.id.tv52_4);
//
        final EditText price = (EditText) findViewById(R.id.et52_1);
         sp521 = (Spinner)findViewById(R.id.sp52_1);
        final EditText fee = (EditText) findViewById(R.id.et52_3);
//        final EditText name = (EditText) findViewById(R.id.et46_4);
        final EditText other = (EditText) findViewById(R.id.et52_2);
//
        final TextView pTotal = (TextView) findViewById(R.id.tv52_13);
        final TextView fTotal = (TextView) findViewById(R.id.tv52_15);
        final TextView total2 = (TextView) findViewById(R.id.tv52_17);
        Button btn521 = (Button)findViewById(R.id.btn52_1);
        Button btn522 = (Button)findViewById(R.id.btn52_2);
//
        product.setText(bundle0.getString("product"));
        num.setText(Integer.toString(bundle0.getInt("num")));
        receipt.setText(bundle0.getString("receipt"));
//        price.setText(Integer.toString(bundle0.getInt("price")));
//        fee.setText(Integer.toString(bundle0.getInt("fee")));

        if(bundle0.getInt("price")==0)
        {
            price.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
//            price.setText(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(price.getText().length()!=0) {
                        int ptotal = Integer.parseInt(price.getText().toString()) * Integer.parseInt(num.getText().toString());
                        pTotal.setText(Integer.toString(ptotal));
                    }else if(s.length()==0){
                        pTotal.setText("$原價*數量");
                        total2.setText("$(原價+代購)*數量");
                    }
                    if(price.getText().length()!=0&&fee.getText().length()!=0) {
                        int ptotal = Integer.parseInt(price.getText().toString()) * Integer.parseInt(num.getText().toString());
                        pTotal.setText(Integer.toString(ptotal));
                        int ftota1 = Integer.parseInt(fee.getText().toString()) * Integer.parseInt(num.getText().toString());
                        fTotal.setText(Integer.toString(ftota1));
                        int Total2 = ptotal + ftota1;
                        total2.setText(Integer.toString(Total2));
                    }

                }
            });

        }
        else {
            price.setText(Integer.toString(bundle0.getInt("price")));
            if(price.getText().length()!=0&&fee.getText().length()!=0){
                int ptotal=Integer.parseInt(price.getText().toString())*Integer.parseInt(num.getText().toString());
                pTotal.setText(Integer.toString(ptotal));
                int ftota1=Integer.parseInt(fee.getText().toString())*Integer.parseInt(num.getText().toString());
                fTotal.setText(Integer.toString(ftota1));
                int Total2=ptotal+ftota1;
                total2.setText(Integer.toString(Total2));


            }
            price.setFocusable(false);
        }
//
        if(bundle0.getInt("fee")==0)
        {
            fee.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(fee.getText().length()!=0){
                        int ftota1=Integer.parseInt(fee.getText().toString())*Integer.parseInt(num.getText().toString());
                        fTotal.setText(Integer.toString(ftota1));
                    }else if(s.length()==0){
                        fTotal.setText("$代購*數量");
                        total2.setText("$(原價+代購)*數量");
                    }

                    if(price.getText().length()!=0&&fee.getText().length()!=0){
                        int ptotal=Integer.parseInt(price.getText().toString())*Integer.parseInt(num.getText().toString());
                        pTotal.setText(Integer.toString(ptotal));
                        int ftota1=Integer.parseInt(fee.getText().toString())*Integer.parseInt(num.getText().toString());
                        fTotal.setText(Integer.toString(ftota1));
                        int Total2=ptotal+ftota1;
                        total2.setText(Integer.toString(Total2));

                    }


                }
            });

        }else{
            fee.setText(Integer.toString(bundle0.getInt("fee")));
            if(price.getText().length()!=0&&fee.getText().length()!=0){
                int ptotal=Integer.parseInt(price.getText().toString())*Integer.parseInt(num.getText().toString());
                pTotal.setText(Integer.toString(ptotal));
                int ftota1=Integer.parseInt(fee.getText().toString())*Integer.parseInt(num.getText().toString());
                fTotal.setText(Integer.toString(ftota1));
                int Total2=ptotal+ftota1;
                total2.setText(Integer.toString(Total2));
            }
            fee.setFocusable(false);
        }



        sp521.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dtemp= (String) deliverList.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
        btn521.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout2.this.finish();
            }

        });
        btn522.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Checkout2.this)
                        .setTitle("訂單確認")
                        .setMessage("按下確認後，訂單就不能修改囉！\n請務必確認資訊無誤，謝謝")
                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {




                                if (num.getText().length() == 0||price.getText().length() == 0||fee.getText().length() == 0) {
                                    Toast.makeText(Checkout2.this, "請檢查\n1.單價\n2.代購費\n有無確實填寫，必且確定交易方式，謝謝！", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    final String on=Integer.toString((int) (1000000*Math.random()+1));

                                    final long messageTime = new Date().getTime();
                                    final String time = Long.toString(messageTime);

                                    final Bundle bundle=new Bundle();
                                    bundle.putString("ordernum","0"+time);
//                                bundle.putString("name",name.getText().toString());
//                                bundle.putString("address",address.getText().toString());
                                    bundle.putString("moneyway",dtemp);
//                                bundle.putString("seller",seller);
//                                bundle.putString("product",product.getText().toString());
                                bundle.putInt("num",Integer.parseInt(num.getText().toString()));
                                    bundle.putInt("price",Integer.parseInt(price.getText().toString()));
                                    bundle.putInt("fee",Integer.parseInt(fee.getText().toString()));
                                    bundle.putString("usernick",usernick);
                                    bundle.putString("user",buyer);
//                                bundle.putString("other",other.getText().toString());
//                                bundle.putLong("productOrderTime",productOrderTime);








                                    DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                                    reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                                                if(ds.child("mail").getValue().equals(iam)){
                                                    mynum[0] = ds.child("memberNum").getValue().toString();
                                                    m[0] =true;

                                                }
                                                if(ds.child("mail").getValue().equals(buyer)){
                                                    mynum[1] = ds.child("memberNum").getValue().toString();
                                                    m[1] =true;

                                                }

//
//
                                            }
                                            if(m[0]==true&&m[1]==true){

                                                FirebaseDatabase.getInstance()
                                                        .getReference("buyer_file")
                                                        .child(mynum[0])
                                                        .child("mybuy")
                                                        .child("0"+time)
                                                        .setValue(new MyBuy(

                                                                        time,
                                                                        "買家尚未提供",
                                                                        "買家尚未提供",
                                                                        "等待物流資訊",
                                                                        time,
                                                                        dtemp,
                                                                        buyer,
                                                                        product.getText().toString(),
                                                                        Integer.parseInt(num.getText().toString()),
                                                                        Integer.parseInt(price.getText().toString()),
                                                                        Integer.parseInt(fee.getText().toString()),
                                                                        other.getText().toString(),
                                                                        productOrderTime,
                                                                        "尚未出貨",
                                                                        "尚未完成訂單",
                                                                        "0"+time,
                                                                        "【接單】",
                                                              picture,
                                                                "尚未出貨",
                                                                usernick,
                                                                receipt.getText().toString(),
                                                                "null"
                                                                )

                                                        );



                                                    FirebaseDatabase.getInstance()
                                                            .getReference("buyer_file")
                                                            .child(mynum[1])
                                                            .child("mybuy")
                                                            .child("0"+time)
                                                            .setValue(new MyBuy(

                                                                            time,
                                                                            "買家尚未提供",
                                                                            "買家尚未提供",
                                                                            "等待物流資訊",
                                                                            time,
                                                                            dtemp,
                                                                            iam,
                                                                            product.getText().toString(),
                                                                            Integer.parseInt(num.getText().toString()),
                                                                            Integer.parseInt(price.getText().toString()),
                                                                            Integer.parseInt(fee.getText().toString()),
                                                                            other.getText().toString(),
                                                                            productOrderTime,
                                                                            "尚未出貨",
                                                                            "尚未完成訂單",
                                                                            "0"+time,
                                                                            "【代購】",
                                                                            picture,
                                                                    "尚未出貨",
                                                                    nickname,
                                                                    receipt.getText().toString(),
                                                                    "null"
                                                                    )

                                                            );
                                                Intent intent = new Intent();
                                                intent.setClass(Checkout2.this, PaymentCompleted2.class);
                                                bundle.putString("picture",picture);
                                                    intent.putExtras(bundle);
                                                startActivity(intent);
                                                Checkout2.this.finish();
//
                                            }

                                        }




                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }


                                    });
//
//
//
//
                                }
//
                            }
                        })
                        .show();
//
//
//
            }
//
//
        });



        final boolean[] m = {false};

        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_order")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.child("messageTime").getValue().equals(bundle0.getLong("productOrderTime"))){
                        mypic[0] = ds.child("picture").getValue().toString();
                        if(ds.child("delivery").getValue().toString().contains("宅配(轉帳)")){
                            deliver.add("宅配(轉帳)");
                        }
                        if(ds.child("delivery").getValue().toString().contains("7-11貨到付款")){
                            deliver.add("7-11貨到付款");
                        }
                        if(ds.child("delivery").getValue().toString().contains("7-11取貨(轉帳)")){
                            deliver.add("7-11取貨(轉帳)");
                        }
                        if(ds.child("delivery").getValue().toString().contains("全家貨到付款")){
                            deliver.add("全家貨到付款");
                        }
                        if(ds.child("delivery").getValue().toString().contains("全家取貨(轉帳)")){
                            deliver.add("全家取貨(轉帳)");
                        }

                        deliverList = new ArrayAdapter<String>(Checkout2.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                deliver
                        );
                        sp521.setAdapter(deliverList);
                        dtemp= (String) deliverList.getItem(0);

                        m[0] =true;

                    }

                    if(m[0]==true){
                        String test=mypic[0];
                        if(test.contains("null")){}
                        else{

                            riversRef = mStorageRef.child("buyer_order").child(mypic[0]);

                            Log.d("down", "onSuccess: "+mypic[0]);
//
                            downloadImg(riversRef);
                        }


                    }


                }


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });







    }

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(Checkout2.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Checkout2.this)){
                    Glide.with(Checkout2.this)

                            .load(ref)
                            .into(image);

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });
    }
    public static boolean isDestroy(Activity activity) {
        if (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Checkout2.this.finish();
        }
        return false;
    }
}

