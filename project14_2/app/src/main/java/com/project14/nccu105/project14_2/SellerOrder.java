package com.project14.nccu105.project14_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
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

import java.io.IOException;
import java.util.ArrayList;

public class SellerOrder extends AppCompatActivity {
    Button btn91, btn281, btn282, btn93, btn94, btn95, btn96, btn97;
    ImageButton ib281,heart;
    private FirebaseListAdapter<ChatMessage> adapter;
    String iam= FirebaseAuth.getInstance()
            .getCurrentUser()
            .getEmail();
    String user="";
    String nickname="";
    String from="";
    int temp;
    boolean ok=false;



    private StorageReference mStorageRef;
    private StorageReference riversRef;
    String[] mypic =new String[1];
    ImageView iv281,iv282;
    String time="";

    String ordernum="";
    String ordername="";
    ArrayList likelist=new ArrayList();
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.sellerorder);


        heart = (ImageButton)findViewById(R.id.btnheart1);
//        LinearLayout ll28 = (LinearLayout)findViewById(R.id.ll28);
//
//        ll28.scrollTo;
        final ListView listOfMessages = (ListView)findViewById(R.id.seller_product_messages);

//        ib281=(ImageButton)findViewById(R.id.ib28_1);
//        getSupportActionBar().hide();

        iv281= (ImageView)findViewById(R.id.iv28_1);
        iv282= (ImageView)findViewById(R.id.iv28_2);

        TextView Topprice = (TextView)findViewById(R.id.tv28_2);
        TextView Topfee = (TextView)findViewById(R.id.tv28_5);
        TextView Brand = (TextView)findViewById(R.id.tv28_6);
        TextView Classify = (TextView)findViewById(R.id.tv28_25);
        TextView Name = (TextView)findViewById(R.id.tv28_10);
        TextView Standard = (TextView)findViewById(R.id.tv28_12);
        TextView Model = (TextView)findViewById(R.id.tv28_35);
        TextView Url = (TextView)findViewById(R.id.tv28_36);
        TextView Country = (TextView)findViewById(R.id.tv28_37);
        TextView Place = (TextView)findViewById(R.id.tv28_38);
        TextView Other = (TextView)findViewById(R.id.tv28_27);
        TextView Price = (TextView)findViewById(R.id.tv28_30);
        TextView Num = (TextView)findViewById(R.id.tv28_32);
        TextView Fee = (TextView)findViewById(R.id.tv28_34);
        TextView Delivery = (TextView)findViewById(R.id.tv28_40);
        TextView Receipt = (TextView)findViewById(R.id.tv28_41);
        TextView Time = (TextView)findViewById(R.id.tv28_3);
        final TextView User = (TextView)findViewById(R.id.tv28_23);



        final ScrollView sv28 = (ScrollView) findViewById(R.id.sv28);
//        sv28.scrollTo(0,0);
        sv28.post(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
//                    mRootScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                int[] location = new int[2];
//                Title.getLocationOnScreen(location);
                int offset = location[1] - sv28.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                sv28.smoothScrollTo(0, offset);
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();
        final Bundle bundle = getIntent().getExtras();
        from=bundle.getString("from");


        final boolean[] m = {false};


        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("seller_order")};
        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("messageTime").getValue().equals(bundle.getLong("time"))){
//                        mynum[0] = ds.child("memberNum").getValue().toString();
                        user=ds.child("user").getValue().toString();
                        mypic[0] = ds.child("picture").getValue().toString();
                        ordernum=ds.getKey();
                        ordername=ds.child("name").getValue().toString();
//                        temp=Integer.parseInt(ds.child("see").getValue().toString());
                        temp = temp + 1;
                        time=Long.toString(bundle.getLong("time"));


                        Log.d("BO", "onDataChange1: "+mypic[0]);
                        m[0] =true;

                    }

                    if(m[0]==true){

                        ok=true;

                        String test=mypic[0];
                        if(test.contains("null")){}
                        else{

                            riversRef = mStorageRef.child("seller_order").child(mypic[0]);

                            Log.d("down", "onSuccess: "+mypic[0]);
//
                            downloadImg(riversRef,iv281);
                        }


                    }


                }



            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
