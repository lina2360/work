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
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Order extends AppCompatActivity {
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    ImageView image;

    String[] mypic =new String[1];
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    Boolean p1=false;
    Boolean p2=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        final Bundle bundle=getIntent().getExtras();
        final String orderNum=bundle.getString("orderNum");
        final String user2=bundle.getString("user");
//        Toast.makeText(Order.this,user2, Toast.LENGTH_SHORT).show();
//        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar47);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(Order.this, Notify.class);
                startActivity(intent1);
                finish();
            }
        });

        image = (ImageView) findViewById(R.id.iv47_1);

        final TextView tv472 = (TextView)findViewById(R.id.tv47_2);
        final TextView tv473 = (TextView)findViewById(R.id.tv47_3);
        final TextView tv475 = (TextView)findViewById(R.id.tv47_5);
        final TextView tv476 = (TextView)findViewById(R.id.tv47_6);
        final TextView tv479 = (TextView)findViewById(R.id.tv47_9);
        final TextView tv4711 = (TextView)findViewById(R.id.tv47_11);
        final TextView tv4712 = (TextView)findViewById(R.id.tv47_12);
        final TextView tv4713 = (TextView)findViewById(R.id.tv47_13);
        final TextView tv4714 = (TextView)findViewById(R.id.tv47_14);
        final TextView tv4715 = (TextView)findViewById(R.id.tv47_15);
        final TextView tv4717 = (TextView)findViewById(R.id.tv47_17);
        final TextView tv4719 = (TextView)findViewById(R.id.tv47_19);
        final TextView tv4721 = (TextView)findViewById(R.id.tv47_21);
//        TextView tv4723 = (TextView)findViewById(R.id.tv47_23);
        final TextView tv4725 = (TextView)findViewById(R.id.tv47_25);
        final TextView tv4727 = (TextView)findViewById(R.id.tv47_27);
        final TextView tv4728 = (TextView)findViewById(R.id.tv47_28);
        final TextView tv4729 = (TextView)findViewById(R.id.tv47_29);
        final TextView tv4730 = (TextView)findViewById(R.id.tv47_30);
        Button btn471 = (Button)findViewById(R.id.btn47_1);
        final Button btn472 = (Button)findViewById(R.id.btn47_2);
        final Button btn473 = (Button)findViewById(R.id.btn47_3);
        tv4712.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Order.this, Member_page.class);
                Bundle bundle = new Bundle();
                bundle.putString("from", "Order");
                bundle.putString("user", tv4712.getText().toString());
                bundle.putString("useracc", user2);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


        btn471.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] count = {0};
                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            count[0] = count[0] +1;
                        }


                        final String[] mynum = new String[1];
                        final boolean[] m = {false};
                        final String[] user2num = new String[1];
                        final boolean[] u = {false};

                        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};//聊天室
                        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                                    if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getEmail())){
                                        mynum[0] = ds.child("mail").getValue().toString();
                                        Log.d("TAG", "onDataChange1: "+mynum[0]);
                                        m[0] =true;
                                    }
                                    Log.d("TAG", "ds.child(mail)2: "+ds.child("mail").getValue());
                                    if(ds.child("mail").getValue().equals(user2)){
                                        user2num[0] = ds.child("mail").getValue().toString();
                                        Log.d("TAG", "onDataChange2: "+user2num[0]);
                                        u[0] =true;
                                    }
                                    if(m[0]==true&&u[0]==true){
                                        if(user2.equals(iam)){
                                            Toast.makeText(Order.this, "這是您的請求單喔~", Toast.LENGTH_SHORT).show();
                                        }else{
//                                            Log.d("TAG", "mynum: "+mynum[0]+"user2: "+user2num[0]);
                                            Bundle bundle2=new Bundle();

//                                            int max= Math.max(Integer.parseInt(mynum[0]),Integer.parseInt(user2num[0]));
//                                            int min= Math.min(Integer.parseInt(mynum[0]),Integer.parseInt(user2num[0]));
//                                            Log.d("TAG", "max "+max+"min "+min);
                                            bundle2.putString("user1",mynum[0]);
                                            bundle2.putString("user2",user2num[0]);
                                            bundle2.putString("from","Order");
                                            bundle2.putString("from2","Order");
//                                            bundle2.putString("usernic", tv4712.getText().toString());

                                            bundle2.putString("orderNum", bundle.getString("orderNum"));
                                            bundle2.putString("orderkind", bundle.getString("orderkind"));
                                            bundle2.putString("orderpic", bundle.getString("orderpic"));
                                            bundle2.putString("usernic",bundle.getString("usernic"));
                                            bundle2.putString("user", bundle.getString("user"));
                                            Intent intent = new Intent();
                                            intent.setClass(Order.this, ChatRoom2.class);
                                            intent.putExtras(bundle2);
                                            startActivity(intent);
                                            Order.this.finish();

                                        }


                                    }

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }


                        });
