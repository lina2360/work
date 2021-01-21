package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
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
import android.widget.SimpleAdapter;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkout extends AppCompatActivity {
    String dtemp = "";
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    ImageView image;
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String[] mypic =new String[1];
    String nickname="";
    String usernick="";
    ArrayAdapter<String>  deliverList;
    List<String> deliver;
    Spinner sp461,sp462,sp463;
    ArrayAdapter<String> addressList;
    List<String> addressl;
    String atemp = "";
    ArrayAdapter<String> accountList;
    List<String> account;
    String acctemp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

//        getSupportActionBar().hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
        deliver = new ArrayList<String>();

        final Bundle bundle0=getIntent().getExtras();

        final String picture=bundle0.getString("picture");

        final String seller=bundle0.getString("seller");
        final Long productOrderTime=bundle0.getLong("productOrderTime");

        image = (ImageView) findViewById(R.id.iv46_1);

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
                    if(ds.child("mail").getValue().equals(seller)){
                        usernick=ds.child("nickname").getValue().toString();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        final TextView product = (TextView) findViewById(R.id.tv46_2);
        final TextView price = (TextView) findViewById(R.id.tv46_3);
        final TextView fee = (TextView) findViewById(R.id.tv46_4);
        final TextView receipt = (TextView) findViewById(R.id.et46_5);

        if(bundle0.getString("receipt").contains("不提供收據")){
            receipt.setText("賣家不提供收據");
            receipt.setFocusable(false);
        }
        Log.d("收據", "onCreate: "+bundle0.getString("receipt"));


        final EditText num = (EditText) findViewById(R.id.et46_1);
        sp461 = (Spinner)findViewById(R.id.sp46_1);
        sp462 = (Spinner)findViewById(R.id.sp46_2);
        sp463 = (Spinner)findViewById(R.id.sp46_3);
//        final EditText address = (EditText) findViewById(R.id.et46_3);
        final EditText name = (EditText) findViewById(R.id.et46_4);
        final EditText other = (EditText) findViewById(R.id.et46_2);

        final TextView pTotal = (TextView) findViewById(R.id.tv46_13);
        final TextView fTotal = (TextView) findViewById(R.id.tv46_15);
        final TextView total2 = (TextView) findViewById(R.id.tv46_17);
        Button btn461 = (Button)findViewById(R.id.btn46_1);
        Button btn462 = (Button)findViewById(R.id.btn46_2);

        product.setText(bundle0.getString("product"));
        price.setText(Integer.toString(bundle0.getInt("price")));
        fee.setText(Integer.toString(bundle0.getInt("fee")));


        account = new ArrayList<String>();

        DatabaseReference[] reference_contacts4 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts4[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("mail").getValue().equals(iam)){
                        if(ds.child("creditCard").hasChildren()) {

                            for (int i=1;i<=ds.child("creditCard").getChildrenCount();i++){
                                account.add(ds.child("creditCard").child(Integer.toString(i)).child("city").getValue().toString()+
                                        ds.child("creditCard").child(Integer.toString(i)).child("myaddress").getValue().toString());
                            }


                        }else{

                            account.add("請新增帳戶");
                        }
                    }
                }
                accountList = new ArrayAdapter<String>(Checkout.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        account
                );
                sp463.setAdapter(accountList);

                acctemp= (String) accountList.getItem(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


num.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if (s.length()==0){
//
//        }

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s.length()!=0){
            int ptotal=Integer.parseInt(price.getText().toString())*Integer.parseInt(num.getText().toString());
            pTotal.setText(Integer.toString(ptotal));
            int ftota1=Integer.parseInt(fee.getText().toString())*Integer.parseInt(num.getText().toString());
            fTotal.setText(Integer.toString(ftota1));
            int Total2=ptotal+ftota1;
            total2.setText(Integer.toString(Total2));
        }else{
            pTotal.setText("$原價*數量");
            fTotal.setText("$代購*數量");
            total2.setText("$(原價+代購)*數量");
        }



    }
});




        btn461.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout.this.finish();
            }

        });
        btn462.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(Checkout.this)
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




                                if (num.getText().length() == 0||atemp.isEmpty()||name.getText().length() == 0) {
                                    Toast.makeText(Checkout.this, "請檢查\n1.數量\n2.地址\n3.真實姓名\n有無確實填寫，必且確定交易方式，謝謝！", Toast.LENGTH_SHORT).show();

                                }
                                else if (Integer.parseInt(num.getText().toString())>bundle0.getInt("num")) {
                                    Toast.makeText(Checkout.this, "購買數量不能超過賣家提供的數量唷！", Toast.LENGTH_SHORT).show();

                                }

                                else
                                {

                                    final String on=Integer.toString((int) (1000000*Math.random()+1));
                                    long messageTime = new Date().getTime();
                                    final String time = Long.toString(messageTime);
                                    final  String[] mynum = new String[2];


                                    final Bundle bundle=new Bundle();
                                    bundle.putString("ordernum","1"+time);
                                    bundle.putString("address",atemp);
                                    bundle.putString("moneyway",dtemp);
                                    bundle.putInt("num",Integer.parseInt(num.getText().toString()));
                                    bundle.putInt("price",Integer.parseInt(price.getText().toString()));
                                    bundle.putInt("fee",Integer.parseInt(fee.getText().toString()));
                                    bundle.putString("usernick",usernick);
                                    bundle.putString("user",seller);


                                    final boolean[] m = {false,false};
                                    DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                                    reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                if(ds.child("mail").getValue().equals(iam)){
                                                    mynum[0] = ds.child("memberNum").getValue().toString();
                                                    m[0] =true;

                                                }
                                                if(ds.child("mail").getValue().equals(seller)){
                                                    mynum[1] = ds.child("memberNum").getValue().toString();
                                                    m[1] =true;

                                                }

                                            }
                                            if(m[0]==true&&m[1]==true){

                                                FirebaseDatabase.getInstance()
                                                        .getReference("buyer_file")
                                                        .child(mynum[0])
                                                        .child("mybuy")
                                                        .child("1"+time)
                                                        .setValue(new MyBuy(

                                                                        time,
                                                                        name.getText().toString(),
                                                                        atemp,
                                                                        "尚未出貨",
                                                                        time,
                                                                        dtemp,
                                                                        seller,
                                                                        product.getText().toString(),
                                                                        Integer.parseInt(num.getText().toString()),
                                                                        Integer.parseInt(price.getText().toString()),
                                                                        Integer.parseInt(fee.getText().toString()),
                                                                        other.getText().toString(),
                                                                        productOrderTime,
                                                                        "尚未出貨",
                                                                        "尚未完成訂單",
                                                                        "1"+time,
                                                                        "【購物】",
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
                                                        .child("1"+time)
                                                        .setValue(new MyBuy(

                                                                        time,
                                                                        name.getText().toString(),
                                                                        atemp,
                                                                        "尚未出貨",
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
                                                                        "1"+time,
                                                                        "【下單】",
                                                                        picture,
                                                                "尚未出貨",
                                                                nickname,
                                                                receipt.getText().toString(),
                                                                "null"

                                                                )

                                                        );
                                                if(dtemp.contains("轉帳")){

                                                    FirebaseDatabase.getInstance()
                                                            .getReference("PayCheck")
                                                            .child("1"+time)
                                                            .setValue(new PayConfContainer(
                                                                            acctemp,
                                                                            total2.getText().toString(),
                                                                            "no"

                                                                    )

                                                            );


                                                }



                                                Intent intent = new Intent();

                                                    intent.setClass(Checkout.this, PaymentCompleted.class);
                                                    bundle.putString("picture",picture);
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                    Checkout.this.finish();




                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            }
                        })
                        .show();

                }


        });

        final boolean[] m = {false};

        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("seller_order")};
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

                        deliverList = new ArrayAdapter<String>(Checkout.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                deliver
                               );
                        sp461.setAdapter(deliverList);
                        dtemp= (String) deliverList.getItem(0);
                        m[0] =true;

                    }

                    if(m[0]==true){
                        String test=mypic[0];
                        if(test.contains("null")){}
                        else{

                            riversRef = mStorageRef.child("seller_order").child(mypic[0]);
                            Log.d("down", "onSuccess: "+mypic[0]);
                            downloadImg(riversRef);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
        addressl = new ArrayList<String>();

        DatabaseReference[] reference_contacts3 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts3[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                    if(ds.child("mail").getValue().equals(iam)){
                        if(ds.child("address").hasChildren()) {

                            for (int i=1;i<=ds.child("address").getChildrenCount();i++){//之後要判斷超商還是宅配地址
                                if(ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("店")) {
                                    addressl.add(ds.child("address").child(Integer.toString(i)).child("city").getValue().toString() +
                                            ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString());
                                }
                                else  if(ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("店")&&
                                        ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("全家")) {
                                    addressl.add(ds.child("address").child(Integer.toString(i)).child("city").getValue().toString() +
                                            ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString());
                                }else  if(ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("店")&&
                                        ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("7-11"))
                                {
                                    addressl.add(ds.child("address").child(Integer.toString(i)).child("city").getValue().toString()+
                                            ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString());

                                }
                            }


                        }else{

                            addressl.add("請新增地址");
                        }
                    }
                }
                addressList = new ArrayAdapter<String>(Checkout.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        addressl
                );
                sp462.setAdapter(addressList);

                atemp= (String) addressList.getItem(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究

        sp461.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dtemp= (String) deliverList.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp462.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atemp= (String) addressList.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(Checkout.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Checkout.this)){
                    Glide.with(Checkout.this)

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
            Checkout.this.finish();
        }
        return false;
    }
}