//        ScrollView sv281 = (ScrollView) findViewById(R.id.sv28_1);
//
//        if(sv281.isFocused()){
//            sv28.isFocu()==false;
//        }





        final String title=bundle.getString("name");
        final String brand=bundle.getString("brand");
        final String classify=bundle.getString("classify");
        final String standard=bundle.getString("standard");
        String model=bundle.getString("model");
        if(model.equals("")){
            model="(無說明型號)";
        }
        String url=bundle.getString("url");
        if(url.equals(""))
        {
            url="(無參考網址)";
        }
        final String country=bundle.getString("country");
        String place=bundle.getString("place");
        if(place.equals("")){
            place="(無參考地點)";
        }
        String other=bundle.getString("other");
        if(other.equals(""))
        {
            other="無";
        }
        final String price=bundle.getString("price");
        final String num=bundle.getString("num");
        final String fee=bundle.getString("fee");
        final String delivery=bundle.getString("delivery");
        final Long time=bundle.getLong("time");
        final String seller=bundle.getString("seller");
        final String sellernick=bundle.getString("sellernick");
        final String receipt=bundle.getString("receipt");


//        Title.setText(title);
        Topprice.setText("單價NTD$"+price);
        Topfee.setText("代購費NTD$"+fee);
        Brand.setText(brand);
        Classify.setText(classify);
        Name.setText(title);
        Standard.setText(standard);
        Model.setText(model);
        Url.setText(url);
        Country.setText(country);
        Place.setText(place);
        Other.setText(other);
        Price.setText(price);
        Num.setText(num);
        Fee.setText(fee);
        Delivery.setText(delivery);
        Time.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                time));

        User.setText(sellernick);
        Receipt.setText(receipt);



        DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
        reference_contacts[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

//                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                    if(ds.child("mail").getValue().equals(iam)){
                        nickname=ds.child("nickname").getValue().toString();
                        for (DataSnapshot dds : ds.child("fansNum").getChildren())
                            likelist.add(dds.getKey());
                        if(likelist.contains(ordernum))
                        {
                            heart.setBackgroundResource(R.drawable.ic_heart_dark);
//                    ischange=true;
                        }
                        if(sellernick.equals(nickname))
                        {}
                        else {

                            User.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {



                                    Intent intent = new Intent();
                                    intent.setClass(SellerOrder.this, Member_page.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("from", "SellerOrder");
                                    bundle.putString("useracc", String.valueOf(seller));
                                    bundle.putString("user", String.valueOf(sellernick));
                                    intent.putExtras(bundle);
                                    startActivity(intent);


                                }
                            });
                        }
                        // Read the input field and push a new instance
                        // of ChatMessage to the Firebase database

                    }
                    if(ds.child("mail").getValue().equals(user)){
                        String test=ds.child("picture").getValue().toString();
                        if(test.contains("null")){}
                        else{
                            riversRef = mStorageRef.child("member_picture").child(ds.child("picture").getValue().toString());
                            downloadImg(riversRef,iv282);
                        }
                    }



                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });





        final String times=Long.toString(time);


        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab28);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = (EditText)findViewById(R.id.et28_1);

                if (input.getText().toString().equals("")||input.getText().toString().equals(" ")||input.getText().toString().equals("  "))
                {

                    Toast.makeText(SellerOrder.this, "輸入不可為空", Toast.LENGTH_SHORT).show();

                }
                else {


                    FirebaseDatabase.getInstance()
                            .getReference("seller_product_chat")
                            .child(times)
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                            nickname
                                    ,iam
                                    )
                            );

                    // Clear the input
                    input.setText("");



                }
                listOfMessages.setStackFromBottom(false);
                if (!listOfMessages.isStackFromBottom()) {
                    listOfMessages.setStackFromBottom(true);
                }


            }
        });

        btn281 = (Button)findViewById(R.id.btn28_1);

        btn281.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seller.equals(iam)){
                    Toast.makeText(SellerOrder.this, "這是您的商品單喔~", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putLong("productOrderTime", time);
                    bundle.putString("seller", seller);
                    bundle.putString("product", title);
                    bundle.putString("picture", mypic[0]);
                    bundle.putInt("price", Integer.parseInt(price));
                    bundle.putString("receipt", receipt);
                    bundle.putInt("num", Integer.parseInt(num));
                    bundle.putInt("fee", Integer.parseInt(fee));
                    intent.setClass(SellerOrder.this, Checkout.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

        });


        btn282 = (Button)findViewById(R.id.btn28_2);

        btn282.setOnClickListener(new View.OnClickListener() {
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

                        final String user2=user;

                        final String[] mynum = new String[1];
                        final boolean[] m = {false};
                        final String[] user2num = new String[1];
                        final boolean[] u = {false};

                        DatabaseReference[] reference_contacts2 = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                        reference_contacts2[0].addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                    Log.d("TAG", "ds.child(mail)1: "+ds.child("mail").getValue());
                                    if(ds.child("mail").getValue().equals(iam)){
                                        mynum[0] = ds.child("mail").getValue().toString();
//                                        Log.d("TAG", "onDataChange1: "+mynum[0]);
                                        m[0] =true;
                                    }
                                    Log.d("TAG", "ds.child(mail)2: "+ds.child("mail").getValue());
                                    if(ds.child("mail").getValue().equals(user2)){
                                        user2num[0] = ds.child("mail").getValue().toString();
//                                        Log.d("TAG", "onDataChange2: "+user2num[0]);
                                        u[0] =true;
                                    }
                                    if(m[0]==true&&u[0]==true){
                                          if(user2.equals(iam)){
                                              Toast.makeText(SellerOrder.this, "這是您的商品單喔~", Toast.LENGTH_SHORT).show();
                                          }else{
//                                              Log.d("TAG", "mynum: "+mynum[0]+"user2: "+user2num[0]);
                                              Bundle bundle2=new Bundle();

//                                              int max= Math.max(Integer.parseInt(mynum[0]),Integer.parseInt(user2num[0]));
//                                              int min= Math.min(Integer.parseInt(mynum[0]),Integer.parseInt(user2num[0]));
//                                              Log.d("TAG", "max "+max+"min "+min);
                                              bundle2.putString("user1",mynum[0]);
                                              bundle2.putString("user2",user2num[0]);
                                              bundle2.putString("usernic", String.valueOf(sellernick));


                                              bundle2.putString("classify",classify);
                                              bundle2.putString("brand", brand);
                                              bundle2.putString("name", title);
                                              bundle2.putString("standard", standard);
                                              bundle2.putString("model",bundle.getString("model"));
                                              bundle2.putString("url", bundle.getString("url"));
                                              bundle2.putString("country", country);
                                              bundle2.putString("place",bundle.getString("place"));
                                              bundle2.putString("other", bundle.getString("other"));
                                              bundle2.putString("price", price);
                                              bundle2.putString("num",num);
                                              bundle2.putString("fee", fee);
                                              bundle2.putString("delivery", delivery);
                                              bundle2.putLong("time", time);
                                              bundle2.putString("who",seller );
                                              bundle2.putString("whonick",sellernick);
                                              bundle2.putString("receipt", receipt);

                                              bundle2.putString("from", "Forun");
                                              bundle2.putString("from2", "SellerOrder");
                                              Intent intent = new Intent();
                                              intent.setClass(SellerOrder.this, ChatRoom2.class);
                                              intent.putExtras(bundle2);
                                              startActivity(intent);
                                              SellerOrder.this.finish();

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

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference("seller_product_chat").child(times)) {
            @Override
            protected void populateView(View v, final ChatMessage model, int position) {

                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                final TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
//                messageUser.setText(FirebaseAuth.getInstance()
//                        .getCurrentUser().getEmail()
//                        );
                messageUser.setText(model.getMessageUser());
                messageUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(model.getMessageUser().equals(FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getEmail())){

                        }else{
                            Intent intent = new Intent();



                            intent.setClass(SellerOrder.this, Member_page.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("from","SellerOrder");
                            bundle.putString("useracc", model.getUseracc());
                            bundle.putString("user", model.getMessageUser());
                            intent.putExtras(bundle);
//                            temp = temp + 1;
//
//                            FirebaseDatabase.getInstance()
//                                    .getReference("seller_order")
//                                    .child(Long.toString(bundle.getLong("time")))
//                                    .child("see")
//                                    .setValue(temp);
                            startActivity(intent);

//                        ArticlePage.this.finish();
                        }


                    }
                });

                // Format the date before showing it
                messageTime.setText(DateFormat.format("yyyy-MM-dd(HH:mm)",
                        model.getMessageTime()));
            }
        };
//
        listOfMessages.setAdapter(adapter);

                listOfMessages.setScrollbarFadingEnabled(false);
                listOfMessages.setOnTouchListener(new AdapterView.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        sv28.setFocusable(false);
                        listOfMessages.isFocused();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            sv28.stopNestedScroll();
                        }
                        return false;
                    }
                });



        heart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(seller.equals(iam)){
                    Toast.makeText(SellerOrder.this, "這是您的商品單唷!",Toast.LENGTH_SHORT).show();
                }
                else if(!likelist.contains(ordernum))
                {
                    Toast.makeText(SellerOrder.this, "已收藏",Toast.LENGTH_SHORT).show();
                    heart.setBackgroundResource(R.drawable.ic_heart_dark);
                    likelist.add(ordernum);
//                    ischange=true;
                }
                else {

                    heart.setBackgroundResource(R.drawable.ic_heart_light);
                    likelist.remove(ordernum);
//                    ischange=false;
                }
            }
        });

