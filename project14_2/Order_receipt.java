package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class Order_receipt extends AppCompatActivity {
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    String receiptpic="null";
    String receiptpic2="null";
    Button btn691;
    String user2="";
    String mynum="";
    String user2num="";
    private StorageReference mStorageRef;
    private StorageReference riversRef;
    ImageView iv69;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_receipt);
        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar69);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order_receipt.this.finish();
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        iv69= (ImageView)findViewById(R.id.iv69);




        final Bundle bundle=getIntent().getExtras();
        final String ordernum=bundle.getString("ordernum");
        final String orderkind=bundle.getString("orderkind");
       user2=bundle.getString("user2");

        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.child("mail").getValue().equals(iam)){
                        receiptpic=ds.child("mybuy").child(ordernum).child("receiptpic").getValue().toString();

                        if(receiptpic.contains("null")){}
                        else{
                            btn691.setText("已上傳完成");
                            riversRef = mStorageRef.child("order_receipt").child(receiptpic);
                            downloadImg(riversRef,iv69);
                        }
                        mynum=ds.child("memberNum").getValue().toString();
                    }
                    if(ds.child("mail").getValue().equals(user2)){
                        receiptpic2=ds.child("mybuy").child(ordernum).child("receiptpic").getValue().toString();
                        user2num=ds.child("memberNum").getValue().toString();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });




//        if(orderkind.contains("購物")||orderkind.contains("下單")){
//            //商品單
//
//
//        }else{
//            //請求單
//
//        }

        btn691 = (Button) findViewById(R.id.btn69);
        btn691.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn691.getText().toString().equals("已上傳完成")){
                    Toast.makeText(Order_receipt.this, "收據已經上傳完成囉！", Toast.LENGTH_SHORT).show();
                }else{
                    if(bundle.getString("who").equals("0")){
                        Toast.makeText(Order_receipt.this, "請等待賣家上傳唷！", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent();
                        intent.setClass(Order_receipt.this, PictureChange.class);
                        Bundle bundle1=new Bundle();
                        bundle1.putString("mynum", mynum);
                        bundle1.putString("user2num",user2num);
                        bundle1.putString("mypic",receiptpic);
                        bundle1.putString("from","Order_receipt");
                        bundle1.putString( "ordernum",ordernum);
                        bundle1.putString("orderkind",orderkind);
                        bundle1.putString("user2",user2);
                        bundle1.putString("who",bundle.getString("who"));
                        intent.putExtras(bundle1);
                        startActivity(intent);
                        Order_receipt.this.finish();

                    }


                }


            }
        });





    }

    private void downloadImg(final StorageReference ref, final ImageView img){
        if(ref == null){
            Toast.makeText(Order_receipt.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(Order_receipt.this)){
                    Glide.with(Order_receipt.this)

                            .load(ref)
                            .into(img);

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
//            Intent intent1 = new Intent();
//            intent1.setClass(Order_receipt.this, Notify.class);
//            startActivity(intent1);
            Order_receipt.this.finish();
        }
        return false;
    }
}
