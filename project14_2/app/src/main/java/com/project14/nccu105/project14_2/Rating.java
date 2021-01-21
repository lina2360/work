package com.project14.nccu105.project14_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;


public class Rating extends AppCompatActivity {
    Float rate= 3.0f;
    Boolean p1=false;
    Boolean p2=false;
    String comment="無";
    final String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    String nickname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);

        getSupportActionBar().hide();



        final Bundle bundle=getIntent().getExtras();
        final String orderNum=bundle.getString("orderNum");
        final String user2=bundle.getString("user2");
        final String orderkind=bundle.getString("orderkind");
        final String orderpic =bundle.getString("orderpic");
        final String product =bundle.getString("product");

        Button btn581= (Button)findViewById(R.id.btn58_1);
        Button btn582= (Button)findViewById(R.id.btn58_2);
        final EditText et581= (EditText) findViewById(R.id.et58_1);
        RatingBar ratingBar= (RatingBar)findViewById(R.id.ratingBar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ratingBar.setMax(100); //設定最大值
        ratingBar.setNumStars(5); //設定最大星型數量
        ratingBar.setStepSize((float) 0.5); //設定步進值
        ratingBar.setRating((float) 2.5); //設定目前顯示的星型數量
//        ratingBar.setIsIndicator(true); //設定是否可被使用者修改評分

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                Toast.makeText(getApplicationContext(), "rating: " + rating, Toast.LENGTH_LONG).show();
                rate=rating;
            }
        });
        et581.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                comment=et581.getText().toString();

            }
        });

        btn581.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1=new Bundle();
                bundle1.putString("orderNum",orderNum);
                bundle1.putString("orderkind", orderkind);
                bundle1.putString("orderpic", orderpic);
                Intent intent1 = new Intent();
                intent1.setClass(Rating.this,Order.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                Rating.this.finish();
            }
        });

        btn582.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Rating.this)
                        .setTitle("送出評價")
                        .setMessage("評價日後不能再做更改，因此請確認無誤後再送出，以免造成對方困擾，感謝您！")
                        .setPositiveButton("給評囉！", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                final long messageTime = new Date().getTime();
                                final String time = Long.toString(messageTime);

                                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};//顯示資訊
                                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            if(ds.child("mail").getValue().equals(iam)){
                                                nickname=ds.child("nickname").getValue().toString();

                                                String childf="orderState";
                                                String valuef="已給評價";
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
                                            else if(ds.child("mail").getValue().equals(user2)){

                                                String childf="renewTime";
                                                String valuef=time;
                                                    FirebaseDatabase.getInstance()
                                                            .getReference("buyer_file")
                                                            .child(ds.child("memberNum").getValue().toString())
                                                            .child("mybuy")
                                                            .child(orderNum)
                                                            .child(childf)
                                                            .setValue(valuef);

                                                if(orderkind.equals("【購物】"))//使用者是商品單買家
                                                {
                                                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("seller_file")};
                                                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                                                                if(ds.child("email").getValue().equals(user2)){
                                                                    FirebaseDatabase.getInstance()
                                                                            .getReference("seller_file")
                                                                            .child(ds.child("sellernum").getValue().toString())//存成賣家評價
                                                                            .child("sellerrating")
                                                                            .child(String.valueOf(messageTime))
                                                                            .setValue(new RatingContainer(
                                                                                    iam,
                                                                                    "【商品單】",
                                                                                    product,
                                                                                    rate,
                                                                                    comment,
                                                                                    messageTime,
                                                                                    nickname
                                                                            ));


                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }


                                                    });
                                                }
                                                if(orderkind.equals("【下單】"))//使用者是商品單賣家
                                                {
                                                    FirebaseDatabase.getInstance()
                                                            .getReference("buyer_file")
                                                            .child(ds.child("memberNum").getValue().toString())//存成買家評價
                                                            .child("buyerRating")
                                                            .child(String.valueOf(messageTime))
                                                            .setValue(new RatingContainer(
                                                                    iam,
                                                                    "【商品單】",
                                                                    product,
                                                                    rate,
                                                                    comment,
                                                                    messageTime,
                                                                    nickname
                                                            ));
                                                }

                                                if(orderkind.equals("【代購】"))//使用者是請求單買家
                                                {


                                                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("seller_file")};
                                                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                                                                if(ds.child("email").getValue().equals(user2)){
                                                                    FirebaseDatabase.getInstance()
                                                                            .getReference("seller_file")
                                                                            .child(ds.child("sellernum").getValue().toString())//存成賣家評價
                                                                            .child("sellerrating")
                                                                            .child(String.valueOf(messageTime))
                                                                            .setValue(new RatingContainer(
                                                                                    iam,
                                                                                    "【請求單】",
                                                                                    product,
                                                                                    rate,
                                                                                    comment,
                                                                                    messageTime,
                                                                                    nickname
                                                                            ));


                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }


                                                    });

                                                }
                                                if(orderkind.equals("【接單】"))//使用者是請求單賣家
                                                {
                                                    FirebaseDatabase.getInstance()
                                                            .getReference("buyer_file")
                                                            .child(ds.child("memberNum").getValue().toString())
                                                            .child("buyerRating")
                                                            .child(String.valueOf(messageTime))
                                                            .setValue(new RatingContainer(
                                                                    iam,
                                                                    "【請求單】",
                                                                    product,
                                                                    rate,
                                                                    comment,
                                                                    messageTime,
                                                                    nickname
                                                            ));
                                                }


                                                p2=true;
                                            }

                                        }
                                        if(p1==true&&p2==true){
//
                                            Intent intent1 = new Intent();
                                            intent1.setClass(Rating.this,Notify.class);
                                            startActivity(intent1);
                                            Rating.this.finish();

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
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final Bundle bundle=getIntent().getExtras();
            final String orderNum=bundle.getString("orderNum");
            final String user2=bundle.getString("user2");
            final String orderkind=bundle.getString("orderkind");
            final String orderpic =bundle.getString("orderpic");
            final String product =bundle.getString("product");

            Bundle bundle1=new Bundle();
            bundle1.putString("orderNum",orderNum);
            bundle1.putString("orderkind", orderkind);
            bundle1.putString("orderpic", orderpic);
            Intent intent1 = new Intent();
            intent1.setClass(Rating.this,Order.class);
            intent1.putExtras(bundle1);
            startActivity(intent1);
            Rating.this.finish();
        }
        return false;
    }
}