//        View.OnClickListener OCL = new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if (v instanceof ImageButton) {
//                    int id = ((ImageButton) v).getId();
//                    switch (id) {
//                        case R.id.ib28_1:
//                            String msg6 = "返回論壇";
//                            Intent intent6 = new Intent();
//                            intent6.setClass(SellerOrder.this, Seller.class);
//                            startActivity(intent6);
//                            SellerOrder.this.finish();
//                            break;
//
//                    }
//                };
//            }
//        };
//        ib281.setOnClickListener(OCL);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar28);

        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationIcon(R.drawable.arrow_back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();


                if(from.equals("Seller"))
                    intent1.setClass(SellerOrder.this, Seller.class);
                else if(from.equals("Result"))
                    SellerOrder.this.finish();
                else
                    intent1.setClass(SellerOrder.this, Forum.class);

                if(likelist.contains(ordernum)){
                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getEmail())){
                                    FirebaseDatabase.getInstance().getReference("buyer_file")
                                            .child(ds.child("memberNum").getValue().toString())
                                            .child("fansNum").child(ordernum).setValue(new LikeProduct(
                                            ordernum,
                                            ordername,
                                            mypic[0]
                                            )

                                    );

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }


                    });

                }
                else{

                    DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                    reference_contacts[0].addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getEmail())){
                                    for (DataSnapshot dds : ds.child("fansNum").getChildren()){
                                        if(dds.getKey().equals(ordernum)){
                                                FirebaseDatabase.getInstance().getReference("buyer_file")
                                                        .child(ds.child("memberNum").getValue().toString())
                                                        .child("fansNum").child(ordernum).removeValue();


                                        }
                                    }

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }


                    });


                }

