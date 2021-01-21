package com.project14.nccu105.project14_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class BuyerShippingAddress extends AppCompatActivity {
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    Boolean p1=false;
    Boolean p2=false;
    ArrayAdapter<String> addressList,accountList;
    List<String> address,account;
    Spinner sp561,sp562;
    String atemp = "";
    String accounttemp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_shipping_address);

        getSupportActionBar().hide();

        Button btn561= (Button)findViewById(R.id.btn56_1);
        Button btn562= (Button)findViewById(R.id.btn56_2);
        Button btn563= (Button)findViewById(R.id.btn56_3);

        final EditText et561=(EditText)findViewById(R.id.et56_1);
        EditText et562=(EditText)findViewById(R.id.et56_2);
        sp561= (Spinner)findViewById(R.id.sp56_1);
        sp562= (Spinner)findViewById(R.id.sp56_2);
        address = new ArrayList<String>();
        account = new ArrayList<String>();


        final Bundle bundle=getIntent().getExtras();
        final String orderNum=bundle.getString("orderNum");
        final String user2=bundle.getString("user2");
        final String orderkind=bundle.getString("orderkind");
        final String orderpic =bundle.getString("orderpic");
        final String moneyway =bundle.getString("moneyway");
        final String totalm =bundle.getString("totalm");

        if(moneyway.contains("宅配")){//設定抓哪種資料，從買家資料庫中抓該用戶填的預設地址或分店名

            DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
            reference_contacts1[0].addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                        if(ds.child("mail").getValue().equals(iam)){
                            if(ds.child("address").hasChildren()) {

                                for (int i=1;i<=ds.child("address").getChildrenCount();i++){//之後要判斷超商還是宅配地址
                                    if(!ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("店")) {
                                        address.add(ds.child("address").child(Integer.toString(i)).child("city").getValue().toString() +
                                                ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString());
                                    }
                                }
                                if(address.size()==0){
                                    address.add("請新增地址");
                                }

                            }
                        }
                    }
                    addressList = new ArrayAdapter<String>(BuyerShippingAddress.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            address
                    );
                    sp561.setAdapter(addressList);
                    atemp= (String) addressList.getItem(0);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        else if(moneyway.contains("全家")){

            DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
            reference_contacts1[0].addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                        if(ds.child("mail").getValue().equals(iam)){
                            if(ds.child("address").hasChildren()) {

                                    for (int i = 1; i <= ds.child("address").getChildrenCount(); i++) {//之後要判斷超商還是宅配地址
                                        if(ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("店")&&
                                                ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("全家")) {
                                        address.add(ds.child("address").child(Integer.toString(i)).child("city").getValue().toString() +
                                                ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString());
                                    }
                                }
                                if(address.size()==0){
                                    address.add("請新增地址");
                                }

                            }
                        }
                    }
                    addressList = new ArrayAdapter<String>(BuyerShippingAddress.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            address
                    );
                    sp561.setAdapter(addressList);
                    atemp= (String) addressList.getItem(0);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        else if(moneyway.contains("7-11")){


            DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
            reference_contacts1[0].addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                        if(ds.child("mail").getValue().equals(iam)){
                            if(ds.child("address").hasChildren()) {
                                for (int i=1;i<=ds.child("address").getChildrenCount();i++){//之後要判斷超商還是宅配地址
                                    if(ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("店")&&
                                            ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString().contains("7-11"))
                                    {
                                        address.add(ds.child("address").child(Integer.toString(i)).child("city").getValue().toString()+
                                            ds.child("address").child(Integer.toString(i)).child("myaddress").getValue().toString());

                                    }
                                }


                            }else {
                                address.add("請新增地址");
                            }
                        }
                    }
                    addressList = new ArrayAdapter<String>(BuyerShippingAddress.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            address
                    );
                    sp561.setAdapter(addressList);
                    atemp= (String) addressList.getItem(0);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        sp561.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atemp= (String) addressList.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp562.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accounttemp= (String) accountList.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(!moneyway.contains("轉帳")){
            account.add("不須選擇");
            accountList = new ArrayAdapter<String>(BuyerShippingAddress.this,
                    android.R.layout.simple_spinner_dropdown_item,
                    account
            );
            sp562.setAdapter(accountList);
            accounttemp= (String) accountList.getItem(0);
        }else {

            DatabaseReference[] reference_contacts1 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
            reference_contacts1[0].addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren() ) {
                        if(ds.child("mail").getValue().equals(iam)){
                            if(ds.child("creditCard").hasChildren()) {
                                account.add("尚未選擇");
                                for (int i=1;i<=ds.child("creditCard").getChildrenCount();i++){

                                    account.add(ds.child("creditCard").child(Integer.toString(i)).child("city").getValue().toString() +
                                            ds.child("creditCard").child(Integer.toString(i)).child("myaddress").getValue().toString());

                                }


                            }else{
//                            if(address.size()==0){
                                account.add("請新增帳號");
//                            }

                            }
                        }
                    }
                    accountList = new ArrayAdapter<String>(BuyerShippingAddress.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            account
                    );
                    sp562.setAdapter(accountList);
                    accounttemp= (String) accountList.getItem(0);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }



//        Toast.makeText(BuyerShippingAddress.this, user2, Toast.LENGTH_SHORT).show();
        btn561.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BuyerShippingAddress.this)
                        .setTitle("離開此頁")
                        .setMessage("按下'確認'後，畫面將會移到個人設定，感謝您！")
                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent1 = new Intent();
                                intent1.setClass(BuyerShippingAddress.this,Personal_setting.class);
                                startActivity(intent1);
                                BuyerShippingAddress.this.finish();
                            }
                                })
                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();;

            }
        });


        btn562.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1=new Bundle();
                bundle1.putString("orderNum",orderNum);
                bundle1.putString("orderkind", orderkind);
                bundle1.putString("orderpic", orderpic);
                bundle1.putString("user2",user2);
                Intent intent1 = new Intent();
                intent1.setClass(BuyerShippingAddress.this,Order.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                BuyerShippingAddress.this.finish();
            }
        });

        btn563.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et561.getText().toString().isEmpty()){// ||et562.getText().equals("")
                    Toast.makeText(BuyerShippingAddress.this, "請填寫完整，謝謝！",
                            Toast.LENGTH_LONG)
                            .show();

                }else if(atemp.equals("請新增地址")){
                    Toast.makeText(BuyerShippingAddress.this, "請先新增地址，謝謝！",
                            Toast.LENGTH_LONG)
                            .show();
                }
                else if(moneyway.contains("轉帳")&&accounttemp.contains("未選擇")){
                    Toast.makeText(BuyerShippingAddress.this, "請先新增匯款帳號，謝謝！",
                            Toast.LENGTH_LONG)
                            .show();
                }
                else{

                    new AlertDialog.Builder(BuyerShippingAddress.this)
                            .setTitle("物流資訊確認")
                            .setMessage("按下'確認'後，我們將更新物流資訊至訂單，日後不能再做更改，感謝您！")
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    long messageTime = new Date().getTime();
                                    final String time = Long.toString(messageTime);
                                    if(moneyway.contains("轉帳")){

                                        if(!accounttemp.contains("未選擇")){

                                            FirebaseDatabase.getInstance()
                                                    .getReference("PayCheck")
                                                    .child(orderNum)
                                                    .setValue(new PayConfContainer(
                                                                    accounttemp,
                                                                    totalm,
                                                                    "no"

                                                            )

                                                    );


                                        }


                                    }


                                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};//顯示資訊
                                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                if(ds.child("mail").getValue().equals(iam)){

                                                    String childf="orderState";
                                                    String valuef="尚未出貨";
                                                    for(int i=1;i<=4;i++){
                                                        FirebaseDatabase.getInstance()
                                                                .getReference("buyer_file")
                                                                .child(ds.child("memberNum").getValue().toString())
                                                                .child("mybuy")
                                                                .child(orderNum)
                                                                .child(childf)
                                                                .setValue(valuef);
                                                        if(i==1)
                                                        {
                                                            childf="renewTime";
                                                            valuef=time;
                                                        }
                                                        if(i==2)//名字
                                                        {
                                                            childf="myName";
                                                            valuef=et561.getText().toString();
                                                        }
                                                        if(i==3)//地址
                                                        {
                                                            childf="address";
                                                            valuef=atemp;
                                                        }
//
                                                    }

                                                    p1=true;
                                                }
                                                else if(ds.child("mail").getValue().equals(
                                                        user2)){


                                                    String childf="orderState";
                                                    String valuef="尚未出貨";
                                                    for(int i=1;i<=4;i++){
                                                        FirebaseDatabase.getInstance()
                                                                .getReference("buyer_file")
                                                                .child(ds.child("memberNum").getValue().toString())
                                                                .child("mybuy")
                                                                .child(orderNum)
                                                                .child(childf)
                                                                .setValue(valuef);
                                                        if(i==1)
                                                        {
                                                            childf="renewTime";
                                                            valuef=time;
                                                        }
                                                        if(i==2)//名字
                                                        {
                                                            childf="myName";
                                                            valuef=et561.getText().toString();
                                                        }
                                                        if(i==3)//地址
                                                        {
                                                            childf="address";
                                                            valuef=atemp;
                                                        }

                                                    }

                                                    p2=true;
                                                }

                                            }
                                            if(p1==true&&p2==true){
//
                                                Intent intent1 = new Intent();
                                                intent1.setClass(BuyerShippingAddress.this,Notify.class);
                                                startActivity(intent1);
                                                BuyerShippingAddress.this.finish();

                                            }

                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });


//
                                }
                            })
                            .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();

                }







            }

        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final Bundle bundle=getIntent().getExtras();
            final String orderNum=bundle.getString("orderNum");
            final String user2=bundle.getString("user2");
            final String orderkind=bundle.getString("orderkind");
            final String orderpic =bundle.getString("orderpic");

            Bundle bundle1=new Bundle();
            bundle1.putString("orderNum",orderNum);
            bundle1.putString("orderkind", orderkind);
            bundle1.putString("orderpic", orderpic);

            Intent intent1 = new Intent();
            intent1.setClass(BuyerShippingAddress.this,Order.class);
            intent1.putExtras(bundle1);
            startActivity(intent1);
            BuyerShippingAddress.this.finish();
        }
        return false;
    }
}