//                        if(count==0){
//                            FirebaseDatabase.getInstance()
//                                    .getReference("chatroom")
//                                    .child(recognize)
//                                    .push()
//                                    .setValue(new ChatMessage(
//                                                    "weclome!",
//                                                    "system"
//                                            )
//                                    );
//                            }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};//顯示資訊
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
//            boolean m = false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    if(ds.child("mail").getValue().equals(iam)){
                    //訂單資訊
                        tv472.setText(ds.child("mybuy").child(orderNum).child("myName").getValue().toString());
                        tv473.setText( ds.child("mybuy").child(orderNum).child("address").getValue().toString());
                        tv475.setText( ds.child("mybuy").child(orderNum).child("orderState").getValue().toString());
                        tv4728.setText(ds.child("mybuy").child(orderNum).child("deliveryNum").getValue().toString());

                        tv476.setText( DateFormat.format("yyyy-MM-dd(HH:mm)",
                                Long.parseLong(ds.child("mybuy").child(orderNum).child("renewTime").getValue().toString())));

                        int sum=Integer.parseInt(ds.child("mybuy").child(orderNum).child("price").getValue().toString())+Integer.parseInt(ds.child("mybuy").child(orderNum).child("fee").getValue().toString());
                        int total=sum*Integer.parseInt(ds.child("mybuy").child(orderNum).child("num").getValue().toString());
                        tv479.setText("$"+Integer.toString(total));

                        tv4711.setText( ds.child("mybuy").child(orderNum).child("moneyWay").getValue().toString());
                        tv4712.setText( ds.child("mybuy").child(orderNum).child("usernick").getValue().toString());
                        //右下按鈕狀態
                        //商品單 買方
                        if(bundle.getString("orderkind").equals("【購物】")&&tv475.getText().equals("尚未出貨")){
                            tv4729.setText("賣家");
                            btn472.setText("取消訂單");
                        }
                        else if(bundle.getString("orderkind").equals("【購物】")&&tv475.getText().equals("已出貨")){
                            tv4729.setText("賣家");
                            btn472.setText("完成訂單");
                        }
                        else if(bundle.getString("orderkind").equals("【購物】")&&tv475.getText().equals("已完成訂單")){
                            tv4729.setText("賣家");
                            btn472.setText("給賣家評價");

                        }
                        else if(bundle.getString("orderkind").equals("【購物】")&&tv475.getText().equals("已給評價")){
                            tv4729.setText("賣家");
                            btn472.setText("已給評價");
                        }
                        else if(bundle.getString("orderkind").equals("【購物】")&&tv475.getText().equals("請求取消訂單")){
                            tv4729.setText("賣家");
                            btn472.setText("等待賣家回應");
                        }
                        ///商品單 賣方
                        else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("尚未出貨")){
                            tv4729.setText("買家");
                            btn472.setText("填物流編號並出貨");
                        }
                        else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("已出貨")){
                            tv4729.setText("買家");
                            btn472.setText("等待收貨");
                        }
                        else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("已完成訂單")){
                            tv4729.setText("買家");
                            btn472.setText("給買家評價");
                        }
                        else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("已給評價")){
                            tv4729.setText("買家");
                            btn472.setText("已給評價");
                        }
                        else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("請求取消訂單")){
                            tv4729.setText("買家");
                            btn472.setText("回應");
                        }
                        //請求單 賣方
                        else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("等待物流資訊")){
                            tv4729.setText("買家");
                            btn472.setText("等待物流資訊");
                        }
                        else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("尚未出貨")){
                            tv4729.setText("買家");
                            btn472.setText("填物流編號並出貨");
                        }
                        else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("已出貨")){
                            tv4729.setText("買家");
                            btn472.setText("等待收貨");
                        }
                        else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("已完成訂單")){
                            tv4729.setText("買家");
                            btn472.setText("給買方評價");
                        }
                        else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("已給評價")){
                            tv4729.setText("買家");
                            btn472.setText("已給評價");
                        }
                        else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("請求取消訂單")){
                            tv4729.setText("買家");
                            btn472.setText("回應");
                        }
                        //請求單 買方
                        else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("等待物流資訊")){
                            tv4729.setText("賣家");
                            btn472.setText("提供物流資訊");
                        }
                        else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("尚未出貨")){
                            tv4729.setText("賣家");
                            btn472.setText("取消訂單");
                        }
                        else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("已出貨")){
                            tv4729.setText("賣家");
                            btn472.setText("完成訂單");
                        }
                        else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("已完成訂單")){
                            tv4729.setText("賣家");
                            btn472.setText("給賣方評價");
                        }
                        else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("已給評價")){
                            tv4729.setText("賣家");
                            btn472.setText("已給評價");
                        }
                        else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("請求取消訂單")){
                            tv4729.setText("賣家");
                            btn472.setText("等待賣家回應");
                        }



                        if(ds.child("mybuy").child(orderNum).child("receipt").getValue().toString().contains("不提供"))
                            tv4730.setText(ds.child("mybuy").child(orderNum).child("receipt").getValue().toString());
                        else if(ds.child("mybuy").child(orderNum).child("receipt").getValue().toString().contains("不"))
                            tv4730.setText("買家不需要收據");
                        else if(ds.child("mybuy").child(orderNum).child("receipt").getValue().toString().contains("出貨前給予證明"))
                            tv4730.setText("買家要收據(出貨前證明)");
                        else
                            tv4730.setText("買家要收據");



                        tv4713.setText( ds.child("mybuy").child(orderNum).child("product").getValue().toString());
                        tv4714.setText( ds.child("mybuy").child(orderNum).child("num").getValue().toString()+"(單位)");



                        tv4715.setText( "$"+Integer.toString(sum));
                        if(!ds.child("mybuy").child(orderNum).child("other").getValue().toString().isEmpty())
                        tv4717.setText( ds.child("mybuy").child(orderNum).child("other").getValue().toString());

                        tv4719.setText( ds.child("mybuy").child(orderNum).getKey());
                        tv4721.setText( DateFormat.format("yyyy-MM-dd(HH:mm)",
                                Long.parseLong(ds.child("mybuy").child(orderNum).child("orderTime").getValue().toString())));

                        if( ds.child("mybuy").child(orderNum).child("deliveryTime").getValue().toString().equals("尚未出貨"))
                            tv4725.setText( ds.child("mybuy").child(orderNum).child("deliveryTime").getValue().toString());
                        else {
                            tv4725.setText( DateFormat.format("yyyy-MM-dd(HH:mm)",
                                    Long.parseLong(ds.child("mybuy").child(orderNum).child("deliveryTime").getValue().toString())));
                        }


                        if( ds.child("mybuy").child(orderNum).child("completeTime").getValue().toString().equals("尚未完成訂單"))
                            tv4727.setText( ds.child("mybuy").child(orderNum).child("completeTime").getValue().toString());
                        else{
                            tv4727.setText( DateFormat.format("yyyy-MM-dd(HH:mm)",
                                    Long.parseLong(ds.child("mybuy").child(orderNum).child("completeTime").getValue().toString())));
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        if(bundle.getString("orderkind").equals("【購物】")||bundle.getString("orderkind").equals("【下單】"))
        {
            Uri file = Uri.fromFile(new File(bundle.getString("orderpic")));
            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentDisposition("universe")
                    .setContentType("image/jpg")
                    .build();
            riversRef = mStorageRef.child("seller_order").child(file.getLastPathSegment());
            downloadImg(riversRef);
        }
        if(bundle.getString("orderkind").equals("【接單】")||bundle.getString("orderkind").equals("【代購】"))
        {
            Uri file = Uri.fromFile(new File(bundle.getString("orderpic")));
            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentDisposition("universe")
                    .setContentType("image/jpg")
                    .build();
            riversRef = mStorageRef.child("buyer_order").child(file.getLastPathSegment());
            downloadImg(riversRef);
        }


        View.OnClickListener OCL = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    int id = ((Button) v).getId();
                    switch (id) {
                        case R.id.btn47_3:
                            if( tv4730.getText().toString().contains("不提供")){
                                Toast.makeText(Order.this, "賣家不提供收據唷！", Toast.LENGTH_SHORT).show();
                            }
                            else if(tv4730.getText().equals("買家不需要收據"))
                            {
                                Toast.makeText(Order.this, "買家不需要收據唷！", Toast.LENGTH_SHORT).show();
                            }
                            else if(tv4730.getText().equals("買家要收據，需要出貨前證明"))
                            {
                                Intent intent1 = new Intent();
                                Bundle bundle1=new Bundle();
                                bundle1.putString( "ordernum",tv4719.getText().toString());
                                bundle1.putString("orderkind",bundle.getString("orderkind"));
                                bundle1.putString("user2",user2);
                                if(tv4729.getText().toString().equals("賣家"))
                                bundle1.putString("who","0");
                                else
                                    bundle1.putString("who","1");
                                intent1.setClass(Order.this,Order_receipt.class);
                                intent1.putExtras(bundle1);
                                startActivity(intent1);
                            }
                            else{//tv4730.setText("買家要收據");
                                Intent intent1 = new Intent();
                                Bundle bundle1=new Bundle();
                                bundle1.putString( "ordernum",tv4719.getText().toString());
                                bundle1.putString("orderkind",bundle.getString("orderkind"));
                                bundle1.putString("user2",user2);
                                if(tv4729.getText().toString().equals("賣家"))
                                    bundle1.putString("who","0");
                                else
                                    bundle1.putString("who","1");
                                intent1.setClass(Order.this,Order_receipt.class);
                                intent1.putExtras(bundle1);
                                startActivity(intent1);
                            }


                            break;

                        case R.id.btn47_2:
                            //商品單 買方
                            if(bundle.getString("orderkind").equals("【購物】")&&tv475.getText().equals("尚未出貨")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("取消訂單")
                                        .setMessage("按下'確認'後，我們將會通知賣家，如雙方皆確認取消訂單，我們將會將訂單作廢，感謝您！")
                                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                long messageTime = new Date().getTime();
                                                final String time = Long.toString(messageTime);

                                                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};//顯示資訊
                                                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                            if(ds.child("mail").getValue().equals(iam)){

                                                                String childf="orderState";
                                                                String valuef="請求取消訂單";
                                                                for(int i=1;i<=2;i++){
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

                                                                }
                                                                p1=true;
                                                            }
                                                            else if(ds.child("mail").getValue().equals(ds.child("mybuy").child(orderNum).child("seller").getValue().toString())){
                                                                String childf="orderState";
                                                                String valuef="請求取消訂單";
                                                                for(int i=1;i<=2;i++){
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
                                                                }
//
                                                                p2=true;
                                                            }
                                                        }
                                                        if(p1==true&&p2==true){
//
                                                            Intent intent1 = new Intent();
                                                            intent1.setClass(Order.this,Notify.class);
                                                            startActivity(intent1);
                                                            Order.this.finish();
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
                            }//商品單 買方
                            else if(bundle.getString("orderkind").equals("【購物】")&&tv475.getText().equals("已出貨")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("完成訂單")
                                        .setMessage("按下'確認'後，我們將會撥款給賣家，並且請您不吝給予賣家評價做為日後他人參考，感謝您！")
                                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                long messageTime = new Date().getTime();
                                                final String time = Long.toString(messageTime);

                                                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};//顯示資訊
                                                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                            if(ds.child("mail").getValue().equals(iam)){

                                                                String childf="orderState";
                                                                String valuef="已完成訂單";
                                                                for(int i=1;i<=3;i++){
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
                                                                    else if(i==2){
                                                                        childf="completeTime";
                                                                        valuef=time;
                                                                    }

                                                                }
                                                                p1=true;
                                                            }
                                                            else if(ds.child("mail").getValue().equals(user2)){
                                                                String childf="orderState";
                                                                String valuef="已完成訂單";
                                                                for(int i=1;i<=3;i++){
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
                                                                    else if(i==2){
                                                                        childf="completeTime";
                                                                        valuef=time;
                                                                    }

                                                                }
//
                                                                p2=true;
                                                            }
                                                        }
                                                        if(p1==true&&p2==true){
//
                                                            Intent intent1 = new Intent();
                                                            intent1.setClass(Order.this,Notify.class);
                                                            startActivity(intent1);
                                                            Order.this.finish();
                                                        }

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    }

                                                });


                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();


                            }//商品單 買方
                            else if(bundle.getString("orderkind").equals("【購物】")&&tv475.getText().equals("已完成訂單")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("完成評價")
                                        .setMessage("還沒給評價嗎?快點來寫下您這次交易的感想吧，GOGO！")
                                        .setPositiveButton("現在就給評去", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Bundle bundle1=new Bundle();
                                                bundle1.putString("orderNum",orderNum);
                                                bundle1.putString("user2",user2);
                                                bundle1.putString("orderkind", bundle.getString("orderkind"));
                                                bundle1.putString("orderpic", bundle.getString("orderpic"));
                                                bundle1.putString("product", tv4713.getText().toString());
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,Rating.class);
                                                intent1.putExtras(bundle1);
                                                startActivity(intent1);
                                                Order.this.finish();

                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }//商品單 賣方
                            else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("尚未出貨")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("填物流編號並出貨")
                                        .setMessage("按下'確認'後，請您立即填寫物流編號提供給買方做進度查詢，送出後我們將會更改訂單狀態，感謝您！")
                                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Bundle bundle1=new Bundle();
                                                bundle1.putString("orderNum",orderNum);
                                                bundle1.putString("user2",user2);
                                                bundle1.putString("orderkind", bundle.getString("orderkind"));
                                                bundle1.putString("orderpic", bundle.getString("orderpic"));
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,PackageNumber.class);
                                                intent1.putExtras(bundle1);
                                                startActivity(intent1);
                                                Order.this.finish();

                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();

                            }//商品單 賣方
                            else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("已出貨")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("等待收款")
                                        .setMessage("確認買家收到貨後會盡快撥款給您，請稍作等待，謝謝您！")
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }
                            else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("已完成訂單")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("完成評價")
                                        .setMessage("還沒給評價嗎?快點來寫下您這次交易的感想吧，GOGO！")
                                        .setPositiveButton("現在就給評去", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Bundle bundle1=new Bundle();
                                                bundle1.putString("orderNum",orderNum);
                                                bundle1.putString("user2",user2);
                                                bundle1.putString("orderkind", bundle.getString("orderkind"));
                                                bundle1.putString("orderpic", bundle.getString("orderpic"));
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,Rating.class);
                                                intent1.putExtras(bundle1);
                                                startActivity(intent1);
                                                Order.this.finish();

                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }
                            else if(bundle.getString("orderkind").equals("【下單】")&&tv475.getText().equals("請求取消訂單")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("取消訂單")
                                        .setMessage("如果同意買家取消訂單，請按下'確認取消'鍵，則此訂單將會作廢，謝謝！")
                                        .setPositiveButton("確認取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(Order.this, "取消訂單成功！", Toast.LENGTH_SHORT).show();
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,Notify.class);
                                                startActivity(intent1);
                                                Order.this.finish();
                                            }
                                        })
                                        .setNegativeButton("拒絕取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(Order.this, "更新訂單資訊為:尚未出貨", Toast.LENGTH_LONG).show();
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,Notify.class);
                                                startActivity(intent1);
                                                Order.this.finish();
                                            }
                                        })
                                        .show();
                            }
                            //請求單 賣方
                            else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("等待物流資訊")){
                                  new AlertDialog.Builder(Order.this)
                                        .setTitle("等待物流資訊")
                                        .setMessage("請先與買家溝通好交易方式，再請買家更新訂單中的物流資訊，感謝您！")
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }//請求單 賣方
                            else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("尚未出貨")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("填物流編號並出貨")
                                        .setMessage("按下'確認'後，請您立即填寫物流編號提供給買方做進度查詢，送出後我們將會更改訂單狀態，感謝您！")
                                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Bundle bundle1=new Bundle();
                                                bundle1.putString("orderNum",orderNum);
                                                bundle1.putString("user2",user2);
                                                bundle1.putString("orderkind", bundle.getString("orderkind"));
                                                bundle1.putString("orderpic", bundle.getString("orderpic"));
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,PackageNumber.class);
                                                intent1.putExtras(bundle1);
                                                startActivity(intent1);
                                                Order.this.finish();

                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }//請求單 賣方
                            else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("已出貨")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("等待收款")
                                        .setMessage("確認買家收到貨後會盡快撥款給您，請稍作等待，謝謝您！")
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }
                            else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("已完成訂單")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("完成評價")
                                        .setMessage("還沒給評價嗎?快點來寫下您這次交易的感想吧，GOGO！")
                                        .setPositiveButton("現在就給評去", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Bundle bundle1=new Bundle();
                                                bundle1.putString("orderNum",orderNum);
                                                bundle1.putString("user2",user2);
                                                bundle1.putString("orderkind", bundle.getString("orderkind"));
                                                bundle1.putString("orderpic", bundle.getString("orderpic"));
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,Rating.class);
                                                intent1.putExtras(bundle1);
                                                startActivity(intent1);
                                                Order.this.finish();

                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }
                            else if(bundle.getString("orderkind").equals("【接單】")&&tv475.getText().equals("請求取消訂單")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("取消訂單")
                                        .setMessage("如果同意買家取消訂單，請按下'確認取消'鍵，則此訂單將會作廢，謝謝！")
                                        .setPositiveButton("確認取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(Order.this, "取消訂單成功！", Toast.LENGTH_SHORT).show();
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,Notify.class);
                                                startActivity(intent1);
                                                Order.this.finish();
                                            }
                                        })
                                        .setNegativeButton("拒絕取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(Order.this, "更新訂單資訊為:尚未出貨", Toast.LENGTH_LONG).show();
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,Notify.class);
                                                startActivity(intent1);
                                                Order.this.finish();
                                            }
                                        })
                                        .show();
                            }
                            //請求單 買方
                            else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("等待物流資訊")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("填物流資訊")
                                        .setMessage("請先與賣家溝通好交易方式，再按下'確認'鍵去更新訂單中的物流資訊，感謝您")
                                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Bundle bundle1=new Bundle();
                                                bundle1.putString("orderNum",orderNum);
                                                bundle1.putString("user2",user2);
                                                bundle1.putString("orderkind", bundle.getString("orderkind"));
                                                bundle1.putString("orderpic", bundle.getString("orderpic"));
                                                bundle1.putString("moneyway", tv4711.getText().toString());
                                                bundle1.putString("totalm", tv479.getText().toString());
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,BuyerShippingAddress.class);
                                                intent1.putExtras(bundle1);
                                                startActivity(intent1);
                                                Order.this.finish();

                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();

                            }//請求單 買方
                            else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("尚未出貨")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("取消訂單")
                                        .setMessage("按下'確認'後，我們將會通知賣家，如雙方皆確認取消訂單，我們將會將訂單作廢，感謝您！")
                                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                long messageTime = new Date().getTime();
                                                final String time = Long.toString(messageTime);

                                                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};//顯示資訊
                                                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                            if(ds.child("mail").getValue().equals(iam)){

                                                                String childf="orderState";
                                                                String valuef="請求取消訂單";
                                                                for(int i=1;i<=2;i++){
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

                                                                }
                                                                p1=true;
                                                            }
                                                            else if(ds.child("mail").getValue().equals(ds.child("mybuy").child(orderNum).child("seller").getValue().toString())){
                                                                String childf="orderState";
                                                                String valuef="請求取消訂單";
                                                                for(int i=1;i<=2;i++){
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
                                                                }
//
                                                                p2=true;
                                                            }
                                                        }
                                                        if(p1==true&&p2==true){
//
                                                            Intent intent1 = new Intent();
                                                            intent1.setClass(Order.this,Notify.class);
                                                            startActivity(intent1);
                                                            Order.this.finish();
                                                        }

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    }

                                                });
                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }//請求單 買方
                            else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("已出貨")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("完成訂單")
                                        .setMessage("按下'確認'後，我們將會撥款給賣家，並且請您不吝給予賣家評價做為日後他人參考，感謝您！")
                                        .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                long messageTime = new Date().getTime();
                                                final String time = Long.toString(messageTime);

                                                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};//顯示資訊
                                                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                            if(ds.child("mail").getValue().equals(iam)){

                                                                String childf="orderState";
                                                                String valuef="已完成訂單";
                                                                for(int i=1;i<=3;i++){
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
                                                                    else if(i==2){
                                                                        childf="completeTime";
                                                                        valuef=time;
                                                                    }

                                                                }
                                                                p1=true;
                                                            }
                                                            else if(ds.child("mail").getValue().equals(ds.child("mybuy").child(orderNum).child("seller").getValue().toString())){
                                                                String childf="orderState";
                                                                String valuef="已完成訂單";
                                                                for(int i=1;i<=3;i++){
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
                                                                    else if(i==2){
                                                                        childf="completeTime";
                                                                        valuef=time;
                                                                    }

                                                                }
//
                                                                p2=true;
                                                            }
                                                        }
                                                        if(p1==true&&p2==true){
//
                                                            Intent intent1 = new Intent();
                                                            intent1.setClass(Order.this,Notify.class);
                                                            startActivity(intent1);
                                                            Order.this.finish();
                                                        }

                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    }

                                                });


                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }
                            else if(bundle.getString("orderkind").equals("【代購】")&&tv475.getText().equals("已完成訂單")){
                                new AlertDialog.Builder(Order.this)
                                        .setTitle("完成評價")
                                        .setMessage("還沒給評價嗎?快點來寫下您這次交易的感想吧，GOGO！")
                                        .setPositiveButton("現在就給評去", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Bundle bundle1=new Bundle();
                                                bundle1.putString("orderNum",orderNum);
                                                bundle1.putString("user2",user2);
                                                bundle1.putString("orderkind", bundle.getString("orderkind"));
                                                bundle1.putString("orderpic", bundle.getString("orderpic"));
                                                Intent intent1 = new Intent();
                                                intent1.setClass(Order.this,Rating.class);
                                                intent1.putExtras(bundle1);
                                                startActivity(intent1);
                                                Order.this.finish();

                                            }
                                        })
                                        .setNegativeButton("關閉視窗", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();
                            }




                            break;
                    }
                }
            }
        };
        btn472.setOnClickListener(OCL);
        btn473.setOnClickListener(OCL);


    }

    private void downloadImg(final StorageReference ref){
        if(ref == null){
            Toast.makeText(Order.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Order.this)){
                    Glide.with(Order.this)

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
            Intent intent1 = new Intent();
            intent1.setClass(Order.this, Notify.class);
            startActivity(intent1);
            finish();
        }
        return false;
    }
}