//                temp = temp + 1;
//
//                FirebaseDatabase.getInstance()
//                        .getReference("seller_order")
//                        .child(Long.toString(bundle.getLong("time")))
//                        .child("see")
//                        .setValue(temp);
                if(!from.equals("Result")){
                    startActivity(intent1);
                    finish();
                }

            }
        });

    }

    private void downloadImg(final StorageReference ref, final ImageView img){
        if(ref == null){
            Toast.makeText(SellerOrder.this, "請先上傳照片", Toast.LENGTH_SHORT).show();
            Log.d("down", "onSuccess: ");
            return;
        }



        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(!isDestroy(SellerOrder.this)){
                    Glide.with(SellerOrder.this)

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
//        FirebaseDatabase.getInstance()
//                .getReference("seller_order")
//                .child(time)
//                .child("see")
//                .setValue(temp);

        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            temp = temp + 1;



            Intent intent1 = new Intent();



            if(from.equals("Seller"))
                intent1.setClass(SellerOrder.this, Seller.class);
            else if(from.equals("Result"))
                SellerOrder.this.finish();
            else
                intent1.setClass(SellerOrder.this, Forum.class);

            if(likelist.contains(ordernum)){
                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                    .getCurrentUser()
                                    .getEmail())){
                                FirebaseDatabase.getInstance().getReference("buyer_file")
                                        .child(ds.child("memberNum").getValue().toString())
                                        .child("fansNum").child(ordernum).setValue(new LikeProduct(
                                        ordernum,
                                        ordername,
                                        mypic[0]
                                ));

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

            }
            else{

                DatabaseReference[] reference_contacts = {FirebaseDatabase.getInstance().getReference("buyer_file")};
                reference_contacts[0].addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if(ds.child("mail").getValue().equals(FirebaseAuth.getInstance()
                                    .getCurrentUser()
                                    .getEmail())){
                                for (DataSnapshot dds : ds.child("fansNum").getChildren()){
                                    if(dds.getKey().equals(ordernum)){

                                            FirebaseDatabase.getInstance().getReference("buyer_file")
                                                    .child(ds.child("memberNum").getValue().toString())
                                                    .child("fansNum").child(ordernum).removeValue();


                                    }
                                }

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });


            }

            if(!from.equals("Result")){
                startActivity(intent1);
                finish();
            }





        }
        return false;
    }



}